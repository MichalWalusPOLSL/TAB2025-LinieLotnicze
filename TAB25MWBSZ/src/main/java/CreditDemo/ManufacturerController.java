package CreditDemo;

import com.project.demo.repository.ManufacturerRepository;
import model.ManufacturerEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class ManufacturerController {

    @Autowired
    private ManufacturerRepository manufacturerRepository;

    // POST x
    @PostMapping("/manufacturersAdd")
    public ManufacturerEntity createManufacturer(@RequestBody ManufacturerEntity manufacturer) {
        return manufacturerRepository.save(manufacturer);
    }

    // DELETE /api/manufacturersDelete/{id}
    @DeleteMapping("/manufacturersDelete/{id}")
    public String deleteManufacturer(@PathVariable Long id) {
        Optional<ManufacturerEntity> manufacturer = manufacturerRepository.findById(id);
        if (manufacturer.isPresent()) {
            manufacturerRepository.deleteById(id);
            return "Usunięto producenta o ID: " + id;
        } else {
            return "Producent o ID " + id + " nie istnieje.";
        }
    }

    // GET /api/manufacturers
    @GetMapping("/manufacturers")
    public List<ManufacturerEntity> getAllManufacturers() {
        return manufacturerRepository.findAll();
    }

    // GET /api/manufacturers/{id}
    @GetMapping("/manufacturers/{id}")
    public ManufacturerEntity getManufacturerById(@PathVariable Long id) {
        return manufacturerRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Nie znaleziono producenta o ID: " + id));
    }
}
