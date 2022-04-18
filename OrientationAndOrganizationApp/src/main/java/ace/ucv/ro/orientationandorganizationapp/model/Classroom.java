package ace.ucv.ro.orientationandorganizationapp.model;

import ace.ucv.ro.orientationandorganizationapp.model.enums.Building;
import ace.ucv.ro.orientationandorganizationapp.model.enums.ClassroomType;
import ace.ucv.ro.orientationandorganizationapp.model.enums.Faculty;
import ace.ucv.ro.orientationandorganizationapp.model.enums.StudyProgramServed;

import javax.persistence.*;


@Entity(name = "classroom")
public class Classroom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //like: 309-bis
    private String room;

    //like: retele de calculatoare - bis
    @Column(name = "classroom_name")
    private String name;

    @Enumerated(EnumType.STRING)
    private Faculty faculty;

    private Building building;

    private int floor;

    private int seats;

    @Enumerated(EnumType.STRING)
    private ClassroomType type;

    //AIA, CEN, CR,
    private StudyProgramServed studyProgramServed;

//    @Lob
//    @Basic(fetch = FetchType.LAZY)
//    private byte[] profilePicture;


}
