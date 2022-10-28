package io.github.prem_sh.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.github.prem_sh.dto.DeviceIdentityInputDto;
import io.github.prem_sh.models.DeviceIdentity;
import io.github.prem_sh.models.Plc;

@Repository
public interface DeviceRepository extends JpaRepository<DeviceIdentity, String>{

}
