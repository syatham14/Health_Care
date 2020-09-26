package com.healthcare.service;

import java.util.List;
import java.util.Optional;

import javax.validation.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.healthcare.constant.HealthCareConstants;
import com.healthcare.model.Enrollee;
import com.healthcare.model.GenericResponse;
import com.healthcare.repository.EnrolleeRepository;

@Service
public class EnrolleeServiceImpl implements EnrolleeService {
	@Autowired
	EnrolleeRepository enrolleeRepo;

	@Override
	public Enrollee createOrUpdate(Enrollee enrollee) {
		if (enrollee != null) {
			try {
				/*
				 * saves record into the table if it is not exist or updates if
				 * it is already exist and returns the data
				 */
				return enrolleeRepo.save(enrollee);
			} catch (Exception e) {
				throw new ValidationException(e.getMessage());
			}
		}
		return null;
	}

	/* Deletes record in the table with the given id */
	@Override
	public GenericResponse delete(Integer id) {
		GenericResponse resp = null;
		try {
			// reads the record based on the id
			Enrollee b = get(id);
			// delete record by passing entire object
			enrolleeRepo.delete(b);
			resp = new GenericResponse();
			resp.setMessage(id + " " + HealthCareConstants.DELETED);
		} catch (Exception e) {
			throw new ValidationException(e.getMessage());
		}
		return resp;
	}

	/* Retrieves all records from the table */
	@Override
	public List<Enrollee> getAll() {
		return (List<Enrollee>) enrolleeRepo.findAll();
	}

	/* Retrieves a record from the table based on given id */
	@Override
	public Enrollee get(Integer id) {
		Optional<Enrollee> enrollee = null;
		try {
			enrollee = enrolleeRepo.findById(id);
		} catch (Exception e) {
			throw new ValidationException(e.getMessage());
		}
		// returns only if the record exists in the database
		if (enrollee.isPresent())
			return enrollee.get();
		else
			throw new ValidationException("Record not found with the id " + id);
	}

}
