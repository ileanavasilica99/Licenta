package ace.ucv.ro.orientationandorganizationapp.service;

import ace.ucv.ro.orientationandorganizationapp.controller.dto.RequestUpdateDto;
import ace.ucv.ro.orientationandorganizationapp.controller.dto.classroom.ClassroomDto;
import ace.ucv.ro.orientationandorganizationapp.controller.dto.classroom.SearchClassroomResponse;
import ace.ucv.ro.orientationandorganizationapp.entity.Classroom;
import ace.ucv.ro.orientationandorganizationapp.entity.Image;
import ace.ucv.ro.orientationandorganizationapp.entity.enums.ClassroomType;
import ace.ucv.ro.orientationandorganizationapp.entity.enums.Faculty;
import ace.ucv.ro.orientationandorganizationapp.exception.ClassroomNotFoundException;
import ace.ucv.ro.orientationandorganizationapp.exception.ImageNotFoundException;
import ace.ucv.ro.orientationandorganizationapp.repository.ClassroomRepository;
import ace.ucv.ro.orientationandorganizationapp.repository.ImageRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static ace.ucv.ro.orientationandorganizationapp.service.mapper.ClassroomMapper.mapDtoToModel;

@Service
@AllArgsConstructor
public class ClassroomService {
    private final ImageRepository imageRepository;
    private final ImageService imageService;
    private final ClassroomRepository classroomRepository;

    public void save(ClassroomDto classroomDto, MultipartFile image) {
        imageService.saveFile(image);
        Image savedImage = imageRepository.findByName(image.getOriginalFilename())
                .orElseThrow(ImageNotFoundException::new);

        Classroom classroom = mapDtoToModel(classroomDto);
        classroom.setImage(savedImage);

        classroomRepository.save(classroom);
    }

    public SearchClassroomResponse findClassroom(String classroomName) {
        Classroom classroom = classroomRepository.findByName(classroomName)
                .orElseThrow(() -> new ClassroomNotFoundException("No classes were found with the name: " + classroomName));

        return new SearchClassroomResponse(
                classroom.getFaculty().name(),
                classroom.getArea(),
                classroom.getFloor()
        );
    }

    public Optional<Image> findImageForClassroom(String classroomName) {
        Classroom classroom = classroomRepository.findByName(classroomName)
                .orElseThrow(() -> new ClassroomNotFoundException("No classes were found with the name: " + classroomName));

        return Optional.ofNullable(classroom.getImage());
    }

    public List<ClassroomDto> findClassrooms() {
        List<Classroom> classrooms = classroomRepository.findAll();

        return classrooms.stream().map(classroom -> new ClassroomDto(
                classroom.getName(),
                classroom.getFloor(),
                classroom.getFaculty().toString(),
                classroom.getArea(),
                classroom.getSeats(),
                classroom.getType().toString()
        )).collect(Collectors.toList());
    }

    public ClassroomDto updateClassroom(String classroomName, RequestUpdateDto request) {
        Classroom classroom = classroomRepository.findByName(classroomName)
                .orElseThrow();

        Classroom updatedClassroom = classroomRepository.save(
                setClassroomField(classroom, request.getFieldValue(), request.getFieldValue())
        );

        return new ClassroomDto(
                updatedClassroom.getName(),
                updatedClassroom.getFloor(),
                updatedClassroom.getFaculty().toString(),
                updatedClassroom.getArea(),
                updatedClassroom.getSeats(),
                updatedClassroom.getType().toString()
        );
    }

    public void delete(String classroomName) {
        Classroom classroom = classroomRepository.findByName(classroomName)
                .orElseThrow();

        classroomRepository.delete(classroom);
    }

    private Classroom setClassroomField(Classroom classroom, String fieldName, String value) {
        switch (fieldName) {
            case "name":
                classroom.setName(value);
                break;
            case "floor":
                classroom.setFloor(Integer.parseInt(value));
                break;
            case "faculty":
                classroom.setFaculty(Faculty.valueOf(value));
                break;
            case "area":
                classroom.setArea(value);
                break;
            case "seats":
                classroom.setSeats(Integer.parseInt(value));
                break;
            case "classroomType":
                classroom.setType(ClassroomType.valueOf(value));
                break;
            default:
                break;
        }

        return classroom;
    }

}
