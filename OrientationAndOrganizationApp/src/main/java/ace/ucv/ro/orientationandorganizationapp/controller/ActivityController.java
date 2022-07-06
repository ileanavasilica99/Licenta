package ace.ucv.ro.orientationandorganizationapp.controller;

import ace.ucv.ro.orientationandorganizationapp.controller.dto.RequestUpdateDto;
import ace.ucv.ro.orientationandorganizationapp.controller.dto.activity.*;
import ace.ucv.ro.orientationandorganizationapp.entity.Activity;
import ace.ucv.ro.orientationandorganizationapp.service.ActivityService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/activities")
public class ActivityController {
    private final ActivityService activityService;

    @PostMapping
    //Asta poate sa faca doar un admin
    public ResponseEntity<DisplayActivityDto> save(@RequestBody @Valid SaveActivityDtoWithClassroom saveActivityDto) {

        return new ResponseEntity<>(activityService.save(saveActivityDto), HttpStatus.CREATED);
    }

    @GetMapping
    //Asta poate sa faca doar un admin
    public ResponseEntity<List<CompleteDisplayActivityDto>> getAllActivities() {

        return new ResponseEntity<>(activityService.findAllActivities(), HttpStatus.OK);
    }

    @DeleteMapping(path = "/{activityId}/delete")
    //Asta poate sa faca doar un admin
    public ResponseEntity<String> delete(@PathVariable("activityId") Long activityId) {
        activityService.delete(activityId);

        return new ResponseEntity<>("Activitatea a fost stearsa cu succes !", HttpStatus.OK);
    }

    @PatchMapping(path = "/{activityId}/update")
    //Asta poate sa faca doar un admin
    public ResponseEntity<CompleteDisplayActivityDto> update(@PathVariable("activityId") Long activityId,
                                                             @RequestBody RequestUpdateDto request) {

        return new ResponseEntity<>(activityService.update(activityId, request), HttpStatus.OK);
    }

    @PostMapping(path = "/{teacherId}/addNew/{courseName}")
    //TODO: Asta poate sa faca doar TEACHER-ul
    public ResponseEntity<List<String>> addNewActivity(@PathVariable Long teacherId,
                                                       @PathVariable @Valid String courseName,
                                                       @RequestBody @Valid SaveActivityDto saveActivityDto) {

        List<String> freeClassrooms = activityService.addNewActivity(saveActivityDto, courseName);

        return new ResponseEntity<>(freeClassrooms, HttpStatus.OK);
    }

    @PatchMapping(path = "/{teacherId}/{activityId}/{reservedClassroom}")
    //TODO: Asta poate sa faca doar TEACHER-ul
    public ResponseEntity<DisplayActivityDto> updateActivity(@PathVariable Long teacherId,
                                                             @PathVariable Long activityId,
                                                             @PathVariable String reservedClassroom) {

        return new ResponseEntity<>(activityService.updateActivity(teacherId, activityId, reservedClassroom), HttpStatus.OK);
    }

    @GetMapping({"/{teacherId}/list"})
    public ModelAndView getAllEmployees(@PathVariable Long teacherId) {
        ModelAndView mav = new ModelAndView("teacher_activities");
        mav.addObject("activities", activityService.findActivities(teacherId));
        return mav;
    }

    @GetMapping("/addActivityForm")
    public ModelAndView addEmployeeForm() {
        ModelAndView mav = new ModelAndView("add-activity-form");
        NewActivityDto activity = new NewActivityDto();
        mav.addObject("employee", activity);
        return mav;
    }

    @PostMapping("/saveEmployee")
    public String saveEmployee(@ModelAttribute NewActivityDto employee) {
        activityService.addNewActivityDto(employee);
        return "redirect:http://localhost:8082/activities/list";
    }

    @GetMapping("/showUpdateForm")
    public ModelAndView showUpdateForm(@RequestParam Long activityId) {
        ModelAndView mav = new ModelAndView("add-activity-form");
        Activity employee = activityService.findById(activityId);
        //Activity employee = activityRepo.findById(employeeId).get();
        mav.addObject("employee", employee);
        return mav;
    }
}
