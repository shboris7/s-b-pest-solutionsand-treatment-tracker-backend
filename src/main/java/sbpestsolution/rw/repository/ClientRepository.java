package sbpestsolution.rw.repository;

import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import sbpestsolution.rw.domain.Client;

public interface ClientRepository extends JpaRepository<Client, UUID> {

    List<Client> findByLastNameIgnoreCaseOrderByFirstNameAsc(String lastName);

    @Query("SELECT DISTINCT j.client FROM ServiceJob j WHERE j.technician.id = :technicianId")
    List<Client> getClientsOfTechnician(@Param("technicianId") UUID technicianId);
}