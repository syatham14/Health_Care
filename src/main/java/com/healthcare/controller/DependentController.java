package com.healthcare.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.healthcare.constant.HealthCareConstants;
import com.healthcare.model.Dependent;
import com.healthcare.model.GenericResponse;
import com.healthcare.service.DependentService;

@RestController
@CrossOrigin
@RequestMapping(HealthCareConstants.DEPENDENT)
public class DependentController {
	@Autowired
	DependentService dependentService;

	/* Creates new record into the table */
	@PostMapping(HealthCareConstants.CREATE)
	public ResponseEntity<Dependent> create(@RequestBody Dependent dependent) {
		return new ResponseEntity<Dependent>(dependentService.createOrUpdate(dependent), HttpStatus.OK);
	}

	/* Updates existing record in the table */
	@PutMapping(HealthCareConstants.UPDATE)
	public ResponseEntity<Dependent> update(@RequestBody Dependent dependent) {
		return new ResponseEntity<Dependent>(dependentService.createOrUpdate(dependent), HttpStatus.OK);
	}

	/* Deletes record in the table with the given id */
	@DeleteMapping(HealthCareConstants.DELETE)
	public ResponseEntity<GenericResponse> delete(@RequestParam Integer id) {
		return new ResponseEntity<GenericResponse>(dependentService.delete(id), HttpStatus.OK);
	}

	/* Retrieves a record from the table based on given id */
	@GetMapping(HealthCareConstants.GET)
	public ResponseEntity<Dependent> get(@RequestParam Integer id) {
		return new ResponseEntity<Dependent>(dependentService.get(id), HttpStatus.OK);
	}

	/* Retrieves all records from the table */
	@GetMapping(HealthCareConstants.GET_ALL)
	public ResponseEntity<List<Dependent>> getAll() {
		return new ResponseEntity<List<Dependent>>(dependentService.getAll(), HttpStatus.OK);
	}
}
