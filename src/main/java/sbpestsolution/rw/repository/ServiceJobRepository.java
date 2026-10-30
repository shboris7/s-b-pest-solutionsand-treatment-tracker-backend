package sbpestsolution.rw.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import sbpestsolution.rw.domain.JobStatus;
import sbpestsolution.rw.domain.ServiceJob;

public interface ServiceJobRepository extends JpaRepository<ServiceJob, UUID> {

    List<ServiceJob> findByStatusOrderByJobDateAsc(JobStatus status);

    List<ServiceJob> findByJobDateBetweenOrderByJobDateAsc(LocalDate start, LocalDate end);

    boolean existsByTechnicianIdAndJobDateAndStatusNot(UUID technicianId, LocalDate jobDate, JobStatus status);

    long countByJobDate(LocalDate jobDate);

    long countByStatus(JobStatus status);

    @Query("SELECT j.status, COUNT(j) FROM ServiceJob j GROUP BY j.status")
    List<Object[]> getStatusCounts();

    @Modifying
    @Query("UPDATE ServiceJob j SET j.status = :newStatus "
            + "WHERE j.technician.id = :technicianId AND j.jobDate = :day AND j.status <> :treated")
    int cancelDay(@Param("technicianId") UUID technicianId, @Param("day") LocalDate day,
            @Param("newStatus") JobStatus newStatus, @Param("treated") JobStatus treated);
}