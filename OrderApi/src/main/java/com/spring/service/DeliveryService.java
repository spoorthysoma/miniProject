package com.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.model.DeliveryDetails;
import com.spring.model.OrderItem;
import com.spring.repository.DeliveryRepository;

@Service
public class DeliveryService implements DeliveryServiceIntf {

	@Autowired
	DeliveryRepository deliveryRepository;

	public DeliveryService() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DeliveryService(DeliveryRepository deliveryRepository) {
		super();
		this.deliveryRepository = deliveryRepository;
	}

	public int saveDelivery(DeliveryDetails deliveryDetails, int userId) {
		
		//return deliveryRepository.createDelivery(deliveryDetails, userId);
		int a ;
		a=deliveryRepository.createDelivery(deliveryDetails, userId);
		System.out.println(a);
		return a;
	}

	public List<OrderItem> getUserOrder(int userId) {

		return deliveryRepository.getOrders(userId);
	}

	public int updateDelivery(DeliveryDetails deliveryDetails) {

		return deliveryRepository.updateDelivery(deliveryDetails);
	}

}
