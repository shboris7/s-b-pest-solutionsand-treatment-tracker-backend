package sbpestsolution.rw.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import sbpestsolution.rw.domain.JobStatus;
import sbpestsolution.rw.domain.ServiceJob;

public interface ServiceJobRepository extends JpaRepository<ServiceJob, UUID> {

    List<ServiceJob> findByStatusOrderByJobDateAsc(JobStatus status);

    List<ServiceJob> findByJobDateBetweenOrderByJobDateAsc(LocalDate start, LocalDate end);
}