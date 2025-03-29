package CreditDemo;

import model.CustomerEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.project.demo.repository.CustomerRepository;

@RestController
@RequestMapping("/api/customers")
public class CustomerPostController {

    @Autowired
    private CustomerRepository customerRepository;

    @PostMapping
    public CustomerEntity createCustomer(@RequestBody CustomerEntity customer) {
        return customerRepository.save(customer);
    }
}
