package com.healthcare.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.healthcare.model.Enrollee;
import com.healthcare.model.GenericResponse;

@Service
public interface EnrolleeService {
	public Enrollee createOrUpdate(Enrollee enrollee);

	public GenericResponse delete(Integer id);

	public List<Enrollee> getAll();

	public Enrollee get(Integer id);

}
