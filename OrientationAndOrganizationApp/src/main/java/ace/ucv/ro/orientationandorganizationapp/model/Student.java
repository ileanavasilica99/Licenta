package ace.ucv.ro.orientationandorganizationapp.model;


import ace.ucv.ro.orientationandorganizationapp.model.enums.CivilStatus;
import ace.ucv.ro.orientationandorganizationapp.model.enums.EducationCycle;
import ace.ucv.ro.orientationandorganizationapp.model.enums.Gender;
import ace.ucv.ro.orientationandorganizationapp.model.enums.StudyProgram;

import javax.persistence.*;

@Entity(name = "student")
public class Student extends User{
    @Id
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    //BACHELOR,MASTER,DOCTORAL
    private EducationCycle educationCycle;

    @Column(nullable = false)
    //AIA, CEN, CR, ISM, MCT, ROB, ELA
    private StudyProgram studyProgram;

    @Column(nullable = false)
    private int studyYear;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CivilStatus civilStatus;

    @Column(nullable = false)
    private Boolean orphan;
}
