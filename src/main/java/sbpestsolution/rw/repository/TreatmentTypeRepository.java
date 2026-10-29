package sbpestsolution.rw.repository;

import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import sbpestsolution.rw.domain.TreatmentType;

public interface TreatmentTypeRepository extends JpaRepository<TreatmentType, UUID> {

    @Query("SELECT tt FROM TreatmentType tt WHERE tt.technicians IS EMPTY")
    List<TreatmentType> getUnusedTreatmentTypes();
}