package sbpestsolution.rw.repository;

import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import sbpestsolution.rw.domain.Technician;
import sbpestsolution.rw.domain.Van;

public interface TechnicianRepository extends JpaRepository<Technician, UUID> {

    @Query("SELECT DISTINCT t FROM Technician t JOIN t.treatmentTypes tt "
            + "WHERE LOWER(tt.name) LIKE LOWER(CONCAT('%', :name, '%'))")
    List<Technician> searchByTreatmentTypeName(@Param("name") String name);

    boolean existsByVan(Van van);
}