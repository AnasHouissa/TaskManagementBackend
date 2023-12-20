package tn.houissa.projectmanagement.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.aspectj.weaver.patterns.IToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import tn.houissa.projectmanagement.entities.User;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;

@Service
public class JwtService {

    /** Key to sign and verify the integrity of token**/
    private static final String SECRET_KEY = "566673667a7472346f74716b633533474235677633556b445137546552656931";

    /** method to extract email from token **/
    public String extractEmail(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    /** Method to extract the expiration date from the token **/
    private Date extractExpiration(String token) {
        return extractClaim(token,Claims::getExpiration);
    }

    /** generic method to extract any claim from token **/

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);

    }

    /** Method to generate token without extra claims**/

    public String generateToken(UserDetails userDetails){
        return generateToken(new HashMap<>(),userDetails);
    }
    /** Method to generate token with extra claims**/

    public String generateToken(
            Map<String, Objects> extraClaims,
            UserDetails userDetails
    ) {
        return Jwts.builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    /** Method to extract all claims from token **/

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder().setSigningKey(getSignInKey()).build().parseClaimsJwt(token).getBody();
    }

    /** Method to convert the key from string to a Key object **/
    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    /** Method to validate a token **/
    public boolean isTokenValid(String token,UserDetails userDetails){
        final String email = extractEmail(token);
        return(email.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
    /** Method to check if token is expired **/
    private boolean isTokenExpired(String token){
        return extractExpiration(token).before(new Date());
    }



}
