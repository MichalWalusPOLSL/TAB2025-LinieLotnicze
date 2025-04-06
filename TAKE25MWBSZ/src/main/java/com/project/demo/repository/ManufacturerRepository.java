package com.project.demo.repository;

import model.ManufacturerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ManufacturerRepository extends JpaRepository<ManufacturerEntity, Long> {
}
