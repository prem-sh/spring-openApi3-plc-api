package io.github.prem_sh.controllers;

import java.util.List;

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

import io.github.prem_sh.dto.ErrorMessageDto;
import io.github.prem_sh.models.ManufacturingUnit;
import io.github.prem_sh.repository.ManufacturingUnitRepository;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/mfu")
@Tag(name = "Manufacturating Unit API", description = "Manufacturing units are Industrial units deployed to manufacture plc devices.\n This API gives access to perform CRUD operations on these data")
@SecurityRequirement(name = "Basic-auth")
public class ManufacturingUnitController {
	@Autowired
	private ManufacturingUnitRepository manufacturingUnitRepository;

	@Operation(summary = "Create Manufacturing units", description = "A private api, used to create new Manufacturing unit in database", responses = {
			@ApiResponse(responseCode = "200", description = "Success, Manufacturing Unit created and created object will be returned", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ManufacturingUnit.class)))})
	@SecurityRequirement(name = "Basic-auth")
	@PostMapping
	public ResponseEntity<ManufacturingUnit> create(@RequestBody ManufacturingUnit manufacturingUnitRef) {
		return new ResponseEntity<ManufacturingUnit>(manufacturingUnitRepository.save(manufacturingUnitRef),
				HttpStatus.OK);
	}

	@PutMapping("/{id}")
	@Operation(summary = "Update Manufacturing units", description = "A private api, used to update manufacturing unit", responses = {
			@ApiResponse(responseCode = "200", description = "Success, Manufacturing Unit created and created device will be returned", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ManufacturingUnit.class)))})
	@SecurityRequirement(name = "Basic-auth")
	public ResponseEntity<ManufacturingUnit> update(@RequestBody ManufacturingUnit manufacturingUnitRef,
			@PathVariable Long id) {
		ManufacturingUnit manufacturingUnitRefRef = manufacturingUnitRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("ManufacturingUnit not found"));
		manufacturingUnitRefRef.setFacilityCapacity(manufacturingUnitRef.getFacilityCapacity());
		manufacturingUnitRefRef.setFacilityAddress(manufacturingUnitRef.getFacilityAddress());
		manufacturingUnitRefRef.setCountry(manufacturingUnitRef.getCountry());
		return new ResponseEntity<ManufacturingUnit>(manufacturingUnitRepository.save(manufacturingUnitRefRef),
				HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	@Operation(summary = "Edit existing device", description = "Private Api, Used to Delete Manufacturing Unit", responses = {
			@ApiResponse(responseCode = "200", description = "Success, Manufacturing Unit deleted successfuly", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ManufacturingUnit.class))),
			@ApiResponse(responseCode = "404", description = "NotFound, Manufacturing Unit with requested id not found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessageDto.class))) })
	public ResponseEntity<String> delete(@PathVariable Long id) {
		manufacturingUnitRepository.deleteById(id);
		return new ResponseEntity<String>("ManufacturingUnit deleted", HttpStatus.OK);
	}

	@Hidden
	@GetMapping("/{id}")
	public ResponseEntity<ManufacturingUnit> getByid(@PathVariable Long id) {
		return new ResponseEntity<ManufacturingUnit>(manufacturingUnitRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("ManufacturingUnit not found")), HttpStatus.OK);
	}

	@Operation(summary = "Fetch all Manufacturing Units", description = "Used to get", responses = {
			@ApiResponse(responseCode = "200", description = "Success", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ManufacturingUnit.class))
			)})
	@GetMapping
	public ResponseEntity<List<ManufacturingUnit>> getAll() {
		return new ResponseEntity<List<ManufacturingUnit>>(manufacturingUnitRepository.findAll(), HttpStatus.OK);
	}
}
