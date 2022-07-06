package ace.ucv.ro.orientationandorganizationapp.repository;

import ace.ucv.ro.orientationandorganizationapp.entity.Activity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ActivityRepository extends JpaRepository<Activity, Long> {
    Optional<Activity> findById(Long id);

    Optional<List<Activity>> findByStudentGroupAndStudentSubgroup(String studentGroup, String studentSubgroup);

    Optional<List<Activity>> findByTeacherId(Long id);

    Optional<List<Activity>> findByCourseId(Long id);
}
