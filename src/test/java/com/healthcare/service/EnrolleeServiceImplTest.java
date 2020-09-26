package com.healthcare.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.ValidationException;

import org.hibernate.exception.ConstraintViolationException;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.healthcare.model.Enrollee;
import com.healthcare.model.GenericResponse;
import com.healthcare.repository.EnrolleeRepository;

@RunWith(MockitoJUnitRunner.class)
public class EnrolleeServiceImplTest {
	@Mock
	private EnrolleeRepository repo;
	@InjectMocks
	private EnrolleeServiceImpl service;
	Enrollee orderdetail = null;
	ConstraintViolationException cve = null;

	@Test
	public void testCreate() {
		orderdetail = new Enrollee();
		when(repo.save(any(Enrollee.class))).thenReturn(orderdetail);
		service.createOrUpdate(orderdetail);
	}

	@Test
	public void testCreateWithNull() {
		Enrollee result = service.createOrUpdate(orderdetail);
		Assertions.assertNull(result);
	}

	@Test(expected = ValidationException.class)
	public void testCreateException() {
		orderdetail = new Enrollee();
		cve = new ConstraintViolationException(null, null, null);
		when(repo.save(any(Enrollee.class))).thenThrow(cve);
		service.createOrUpdate(orderdetail);
	}

	@Test
	public void testUpdate() {
		orderdetail = new Enrollee();
		orderdetail.setId(2);
		Optional<Enrollee> ca = Optional.of(orderdetail);
		when(repo.findById(anyInt())).thenReturn(ca);
		when(repo.save(any(Enrollee.class))).thenReturn(orderdetail);
		service.createOrUpdate(orderdetail);
	}

	@Test(expected = ValidationException.class)
	public void testUpdateException() {
		orderdetail = new Enrollee();
		orderdetail.setId(2);
		cve = new ConstraintViolationException(null, null, null);
		when(repo.save(any(Enrollee.class))).thenThrow(cve);
		service.createOrUpdate(orderdetail);
	}

	@Test
	public void testGetAll() {
		List<Enrollee> value = new ArrayList<Enrollee>();
		when(repo.findAll()).thenReturn(value);
		service.getAll();
	}

	@Test
	public void testGenericResponse() {
		testUpdate();
		GenericResponse result = service.delete(1);
		assertEquals("1 deleted", result.getMessage());
	}

}
