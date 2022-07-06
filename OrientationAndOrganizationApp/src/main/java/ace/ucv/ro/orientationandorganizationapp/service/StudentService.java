package ace.ucv.ro.orientationandorganizationapp.service;

import ace.ucv.ro.orientationandorganizationapp.controller.dto.RequestUpdateDto;
import ace.ucv.ro.orientationandorganizationapp.controller.dto.activity.ActivityDtoForStudent;
import ace.ucv.ro.orientationandorganizationapp.controller.dto.student.CompleteStudentDto;
import ace.ucv.ro.orientationandorganizationapp.controller.dto.student.DisplayStudentDto;
import ace.ucv.ro.orientationandorganizationapp.controller.dto.student.StudentDto;
import ace.ucv.ro.orientationandorganizationapp.entity.Activity;
import ace.ucv.ro.orientationandorganizationapp.entity.Student;
import ace.ucv.ro.orientationandorganizationapp.entity.enums.Role;
import ace.ucv.ro.orientationandorganizationapp.entity.enums.Specialization;
import ace.ucv.ro.orientationandorganizationapp.exception.UserNotFoundException;
import ace.ucv.ro.orientationandorganizationapp.repository.ActivityRepository;
import ace.ucv.ro.orientationandorganizationapp.repository.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;
    private final ActivityRepository activityRepository;

    public void save(CompleteStudentDto studentDto) {
        Student student = new Student(
                studentDto.getFirstName(),
                studentDto.getLastName(),
                studentDto.getAddress(),
                studentDto.getCNP(),
                studentDto.getPhoneNumber(),
                studentDto.getEmail(),
                studentDto.getDateOfBirth(),
                Role.valueOf(studentDto.getRole()),
                Specialization.valueOf(studentDto.getSpecialization()),
                studentDto.getStudyYear(),
                studentDto.getStudentGroup(),
                studentDto.getStudentSubgroup()
        );

        //TODO: GENEREAZA O PAROLA RANDOM
        student.setPassword(generatePassword());

        System.out.println();
        studentRepository.save(student);
    }

    private String generatePassword() {
        String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvxyz0123456789" ;
        StringBuilder s = new StringBuilder(9);

        for (int i = 0; i < 9; i++) {
            int ch = (int) (ALPHA_NUMERIC_STRING.length() * Math.random());
            s.append(ALPHA_NUMERIC_STRING.charAt(ch));
        }

        return s.toString();
    }

    public List<ActivityDtoForStudent> viewSchedule(Long studentId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow();

        List<Activity> activities = activityRepository.findByStudentGroupAndStudentSubgroup(
                        student.getStudentGroup(), student.getStudentSubgroup())
                .orElseThrow();

        return activities.stream().map(
                activity -> new ActivityDtoForStudent(
                        activity.getCourse().getName(),
                        activity.getActivityType().toString(),
                        activity.getDay().toString(),
                        activity.getStartHour(),
                        activity.getEndHour(),
                        activity.getTeacher().getFirstName() + " " + activity.getTeacher().getLastName(),
                        activity.getReservedClassroom().getName()
                )).collect(Collectors.toList());
    }

    public DisplayStudentDto viewProfile(Long studentId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow();

        return new DisplayStudentDto(
                student.getFirstName(),
                student.getLastName(),
                student.getAddress(),
                student.getCNP(),
                student.getPhoneNumber(),
                student.getEmail(),
                student.getDateOfBirth(),
                student.getSpecialization().toString(),
                student.getStudyYear(),
                student.getStudentGroup(),
                student.getStudentSubgroup()
        );
    }

    public DisplayStudentDto editProfile(Long studentId, String fieldName, String fieldValue) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new UserNotFoundException("Nu a fost gasit niciun student cu acest ID"));
        Student studentUpdated = studentRepository.save(setFieldForStudentAccess(fieldName, fieldValue, student));

        return new DisplayStudentDto(
                studentUpdated.getFirstName(),
                studentUpdated.getLastName(),
                studentUpdated.getAddress(),
                studentUpdated.getCNP(),
                studentUpdated.getPhoneNumber(),
                studentUpdated.getEmail(),
                studentUpdated.getDateOfBirth(),
                studentUpdated.getSpecialization().toString(),
                studentUpdated.getStudyYear(),
                studentUpdated.getStudentGroup(),
                studentUpdated.getStudentSubgroup()
        );
    }

    public List<StudentDto> findAllStudents() {
        List<Student> students = studentRepository.findAll();

        return students.stream()
                .map(student -> new StudentDto(
                        student.getFirstName(),
                        student.getLastName(),
                        student.getAddress(),
                        student.getCNP(),
                        student.getPhoneNumber(),
                        student.getEmail(),
                        student.getDateOfBirth(),
                        student.getSpecialization().toString(),
                        student.getStudyYear(),
                        student.getStudentGroup(),
                        student.getStudentSubgroup(),
                        student.getPassword()
                ))
                .collect(Collectors.toList());
    }

    public void delete(Long studentId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow();

        studentRepository.delete(student);
    }

    public CompleteStudentDto updateStudent(RequestUpdateDto request, Long studentId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow();

        Student studentUpdated = studentRepository
                .save(setFieldForAdminAccess(request.getFieldName(), request.getFieldValue(), student));

        return new CompleteStudentDto(
                studentUpdated.getFirstName(),
                studentUpdated.getLastName(),
                studentUpdated.getAddress(),
                studentUpdated.getCNP(),
                studentUpdated.getPhoneNumber(),
                studentUpdated.getEmail(),
                studentUpdated.getDateOfBirth(),
                studentUpdated.getSpecialization().toString(),
                studentUpdated.getStudyYear(),
                studentUpdated.getStudentGroup(),
                studentUpdated.getStudentSubgroup(),
                studentUpdated.getRole().toString());
    }

    private Student setFieldForAdminAccess(String field, String value, Student student) {
        switch (field) {
            case "address":
                student.setAddress(value);
                break;
            case "phoneNumber":
                student.setPhoneNumber(Long.valueOf(value));
                break;
            case "email":
                student.setEmail(value);
                break;
            case "password":
                student.setPassword(value);
                break;
            case "specialization":
                student.setSpecialization(Specialization.valueOf(value));
                break;
            case "studyYear":
                student.setStudyYear(Integer.parseInt(value));
                break;
            case "studentGroup":
                student.setStudentGroup(value);
                break;
            case "studentSubgroup":
                student.setStudentSubgroup(value);
                break;
            case "role":
                student.setRole(Role.valueOf(value));
                break;
            default:
                break;
        }

        return student;
    }

    private Student setFieldForStudentAccess(String field, String value, Student student) {
        switch (field) {
            case "address":
                student.setAddress(value);
                break;
            case "phoneNumber":
                student.setPhoneNumber(Long.valueOf(value));
                break;
            default:
                break;
        }

        return student;
    }


}
