package ace.ucv.ro.orientationandorganizationapp.service.mapper;

import ace.ucv.ro.orientationandorganizationapp.controller.dto.classroom.ClassroomDto;
import ace.ucv.ro.orientationandorganizationapp.entity.Classroom;
import ace.ucv.ro.orientationandorganizationapp.entity.enums.ClassroomType;
import ace.ucv.ro.orientationandorganizationapp.entity.enums.Faculty;
import org.springframework.stereotype.Component;

@Component
public class ClassroomMapper {

    public static Classroom mapDtoToModel(ClassroomDto classroomDto) {
        return new Classroom(
                classroomDto.getName(),
                classroomDto.getFloor(),
                Faculty.valueOf(classroomDto.getFaculty()),
                classroomDto.getArea(),
                classroomDto.getSeats(),
                ClassroomType.valueOf(classroomDto.getClassroomType())
        );
    }
}
