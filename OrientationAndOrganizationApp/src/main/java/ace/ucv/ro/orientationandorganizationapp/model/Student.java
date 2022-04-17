package ace.ucv.ro.orientationandorganizationapp.model;


import ace.ucv.ro.orientationandorganizationapp.model.enums.CivilStatus;
import ace.ucv.ro.orientationandorganizationapp.model.enums.Gender;
import ace.ucv.ro.orientationandorganizationapp.model.enums.StudyProgram;

import javax.persistence.*;
import java.util.List;

@Entity(name = "student")
public class Student extends Person{
    @Id
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StudyProgram studyProgram;

    @Column(nullable = false)
    private String specialization;

    @Column(nullable = false)
    private int studyYear;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CivilStatus civilStatus;

//    @ElementCollection
//    private List<String> legalSupporters;

    @Column(nullable = false)
    private Boolean orphan;
}
