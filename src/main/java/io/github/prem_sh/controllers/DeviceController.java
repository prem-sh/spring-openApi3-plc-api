package io.github.prem_sh.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.prem_sh.dto.DeviceIdentityInputDto;
import io.github.prem_sh.models.DeviceIdentity;
import io.github.prem_sh.repository.DeviceRepository;
import io.github.prem_sh.repository.ManufacturingUnitRepository;
import io.github.prem_sh.repository.PlcRepository;

@RestController
@RequestMapping("/device")
public class DeviceController {
	@Autowired
	private DeviceRepository deviceRepository;
	@Autowired
	private ManufacturingUnitRepository manufacturingUnitRepository;
	@Autowired
	PlcRepository plcRepository;

	@PostMapping
	public ResponseEntity<DeviceIdentity> create(@RequestBody DeviceIdentityInputDto deviceDetails) {
		if(deviceRepository.findById(deviceDetails.getDeviceId()).isEmpty()) {
			DeviceIdentity device = new DeviceIdentity();
			device.setDeviceId(deviceDetails.getDeviceId());
			device.setManufacturingUnit(
					manufacturingUnitRepository.findById(
							deviceDetails.getManufacturingUnitId()
							).orElseThrow(()-> new EntityNotFoundException("Manufacturing unit not found")));
			device.setPlcModel(plcRepository.findById(deviceDetails.getPlcModelId()).orElseThrow(
					()->new EntityNotFoundException("Plc model not found")));
			try {
				device.setMfd(new SimpleDateFormat("dd-MM-yyyy").parse(deviceDetails.getMfd()));
			} catch (ParseException e) {
				device.setMfd(null);
			}
			device.setQualityCheck(deviceDetails.getQualityCheck());

			return new ResponseEntity<DeviceIdentity>(deviceRepository.save(device), HttpStatus.OK);
		}else throw new EntityExistsException("Device with same id already exist");
		
	}

	@PutMapping("/{id}")
	public ResponseEntity<DeviceIdentity> update(@RequestBody DeviceIdentityInputDto deviceDetails,
			@PathVariable String id) {
		Optional<DeviceIdentity> opt = deviceRepository.findById(deviceDetails.getDeviceId());
		if(!opt.isEmpty()) {
			DeviceIdentity device = opt.get();
			device.setDeviceId(deviceDetails.getDeviceId());
			device.setManufacturingUnit(
					manufacturingUnitRepository.findById(
							deviceDetails.getManufacturingUnitId()
							).orElseThrow(()-> new EntityNotFoundException("Manufacturing unit not found")));
			device.setPlcModel(plcRepository.findById(deviceDetails.getPlcModelId()).orElseThrow(
					()->new EntityNotFoundException("Plc model not found")));
			try {
				device.setMfd(new SimpleDateFormat("dd-MM-yyyy").parse(deviceDetails.getMfd()));
			} catch (ParseException e) {
				device.setMfd(null);
			}
			device.setQualityCheck(deviceDetails.getQualityCheck());

			return new ResponseEntity<DeviceIdentity>(deviceRepository.save(device), HttpStatus.OK);
		}else throw new EntityNotFoundException("Device not found");
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> delete(@PathVariable String id) {
		deviceRepository.deleteById(id);
		return new ResponseEntity<String>("DeviceIdentity deleted", HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<DeviceIdentity> getByid(@PathVariable String id) {
		return new ResponseEntity<DeviceIdentity>(deviceRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Device not found")), HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<List<DeviceIdentity>> getAll() {
		return new ResponseEntity<List<DeviceIdentity>>(deviceRepository.findAll(), HttpStatus.OK);
	}
}
