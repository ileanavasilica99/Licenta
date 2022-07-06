package ace.ucv.ro.orientationandorganizationapp.entity;


import ace.ucv.ro.orientationandorganizationapp.entity.enums.Department;
import ace.ucv.ro.orientationandorganizationapp.entity.enums.Role;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity(name = "teacher")
@Setter @Getter
@NoArgsConstructor
public class Teacher extends User{
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Department department;

    @Column(nullable = false)
    private String academicDegree;

    @OneToMany(mappedBy="teacher")
    public List<Course> courses;

    @OneToMany(mappedBy="teacher")
    public List<Activity> activities;

    public Teacher(String firstName, String lastName, String address, Long CNP, Long phoneNumber, String email,
                   LocalDate dateOfBirth, Role role, Department department, String academicDegree) {
        super(firstName, lastName, address, CNP, phoneNumber, email, dateOfBirth, role);
        this.department = department;
        this.academicDegree = academicDegree;
    }
}
