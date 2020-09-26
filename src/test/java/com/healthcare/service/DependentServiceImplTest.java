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

import com.healthcare.model.Dependent;
import com.healthcare.model.GenericResponse;
import com.healthcare.repository.DependentRepository;

@RunWith(MockitoJUnitRunner.class)
public class DependentServiceImplTest {
	@Mock
	private DependentRepository repo;
	@InjectMocks
	private DependentServiceImpl service;
	Dependent orderdetail = null;
	ConstraintViolationException cve = null;

	@Test
	public void testCreate() {
		orderdetail = new Dependent();
		when(repo.save(any(Dependent.class))).thenReturn(orderdetail);
		service.createOrUpdate(orderdetail);
	}

	@Test
	public void testCreateWithNull() {
		Dependent result = service.createOrUpdate(orderdetail);
		Assertions.assertNull(result);
	}

	@Test(expected = ValidationException.class)
	public void testCreateException() {
		orderdetail = new Dependent();
		cve = new ConstraintViolationException(null, null, null);
		when(repo.save(any(Dependent.class))).thenThrow(cve);
		service.createOrUpdate(orderdetail);
	}

	@Test
	public void testUpdate() {
		orderdetail = new Dependent();
		orderdetail.setId(2);
		Optional<Dependent> ca = Optional.of(orderdetail);
		when(repo.findById(anyInt())).thenReturn(ca);
		when(repo.save(any(Dependent.class))).thenReturn(orderdetail);
		service.createOrUpdate(orderdetail);
	}

	@Test(expected = ValidationException.class)
	public void testUpdateException() {
		orderdetail = new Dependent();
		orderdetail.setId(2);
		cve = new ConstraintViolationException(null, null, null);
		when(repo.save(any(Dependent.class))).thenThrow(cve);
		service.createOrUpdate(orderdetail);
	}

	@Test
	public void testGetAll() {
		List<Dependent> value = new ArrayList<Dependent>();
		when(repo.findAll()).thenReturn(value);
		service.getAll();
	}

	@Test
	public void testDelete() {
		testUpdate();
		GenericResponse result = service.delete(1);
		assertEquals("1 deleted", result.getMessage());
	}

}
