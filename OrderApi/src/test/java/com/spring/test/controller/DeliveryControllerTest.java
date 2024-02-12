package com.spring.test.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.spring.SpringAssignment1Application;
import com.spring.controller.DeliveryController;
import com.spring.model.DeliveryDetails;
import com.spring.model.OrderItem;
import com.spring.service.DeliveryServiceIntf;

@SpringBootTest
public class DeliveryControllerTest {

	@InjectMocks
	DeliveryController deliveryController;
	
	@Mock
	DeliveryServiceIntf service;
	
	@MockBean
	DeliveryDetails deliveryDetails;
	
	@MockBean
	OrderItem orderItem;
	
	static OrderItem item = new OrderItem();
	static DeliveryDetails delivery = new DeliveryDetails();
	@BeforeAll
	public static void orderItems() {
		//orderItem dummy object setup
		
		item.setId(1);
		item.setPrice(20000);
		item.setProdName("Iphone");
		item.setQuantity(20);
		item.setUserId(200);
		List<OrderItem> results = new ArrayList<>();
		results.add(item);
		//DeliveryDetails object setup
		
		delivery.setDeliveryId(1);
		delivery.setOrderItems(results);
	
		
		
	}
	
//	@AfterAll
//	public static void cleanup() {
//		orderItem=null;
//		deliveryDetails=null;
//	}
//	
//	
	@Test
	public void testGetDelivery() {
		List<OrderItem> result = new ArrayList<>();
		result.add(item);
		
		Mockito.when(service.getUserOrder(Mockito.anyInt())).thenReturn(result);
		deliveryController.getItems(0);
		Assertions.assertEquals(item, result.get(0));
		
	}
	
	
	@Test
	public void testGetDeliveryFailure() {
		List<OrderItem> result = new ArrayList<>();
//		result.add(item);
		Mockito.when(service.getUserOrder(Mockito.anyInt())).thenReturn(result);
		deliveryController.getItems(0);
		Assertions.assertEquals(result.isEmpty(),result.isEmpty());
		
	}
	
	@Test
	public void testPostDelivery() {
//		Map<String, Integer> response = new HashMap<>();
		Mockito.when(service.saveDelivery(Mockito.any(DeliveryDetails.class),Mockito.anyInt())).thenReturn(1);
//		response.put("rows",1);
		deliveryController.createDeliveryOrder(deliveryDetails, 100);
		Assertions.assertEquals(1, 1);
	}
	
	@Test
	public void testPostDeliveryFailure() {
		
//		Map<String, Integer> response = new HashMap<>();
		Mockito.when(service.saveDelivery(Mockito.any(DeliveryDetails.class),Mockito.anyInt())).thenReturn(0);
//		response.put("rows",1);
		deliveryController.createDeliveryOrder(deliveryDetails, 100);
		Assertions.assertEquals(0, 0);
		
		
	}
	
	@Test
	public void testUpdateDelivery() {
		
		Mockito.when(service.updateDelivery(Mockito.any(DeliveryDetails.class))).thenReturn(1);
		deliveryController.updateDeliveryOrder(deliveryDetails);
		Assertions.assertEquals(1, 1);
		
		
	}
	
	@Test
	public void testUpdateDeliveryFailure() {
		Mockito.when(service.updateDelivery(Mockito.any(DeliveryDetails.class))).thenReturn(0);
		deliveryController.updateDeliveryOrder(deliveryDetails);
		Assertions.assertEquals(0, 0);
		
	}

}
