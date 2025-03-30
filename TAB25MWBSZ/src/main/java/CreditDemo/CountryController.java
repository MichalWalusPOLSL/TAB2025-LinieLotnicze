package CreditDemo;

import com.project.demo.repository.CountryRepository;
import model.CountryEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class CountryController {

    @Autowired
    private CountryRepository countryRepository;

    // Dodawanie kraju - POST /api/countriesAdd
    @PostMapping("/countriesAdd")
    public CountryEntity createCountry(@RequestBody CountryEntity country) {
        return countryRepository.save(country);
    }

    // Usuwanie kraju po ID - DELETE /api/countriesDelete/{id}
    @DeleteMapping("/countriesDelete/{id}")
    public String deleteCountry(@PathVariable Long id) {
        Optional<CountryEntity> optionalCountry = countryRepository.findById(id);
        if (optionalCountry.isPresent()) {
            countryRepository.deleteById(id);
            return "Usunięto kraj o ID: " + id;
        } else {
            return "Kraj o ID " + id + " nie istnieje.";
        }
    }

 // Lista wszystkich krajów - GET /api/countries
    @GetMapping("/countries")
    public List<CountryEntity> getAllCountries() {
        return countryRepository.findAll();
    }

    // Wyświetlanie pojedynczego kraju - GET /api/countries/{id}
    @GetMapping("/countries/{id}")
    public CountryEntity getCountryById(@PathVariable Long id) {
        return countryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Nie znaleziono kraju o ID: " + id));
    }
}
