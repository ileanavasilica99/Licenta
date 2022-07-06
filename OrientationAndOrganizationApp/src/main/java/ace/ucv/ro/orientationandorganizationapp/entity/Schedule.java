package ace.ucv.ro.orientationandorganizationapp.entity;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.DayOfWeek;
import java.time.LocalTime;

@Entity(name = "schedule")
@Data @NoArgsConstructor
public class Schedule implements Comparable<Schedule> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "start_hour", columnDefinition = "TIME")
    private LocalTime startHour;

    @Column(name = "end_hour", columnDefinition = "TIME")
    private LocalTime endHour;

    @Enumerated(EnumType.STRING)
    private DayOfWeek day;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private Classroom classroom;

    @Override
    public int compareTo(Schedule schedule) {
        return this.startHour.compareTo(schedule.startHour);
    }

    public Schedule(LocalTime startHour, LocalTime endHour, DayOfWeek day, Classroom classroom) {
        this.startHour = startHour;
        this.endHour = endHour;
        this.day = day;
        this.classroom = classroom;
    }
}
