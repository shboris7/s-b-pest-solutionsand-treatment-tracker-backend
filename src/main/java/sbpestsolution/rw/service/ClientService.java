package sbpestsolution.rw.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sbpestsolution.rw.domain.Client;
import sbpestsolution.rw.repository.ClientRepository;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    public String save(Client client) {
        clientRepository.save(client);
        return "Client saved successfully";
    }

    public List<Client> getAll() {
        return clientRepository.findAll();
    }

    public Optional<Client> getById(UUID id) {
        return clientRepository.findById(id);
    }

    public String update(UUID id, Client client) {
        Optional<Client> existing = clientRepository.findById(id);
        if (existing.isEmpty()) {
            return "The client with that id does not exist";
        }
        Client found = existing.get();
        found.setFirstName(client.getFirstName());
        found.setLastName(client.getLastName());
        found.setPhone(client.getPhone());
        found.setAddress(client.getAddress());
        clientRepository.save(found);
        return "Client updated successfully";
    }

    public String delete(UUID id) {
        if (!clientRepository.existsById(id)) {
            return "The client with that id does not exist";
        }
        clientRepository.deleteById(id);
        return "Client deleted successfully";
    }

    public List<Client> findByLastName(String lastName) {
        return clientRepository.findByLastNameIgnoreCaseOrderByFirstNameAsc(lastName);
    }
}