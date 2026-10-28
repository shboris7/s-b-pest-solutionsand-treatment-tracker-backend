package sbpestsolution.rw.repository;

import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import sbpestsolution.rw.domain.Client;

public interface ClientRepository extends JpaRepository<Client, UUID> {

    List<Client> findByLastNameIgnoreCaseOrderByFirstNameAsc(String lastName);
}