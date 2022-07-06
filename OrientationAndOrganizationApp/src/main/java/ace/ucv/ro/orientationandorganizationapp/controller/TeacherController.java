package ace.ucv.ro.orientationandorganizationapp.controller;

import ace.ucv.ro.orientationandorganizationapp.controller.dto.RequestUpdateDto;
import ace.ucv.ro.orientationandorganizationapp.controller.dto.activity.ActivityDtoForTeacher;
import ace.ucv.ro.orientationandorganizationapp.controller.dto.teacher.CompleteTeacherDto;
import ace.ucv.ro.orientationandorganizationapp.controller.dto.teacher.DisplayTeacherDto;
import ace.ucv.ro.orientationandorganizationapp.service.TeacherService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/teachers")
@AllArgsConstructor
public class TeacherController {
    private final TeacherService teacherService;

    @GetMapping(path = "{teacherId}/schedule")
    //TODO: doar profesorul poate sa faca asta
    public ResponseEntity<List<ActivityDtoForTeacher>> viewSchedule(@PathVariable("teacherId") Long teacherId) {

        return new ResponseEntity<>(teacherService.viewSchedule(teacherId), HttpStatus.OK);
    }

    @GetMapping(path = "{teacherId}/profile")
    //TODO: doar profesorul poate sa faca asta
    public ResponseEntity<DisplayTeacherDto> viewProfile(@PathVariable("teacherId") Long teacherId) {

        return new ResponseEntity<>(teacherService.viewProfile(teacherId), HttpStatus.OK);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    //TODO: doar adminul poate sa faca asta
    public ResponseEntity<String> save(@RequestBody @Valid CompleteTeacherDto teacherDto) {
        teacherService.save(teacherDto);

        return new ResponseEntity<>("The teacher was successfully saved in the database.", HttpStatus.CREATED);
    }

    @GetMapping
    //TODO: doar adminul poate sa faca asta
    public ResponseEntity<List<CompleteTeacherDto>> findTeachers() {

        return new ResponseEntity<>(teacherService.findAllTeachers(), HttpStatus.OK);
    }

    @DeleteMapping(path = "/{teacherId}")
    //TODO: doar adminul poate sa faca asta
    public ResponseEntity<String> delete(@PathVariable("teacherId") Long teacherId) {
        teacherService.delete(teacherId);

        return new ResponseEntity<>("Profesorul a fost sters cu succes !", HttpStatus.OK);
    }

    @PatchMapping
    //TODO: doar teacherul poate sa faca asta pentru propriul cont
    public ResponseEntity<CompleteTeacherDto> update(@RequestBody @Valid RequestUpdateDto request){

        Long teacherId = 7L;
        return new ResponseEntity<>(teacherService.updateTeacher(request, teacherId), HttpStatus.OK);
    }
}
