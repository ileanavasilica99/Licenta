package ace.ucv.ro.orientationandorganizationapp.service;

import ace.ucv.ro.orientationandorganizationapp.controller.dto.course.DisplayCourseDto;
import ace.ucv.ro.orientationandorganizationapp.entity.Course;
import ace.ucv.ro.orientationandorganizationapp.repository.CourseRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CourseService {
    private final CourseRepository courseRepository;

    public List<DisplayCourseDto> getAllCourses(String id){
        List<Course> courses = courseRepository.findAllByTeacherId(Long.valueOf(id))
                .orElseThrow(RuntimeException::new);

      return courses.stream().map( course ->
               new DisplayCourseDto(
                       course.getName(),
                       String.valueOf(course.getYear())
               )).collect(Collectors.toList());
    }
}
