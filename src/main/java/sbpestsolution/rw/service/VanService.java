package sbpestsolution.rw.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sbpestsolution.rw.domain.Van;
import sbpestsolution.rw.repository.VanRepository;

@Service
public class VanService {

    @Autowired
    private VanRepository vanRepository;

    public String save(Van van) {
        vanRepository.save(van);
        return "Van saved successfully";
    }

    public List<Van> getAll() {
        return vanRepository.findAll();
    }

    public Optional<Van> getById(UUID id) {
        return vanRepository.findById(id);
    }

    public String update(UUID id, Van van) {
        Optional<Van> existing = vanRepository.findById(id);
        if (existing.isEmpty()) {
            return "The van with that id does not exist";
        }
        Van found = existing.get();
        found.setVanNumber(van.getVanNumber());
        found.setName(van.getName());
        vanRepository.save(found);
        return "Van updated successfully";
    }

    public String delete(UUID id) {
        if (!vanRepository.existsById(id)) {
            return "The van with that id does not exist";
        }
        vanRepository.deleteById(id);
        return "Van deleted successfully";
    }

    public List<Van> findFree() {
        return vanRepository.getFreeVans();
    }
}