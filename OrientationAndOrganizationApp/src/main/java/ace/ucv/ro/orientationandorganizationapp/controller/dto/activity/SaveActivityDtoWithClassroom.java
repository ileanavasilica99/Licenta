package ace.ucv.ro.orientationandorganizationapp.controller.dto.activity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.time.LocalTime;

@Setter@Getter
public class SaveActivityDtoWithClassroom {
    private String courseName;
    private String activityType;
    private String specialization;
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

    private String reservedClassroom;

    private String teacherFirstName;

    private String teacherLastName;

    private String classroomType;
}
