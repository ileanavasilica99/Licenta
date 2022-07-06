package ace.ucv.ro.orientationandorganizationapp.controller.dto.teacher;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.*;
import java.time.LocalDate;

@Getter@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TeacherDto {
    @JsonProperty(value = "firstName")
    @NotNull(message = "First name cannot be null")
    @NotEmpty(message = "First name cannot be empty")
    private String firstName;

    @JsonProperty(value = "lastName")
    @NotNull(message = "Last name cannot be null")
    @NotEmpty(message = "Last name cannot be empty")
    private String lastName;

    @JsonProperty(value = "address")
    @NotNull(message = "Address cannot be null")
    @NotEmpty(message = "Address cannot be empty")
    private String address;

    @JsonProperty(value = "CNP")
    @NotNull(message = "CNP cannot be null")
    @Digits(fraction = 0, integer = 13, message = "CNP must have 13 digits")
    private Long CNP;

    @JsonProperty(value = "phoneNumber")
    @NotNull(message = "Phone number cannot be null")
    private Long phoneNumber;

    @Email(message = "Email is not valid")
    @NotEmpty(message = "Email cannot be empty")
    @NotNull(message = "Email cannot be null")
    @JsonProperty(value = "email")
    private String email;

    @NotNull(message = "Date of birth cannot be null")
    @Past(message = "Date of birth must be in the past")
    @JsonProperty(value = "dateOfBirth")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate dateOfBirth;

    @NotEmpty(message = "Department cannot be empty")
    @NotNull(message = "Department cannot be null")
    @JsonProperty(value = "department")
    private String department;

    @NotEmpty(message = "Academic degree cannot be empty")
    @NotNull(message = "Academic degree cannot be null")
    @JsonProperty(value = "academicDegree")
    private String academicDegree;
}
