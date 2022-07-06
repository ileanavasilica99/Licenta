package ace.ucv.ro.orientationandorganizationapp.entity;

import ace.ucv.ro.orientationandorganizationapp.entity.enums.ClassroomType;
import ace.ucv.ro.orientationandorganizationapp.entity.enums.Faculty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity(name = "classroom")
@Setter
@Getter
@NoArgsConstructor
public class Classroom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int floor;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Faculty faculty;

    @Column(nullable = false)
    private String area;

    @Column(nullable = false)
    private int seats;

    //@Column(nullable = false) - asta adaug-o mai tarziu ca fiind obligatorie
    @Enumerated(EnumType.STRING)
    private ClassroomType type;

    @OneToMany(mappedBy = "classroom", fetch = FetchType.LAZY)
    private List<Schedule> schedules;

    @OneToMany(mappedBy = "reservedClassroom")
    private List<Activity> activitiesList;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "image_id", referencedColumnName = "id")
    private Image image;

    public Classroom(String name, int floor, Faculty faculty, String area, int seats, ClassroomType type) {
        this.name = name;
        this.floor = floor;
        this.faculty = faculty;
        this.area = area;
        this.seats = seats;
        this.type = type;
    }
}
