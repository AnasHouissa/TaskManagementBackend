package tn.houissa.projectmanagement.entities;


import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor // default constructor
@AllArgsConstructor  //
@RequiredArgsConstructor // custom constructor ==> for final and not null fields
@ToString // anotate @ToString.Exclude to make Lombok ignore it or @ToString(onlyExplicitlyIncluded = true) and anotate fields with @ToString.Include
//@Data  // it replaces @ToString, @EqualsAndHashCode, @Getter @Setter, and @RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity

public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    int user_id;

    @NonNull
    @Column(name = "firstName")
    String firstName;

    @NonNull
    @Column(name = "lastName")
    String lastName;

    @NonNull
    @Column(name = "email")
    String email;

    @NonNull
    @Column(name = "password")
    String password;

    @NonNull
    @Column(name = "username")
    String username;

    @OneToMany(mappedBy = "user",cascade = CascadeType.REMOVE)
    @Column(name = "tasks")
    Set<Task> tasks;

}
