package ace.ucv.ro.orientationandorganizationapp.repository;

import ace.ucv.ro.orientationandorganizationapp.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {
    Optional<Teacher> findByFirstNameAndLastName(String firstName, String lastName);

    Optional<Teacher> findById(Long id);

    Optional<Teacher> findByEmail(String email);
}
