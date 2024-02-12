package com.spring.service;

import java.util.List;

import com.spring.model.DeliveryDetails;
import com.spring.model.OrderItem;

public interface DeliveryServiceIntf {
	public int saveDelivery(DeliveryDetails deliveryDetails,int userId);
	public List<OrderItem> getUserOrder(int userId);
	public int updateDelivery(DeliveryDetails deliveryDetails);
}
