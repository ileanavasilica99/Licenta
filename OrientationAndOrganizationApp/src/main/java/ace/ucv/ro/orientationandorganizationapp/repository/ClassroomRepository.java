package ace.ucv.ro.orientationandorganizationapp.repository;

import ace.ucv.ro.orientationandorganizationapp.entity.Classroom;
import ace.ucv.ro.orientationandorganizationapp.entity.enums.ClassroomType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClassroomRepository extends JpaRepository<Classroom, Long> {
    Optional<Classroom> findByName(String name);

    Optional<Classroom> findById(Long id);

    Optional<List<Classroom>> findByType(ClassroomType type);
}
