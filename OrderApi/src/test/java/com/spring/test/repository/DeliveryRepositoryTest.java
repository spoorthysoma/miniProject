package com.spring.test.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.spring.assignment.exception.ResourceNotFoundException;
import com.spring.model.Address;
import com.spring.model.DeliveryDetails;
import com.spring.model.OrderItem;
import com.spring.repository.AddressRepository;
import com.spring.repository.DeliveryRepository;

@SpringBootTest
public class DeliveryRepositoryTest {

	@InjectMocks
	DeliveryRepository repository;

	@Mock
	AddressRepository addressRepository;

	@Mock
	JdbcTemplate jdbcTemplate;
	
	@MockBean
	DeliveryDetails deliveryDetailsMock;

	@Test
	public void testCreate() {
		Address address = new Address();
		DeliveryDetails deliveryDetails = new DeliveryDetails();

		deliveryDetails.setDeliveryId(1);
		deliveryDetails.setDeliveryDate(new Date());
		deliveryDetails.setOrderDate(new Date());
		deliveryDetails.setDeliveryStatus(0);

		address.setId(1);

		when(addressRepository.getUserAddress(anyInt())).thenReturn(address);
		when(jdbcTemplate.update(any(String.class), any(), any(), any(), any(), any())).thenReturn(1);
		int result = repository.createDelivery(deliveryDetails, 100);
		assertEquals(1, result);
	}

//	@Test
//	public void testCreateAddressNotFound() {
//		DeliveryDetails deliveryDetails = new DeliveryDetails();
//	    Address address = new Address();
//	    address.setId(1);
//	    deliveryDetails.setDeliveryAddress(address);
//	    when(addressRepository.getUserAddress(anyInt())).thenReturn(address);
//	    
//	    when(jdbcTemplate.update(any(String.class), any(), any(), any(), any(), any())).thenThrow(DataAccessException.class);
//		 
//	    assertThrows(DataAccessException.class, () -> {
//	        repository.createDelivery(deliveryDetails, 1);
//	    });
//	
//	}

	@Test
	public void testGetOrderItems() {
		OrderItem item = new OrderItem();
		item.setId(1);
		item.setPrice(20000);
		item.setProdName("Iphone");
		item.setQuantity(20);
		item.setUserId(200);
		List<OrderItem> itms = new ArrayList<>(); 
		when(jdbcTemplate.query(any(String.class), any(Object[].class), any(RowMapper.class))).thenReturn(itms);
		List<OrderItem> orderItems = repository.getOrders(1);
		
		assertEquals(orderItems, itms);

	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void testGetOrder() {
		DeliveryDetails deliveryDetails = new DeliveryDetails();
	    Address deliveryAddress = new Address();
	    deliveryAddress.setId(1);
	    deliveryDetails.setDeliveryId(1);
	    deliveryDetails.setDeliveryDate(new Date());
	    deliveryDetails.setDeliveryStatus(0);
	    deliveryDetails.setOrderDate(new Date());
	    deliveryDetails.setDeliveryAddress(deliveryAddress);
		
		when(jdbcTemplate.queryForObject(any(String.class), any(Object[].class), any(RowMapper.class)))
        .thenReturn(deliveryDetails);
		DeliveryDetails result = repository.getOrder(deliveryDetails);
		assertEquals(deliveryDetails.getDeliveryId(), result.getDeliveryId());
		assertEquals(deliveryDetails.getDeliveryStatus(),result.getDeliveryStatus() );
		
		
	}
	
	@Test
	public void testGetOrderNotFound() {
		DeliveryDetails deliveryDetails = new DeliveryDetails();
		Address deliveryAddress = new Address();
	    deliveryAddress.setId(1);
	    deliveryDetails.setDeliveryId(1);
	    deliveryDetails.setDeliveryDate(new Date());
	    deliveryDetails.setDeliveryStatus(0);
	    deliveryDetails.setOrderDate(new Date());
	    deliveryDetails.setDeliveryAddress(deliveryAddress);
		when(jdbcTemplate.queryForObject(any(String.class), any(Object[].class), any(RowMapper.class)))
        .thenThrow(ResourceNotFoundException.class);
		
		assertThrows(ResourceNotFoundException.class, () -> {
			repository.getOrder(deliveryDetails);
        });
		
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void testUpdateDelivery() {
		
		Address address = new Address();
		DeliveryDetails deliveryDetails = new DeliveryDetails();

		deliveryDetails.setDeliveryId(155);
		deliveryDetails.setOrderDate(new Date());
		deliveryDetails.setDeliveryDate(new Date(deliveryDetails.getOrderDate().getTime() + (5 * 24 * 60 * 60 * 1000)));
		deliveryDetails.setDeliveryStatus(0);

		address.setId(1);
		
		when(repository.getOrder(any(DeliveryDetails.class))).thenReturn(deliveryDetails);
		Date checkDate = new Date(deliveryDetails.getOrderDate().getTime() + (6 * 24 * 60 * 60 * 1000));
		if(checkDate.after(deliveryDetails.getDeliveryDate())) {
			deliveryDetails.setDeliveryStatus(1);
		}
		else {
			deliveryDetails.setDeliveryStatus(0);
		}
		
		
		when(jdbcTemplate.update(any(String.class), any(), any())).thenReturn(1);
		
		int result= repository.updateDelivery(deliveryDetails);
		
	
	}
		
		
		
	
}
