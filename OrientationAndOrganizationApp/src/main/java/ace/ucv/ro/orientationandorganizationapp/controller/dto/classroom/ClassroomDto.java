package ace.ucv.ro.orientationandorganizationapp.controller.dto.classroom;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@JsonRootName("Classroom")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@Getter @Setter
@AllArgsConstructor
public class ClassroomDto {
    @JsonProperty(value = "name")
    @NotNull(message = "Name cannot be null")
    @NotEmpty(message = "Name cannot be empty")
    private String name;

    @Min(value = -2, message = "The floor must be between -2 and 10")
    @JsonProperty(value = "floor")
    private int floor;

    @JsonProperty(value = "faculty")
    @NotNull(message = "Faculty cannot be null")
    @NotEmpty(message = "Faculty cannot be empty")
    private String faculty;

    @JsonProperty(value = "area")
    @NotNull(message = "Area cannot be null")
    @NotEmpty(message = "Area cannot be empty")
    private String area;

    @Min(value = 0, message = "The seats must greater then 0")
    @JsonProperty(value = "seats")
    private int seats;

    @JsonProperty(value = "classroomType")
    @NotNull(message = "ClassroomType cannot be null")
    @NotEmpty(message = "ClassroomType cannot be empty")
    private String classroomType;
}
