package com.healthcare.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.healthcare.model.Enrollee;

@Repository
public interface EnrolleeRepository extends CrudRepository<Enrollee, Integer> {

}
