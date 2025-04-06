package CreditDemo;

import com.project.demo.repository.ManufacturerRepository;
import com.project.demo.repository.PlaneModelRepository;
import model.ManufacturerEntity;
import model.PlaneModelEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class PlaneModelController {

    @Autowired
    private PlaneModelRepository planeModelRepository;

    @Autowired
    private ManufacturerRepository manufacturerRepository;

    // POST /api/planeModelsAdd
    @PostMapping("/planeModelsAdd")
    public PlaneModelEntity createPlaneModel(@RequestBody PlaneModelEntity planeModel) {
        Long manufacturerId = planeModel.getManufacturer().getManufacturerId();

        Optional<ManufacturerEntity> optionalManufacturer = manufacturerRepository.findById(manufacturerId);
        if (optionalManufacturer.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Producent o ID " + manufacturerId + " nie istnieje.");
        }

        planeModel.setManufacturer(optionalManufacturer.get());

        return planeModelRepository.save(planeModel);
    }

    // DELETE /api/planeModelsDelete/{id}
    @DeleteMapping("/planeModelsDelete/{id}")
    public String deletePlaneModel(@PathVariable Long id) {
        Optional<PlaneModelEntity> planeModel = planeModelRepository.findById(id);
        if (planeModel.isPresent()) {
            planeModelRepository.deleteById(id);
            return "Usunięto model samolotu o ID: " + id;
        } else {
            return "Model samolotu o ID " + id + " nie istnieje.";
        }
    }

    // GET /api/planeModels
    @GetMapping("/planeModels")
    public List<PlaneModelEntity> getAllPlaneModels() {
        return planeModelRepository.findAll();
    }

    // GET /api/planeModels/{id}
    @GetMapping("/planeModels/{id}")
    public PlaneModelEntity getPlaneModelById(@PathVariable Long id) {
        return planeModelRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Nie znaleziono modelu samolotu o ID: " + id));
    }
}
