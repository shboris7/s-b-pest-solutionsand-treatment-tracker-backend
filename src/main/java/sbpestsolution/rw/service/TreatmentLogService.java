package sbpestsolution.rw.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sbpestsolution.rw.domain.JobStatus;
import sbpestsolution.rw.domain.ServiceJob;
import sbpestsolution.rw.domain.TreatmentLog;
import sbpestsolution.rw.repository.ServiceJobRepository;
import sbpestsolution.rw.repository.TreatmentLogRepository;

@Service
public class TreatmentLogService {

    @Autowired
    private TreatmentLogRepository treatmentLogRepository;

    @Autowired
    private ServiceJobRepository serviceJobRepository;

    public String save(UUID serviceJobId, TreatmentLog treatmentLog) {
        Optional<ServiceJob> serviceJob = serviceJobRepository.findById(serviceJobId);
        if (serviceJob.isEmpty()) {
            return "The service job with that id does not exist";
        }
        if (serviceJob.get().getStatus() != JobStatus.TREATED) {
            return "Treatment logs are only for TREATED service jobs";
        }
        treatmentLog.setAppliedDate(LocalDate.now());
        treatmentLog.setServiceJob(serviceJob.get());
        treatmentLogRepository.save(treatmentLog);
        return "Treatment log saved successfully";
    }

    public List<TreatmentLog> findByClientPhone(String phone) {
        return treatmentLogRepository.getLogsByClientPhone(phone);
    }

    public String delete(UUID id) {
        if (!treatmentLogRepository.existsById(id)) {
            return "The treatment log with that id does not exist";
        }
        treatmentLogRepository.deleteById(id);
        return "Treatment log deleted successfully";
    }
}