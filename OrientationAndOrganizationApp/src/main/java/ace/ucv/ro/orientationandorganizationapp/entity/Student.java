package ace.ucv.ro.orientationandorganizationapp.entity;


import ace.ucv.ro.orientationandorganizationapp.entity.enums.Role;
import ace.ucv.ro.orientationandorganizationapp.entity.enums.Specialization;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDate;

@Entity(name = "student")
@Setter @Getter
@NoArgsConstructor
public class Student extends User{

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Specialization specialization;

    @Column(nullable = false)
    private int studyYear;

    @Column(nullable = false)
    private String studentGroup;

    @Column(nullable = false)
    private String studentSubgroup;

    public Student(String firstName, String lastName, String address, Long CNP, Long phoneNumber, String email,
                   LocalDate dateOfBirth, Role role, Specialization specialization, int studyYear, String
                           studentGroup, String studentSubgroup) {
        super(firstName, lastName, address, CNP, phoneNumber, email, dateOfBirth, role);
        this.specialization = specialization;
        this.studyYear = studyYear;
        this.studentGroup = studentGroup;
        this.studentSubgroup = studentSubgroup;
    }
}
