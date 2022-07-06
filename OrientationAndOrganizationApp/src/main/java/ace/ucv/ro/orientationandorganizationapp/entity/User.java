package ace.ucv.ro.orientationandorganizationapp.entity;

import ace.ucv.ro.orientationandorganizationapp.entity.enums.Role;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.time.LocalDate;

@Entity(name = "user")
@Setter @Getter
@Inheritance(strategy = InheritanceType.JOINED)
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false, unique = true)
    private Long CNP;

    @Column(nullable = false)
    private Long phoneNumber;

    @Email
    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private LocalDate dateOfBirth;

    @Column
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    public User(String firstName, String lastName, String address, Long CNP, Long phoneNumber, String email,
                LocalDate dateOfBirth, Role role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.CNP = CNP;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
        this.role = role;
    }
}
