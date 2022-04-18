package ace.ucv.ro.orientationandorganizationapp.entity;


import ace.ucv.ro.orientationandorganizationapp.entity.enums.CivilStatus;
import ace.ucv.ro.orientationandorganizationapp.entity.enums.EducationCycle;
import ace.ucv.ro.orientationandorganizationapp.entity.enums.StudyProgram;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity(name = "student")
@Data
@AllArgsConstructor
@NoArgsConstructor
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
