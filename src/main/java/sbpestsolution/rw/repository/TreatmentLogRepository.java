package sbpestsolution.rw.repository;

import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import sbpestsolution.rw.domain.ServiceJob;
import sbpestsolution.rw.domain.TreatmentLog;

public interface TreatmentLogRepository extends JpaRepository<TreatmentLog, UUID> {

    @Query("SELECT d FROM TreatmentLog d WHERE d.serviceJob.client.phone = :phone "
            + "ORDER BY d.appliedDate DESC")
    List<TreatmentLog> getLogsByClientPhone(@Param("phone") String phone);

    boolean existsByServiceJob(ServiceJob serviceJob);
}