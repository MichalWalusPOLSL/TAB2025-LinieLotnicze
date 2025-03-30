package com.project.demo.repository;

import model.PlaneEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlaneRepository extends JpaRepository<PlaneEntity, Long> {
}
