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

import io.github.prem_sh.models.ManufacturingUnit;
import io.github.prem_sh.repository.ManufacturingUnitRepository;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/mfu")
@Tag(name = "Manufacturating Unit API", description = "Manufacturing units are Industrial units deployed to manufacture plc devices.\n This API gives access to perform CRUD operations on these data")
@SecurityRequirement(name = "Basic-auth")
public class ManufacturingUnitController {
	@Autowired
	private ManufacturingUnitRepository manufacturingUnitRepository;

	@PostMapping
	public ResponseEntity<ManufacturingUnit> create(@RequestBody ManufacturingUnit manufacturingUnitRef) {
		return new ResponseEntity<ManufacturingUnit>(manufacturingUnitRepository.save(manufacturingUnitRef),
				HttpStatus.OK);
	}

	@PutMapping("/{id}")
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

	@GetMapping
	public ResponseEntity<List<ManufacturingUnit>> getAll() {
		return new ResponseEntity<List<ManufacturingUnit>>(manufacturingUnitRepository.findAll(), HttpStatus.OK);
	}
}
