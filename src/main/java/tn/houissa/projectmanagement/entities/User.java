package tn.houissa.projectmanagement.entities;


import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor // default constructor
@AllArgsConstructor  //
@RequiredArgsConstructor // custom constructor ==> for final and not null fields
@ToString
// anotate @ToString.Exclude to make Lombok ignore it or @ToString(onlyExplicitlyIncluded = true) and anotate fields with @ToString.Include
//@Data  // it replaces @ToString, @EqualsAndHashCode, @Getter @Setter, and @RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "user", uniqueConstraints = @UniqueConstraint(columnNames = "email")
)
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    int user_id;

    @NonNull
    @Column(name = "first_name")
    String firstName;

    @NonNull
    @Column(name = "last_name")
    String lastName;

    @NonNull
    @Column(name = "email")
    String email;

    @NonNull
    @Column(name = "password")
    String password;

    @NonNull
    @Column(name = "is_verified")
    Boolean isVerified;


    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @Column(name = "tasks")
    Set<Task> tasks;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return isVerified;
    }
}
