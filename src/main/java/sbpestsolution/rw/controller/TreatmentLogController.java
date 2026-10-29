package sbpestsolution.rw.controller;

import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sbpestsolution.rw.domain.TreatmentLog;
import sbpestsolution.rw.service.TreatmentLogService;

@RestController
@RequestMapping("/api/treatment-logs")
public class TreatmentLogController {

    @Autowired
    private TreatmentLogService treatmentLogService;

    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestParam UUID serviceJobId,
            @RequestBody TreatmentLog treatmentLog) {
        String message = treatmentLogService.save(serviceJobId, treatmentLog);
        if (message.equals("The service job with that id does not exist")) {
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
        if (message.equals("Treatment logs are only for TREATED service jobs")) {
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }

    @GetMapping("/client/{phone}")
    public ResponseEntity<List<TreatmentLog>> byClient(@PathVariable String phone) {
        return new ResponseEntity<>(treatmentLogService.findByClientPhone(phone), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable UUID id) {
        String message = treatmentLogService.delete(id);
        if (message.equals("The treatment log with that id does not exist")) {
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}