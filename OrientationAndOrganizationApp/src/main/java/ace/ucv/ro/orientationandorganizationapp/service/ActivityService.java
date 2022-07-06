package ace.ucv.ro.orientationandorganizationapp.service;

import ace.ucv.ro.orientationandorganizationapp.controller.dto.RequestUpdateDto;
import ace.ucv.ro.orientationandorganizationapp.controller.dto.activity.*;
import ace.ucv.ro.orientationandorganizationapp.entity.*;
import ace.ucv.ro.orientationandorganizationapp.entity.enums.ActivityType;
import ace.ucv.ro.orientationandorganizationapp.entity.enums.ClassroomType;
import ace.ucv.ro.orientationandorganizationapp.entity.enums.Specialization;
import ace.ucv.ro.orientationandorganizationapp.exception.*;
import ace.ucv.ro.orientationandorganizationapp.repository.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static ace.ucv.ro.orientationandorganizationapp.utils.DateUtils.convertStringToLocalTime;

@Service
@AllArgsConstructor
public class ActivityService {
    private final ActivityRepository activityRepository;
    private final TeacherRepository teacherRepository;
    private final CourseRepository courseRepository;
    private final ClassroomRepository classroomRepository;
    private final ScheduleRepository scheduleRepository;
    private final StudentEvidenceRepository studentEvidenceRepository;

    public DisplayActivityDto save(SaveActivityDtoWithClassroom saveActivityDto) {
        Teacher teacher = teacherRepository.findByFirstNameAndLastName(
                        saveActivityDto.getTeacherFirstName(),
                        saveActivityDto.getTeacherLastName())
                .orElseThrow(() -> new UserNotFoundException("No teacher with such name !"));

        Course course = courseRepository.findByName(saveActivityDto.getCourseName())
                .orElseThrow(() -> new CourseNotFoundException("No course with this name ! Please introduce something else."));

        Classroom classroom = classroomRepository.findByName(saveActivityDto.getReservedClassroom())
                .orElseThrow(() -> new ClassroomNotFoundException("No classroom with this name ! Please introduce something else."));

        Activity activity = new Activity(
                ActivityType.valueOf(saveActivityDto.getActivityType()),
                teacher,
                saveActivityDto.getStudentGroup(),
                saveActivityDto.getStudentSubgroup(),
                DayOfWeek.valueOf(saveActivityDto.getDay()),
                saveActivityDto.getStartHour(),
                saveActivityDto.getEndHour(),
                classroom,
                course
        );
        activity.setSpecialization(Specialization.valueOf(saveActivityDto.getSpecialization()));
        Activity savedActivity = activityRepository.save(activity);

        Schedule schedule = new Schedule(savedActivity.getStartHour(),
                savedActivity.getEndHour(),
                savedActivity.getDay(),
                savedActivity.getReservedClassroom());

        scheduleRepository.save(schedule);

        return new DisplayActivityDto(
                savedActivity.getActivityType().toString(),
                savedActivity.getStudentGroup(),
                savedActivity.getStudentSubgroup(),
                savedActivity.getDay().toString(),
                savedActivity.getStartHour(),
                savedActivity.getEndHour(),
                savedActivity.getTeacher().getFirstName() + " " + savedActivity.getTeacher().getLastName(),
                savedActivity.getReservedClassroom().getName()
        );
    }

    public DisplayActivityDto updateActivity(Long teacherId, Long activityId, String reservedClassroomName) {
        Activity activity = activityRepository.findById(activityId)
                .orElseThrow(() -> new ActivityNotFoundException("Nu este nicio activitate cu id-ul introdus"));

        checkPermission(teacherId, activity);

        Classroom reservedClassroom = classroomRepository.findByName(reservedClassroomName)
                .orElseThrow(() -> new ClassroomNotFoundException("Nu exista o clasa cu id numele introdus"));

        activity.setReservedClassroom(reservedClassroom);

        Activity savedActivity = activityRepository.save(activity);

        Schedule schedule = new Schedule(
                savedActivity.getStartHour(),
                savedActivity.getEndHour(),
                savedActivity.getDay(),
                savedActivity.getReservedClassroom()
        );

        scheduleRepository.save(schedule);

        return new DisplayActivityDto(
                savedActivity.getActivityType().toString(),
                savedActivity.getStudentGroup(),
                savedActivity.getStudentSubgroup(),
                savedActivity.getDay().toString(),
                savedActivity.getStartHour(),
                savedActivity.getEndHour(),
                savedActivity.getTeacher().getFirstName() + " " + savedActivity.getTeacher().getLastName(),
                savedActivity.getReservedClassroom().getName()
        );
    }

    public List<String> addNewActivity(SaveActivityDto saveActivityDto, String courseName) {
        Activity savedActivity = activityRepository.save(saveNewActivity(saveActivityDto, courseName));

        List<Classroom> classroomsOfType = classroomRepository
                .findByType(savedActivity.getClassroomType())
                .orElseThrow(() -> new ClassroomNotFoundException("Nu a fost gasita nicio clasa cu tipul introdus"));

        int numberOfStudents = getNumberOfStudents(savedActivity);

        List<Classroom> finalClassrooms = classroomsOfType.stream()
                .filter(classroom -> classroom.getSeats() >= numberOfStudents)
                .collect(Collectors.toList());

        List<String> freeClassrooms = checkWhichClassroomsIsFree(finalClassrooms, savedActivity);

        if (freeClassrooms.isEmpty()) {
            throw new ClassroomNotFoundException("Nu este nicio clasa libera in intervalul selectat");
        }

        return freeClassrooms;
    }

    private int getNumberOfStudents(Activity savedActivity) {
        ActivityType type = savedActivity.getActivityType();

        if (type.equals(ActivityType.LABORATORY)) {
            List<StudentsEvidence> evidences = studentEvidenceRepository.findAllByStudentGroupAndStudentSubgroup(savedActivity.getStudentGroup(), savedActivity.getStudentSubgroup());
            return calculateStudentsNumber(evidences);

        }

        if (type.equals(ActivityType.SEMINARY)) {
            List<StudentsEvidence> evidences = studentEvidenceRepository.findAllByStudentGroup(savedActivity.getStudentGroup());
            return calculateStudentsNumber(evidences);

        }

        List<StudentsEvidence> evidences = studentEvidenceRepository.findAllBySpecialization(savedActivity.getSpecialization());
        return calculateStudentsNumber(evidences);

    }

    private int calculateStudentsNumber(List<StudentsEvidence> evidences) {
        int studentsNumber = 0;
        for (StudentsEvidence evidence : evidences) {
            studentsNumber += evidence.getStudentsNumber();
        }
        return studentsNumber;
    }

    private Activity saveNewActivity(SaveActivityDto saveActivityDto, String courseName) {
        Teacher teacher = teacherRepository.findByFirstNameAndLastName(
                        saveActivityDto.getTeacherFirstName(),
                        saveActivityDto.getTeacherLastName())
                .orElseThrow(() -> new UserNotFoundException("Nu a fost gasit un profesor cu numele si prenumele introdus"));

        Course course = courseRepository.findByName(courseName)
                .orElseThrow(() -> new CourseNotFoundException("Nu a fost gasita o clasa cu numele introdus"));

        return new Activity(
                ActivityType.valueOf(saveActivityDto.getActivityType()),
                teacher,
                Specialization.valueOf(saveActivityDto.getSpecialization()),
                saveActivityDto.getStudentGroup(),
                saveActivityDto.getStudentSubgroup(),
                DayOfWeek.valueOf(saveActivityDto.getDay()),
                saveActivityDto.getStartHour(),
                saveActivityDto.getEndHour(),
                ClassroomType.valueOf(saveActivityDto.getClassroomType()),
                course
        );
    }

    private List<String> checkWhichClassroomsIsFree(List<Classroom> finalClassrooms, Activity activity) {
        List<String> freeClassrooms = new ArrayList<>();

        for (Classroom classroom : finalClassrooms) {
            List<Interval> busyIntervals = scheduleRepository.findAllByClassroomId(classroom.getId())
                    .orElseThrow()
                    .stream()
                    .filter(schedule -> schedule.getDay().equals(activity.getDay()))
                    .sorted()
                    .map(schedule -> new Interval(schedule.getStartHour(), schedule.getEndHour()))
                    .collect(Collectors.toList());

            for (int index = 0; index < busyIntervals.size(); index++) {
                if ((busyIntervals.get(index).getEndHour().equals(activity.getStartHour()) ||
                        busyIntervals.get(index).getEndHour().isBefore(activity.getStartHour())) &&
                        (busyIntervals.get(index + 1).getStartHour().equals(activity.getEndHour()) ||
                                busyIntervals.get(index + 1).getStartHour().isAfter(activity.getEndHour()))) {

                    freeClassrooms.add(classroom.getName());
                }
            }
        }
        return freeClassrooms;
    }

    public List<CompleteDisplayActivityDto> findAllActivities() {
        List<Activity> activities = activityRepository.findAll();

        return activities.stream().map(activity -> new CompleteDisplayActivityDto(
                activity.getId(),
                activity.getCourse().getName(),
                activity.getActivityType().toString(),
                activity.getStudentGroup(),
                activity.getStudentSubgroup(),
                activity.getDay().toString(),
                activity.getStartHour(),
                activity.getEndHour(),
                activity.getTeacher().getFirstName(),
                activity.getTeacher().getLastName(),
                activity.getReservedClassroom().getName()
        )).collect(Collectors.toList());
    }

    public void delete(Long activityId) {
        Activity activity = activityRepository.findById(activityId)
                .orElseThrow();

        activityRepository.delete(activity);
    }

    public CompleteDisplayActivityDto update(Long activityId, RequestUpdateDto request) {
        Activity activity = activityRepository.findById(activityId)
                .orElseThrow(() -> new ActivityNotFoundException("No such activity! Please introduse other id "));

        Activity savedActivity = activityRepository.save(
                setActivityField(activity, request.getFieldName(), request.getFieldValue()));

        return new CompleteDisplayActivityDto(
                savedActivity.getId(),
                savedActivity.getCourse().getName(),
                savedActivity.getActivityType().toString(),
                savedActivity.getStudentGroup(),
                savedActivity.getStudentSubgroup(),
                savedActivity.getDay().toString(),
                savedActivity.getStartHour(),
                savedActivity.getEndHour(),
                savedActivity.getTeacher().getFirstName(),
                savedActivity.getTeacher().getLastName(),
                savedActivity.getReservedClassroom().getName()
        );
    }

    private Activity setActivityField(Activity activity, String fieldName, String value) {
        switch (fieldName) {
            case "teacher":
                String[] names = value.split(" ");
                String firstName = names[0];
                String lastName = names[1];
                Teacher teacher = teacherRepository.findByFirstNameAndLastName(firstName, lastName)
                        .orElseThrow(() -> new UserNotFoundException("No teacher with this name! Please introduce something else !"));
                activity.setTeacher(teacher);
                break;
            case "startHour":
                LocalTime newStartHour = convertStringToLocalTime(value);
                activity.setStartHour(newStartHour);
                break;
            case "endHour":
                LocalTime newEndHour = convertStringToLocalTime(value);
                activity.setEndHour(newEndHour);
                break;
            case "classroom":
                Classroom classroom = classroomRepository.findByName(value)
                        .orElseThrow();
                activity.setReservedClassroom(classroom);
                break;
            default:
                break;
        }
        return activity;
    }

    public List<CompleteDisplayActivityDto> findActivities(Long teacherId) {
        List<Course> courses = courseRepository.findAllByTeacherId(teacherId)
                .orElseThrow();
        List<Activity> activities = new ArrayList<>();
        for (Course c : courses) {
            Long courseId = c.getId();
            activities.addAll(activityRepository.findByCourseId(courseId).orElseThrow());
        }

        return activities
                .stream()
                .map(activity -> new CompleteDisplayActivityDto(
                        activity.getId(),
                        activity.getCourse().getName(),
                        activity.getActivityType().name(),
                        activity.getStudentGroup(),
                        activity.getStudentSubgroup(),
                        activity.getDay().name(),
                        activity.getStartHour(),
                        activity.getEndHour(),
                        activity.getTeacher().getFirstName(),
                        activity.getTeacher().getLastName(),
                        activity.getReservedClassroom().getName()
                ))
                .collect(Collectors.toList());
    }

    private void checkPermission(Long teacherId, Activity activity) {
        if (!activity.getCourse().getTeacher().getId().equals(teacherId)) {
            throw new NoPermissionException("Nu aveti permisiunea de a modifica aceasta activitate");
        }
    }

    public Activity findById(Long activityId) {
        return activityRepository.findById(activityId)
                .orElseThrow();
    }

    public void addNewActivityDto(NewActivityDto newActivityDto) {
        LocalTime startHour = LocalTime.parse(newActivityDto.getStartHour(), DateTimeFormatter.ISO_LOCAL_TIME);
        LocalTime endHour = LocalTime.parse(newActivityDto.getEndHour(), DateTimeFormatter.ISO_LOCAL_TIME);

        Teacher teacher = teacherRepository.findByEmail(newActivityDto.getTeacher())
                .orElseThrow(() -> new UserNotFoundException("Nu a fost gasit niciun profesor cu acest email"));

        Course course = courseRepository.findByName(newActivityDto.getCourse()).orElseThrow(()
                -> new CourseNotFoundException("Nu a fost gasit niciun curs cu acest nume"));
        Activity activity = new Activity(
                ActivityType.valueOf(newActivityDto.getActivityType()),
                teacher,
                Specialization.valueOf(newActivityDto.getSpecialization()),
                newActivityDto.getStudentGroup(),
                newActivityDto.getStudentSubgroup(),
                DayOfWeek.valueOf(newActivityDto.getDay()),
                startHour,
                endHour,
                ClassroomType.valueOf(newActivityDto.getClassroomType()),
                course
        );

        activityRepository.save(activity);
    }
}
