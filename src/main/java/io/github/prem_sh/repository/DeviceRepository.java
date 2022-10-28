package io.github.prem_sh.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.github.prem_sh.models.DeviceIdentity;

@Repository
public interface DeviceRepository extends JpaRepository<DeviceIdentity, String>{

}
