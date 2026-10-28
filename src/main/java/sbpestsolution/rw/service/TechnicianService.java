package sbpestsolution.rw.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sbpestsolution.rw.domain.Technician;
import sbpestsolution.rw.repository.TechnicianRepository;

@Service
public class TechnicianService {

    @Autowired
    private TechnicianRepository technicianRepository;

    public String save(Technician technician) {
        technicianRepository.save(technician);
        return "Technician saved successfully";
    }

    public List<Technician> getAll() {
        return technicianRepository.findAll();
    }

    public Optional<Technician> getById(UUID id) {
        return technicianRepository.findById(id);
    }

    public String update(UUID id, Technician technician) {
        Optional<Technician> existing = technicianRepository.findById(id);
        if (existing.isEmpty()) {
            return "The technician with that id does not exist";
        }
        Technician found = existing.get();
        found.setFirstName(technician.getFirstName());
        found.setLastName(technician.getLastName());
        found.setCertificationLevel(technician.getCertificationLevel());
        if (technician.getVan() != null) {
            found.setVan(technician.getVan());
        }
        technicianRepository.save(found);
        return "Technician updated successfully";
    }

    public String delete(UUID id) {
        if (!technicianRepository.existsById(id)) {
            return "The technician with that id does not exist";
        }
        technicianRepository.deleteById(id);
        return "Technician deleted successfully";
    }
}