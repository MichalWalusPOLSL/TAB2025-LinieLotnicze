package CreditDemo;

import com.project.demo.repository.CustomerFlightRepository;
import com.project.demo.repository.CustomerRepository;
import com.project.demo.repository.FlightRepository;
import model.CustomerEntity;
import model.CustomerFlightEntity;
import model.FlightEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CustomerFlightController {

    @Autowired
    private CustomerFlightRepository customerFlightRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private FlightRepository flightRepository;

    // POST /api/customerFlightsAdd
    @PostMapping("/customerFlightsAdd")
    public CustomerFlightEntity createCustomerFlight(@RequestBody CustomerFlightEntity customerFlight) {
        Long customerId = customerFlight.getCustomer().getCustomerId();
        Long flightId = customerFlight.getFlight().getFlightId();

        CustomerEntity customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Klient o ID " + customerId + " nie istnieje."));

        FlightEntity flight = flightRepository.findById(flightId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Lot o ID " + flightId + " nie istnieje."));

        customerFlight.setCustomer(customer);
        customerFlight.setFlight(flight);

        return customerFlightRepository.save(customerFlight);
    }

    // DELETE /api/customerFlightsDelete/{id}
    @DeleteMapping("/customerFlightsDelete/{id}")
    public String deleteCustomerFlight(@PathVariable Long id) {
        if (customerFlightRepository.existsById(id)) {
            customerFlightRepository.deleteById(id);
            return "Usunięto zapis na lot o ID: " + id;
        } else {
            return "Zapis o ID " + id + " nie istnieje.";
        }
    }

    // GET /api/customerFlights
    @GetMapping("/customerFlights")
    public List<CustomerFlightEntity> getAllCustomerFlights() {
        return customerFlightRepository.findAll();
    }

    // GET /api/customerFlights/{id}
    @GetMapping("/customerFlights/{id}")
    public CustomerFlightEntity getCustomerFlightById(@PathVariable Long id) {
        return customerFlightRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Nie znaleziono zapisu o ID: " + id));
    }
}
