package tn.houissa.projectmanagement.services.userservice;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import tn.houissa.projectmanagement.config.JwtService;
import tn.houissa.projectmanagement.entities.User;
import tn.houissa.projectmanagement.repositories.IUserRepository;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService implements IUserService {

    private final IUserRepository IUserRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Override
    public String login(String email, String password) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(email, password)
        );
        var user = getUserByEmail(email);
        if(user!=null){
            return jwtService.generateToken(user);
        }
        return null;
    }

    @Override
    public Boolean register(User user) {
        if (validUser(user)) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setIsVerified(false);
            IUserRepository.save(user);
            return true;
        }
        return false;
    }

    @Override
    public List<User> getUsers() {
        return IUserRepository.findAll();
    }

    @Override
    public User updateUser(User user) {
        User usertoUpdate = IUserRepository.findById(user.getUser_id()).orElse(null);
        if (validUser(user) && usertoUpdate != null) {
            return IUserRepository.save(user);
        }
        return null;
    }

    @Override
    public boolean deleteUser(int idUser) {
        User user = IUserRepository.findById(idUser).orElse(null);
        if (user != null) {
            IUserRepository.delete(user);
            return true;
        }
        return false;
    }

    @Override
    public User getUser(int idUser) {
        return IUserRepository.findById(idUser).orElse(null);
    }

    @Override
    public User getUserByEmail(String email) {
        return IUserRepository.findByEmail(email).orElse(null);
    }

    private boolean validUser(User user) {
        return user != null && !user.getFirstName().isEmpty() && !user.getLastName().isEmpty() && !user.getEmail().isEmpty() && !user.getPassword().isEmpty();
    }
}
