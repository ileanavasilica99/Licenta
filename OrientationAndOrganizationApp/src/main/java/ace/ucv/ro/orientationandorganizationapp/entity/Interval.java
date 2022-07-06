package ace.ucv.ro.orientationandorganizationapp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalTime;

@Data
@AllArgsConstructor
public class Interval {
    private LocalTime startHour;
    private LocalTime endHour;
}
