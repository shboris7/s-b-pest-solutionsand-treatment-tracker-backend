package sbpestsolution.rw.repository;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import sbpestsolution.rw.domain.Technician;

public interface TechnicianRepository extends JpaRepository<Technician, UUID> {
}