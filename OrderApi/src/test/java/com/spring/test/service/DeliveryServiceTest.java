package com.spring.test.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.spring.model.DeliveryDetails;
import com.spring.model.OrderItem;
import com.spring.repository.DeliveryRepository;
import com.spring.service.DeliveryService;

@SpringBootTest
public class DeliveryServiceTest {
	
	@InjectMocks
	DeliveryService service;
	
	@Mock
	DeliveryRepository deliveryRepository;
	
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
	
	@Test
	public void testSaveDelivery() {
	Mockito.when(deliveryRepository.createDelivery(Mockito.any(DeliveryDetails.class), Mockito.anyInt())).thenReturn(1);
	service.saveDelivery(deliveryDetails, 1);
	
	}
	
	@Test
	public void testGetUserOrder() {
		List<OrderItem> result = new ArrayList<>();
		result.add(item);
		Mockito.when(deliveryRepository.getOrders(Mockito.anyInt())).thenReturn(result);
		service.getUserOrder(1);
		Assertions.assertEquals(item, result.get(0));
		
	}
	
	
	@Test
	public void testUpdateDeliveryService() {
		
		Mockito.when(deliveryRepository.updateDelivery(Mockito.any(DeliveryDetails.class))).thenReturn(1);
		service.updateDelivery(deliveryDetails);
		
	}
	
	


}
