package ace.ucv.ro.orientationandorganizationapp.entity;

import ace.ucv.ro.orientationandorganizationapp.entity.enums.Specialization;
import lombok.Getter;

import javax.persistence.*;

@Entity(name = "students_evidence")
@Getter
public class StudentsEvidence {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Specialization specialization;

    @Column(nullable = false)
    private int year;

    @Column(nullable = false)
    private String studentGroup;

    @Column(nullable = false)
    private String studentSubgroup;

    @Column(nullable = false)
    private int studentsNumber;
}
