package ace.ucv.ro.orientationandorganizationapp.controller.dto.activity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.time.LocalTime;

@Setter@Getter
@AllArgsConstructor
public class DisplayActivityDto {
    private String activityType;
    private String studentGroup;
    private String studentSubgroup;
    private String day;

    @NotNull(message = "Start hour cannot be null")
    @JsonProperty(value = "startHour")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
    @JsonSerialize(using = LocalTimeSerializer.class)
    @JsonDeserialize(using = LocalTimeDeserializer.class)
    private LocalTime startHour;

    @NotNull(message = "End hour cannot be null")
    @JsonProperty(value = "endHour")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
    @JsonSerialize(using = LocalTimeSerializer.class)
    @JsonDeserialize(using = LocalTimeDeserializer.class)
    private LocalTime endHour;

    private String teacher;

    private String classroom;

//    public DisplayActivityDto(String activityType, String studentGroup, String studentSubgroup, String day,
//                              LocalTime startHour, LocalTime endHour, String teacher, String classroom) {
//        this.activityType = activityType;
//        this.studentGroup = studentGroup;
//        this.studentSubgroup = studentSubgroup;
//        this.day = day;
//        this.startHour = startHour;
//        this.endHour = endHour;
//        this.teacher = teacher;
//        this.classroom = classroom;
//    }
}
