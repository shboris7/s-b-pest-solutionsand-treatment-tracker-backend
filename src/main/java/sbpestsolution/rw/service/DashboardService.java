package sbpestsolution.rw.service;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sbpestsolution.rw.domain.JobStatus;
import sbpestsolution.rw.repository.ClientRepository;
import sbpestsolution.rw.repository.ServiceJobRepository;
import sbpestsolution.rw.repository.TechnicianRepository;
import sbpestsolution.rw.repository.TreatmentLogRepository;
import sbpestsolution.rw.repository.VanRepository;

@Service
public class DashboardService {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private TechnicianRepository technicianRepository;

    @Autowired
    private VanRepository vanRepository;

    @Autowired
    private ServiceJobRepository serviceJobRepository;

    @Autowired
    private TreatmentLogRepository treatmentLogRepository;

    public Map<String, Long> getSummary() {
        Map<String, Long> summary = new LinkedHashMap<>();
        summary.put("clients", clientRepository.count());
        summary.put("technicians", technicianRepository.count());
        summary.put("freeVans", (long) vanRepository.getFreeVans().size());
        summary.put("jobsToday", serviceJobRepository.countByJobDate(LocalDate.now()));
        summary.put("requested", serviceJobRepository.countByStatus(JobStatus.REQUESTED));
        summary.put("treatmentLogs", treatmentLogRepository.count());
        return summary;
    }
}