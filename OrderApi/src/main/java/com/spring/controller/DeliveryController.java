package com.spring.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.model.DeliveryDetails;
import com.spring.model.OrderItem;
import com.spring.service.DeliveryServiceIntf;

@RestController
@RequestMapping("/order")
public class DeliveryController {
	
	Logger logger = LoggerFactory.getLogger(DeliveryController.class);
	@Autowired
	DeliveryServiceIntf service;
	
	@PostMapping(value="/deliveryDetails/create/{id}",consumes = MediaType.APPLICATION_JSON_VALUE)
	public  ResponseEntity<Map<String, Integer>> createDeliveryOrder(@RequestBody DeliveryDetails delivery,@PathVariable int id){
		
		Map<String, Integer> response = new HashMap<>();
		System.out.println("Dummy");
		int result=service.saveDelivery(delivery,id);
		System.out.println(delivery.getDeliveryId());
		System.out.println("this is controller" + result);
		if(result==0) {
			
			logger.error("Error with the SQL synatax, check the request body or the delivery ID");
			System.out.println(response);
			response.put("rows", result);
			return ResponseEntity.notFound().build();
			
		}
		else {
			logger.info("Record created with the values"+delivery);
			response.put("rows", result);
			
			return ResponseEntity.ok(response);
			
			
		}
		
	}
	
	@GetMapping("/deliveryDetails/{id}")
	public ResponseEntity<List<OrderItem>> getItems(@PathVariable int id){
		
		logger.info("Getting the order item list with userId: "+id);
		
		List<OrderItem> result = service.getUserOrder(id);
		if(result.isEmpty()) {
			logger.error("User does not have any orders");
			
			return new ResponseEntity<List<OrderItem>>(result,HttpStatus.NOT_FOUND);
			
		}
		else {
			logger.info("Record created with the values"+result);
			return new ResponseEntity<List<OrderItem>>(result,HttpStatus.OK);
			
		}
		
		
		
	}
	
	@PutMapping("/deliveryDetails/update")
	public  ResponseEntity<Map<String, Integer>> updateDeliveryOrder(@RequestBody DeliveryDetails delivery){
		int result=service.updateDelivery(delivery);
		Map<String, Integer> response = new HashMap<>();
		if(result==0) {
			logger.error("DeliveryId not found");
			response.put("rows", result);
			return ResponseEntity.notFound().build();
		}
		else {
			logger.info("Record updated as"+delivery);
			response.put("rows", result);
			return ResponseEntity.ok(response);
			
		}
		
	}

}
