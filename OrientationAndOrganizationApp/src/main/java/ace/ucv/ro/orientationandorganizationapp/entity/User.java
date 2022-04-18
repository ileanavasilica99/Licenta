package ace.ucv.ro.orientationandorganizationapp.entity;

import ace.ucv.ro.orientationandorganizationapp.entity.enums.Gender;

import javax.persistence.*;
import javax.persistence.InheritanceType;
import javax.validation.constraints.*;
import java.time.LocalDate;

@Entity(name = "user")
@Inheritance(strategy = InheritanceType.JOINED)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(unique = true, nullable = false)
    private String GDPR;

    @Column(unique = true, nullable = false)
    private String CNP;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Gender gender;

    @Column(nullable = false)
    private String nationality;

    @Column(nullable = false)
    private LocalDate birthDate;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private Long phoneNumber;

    @Column(nullable = false, unique = true)
    @Email
    private String institutionalEmail;

    @Column(nullable = false)
    @Email
    private String personalEmail;
}
