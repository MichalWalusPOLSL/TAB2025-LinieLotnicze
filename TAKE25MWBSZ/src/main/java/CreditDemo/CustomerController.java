package CreditDemo;

import com.project.demo.repository.CountryRepository;
import com.project.demo.repository.CustomerRepository;
import model.CountryEntity;
import model.CustomerEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class CustomerController {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CountryRepository countryRepository;

    // POST /api/customersAdd
    @PostMapping("/customersAdd")
    public CustomerEntity createCustomer(@RequestBody CustomerEntity customer) {
        Long countryId = customer.getNationality().getCountryId();

        Optional<CountryEntity> optionalCountry = countryRepository.findById(countryId);
        if (optionalCountry.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Kraj o ID " + countryId + " nie istnieje.");
        }

        customer.setNationality(optionalCountry.get());

        return customerRepository.save(customer);
    }

    // DELETE /api/customersDelete/{id}
    @DeleteMapping("/customersDelete/{id}")
    public String deleteCustomer(@PathVariable Long id) {
        Optional<CustomerEntity> customer = customerRepository.findById(id);
        if (customer.isPresent()) {
            customerRepository.deleteById(id);
            return "Usunięto klienta o ID: " + id;
        } else {
            return "Klient o ID " + id + " nie istnieje.";
        }
    }

    // GET /api/customers
    @GetMapping("/customers")
    public List<CustomerEntity> getAllCustomers() {
        return customerRepository.findAll();
    }

    // GET /api/customers/{id}
    @GetMapping("/customers/{id}")
    public CustomerEntity getCustomerById(@PathVariable Long id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Nie znaleziono klienta o ID: " + id));
    }
}
