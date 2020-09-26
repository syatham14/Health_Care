package com.healthcare.controller;

import static org.junit.Assert.assertEquals;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import com.healthcare.model.Dependent;
import com.healthcare.model.Enrollee;
import com.healthcare.model.GenericResponse;
import com.healthcare.service.EnrolleeService;

@RunWith(MockitoJUnitRunner.class)
public class EnrolleeControllerTest {
	@InjectMocks
	EnrolleeController controller;
	@Mock
	EnrolleeService service;

	@Test
	public void testCreate() throws ParseException {
		Enrollee od = getEnrollee();
		Mockito.when(service.createOrUpdate(Mockito.any())).thenReturn(od);
		controller.create(Mockito.any());
	}

	@Test
	public void testupdate() throws ParseException {
		Enrollee od = getEnrollee();
		Mockito.when(service.createOrUpdate(Mockito.any())).thenReturn(od);
		controller.update(Mockito.any());
	}

	@Test
	public void testDelete() {
		GenericResponse gr = new GenericResponse();
		gr.setMessage("deleted successfully");
		Mockito.when(service.delete(Mockito.anyInt())).thenReturn(gr);
		ResponseEntity<GenericResponse> result = controller.delete(Mockito.anyInt());
		assertEquals("deleted successfully", result.getBody().getMessage());
		Assert.assertNotNull(result.getBody().toString());
	}

	@Test
	public void testGet() {
		Enrollee od = getEnrollee();
		Mockito.when(service.get(Mockito.anyInt())).thenReturn(od);
		ResponseEntity<Enrollee> result = controller.get(Mockito.anyInt());
		assertEquals(Integer.valueOf(1), result.getBody().getId());
		assertEquals("enrolleName", result.getBody().getName());
		assertEquals("11111111", result.getBody().getPhoneNumber());

		assertEquals(Integer.valueOf(2), result.getBody().getDependents().get(0).getId());
		assertEquals("dependentName", result.getBody().getDependents().get(0).getName());
		Assert.assertNotNull(result.getBody().getBirthDate());
		Assert.assertNotNull(result.getBody().toString());
	}

	@Test
	public void testGetAll() {
		List<Enrollee> value = new ArrayList<Enrollee>();
		value.add(getEnrollee());
		Mockito.when(service.getAll()).thenReturn(value);
		controller.getAll();
	}

	private Enrollee getEnrollee() {
		Enrollee enrollee = new Enrollee();
		enrollee.setActivationStatus(false);
		enrollee.setBirthDate(new Date());
		List<Dependent> dependents = new ArrayList<>();
		Dependent dependent = new Dependent();
		dependent.setBirthDate(new Date());
		dependent.setId(2);
		dependent.setName("dependentName");
		dependents.add(dependent);
		enrollee.setDependents(dependents);
		enrollee.setId(1);
		enrollee.setName("enrolleName");
		enrollee.setPhoneNumber("11111111");
		return enrollee;
	}

}
