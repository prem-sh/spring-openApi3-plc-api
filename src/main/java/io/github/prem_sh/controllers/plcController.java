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
import io.github.prem_sh.models.Plc;
import io.github.prem_sh.repository.PlcRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/plc")
@Tag(name = "PLC API", description = "CRUD operation on PLC information in database")
public class plcController {
	@Autowired PlcRepository plcRepository;

	@Operation(summary = "Create Manufacturing units", description = "A private api, used to create new PLC model in database", responses = {
			@ApiResponse(responseCode = "200", description = "Success, PLC model created and created object will be returned", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Plc.class)))})
	@SecurityRequirement(name = "Basic-auth")
	@PostMapping
	public ResponseEntity<Plc> create(@RequestBody Plc plc) {
		return new ResponseEntity<Plc>(plcRepository.save(plc), HttpStatus.OK);
	}

	@Operation(summary = "Update Manufacturing units", description = "A private api, used to update PLC details.", responses = {
			@ApiResponse(responseCode = "200", description = "Success, PLC details updated", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Plc.class)))})
	@SecurityRequirement(name="Basic-auth")
	@PutMapping("/{id}")
	public ResponseEntity<Plc> update(@RequestBody Plc plc, @PathVariable Long id) {
		Plc plcRef = plcRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Plc model not found"));
		plcRef.setModel(plc.getModel());
		plcRef.setInputs(plc.getInputs());
		plcRef.setOutput(plc.getOutput());
		return new ResponseEntity<Plc>(plcRepository.save(plcRef), HttpStatus.OK);
	}

	@SecurityRequirement(name="Basic-auth")
	@DeleteMapping("/{id}")
	public ResponseEntity<String> delete(@PathVariable Long id) {
		plcRepository.deleteById(id);
		return new ResponseEntity<String>("Plc deleted", HttpStatus.OK);
	}

	@Operation(summary = "Fetch PLC by id", description = "Used to get PLC by Id", responses = {
			@ApiResponse(responseCode = "200", description = "Success", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Plc.class))),
			@ApiResponse(responseCode = "404", description = "NotFound, PLC with requested id not found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessageDto.class))) })
	@GetMapping("/{id}")
	public ResponseEntity<Plc> getByid(@PathVariable Long id) {
		return new ResponseEntity<Plc>(
				plcRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Plc model not found")),
				HttpStatus.OK);
	}

	@Operation(summary = "Fetch all PLCs", description = "Used to get list of all PLCs", responses = {
			@ApiResponse(responseCode = "200", description = "Success", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Plc.class))
			)})
	@GetMapping
	public ResponseEntity<List<Plc>> getAll() {
		return new ResponseEntity<List<Plc>>(plcRepository.findAll(), HttpStatus.OK);
	}
}
