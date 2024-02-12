package com.spring.test.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.spring.assignment.exception.ResourceNotFoundException;
import com.spring.model.Address;
import com.spring.repository.AddressRepository;

@SpringBootTest
public class AddressRepositoryTest {
	
	@InjectMocks
	AddressRepository addressRepository;
	
	
	@Mock
	JdbcTemplate jdbcTemplate;
	
	
	@SuppressWarnings({ "deprecation", "unchecked" })
	@Test
	public void testAddressRepo() {
		
		Address address = new Address();
        address.setId(1);
        address.setStreet("123 Main St");
        address.setCity("Anytown");
        address.setState("CA");
        address.setZipCode("12345");
        when(jdbcTemplate.queryForObject(any(String.class), any(Object[].class), any(RowMapper.class)))
            .thenReturn(address);
        
        Address result = addressRepository.getUserAddress(1);
        
        // Assert
        assertEquals(address.getId(), result.getId());
        assertEquals(address.getStreet(), result.getStreet());
        assertEquals(address.getCity(), result.getCity());
        assertEquals(address.getState(), result.getState());
        assertEquals(address.getZipCode(), result.getZipCode());
        
        
        
		
	}
	
	 @Test
	 public void testGetUserAddressNotFound() {
	      
	        when(jdbcTemplate.queryForObject(any(String.class), any(Object[].class), any(RowMapper.class)))
	            .thenReturn(null);
	        assertThrows(ResourceNotFoundException.class, () -> {
	        	addressRepository.getUserAddress(1);
	        });
	    }
	 
	 

}
