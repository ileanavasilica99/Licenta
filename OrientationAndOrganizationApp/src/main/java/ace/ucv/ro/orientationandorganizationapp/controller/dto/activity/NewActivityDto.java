package ace.ucv.ro.orientationandorganizationapp.controller.dto.activity;

import lombok.Data;

@Data
public class NewActivityDto {
    private Long id;
    private String activityType;
    private String teacher;
    private String studentGroup;
    private String specialization;
    private String studentSubgroup;
    private String day;
    private String startHour;
    private String endHour;
    private String classroomType;
    private String reservedClassroom;
    private String course;
}
