package ace.ucv.ro.orientationandorganizationapp.controller.dto.student;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;
import java.time.LocalDate;

@Setter@Getter
public class CompleteStudentDto extends DisplayStudentDto{

    @NotEmpty(message = "List of roles cannot be empty")
    @NotNull(message = "List of roles cannot be null")
    @JsonProperty(value = "role")
    private String role;

    public CompleteStudentDto(@NotNull(message = "First name cannot be null")
                              @NotEmpty(message = "First name cannot be empty") String firstName,
                              @NotNull(message = "Last name cannot be null")
                              @NotEmpty(message = "Last name cannot be empty") String lastName,
                              @NotNull(message = "Address cannot be null")
                              @NotEmpty(message = "Address cannot be empty") String address,
                              @NotNull(message = "CNP cannot be null")
                              @Digits(fraction = 0, integer = 13, message = "CNP must have 13 digits") Long CNP,
                              @NotNull(message = "Phone number cannot be null") Long phoneNumber,
                              @Email(message = "Email is not valid")
                              @NotEmpty(message = "Email cannot be empty")
                              @NotNull(message = "Email cannot be null") String email,
                              @NotNull(message = "Date of birth cannot be null")
                              @Past(message = "Date of birth must be in the past") LocalDate dateOfBirth,
                              @NotEmpty(message = "Specialization cannot be empty")
                              @NotNull(message = "Specialization cannot be null") String specialization,
                              @NotNull(message = "Study year cannot be null") int studyYear,
                              @NotEmpty(message = "Student group year cannot be empty")
                              @NotNull(message = "Student group cannot be null") String studentGroup,
                              @NotEmpty(message = "Student subgroup cannot be empty")
    @NotNull(message = "Student subgroup cannot be null") String studentSubgroup, String role) {
        super(firstName, lastName, address, CNP, phoneNumber, email, dateOfBirth, specialization,
                studyYear, studentGroup, studentSubgroup);
        this.role = role;
    }
}
