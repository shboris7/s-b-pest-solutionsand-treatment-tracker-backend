package sbpestsolution.rw.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sbpestsolution.rw.domain.JobStatus;
import sbpestsolution.rw.domain.ServiceJob;
import sbpestsolution.rw.repository.ServiceJobRepository;

@Service
public class ServiceJobService {

    @Autowired
    private ServiceJobRepository serviceJobRepository;

    public String save(ServiceJob serviceJob) {
        serviceJobRepository.save(serviceJob);
        return "Service job saved successfully";
    }

    public List<ServiceJob> getAll() {
        return serviceJobRepository.findAll();
    }

    public Optional<ServiceJob> getById(UUID id) {
        return serviceJobRepository.findById(id);
    }

    public String update(UUID id, ServiceJob serviceJob) {
        Optional<ServiceJob> existing = serviceJobRepository.findById(id);
        if (existing.isEmpty()) {
            return "The service job with that id does not exist";
        }
        ServiceJob found = existing.get();
        found.setJobDate(serviceJob.getJobDate());
        found.setPestType(serviceJob.getPestType());
        found.setSiteAddress(serviceJob.getSiteAddress());
        if (serviceJob.getClient() != null) {
            found.setClient(serviceJob.getClient());
        }
        if (serviceJob.getTechnician() != null) {
            found.setTechnician(serviceJob.getTechnician());
        }
        serviceJobRepository.save(found);
        return "Service job updated successfully";
    }

    public String delete(UUID id) {
        if (!serviceJobRepository.existsById(id)) {
            return "The service job with that id does not exist";
        }
        serviceJobRepository.deleteById(id);
        return "Service job deleted successfully";
    }

    public List<ServiceJob> findByStatus(JobStatus status) {
        return serviceJobRepository.findByStatusOrderByJobDateAsc(status);
    }

    public List<ServiceJob> findBetween(LocalDate start, LocalDate end) {
        return serviceJobRepository.findByJobDateBetweenOrderByJobDateAsc(start, end);
    }
}