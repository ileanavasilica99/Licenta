package ace.ucv.ro.orientationandorganizationapp.model;

import ace.ucv.ro.orientationandorganizationapp.model.enums.SubjectType;

import javax.persistence.*;
import java.time.DayOfWeek;
import java.time.LocalTime;

@Entity(name = "schedule")
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private DayOfWeek dayOfWeek;

    private LocalTime startTime;

    private LocalTime endTime;

    @Column(name = "year_group")
    private String group;

    private String subgroup;

    @OneToOne
    @JoinColumn(name = "subject_name")
    private Subject subject;

    @Enumerated(EnumType.STRING)
    private SubjectType type;

    private String teacherName;

    @OneToOne
    @JoinColumn(name = "classroom_name")
    private Classroom classroom;
}
