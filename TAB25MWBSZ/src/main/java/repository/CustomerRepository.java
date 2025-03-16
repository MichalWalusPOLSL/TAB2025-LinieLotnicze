package repository;

import org.springframework.data.jpa.repository.JpaRepository;
import model.CustomerEntity;

public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {
}
