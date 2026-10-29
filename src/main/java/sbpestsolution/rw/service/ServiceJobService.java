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
        if (serviceJob.getJobDate().isBefore(LocalDate.now())) {
            return "Service job date cannot be in the past";
        }
        if (serviceJob.getTechnician() != null
                && serviceJobRepository.existsByTechnicianIdAndJobDateAndStatusNot(
                        serviceJob.getTechnician().getId(), serviceJob.getJobDate(), JobStatus.CALLED_OFF)) {
            return "Technician is already dispatched on that date";
        }
        serviceJob.setStatus(JobStatus.REQUESTED);
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

    public String changeStatus(UUID id, JobStatus newStatus) {
        Optional<ServiceJob> existing = serviceJobRepository.findById(id);
        if (existing.isEmpty()) {
            return "The service job with that id does not exist";
        }
        ServiceJob job = existing.get();
        JobStatus current = job.getStatus();

        boolean allowed = false;
        if (current == JobStatus.REQUESTED
                && (newStatus == JobStatus.DISPATCHED || newStatus == JobStatus.CALLED_OFF)) {
            allowed = true;
        }
        if (current == JobStatus.DISPATCHED
                && (newStatus == JobStatus.TREATED || newStatus == JobStatus.CALLED_OFF)) {
            allowed = true;
        }
        if (!allowed) {
            return "Cannot change status from " + current + " to " + newStatus;
        }

        job.setStatus(newStatus);
        serviceJobRepository.save(job);
        return "Service job status updated successfully";
    }
}