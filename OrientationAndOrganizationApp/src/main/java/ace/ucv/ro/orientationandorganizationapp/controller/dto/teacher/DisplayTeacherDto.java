package ace.ucv.ro.orientationandorganizationapp.controller.dto.teacher;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.List;

@Getter@Setter
@AllArgsConstructor
public class DisplayTeacherDto extends TeacherDto {
    private List<String> courses;

    public DisplayTeacherDto(@NotNull(message = "First name cannot be null")
                             @NotEmpty(message = "First name cannot be empty") String firstName,
                             @NotNull(message = "Last name cannot be null")
                             @NotEmpty(message = "Last name cannot be empty") String lastName,
                             @NotNull(message = "Address cannot be null")
                             @NotEmpty(message = "Address cannot be empty") String address,
                             @NotNull(message = "CNP cannot be null")
                             @Digits(fraction = 0, integer = 13, message = "CNP must have 13 digits") Long CNP,
                             @NotNull(message = "Phone number cannot be null") Long phoneNumber,
                             @Email(message = "Email is not valid") @NotEmpty(message = "Email cannot be empty")
                             @NotNull(message = "Email cannot be null") String email,
                             @NotNull(message = "Date of birth cannot be null")
                             @Past(message = "Date of birth must be in the past") LocalDate dateOfBirth,
                             @NotEmpty(message = "Department cannot be empty")
                             @NotNull(message = "Department cannot be null") String department,
                             @NotEmpty(message = "Academic degree cannot be empty")
                             @NotNull(message = "Academic degree cannot be null") String academicDegree, List<String> courses) {
        super(firstName, lastName, address, CNP, phoneNumber, email, dateOfBirth, department, academicDegree);
        this.courses = courses;
    }
}
