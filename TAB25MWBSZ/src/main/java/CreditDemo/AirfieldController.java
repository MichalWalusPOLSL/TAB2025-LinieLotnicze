package CreditDemo;

import com.project.demo.repository.AirfieldRepository;
import com.project.demo.repository.CityRepository;
import model.AirfieldEntity;
import model.CityEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class AirfieldController {

    @Autowired
    private AirfieldRepository airfieldRepository;

    @Autowired
    private CityRepository cityRepository;

    // POST /api/airfieldsAdd
    @PostMapping("/airfieldsAdd")
    public AirfieldEntity createAirfield(@RequestBody AirfieldEntity airfield) {
        Long cityId = airfield.getLocationCity().getCityId();

        Optional<CityEntity> optionalCity = cityRepository.findById(cityId);
        if (optionalCity.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Miasto o ID " + cityId + " nie istnieje.");
        }

        airfield.setLocationCity(optionalCity.get());

        return airfieldRepository.save(airfield);
    }

    // DELETE /api/airfieldsDelete/{id}
    @DeleteMapping("/airfieldsDelete/{id}")
    public String deleteAirfield(@PathVariable Long id) {
        Optional<AirfieldEntity> airfield = airfieldRepository.findById(id);
        if (airfield.isPresent()) {
            airfieldRepository.deleteById(id);
            return "Usunięto lotnisko o ID: " + id;
        } else {
            return "Lotnisko o ID " + id + " nie istnieje.";
        }
    }

    // GET /api/airfields
    @GetMapping("/airfields")
    public List<AirfieldEntity> getAllAirfields() {
        return airfieldRepository.findAll();
    }

    // GET /api/airfields/{id}
    @GetMapping("/airfields/{id}")
    public AirfieldEntity getAirfieldById(@PathVariable Long id) {
        return airfieldRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Nie znaleziono lotniska o ID: " + id));
    }
}
