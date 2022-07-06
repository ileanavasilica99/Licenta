package ace.ucv.ro.orientationandorganizationapp.repository;

import ace.ucv.ro.orientationandorganizationapp.entity.StudentsEvidence;
import ace.ucv.ro.orientationandorganizationapp.entity.enums.Specialization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentEvidenceRepository extends JpaRepository<StudentsEvidence, Long> {

    List<StudentsEvidence> findAllByStudentGroupAndStudentSubgroup(String group, String subgroup);

    List<StudentsEvidence> findAllByStudentGroup(String group);

    List<StudentsEvidence> findAllBySpecialization(Specialization specialization);
}
