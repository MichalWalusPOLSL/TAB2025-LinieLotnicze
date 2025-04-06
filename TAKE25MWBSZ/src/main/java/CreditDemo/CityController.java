package CreditDemo;

import com.project.demo.repository.CityRepository;
import com.project.demo.repository.CountryRepository;
import model.CityEntity;
import model.CountryEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class CityController {

    @Autowired
    private CityRepository cityRepository;
    
    @Autowired
    private CountryRepository countryRepository;

    @PostMapping("/citiesAdd")
    public CityEntity createCity(@RequestBody CityEntity city) {
        // Sprawdź, czy kraj istnieje
        Long countryId = city.getLocationCountry().getCountryId();

        Optional<CountryEntity> optionalCountry = countryRepository.findById(countryId);
        if (optionalCountry.isEmpty()) {
            throw new RuntimeException("Kraj o ID " + countryId + " nie istnieje.");
        }

        // Przypisz pełny obiekt CountryEntity
        city.setLocationCountry(optionalCountry.get());

        return cityRepository.save(city);
    }

    // Usuwanie miasta - DELETE /api/citiesDelete/{id}
    @DeleteMapping("/citiesDelete/{id}")
    public String deleteCity(@PathVariable Long id) {
        Optional<CityEntity> city = cityRepository.findById(id);
        if (city.isPresent()) {
            cityRepository.deleteById(id);
            return "Usunięto miasto o ID: " + id;
        } else {
            return "Miasto o ID " + id + " nie istnieje.";
        }
    }

    // Wyświetlanie wszystkich miast - GET /api/cities
    @GetMapping("/cities")
    public List<CityEntity> getAllCities() {
        return cityRepository.findAll();
    }

    // Wyświetlanie pojedynczego miasta - GET /api/cities/{id}
    @GetMapping("/cities/{id}")
    public CityEntity getCityById(@PathVariable Long id) {
        return cityRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Nie znaleziono miasta o ID: " + id));
    }
}
