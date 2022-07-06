package ace.ucv.ro.orientationandorganizationapp.repository;

import ace.ucv.ro.orientationandorganizationapp.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    Optional<List<Schedule>> findAllByClassroomId (Long id);
}
