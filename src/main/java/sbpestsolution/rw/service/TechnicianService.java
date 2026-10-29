package sbpestsolution.rw.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sbpestsolution.rw.domain.Technician;
import sbpestsolution.rw.domain.TreatmentType;
import sbpestsolution.rw.domain.Van;
import sbpestsolution.rw.repository.TechnicianRepository;
import sbpestsolution.rw.repository.TreatmentTypeRepository;
import sbpestsolution.rw.repository.VanRepository;

@Service
public class TechnicianService {

    @Autowired
    private TechnicianRepository technicianRepository;

    @Autowired
    private TreatmentTypeRepository treatmentTypeRepository;

    @Autowired
    private VanRepository vanRepository;

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

    public List<Technician> findByTreatmentType(String name) {
        return technicianRepository.searchByTreatmentTypeName(name);
    }

    @Transactional
    public String addTreatmentType(UUID technicianId, UUID treatmentTypeId) {
        Optional<Technician> technician = technicianRepository.findById(technicianId);
        if (technician.isEmpty()) {
            return "The technician with that id does not exist";
        }
        Optional<TreatmentType> treatmentType = treatmentTypeRepository.findById(treatmentTypeId);
        if (treatmentType.isEmpty()) {
            return "That treatment type does not exist";
        }
        Technician foundTechnician = technician.get();
        TreatmentType foundType = treatmentType.get();
        if (foundTechnician.getTreatmentTypes().contains(foundType)) {
            return "The technician already has that treatment type";
        }
        foundTechnician.getTreatmentTypes().add(foundType);
        technicianRepository.save(foundTechnician);
        return "Treatment type added to the technician";
    }

    public String changeVan(UUID technicianId, int vanNumber) {
        Optional<Technician> technician = technicianRepository.findById(technicianId);
        if (technician.isEmpty()) {
            return "The technician with that id does not exist";
        }
        Optional<Van> van = vanRepository.findByVanNumber(vanNumber);
        if (van.isEmpty()) {
            return "The van with that vanNumber does not exist";
        }
        Technician foundTechnician = technician.get();
        Van foundVan = van.get();
        if (foundTechnician.getVan() != null && foundTechnician.getVan().getId().equals(foundVan.getId())) {
            return "Van changed successfully";
        }
        if (technicianRepository.existsByVan(foundVan)) {
            return "That van is taken by another technician";
        }
        foundTechnician.setVan(foundVan);
        technicianRepository.save(foundTechnician);
        return "Van changed successfully";
    }
}