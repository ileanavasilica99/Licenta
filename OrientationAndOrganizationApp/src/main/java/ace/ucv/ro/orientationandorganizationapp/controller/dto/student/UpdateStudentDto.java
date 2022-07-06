package ace.ucv.ro.orientationandorganizationapp.controller.dto.student;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Setter
@Getter
@AllArgsConstructor
public class UpdateStudentDto {
    @JsonProperty(value = "address")
    @NotNull(message = "Address cannot be null")
    @NotEmpty(message = "Address cannot be empty")
    private String address;

    @JsonProperty(value = "phoneNumber")
    @NotNull(message = "Phone number cannot be null")
    private Long phoneNumber;
}
