package sbpestsolution.rw.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sbpestsolution.rw.domain.TreatmentType;
import sbpestsolution.rw.repository.TreatmentTypeRepository;

@Service
public class TreatmentTypeService {

    @Autowired
    private TreatmentTypeRepository treatmentTypeRepository;

    public String save(TreatmentType treatmentType) {
        treatmentTypeRepository.save(treatmentType);
        return "Treatment type saved successfully";
    }

    public List<TreatmentType> getAll() {
        return treatmentTypeRepository.findAll();
    }

    public Optional<TreatmentType> getById(UUID id) {
        return treatmentTypeRepository.findById(id);
    }

    public String update(UUID id, TreatmentType treatmentType) {
        Optional<TreatmentType> existing = treatmentTypeRepository.findById(id);
        if (existing.isEmpty()) {
            return "The treatment type with that id does not exist";
        }
        TreatmentType found = existing.get();
        found.setName(treatmentType.getName());
        treatmentTypeRepository.save(found);
        return "Treatment type updated successfully";
    }

    public String delete(UUID id) {
        if (!treatmentTypeRepository.existsById(id)) {
            return "The treatment type with that id does not exist";
        }
        treatmentTypeRepository.deleteById(id);
        return "Treatment type deleted successfully";
    }
}