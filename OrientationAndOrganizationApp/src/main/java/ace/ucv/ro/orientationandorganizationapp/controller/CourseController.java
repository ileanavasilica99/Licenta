package ace.ucv.ro.orientationandorganizationapp.controller;

import ace.ucv.ro.orientationandorganizationapp.controller.dto.course.DisplayCourseDto;
import ace.ucv.ro.orientationandorganizationapp.service.CourseService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/courses")
public class CourseController {
    private final CourseService courseService;

    @GetMapping(path = "/{teacherId}")
    public ResponseEntity<List<DisplayCourseDto>> getCourses(@PathVariable("teacherId") String teacherId) {

        return ResponseEntity.ok(courseService.getAllCourses(teacherId));
    }

}
