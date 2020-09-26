package com.healthcare.service;

import java.util.List;
import java.util.Optional;

import javax.validation.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.healthcare.constant.HealthCareConstants;
import com.healthcare.model.Dependent;
import com.healthcare.model.GenericResponse;
import com.healthcare.repository.DependentRepository;

@Service
public class DependentServiceImpl implements DependentService {
	@Autowired
	DependentRepository dependentRepo;

	@Override
	public Dependent createOrUpdate(Dependent dependent) {
		if (dependent != null) {
			try {
				/*
				 * saves record into the table if it is not exist or updates if
				 * it is already exist and returns the data
				 */
				return dependentRepo.save(dependent);
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
			Dependent b = get(id);
			// delete record by passing entire object
			dependentRepo.delete(b);
			resp = new GenericResponse();
			resp.setMessage(id + " " + HealthCareConstants.DELETED);
		} catch (Exception e) {
			throw new ValidationException(e.getMessage());
		}
		return resp;
	}

	/* Retrieves all records from the table */
	@Override
	public List<Dependent> getAll() {
		return (List<Dependent>) dependentRepo.findAll();
	}

	/* Retrieves a record from the table based on given id */
	@Override
	public Dependent get(Integer id) {
		Optional<Dependent> dependent = null;
		try {
			dependent = dependentRepo.findById(id);
		} catch (Exception e) {
			throw new ValidationException(e.getMessage());
		}
		// returns only if the record exists in the database
		if (dependent.isPresent())
			return dependent.get();
		else
			throw new ValidationException("Record not found with the id " + id);
	}

}
