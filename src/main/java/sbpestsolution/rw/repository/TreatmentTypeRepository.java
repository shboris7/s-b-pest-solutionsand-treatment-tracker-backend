package sbpestsolution.rw.repository;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import sbpestsolution.rw.domain.TreatmentType;

public interface TreatmentTypeRepository extends JpaRepository<TreatmentType, UUID> {
}