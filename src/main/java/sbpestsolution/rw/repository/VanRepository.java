package sbpestsolution.rw.repository;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import sbpestsolution.rw.domain.Van;

public interface VanRepository extends JpaRepository<Van, UUID> {
}