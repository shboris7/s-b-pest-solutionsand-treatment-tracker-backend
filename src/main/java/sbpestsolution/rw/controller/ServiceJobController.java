package sbpestsolution.rw.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sbpestsolution.rw.domain.JobStatus;
import sbpestsolution.rw.domain.ServiceJob;
import sbpestsolution.rw.service.ServiceJobService;

@RestController
@RequestMapping("/api/service-jobs")
public class ServiceJobController {

    @Autowired
    private ServiceJobService serviceJobService;

    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody ServiceJob serviceJob) {
        return new ResponseEntity<>(serviceJobService.save(serviceJob), HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<ServiceJob>> getAll() {
        return new ResponseEntity<>(serviceJobService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/by-status")
    public ResponseEntity<List<ServiceJob>> byStatus(@RequestParam JobStatus status) {
        return new ResponseEntity<>(serviceJobService.findByStatus(status), HttpStatus.OK);
    }

    @GetMapping("/between")
    public ResponseEntity<List<ServiceJob>> between(@RequestParam String start, @RequestParam String end) {
        LocalDate startDate = LocalDate.parse(start);
        LocalDate endDate = LocalDate.parse(end);
        return new ResponseEntity<>(serviceJobService.findBetween(startDate, endDate), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable UUID id) {
        Optional<ServiceJob> found = serviceJobService.getById(id);
        if (found.isPresent()) {
            return new ResponseEntity<>(found.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>("The service job with that id does not exist", HttpStatus.NOT_FOUND);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> update(@PathVariable UUID id, @RequestBody ServiceJob serviceJob) {
        String message = serviceJobService.update(id, serviceJob);
        if (message.equals("The service job with that id does not exist")) {
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable UUID id) {
        String message = serviceJobService.delete(id);
        if (message.equals("The service job with that id does not exist")) {
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}