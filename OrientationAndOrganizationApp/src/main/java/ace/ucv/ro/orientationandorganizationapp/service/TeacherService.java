package ace.ucv.ro.orientationandorganizationapp.service;

import ace.ucv.ro.orientationandorganizationapp.controller.dto.RequestUpdateDto;
import ace.ucv.ro.orientationandorganizationapp.controller.dto.activity.ActivityDtoForTeacher;
import ace.ucv.ro.orientationandorganizationapp.controller.dto.teacher.DisplayTeacherDto;
import ace.ucv.ro.orientationandorganizationapp.controller.dto.teacher.CompleteTeacherDto;
import ace.ucv.ro.orientationandorganizationapp.entity.Activity;
import ace.ucv.ro.orientationandorganizationapp.entity.Course;
import ace.ucv.ro.orientationandorganizationapp.entity.Teacher;
import ace.ucv.ro.orientationandorganizationapp.entity.enums.Department;
import ace.ucv.ro.orientationandorganizationapp.entity.enums.Role;
import ace.ucv.ro.orientationandorganizationapp.exception.UserNotFoundException;
import ace.ucv.ro.orientationandorganizationapp.repository.ActivityRepository;
import ace.ucv.ro.orientationandorganizationapp.repository.CourseRepository;
import ace.ucv.ro.orientationandorganizationapp.repository.TeacherRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static ace.ucv.ro.orientationandorganizationapp.utils.DateUtils.convertStringToLocalDate;

@Service
@AllArgsConstructor
public class TeacherService {
    private final TeacherRepository teacherRepository;
    private final ActivityRepository activityRepository;
    private final CourseRepository courseRepository;

    public List<ActivityDtoForTeacher> viewSchedule(Long teacherId) {
        List<Activity> activityList = activityRepository.findByTeacherId(teacherId)
                .orElseThrow(() -> new UserNotFoundException("Nu este niciun profesor cu acest id"));

        return activityList.stream().map(
                activity -> new ActivityDtoForTeacher(
                        activity.getCourse().getName(),
                        activity.getActivityType().toString(),
                        activity.getDay().toString(),
                        activity.getStartHour(),
                        activity.getEndHour(),
                        activity.getReservedClassroom().getName(),
                        activity.getStudentGroup(),
                        activity.getStudentSubgroup()
                )).collect(Collectors.toList());
    }

    public DisplayTeacherDto viewProfile(Long teacherId) {
        Teacher teacher = teacherRepository.findById(teacherId).orElseThrow();
        List<String> courses = courseRepository.findAllByTeacherId(teacherId)
                .orElseThrow()
                .stream()
                .map(Course::getName)
                .collect(Collectors.toList());

        return new DisplayTeacherDto(
                teacher.getFirstName(),
                teacher.getLastName(),
                teacher.getAddress(),
                teacher.getCNP(),
                teacher.getPhoneNumber(),
                teacher.getEmail(),
                teacher.getDateOfBirth(),
                teacher.getDepartment().toString(),
                teacher.getAcademicDegree(),
                courses
        );
    }

    public void save(CompleteTeacherDto teacherDto) {
        Teacher teacher = new Teacher(
                teacherDto.getFirstName(),
                teacherDto.getLastName(),
                teacherDto.getAddress(),
                teacherDto.getCNP(),
                teacherDto.getPhoneNumber(),
                teacherDto.getEmail(),
                teacherDto.getDateOfBirth(),
                Role.valueOf(teacherDto.getRole()),
                Department.valueOf(teacherDto.getDepartment()),
                teacherDto.getAcademicDegree()
        );

        teacher.setPassword(teacherDto.getPassword());
        teacherRepository.save(teacher);
    }

    public List<CompleteTeacherDto> findAllTeachers() {
        List<Teacher> teachers = teacherRepository.findAll();

        return teachers.stream().map(teacher -> new CompleteTeacherDto(
                teacher.getFirstName(),
                teacher.getLastName(),
                teacher.getAddress(),
                teacher.getCNP(),
                teacher.getPhoneNumber(),
                teacher.getEmail(),
                teacher.getDateOfBirth(),
                teacher.getDepartment().toString(),
                teacher.getAcademicDegree(),
                teacher.getPassword(),
                teacher.getRole().toString()
        )).collect(Collectors.toList());
    }

    public void delete(Long teacherId) {
        //Verifica ce se intampla daca stergi un teacher care are activities
        Teacher teacher = teacherRepository.findById(teacherId)
                .orElseThrow();

        teacherRepository.delete(teacher);
    }

    public CompleteTeacherDto updateTeacher(RequestUpdateDto request, Long teacherId) {
        Teacher teacher = teacherRepository.findById(teacherId)
                .orElseThrow();

        Teacher savedTeacher = teacherRepository.save(
                setTeacherField(request.getFieldName(), request.getFieldValue(), teacher)
        );

        return new CompleteTeacherDto(
                savedTeacher.getFirstName(),
                savedTeacher.getLastName(),
                savedTeacher.getAddress(),
                savedTeacher.getCNP(),
                savedTeacher.getPhoneNumber(),
                savedTeacher.getEmail(),
                savedTeacher.getDateOfBirth(),
                savedTeacher.getDepartment().toString(),
                savedTeacher.getAcademicDegree(),
                savedTeacher.getPassword(),
                savedTeacher.getRole().toString()
        );
    }

    private Teacher setTeacherField(String fieldName, String value, Teacher teacher) {
        switch (fieldName) {
            case "firstName":
                teacher.setFirstName(value);
                break;
            case "lastName":
                teacher.setLastName(value);
                break;
            case "address":
                teacher.setAddress(value);
                break;
            case "CNP":
                teacher.setCNP(Long.valueOf(value));
                break;
            case "phoneNumber":
                teacher.setPhoneNumber(Long.valueOf(value));
                break;
            case "email":
                teacher.setEmail(value);
                break;
            case "dateOfBirth":
                teacher.setDateOfBirth(convertStringToLocalDate(value));
                break;
            case "password":
                teacher.setPassword(value);
                break;
            case "department":
                teacher.setDepartment(Department.valueOf(value));
                break;
            case "academicDegree":
                teacher.setAcademicDegree(value);
                break;
        }

        return teacher;
    }
}
