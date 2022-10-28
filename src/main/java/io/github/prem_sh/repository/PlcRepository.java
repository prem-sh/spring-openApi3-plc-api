package io.github.prem_sh.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.github.prem_sh.models.Plc;

@Repository
public interface PlcRepository extends JpaRepository<Plc, Long>{

}
