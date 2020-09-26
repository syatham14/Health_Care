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
import com.healthcare.model.GenericResponse;
import com.healthcare.service.DependentService;

@RunWith(MockitoJUnitRunner.class)
public class DependentControllerTest {
	@InjectMocks
	DependentController controller;
	@Mock
	DependentService service;

	@Test
	public void testCreate() throws ParseException {
		Dependent od = getDependent();
		Mockito.when(service.createOrUpdate(Mockito.any())).thenReturn(od);
		controller.create(Mockito.any());
	}

	@Test
	public void testupdate() throws ParseException {
		Dependent od = getDependent();
		Mockito.when(service.createOrUpdate(Mockito.any())).thenReturn(od);
		controller.update(Mockito.any());
	}

	@Test
	public void testDelete() {
		GenericResponse gr = new GenericResponse();
		gr.setMessage("deleted");
		Mockito.when(service.delete(Mockito.anyInt())).thenReturn(gr);
		ResponseEntity<GenericResponse> result = controller.delete(Mockito.anyInt());
		assertEquals("deleted", result.getBody().getMessage());
		Assert.assertNotNull(result.getBody().toString());
	}

	@Test
	public void testGet() {
		Dependent od = getDependent();
		Mockito.when(service.get(Mockito.anyInt())).thenReturn(od);
		ResponseEntity<Dependent> result = controller.get(Mockito.anyInt());
		assertEquals(Integer.valueOf(1), result.getBody().getId());
		assertEquals("test", result.getBody().getName());
		Assert.assertNotNull(result.getBody().getBirthDate());
		Assert.assertNotNull(result.getBody().toString());
	}

	@Test
	public void testGetAll() {
		List<Dependent> value = new ArrayList<Dependent>();
		value.add(getDependent());
		Mockito.when(service.getAll()).thenReturn(value);
		controller.getAll();
	}

	private Dependent getDependent() {
		Dependent dependent = new Dependent();
		dependent.setBirthDate(new Date());
		dependent.setId(1);
		dependent.setName("test");
		return dependent;
	}

}
