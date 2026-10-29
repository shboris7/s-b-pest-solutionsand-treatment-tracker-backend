package sbpestsolution.rw.controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sbpestsolution.rw.domain.Technician;
import sbpestsolution.rw.service.TechnicianService;

@RestController
@RequestMapping("/api/technicians")
public class TechnicianController {

    @Autowired
    private TechnicianService technicianService;

    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody Technician technician) {
        return new ResponseEntity<>(technicianService.save(technician), HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Technician>> getAll() {
        return new ResponseEntity<>(technicianService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/by-treatment-type")
    public ResponseEntity<List<Technician>> byTreatmentType(@RequestParam String name) {
        return new ResponseEntity<>(technicianService.findByTreatmentType(name), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable UUID id) {
        Optional<Technician> found = technicianService.getById(id);
        if (found.isPresent()) {
            return new ResponseEntity<>(found.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>("The technician with that id does not exist", HttpStatus.NOT_FOUND);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> update(@PathVariable UUID id, @RequestBody Technician technician) {
        String message = technicianService.update(id, technician);
        if (message.equals("The technician with that id does not exist")) {
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @PutMapping("/{technicianId}/treatment-types/{treatmentTypeId}")
    public ResponseEntity<String> addTreatmentType(@PathVariable UUID technicianId,
            @PathVariable UUID treatmentTypeId) {
        String message = technicianService.addTreatmentType(technicianId, treatmentTypeId);
        if (message.equals("The technician with that id does not exist")
                || message.equals("That treatment type does not exist")) {
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
        if (message.equals("The technician already has that treatment type")) {
            return new ResponseEntity<>(message, HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @PatchMapping("/{technicianId}/van")
    public ResponseEntity<String> changeVan(@PathVariable UUID technicianId, @RequestParam int vanNumber) {
        String message = technicianService.changeVan(technicianId, vanNumber);
        if (message.equals("The technician with that id does not exist")
                || message.equals("The van with that vanNumber does not exist")) {
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
        if (message.equals("That van is taken by another technician")) {
            return new ResponseEntity<>(message, HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable UUID id) {
        String message = technicianService.delete(id);
        if (message.equals("The technician with that id does not exist")) {
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}