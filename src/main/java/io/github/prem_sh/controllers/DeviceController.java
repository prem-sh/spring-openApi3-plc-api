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
import io.github.prem_sh.dto.ErrorMessageDto;
import io.github.prem_sh.models.DeviceIdentity;
import io.github.prem_sh.repository.DeviceRepository;
import io.github.prem_sh.repository.ManufacturingUnitRepository;
import io.github.prem_sh.repository.PlcRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/device")
@Tag(name = "Devices API", description = "This is the registry of all individual PLC units manufactured")
public class DeviceController {
	@Autowired
	private DeviceRepository deviceRepository;
	@Autowired
	private ManufacturingUnitRepository manufacturingUnitRepository;
	@Autowired
	PlcRepository plcRepository;

	@Operation(
			summary = "Register new device", 
			description = "A private api, used to create new divice in database. NOTE : After manufacturing of plc we should register the plc using this api otherwise buying and selling of the plc is illeagl.",
			responses = {
					@ApiResponse(
							responseCode = "200",
							description = "Success, device created and created device will be returned",
							content = @Content(mediaType = "application/json", schema = @Schema(implementation = DeviceIdentity.class) )
							),
					@ApiResponse(
							responseCode = "406",
							description = "Not_Acceptable, Already have a device with same id",
							content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessageDto.class))
							)
			}
		)
	@SecurityRequirement(name = "Basic-auth")
	@PostMapping
	public ResponseEntity<DeviceIdentity> create(@Parameter(description = "Device object") @RequestBody DeviceIdentityInputDto deviceDetails) {
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

	@Operation(
			summary = "Edit existing device", description = "Private Apin , Used to Update device details",
			responses = {
					@ApiResponse(
							responseCode = "200",
							description = "Success, Device details updated successfully",
							content = @Content(mediaType = "application/json", schema = @Schema(implementation = DeviceIdentity.class))
							),
					@ApiResponse(
							responseCode = "404",
							description = "NotFound, Device with requested id not found",
							content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessageDto.class))
							)
			}
			)
	@SecurityRequirement(name = "Basic-auth")
	@PutMapping("/{id}")
	public ResponseEntity<DeviceIdentity> update(@RequestBody DeviceIdentityInputDto deviceDetails,
			@Parameter(description = "Device id") @PathVariable String id) {
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

	@Operation(
			summary = "Delete existing device"
			)
	@SecurityRequirement(name = "Basic-auth")
	@DeleteMapping("/{id}")
	public ResponseEntity<String> delete(@Parameter(description = "Device id") @PathVariable String id) {
		deviceRepository.deleteById(id);
		return new ResponseEntity<String>("DeviceIdentity deleted", HttpStatus.OK);
	}

	@Operation(summary = "Fetch device by id")
	@GetMapping("/{id}")
	public ResponseEntity<DeviceIdentity> getByid(@Parameter(description = "Device id") @PathVariable String id) {
		return new ResponseEntity<DeviceIdentity>(deviceRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Device not found")), HttpStatus.OK);
	}

	@Operation(summary = "Fetch all devices")
	@GetMapping
	public ResponseEntity<List<DeviceIdentity>> getAll() {
		return new ResponseEntity<List<DeviceIdentity>>(deviceRepository.findAll(), HttpStatus.OK);
	}
}
