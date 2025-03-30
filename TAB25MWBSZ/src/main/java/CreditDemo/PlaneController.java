package CreditDemo;

import com.project.demo.repository.PlaneModelRepository;
import com.project.demo.repository.PlaneRepository;
import model.PlaneEntity;
import model.PlaneModelEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class PlaneController {

    @Autowired
    private PlaneRepository planeRepository;

    @Autowired
    private PlaneModelRepository planeModelRepository;

    // POST /api/planesAdd
    @PostMapping("/planesAdd")
    public PlaneEntity createPlane(@RequestBody PlaneEntity plane) {
        Long modelId = plane.getModel().getPlaneModelId();

        Optional<PlaneModelEntity> optionalModel = planeModelRepository.findById(modelId);
        if (optionalModel.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Model samolotu o ID " + modelId + " nie istnieje.");
        }

        plane.setModel(optionalModel.get());

        return planeRepository.save(plane);
    }

    // DELETE /api/planesDelete/{id}
    @DeleteMapping("/planesDelete/{id}")
    public String deletePlane(@PathVariable Long id) {
        Optional<PlaneEntity> plane = planeRepository.findById(id);
        if (plane.isPresent()) {
            planeRepository.deleteById(id);
            return "Usunięto samolot o ID: " + id;
        } else {
            return "Samolot o ID " + id + " nie istnieje.";
        }
    }

    // GET /api/planes
    @GetMapping("/planes")
    public List<PlaneEntity> getAllPlanes() {
        return planeRepository.findAll();
    }

    // GET /api/planes/{id}
    @GetMapping("/planes/{id}")
    public PlaneEntity getPlaneById(@PathVariable Long id) {
        return planeRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Nie znaleziono samolotu o ID: " + id));
    }
}
