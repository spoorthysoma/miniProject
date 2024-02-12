package com.spring.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "delivery_details")
public class DeliveryDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int deliveryId;
	private Date orderDate;
	private Date deliveryDate;
	private int deliveryStatus;
//	private int orderId;
	
	@Autowired
	@ManyToOne(cascade = CascadeType.ALL)
	private Address deliveryAddress;

	@Autowired
	@OneToMany(mappedBy = "id", cascade = CascadeType.ALL)
	private List<OrderItem> orderItems = new ArrayList<>();

	
	public DeliveryDetails() {
		super();
		// TODO Auto-generated constructor stub
	}


	public DeliveryDetails(int deliveryId, Date orderDate, Date deliveryDate, int deliveryStatus,
			Address deliveryAddress, List<OrderItem> orderItems) {
		super();
		this.deliveryId = deliveryId;
		this.orderDate = orderDate;
		this.deliveryDate = deliveryDate;
		this.deliveryStatus = deliveryStatus;
		this.deliveryAddress = deliveryAddress;
		this.orderItems = orderItems;
	}


	public int getDeliveryId() {
		return deliveryId;
	}


	public void setDeliveryId(int deliveryId) {
		this.deliveryId = deliveryId;
	}


	public Date getOrderDate() {
		return orderDate;
	}


	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}


	public Date getDeliveryDate() {
		return deliveryDate;
	}


	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}


	public int getDeliveryStatus() {
		return deliveryStatus;
	}


	public void setDeliveryStatus(int deliveryStatus) {
		this.deliveryStatus = deliveryStatus;
	}


	public Address getDeliveryAddress() {
		return deliveryAddress;
	}


	public void setDeliveryAddress(Address address) {
		this.deliveryAddress=address;
	}


	public List<OrderItem> getOrderItems() {
		return orderItems;
	}


	public void setOrderItems(List<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}


	@Override
	public String toString() {
		return "DeliveryDetails [deliveryId=" + deliveryId + ", orderDate=" + orderDate + ", deliveryDate="
				+ deliveryDate + ", deliveryStatus=" + deliveryStatus + ", deliveryAddress=" + deliveryAddress
				+ ", orderItems=" + orderItems + "]";
	}

	
	
}
