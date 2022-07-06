package ace.ucv.ro.orientationandorganizationapp.controller;

import ace.ucv.ro.orientationandorganizationapp.controller.dto.RequestUpdateDto;
import ace.ucv.ro.orientationandorganizationapp.controller.dto.classroom.ClassroomDto;
import ace.ucv.ro.orientationandorganizationapp.controller.dto.classroom.SearchClassroomResponse;
import ace.ucv.ro.orientationandorganizationapp.entity.Image;
import ace.ucv.ro.orientationandorganizationapp.service.ClassroomService;
import ace.ucv.ro.orientationandorganizationapp.utils.ImageUtils;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/classrooms")
public class ClassroomController {
    private final ClassroomService classroomService;

    @GetMapping(path = "/{classroomName}/search")
    //Studentul si teacherul pot sa faca asta
    public ResponseEntity<SearchClassroomResponse> search(@PathVariable("classroomName") String classroomName) {

        return new ResponseEntity<>(classroomService.findClassroom(classroomName), HttpStatus.OK);
    }

    @GetMapping(path = "/{classroomName}/search/image")
    //Studentul si teacherul pot sa faca asta
    public ResponseEntity<byte[]> getImageForClassroom(@PathVariable("classroomName") String classroomName) {

        final Optional<Image> classroomImage = classroomService.findImageForClassroom(classroomName);

        return ResponseEntity
                .ok()
                .contentType(MediaType.valueOf(classroomImage.get().getType()))
                .body(ImageUtils.decompressImage(classroomImage.get().getImage()));
    }

    @GetMapping
    //asta poate sa faca toti
    public ResponseEntity<List<ClassroomDto>> getClassrooms() {

        return new ResponseEntity<>(classroomService.findClassrooms(), HttpStatus.OK);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    //Doar adminul poate sa faca asta
    public ResponseEntity<String> save(@RequestPart("classroom") @Valid ClassroomDto classroomDto,
                                       @RequestParam("image") MultipartFile image) {
        classroomService.save(classroomDto, image);

        return new ResponseEntity<>("A new classroom was added with success. ", HttpStatus.CREATED);
    }

    @PatchMapping(path = "/{classroomName}")
    //Doar adminul poate sa faca asta
    public ResponseEntity<ClassroomDto> update(@PathVariable("classroomName") @Valid String classroomName,
                                               @RequestBody RequestUpdateDto request) {

        return new ResponseEntity<>(classroomService.updateClassroom(classroomName, request), HttpStatus.OK);
    }

    @DeleteMapping(path = "/{classroomName}")
    //Doar adminul poate sa faca asta
    public ResponseEntity<String> delete(@PathVariable("classroomName") @Valid String classroomName) {
        classroomService.delete(classroomName);

        return new ResponseEntity<>("The classroom was deleted with success! !", HttpStatus.OK);
    }

}
