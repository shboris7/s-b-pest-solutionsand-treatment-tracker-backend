package sbpestsolution.rw.controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sbpestsolution.rw.domain.Van;
import sbpestsolution.rw.service.VanService;

@RestController
@RequestMapping("/api/vans")
public class VanController {

    @Autowired
    private VanService vanService;

    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody Van van) {
        return new ResponseEntity<>(vanService.save(van), HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Van>> getAll() {
        return new ResponseEntity<>(vanService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable UUID id) {
        Optional<Van> found = vanService.getById(id);
        if (found.isPresent()) {
            return new ResponseEntity<>(found.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>("The van with that id does not exist", HttpStatus.NOT_FOUND);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> update(@PathVariable UUID id, @RequestBody Van van) {
        String message = vanService.update(id, van);
        if (message.equals("The van with that id does not exist")) {
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable UUID id) {
        String message = vanService.delete(id);
        if (message.equals("The van with that id does not exist")) {
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}