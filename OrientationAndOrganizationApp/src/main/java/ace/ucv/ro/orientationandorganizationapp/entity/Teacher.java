package ace.ucv.ro.orientationandorganizationapp.entity;


import ace.ucv.ro.orientationandorganizationapp.entity.enums.Department;

import javax.persistence.*;

@Entity(name = "teacher")
public class Teacher {
    @Id
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Department department;

    @Column(nullable = false)
    private String academicDegree;
}
