package ace.ucv.ro.orientationandorganizationapp.controller;

import ace.ucv.ro.orientationandorganizationapp.controller.dto.RequestUpdateDto;
import ace.ucv.ro.orientationandorganizationapp.controller.dto.activity.ActivityDtoForStudent;
import ace.ucv.ro.orientationandorganizationapp.controller.dto.student.CompleteStudentDto;
import ace.ucv.ro.orientationandorganizationapp.controller.dto.student.DisplayStudentDto;
import ace.ucv.ro.orientationandorganizationapp.controller.dto.student.StudentDto;
import ace.ucv.ro.orientationandorganizationapp.service.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/students")
@AllArgsConstructor
public class StudentController {
    private final StudentService studentService;

    @GetMapping(path = "{studentId}/schedule")
    public ResponseEntity<List<ActivityDtoForStudent>> viewSchedule(@PathVariable("studentId") Long studentId) {

        return new ResponseEntity<>(studentService.viewSchedule(studentId), HttpStatus.OK);
    }

    @GetMapping(path = "{studentId}/profile")
    //TODO: doar studentul poate sa faca asta pentru propriul cont
    public ResponseEntity<DisplayStudentDto> viewProfile(@PathVariable("studentId") Long studentId) {

        return new ResponseEntity<>(studentService.viewProfile(studentId), HttpStatus.OK);
    }

    @PatchMapping(path = "{studentId}/profile/edit")
    //TODO: doar studentul poate sa faca asta pentru propriul cont
    public ResponseEntity<DisplayStudentDto> editProfile(@PathVariable("studentId") Long studentId,
                                                         @RequestBody RequestUpdateDto request) {

        return new ResponseEntity<>(studentService.editProfile(studentId, request.getFieldName(), request.getFieldValue()), HttpStatus.OK);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    //TODO: doar adminul poate sa faca asta
    public ResponseEntity<String> save(@RequestBody @Valid CompleteStudentDto studentDto) {
        studentService.save(studentDto);

        return new ResponseEntity<>("The student was successfully saved in the database.", HttpStatus.CREATED);
    }

    @GetMapping()
    //TODO: doar adminul poate sa faca asta
    public ResponseEntity<List<StudentDto>> findStudents() {

        return new ResponseEntity<>(studentService.findAllStudents(), HttpStatus.OK);
    }

    @PatchMapping(path = "/{studentId}")
    //TODO: doar adminul poate sa faca asta
    public ResponseEntity<CompleteStudentDto> update(@RequestBody @Valid RequestUpdateDto request,
                                                            @PathVariable("studentId") Long studentId) {

        return new ResponseEntity<>(studentService.updateStudent(request, studentId), HttpStatus.OK);
    }

    @DeleteMapping(path = "/{studentId}")
    //TODO: doar adminul poate sa faca asta
    public ResponseEntity<String> delete(@PathVariable("studentId") Long studentId) {
        studentService.delete(studentId);
        return new ResponseEntity<>("Studentul a fost sters cu succes !", HttpStatus.OK);
    }
}
