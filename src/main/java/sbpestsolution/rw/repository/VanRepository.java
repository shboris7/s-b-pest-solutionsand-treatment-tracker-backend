package sbpestsolution.rw.repository;

import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import sbpestsolution.rw.domain.Van;

public interface VanRepository extends JpaRepository<Van, UUID> {

    @Query("SELECT v FROM Van v WHERE NOT EXISTS "
            + "(SELECT t FROM Technician t WHERE t.van = v) ORDER BY v.vanNumber")
    List<Van> getFreeVans();
}