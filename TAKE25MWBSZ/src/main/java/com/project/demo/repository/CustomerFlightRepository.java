package com.project.demo.repository;

import model.CustomerFlightEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerFlightRepository extends JpaRepository<CustomerFlightEntity, Long> {
}
