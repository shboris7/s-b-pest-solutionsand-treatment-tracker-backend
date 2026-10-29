package sbpestsolution.rw.controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sbpestsolution.rw.domain.TreatmentType;
import sbpestsolution.rw.service.TreatmentTypeService;

@RestController
@RequestMapping("/api/treatment-types")
public class TreatmentTypeController {

    @Autowired
    private TreatmentTypeService treatmentTypeService;

    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody TreatmentType treatmentType) {
        return new ResponseEntity<>(treatmentTypeService.save(treatmentType), HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<TreatmentType>> getAll() {
        return new ResponseEntity<>(treatmentTypeService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/unused")
    public ResponseEntity<List<TreatmentType>> unused() {
        return new ResponseEntity<>(treatmentTypeService.findUnused(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable UUID id) {
        Optional<TreatmentType> found = treatmentTypeService.getById(id);
        if (found.isPresent()) {
            return new ResponseEntity<>(found.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>("The treatment type with that id does not exist", HttpStatus.NOT_FOUND);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> update(@PathVariable UUID id, @RequestBody TreatmentType treatmentType) {
        String message = treatmentTypeService.update(id, treatmentType);
        if (message.equals("The treatment type with that id does not exist")) {
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable UUID id) {
        String message = treatmentTypeService.delete(id);
        if (message.equals("The treatment type with that id does not exist")) {
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}