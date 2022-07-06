package ace.ucv.ro.orientationandorganizationapp.entity;

import ace.ucv.ro.orientationandorganizationapp.entity.enums.ActivityType;
import ace.ucv.ro.orientationandorganizationapp.entity.enums.ClassroomType;
import ace.ucv.ro.orientationandorganizationapp.entity.enums.Specialization;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.DayOfWeek;
import java.time.LocalTime;

@Entity(name = "activity")
@Table
@Setter
@Getter
@NoArgsConstructor
public class Activity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private ActivityType activityType;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(nullable = false)
    private Teacher teacher;

    @Column(nullable = false)
    private String studentGroup;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Specialization specialization;

    @Column(nullable = false)
    private String studentSubgroup;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private DayOfWeek day;

    @Column(nullable = false)
    private LocalTime startHour;

    @Column(nullable = false)
    private LocalTime endHour;

    @Enumerated(EnumType.STRING)
    private ClassroomType classroomType;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private Classroom reservedClassroom;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(nullable = false)
    private Course course;

    public Activity(ActivityType activityType, Teacher teacher, Specialization specialization, String studentGroup, String studentSubgroup,
                    DayOfWeek day, LocalTime startHour, LocalTime endHour, ClassroomType classroomType, Course course) {
        this.activityType = activityType;
        this.teacher = teacher;
        this.specialization = specialization;
        this.studentGroup = studentGroup;
        this.studentSubgroup = studentSubgroup;
        this.day = day;
        this.startHour = startHour;
        this.endHour = endHour;
        this.classroomType = classroomType;
        this.course = course;
    }

    public Activity(ActivityType activityType, Teacher teacher, String studentGroup, String studentSubgroup,
                    DayOfWeek day, LocalTime startHour, LocalTime endHour, Classroom reservedClassroom, Course course) {
        this.activityType = activityType;
        this.teacher = teacher;
        this.studentGroup = studentGroup;
        this.studentSubgroup = studentSubgroup;
        this.day = day;
        this.startHour = startHour;
        this.endHour = endHour;
        this.reservedClassroom = reservedClassroom;
        this.course = course;
    }
}
