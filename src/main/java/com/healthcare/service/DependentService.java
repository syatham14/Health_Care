package com.healthcare.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.healthcare.model.Dependent;
import com.healthcare.model.GenericResponse;

@Service
public interface DependentService {
	public Dependent createOrUpdate(Dependent dependent);

	public GenericResponse delete(Integer id);

	public List<Dependent> getAll();

	public Dependent get(Integer id);

}
