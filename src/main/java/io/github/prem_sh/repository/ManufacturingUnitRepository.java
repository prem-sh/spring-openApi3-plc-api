package io.github.prem_sh.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.github.prem_sh.models.ManufacturingUnit;

@Repository
public interface ManufacturingUnitRepository extends JpaRepository<ManufacturingUnit, Long>{

}
