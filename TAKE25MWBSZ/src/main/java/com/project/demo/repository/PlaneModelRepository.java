package com.project.demo.repository;

import model.PlaneModelEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlaneModelRepository extends JpaRepository<PlaneModelEntity, Long> {
}
