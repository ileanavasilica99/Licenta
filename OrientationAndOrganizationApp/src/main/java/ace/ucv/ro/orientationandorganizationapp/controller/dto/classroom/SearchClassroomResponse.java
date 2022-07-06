package ace.ucv.ro.orientationandorganizationapp.controller.dto.classroom;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@JsonRootName("SearchClassroomResponse")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@Setter@Getter
@AllArgsConstructor
public class SearchClassroomResponse {
    @JsonProperty(value = "faculty")
    private String faculty;

    @JsonProperty(value = "area")
    private String area;

    @JsonProperty(value = "floor")
    private int floor;

    @Override
    public String toString() {
        return "The classroom you are looking for is in the faculty " + faculty  +
                ", in area : " + area +
                ", on the " + floor + "th floor" ;
    }
}
