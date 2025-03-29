package CreditDemo;

import model.CountryEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.project.demo.repository.CountryRepository;

import java.util.List;

@RestController
@RequestMapping("/api/countries")
public class CountryController {

    @Autowired
    private CountryRepository countryRepository;

    @PostMapping
    public CountryEntity createCountry(@RequestBody CountryEntity country) {
        return countryRepository.save(country);
    }

    @GetMapping
    public List<CountryEntity> getAllCountries() {
        return countryRepository.findAll();
    }
}
