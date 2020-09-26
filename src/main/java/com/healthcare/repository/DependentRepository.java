package com.healthcare.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.healthcare.model.Dependent;

@Repository
public interface DependentRepository extends CrudRepository<Dependent, Integer> {

}
