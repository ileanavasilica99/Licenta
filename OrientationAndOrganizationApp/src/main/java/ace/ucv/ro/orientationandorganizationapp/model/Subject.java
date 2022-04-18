package ace.ucv.ro.orientationandorganizationapp.model;

import ace.ucv.ro.orientationandorganizationapp.model.enums.ExaminationType;

import javax.persistence.*;

@Entity(name = "subject")
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "subject_name")
    private String name;

    @Enumerated(EnumType.STRING)
    private ExaminationType examinationType;
}
