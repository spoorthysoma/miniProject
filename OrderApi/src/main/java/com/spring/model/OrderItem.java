package com.spring.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;



@Entity
@Table(name = "order_item")
public class OrderItem {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name="prodName",nullable=false)
    private String prodName;
	@Column(name="qty",nullable=false)
    private int quantity;
    @Column(name="price",nullable=false)
    private double price;
    @Column(name="userId",nullable=false)
    private int userId;
    
    
	public OrderItem() {
		super();
		// TODO Auto-generated constructor stub
	}

	public OrderItem(int id, String name, int quantity, double price, DeliveryDetails deliveryDetails) {
		super();
		this.id = id;
		this.prodName = name;
		this.quantity = quantity;
		this.price = price;
//		this.deliveryDetails=deliveryDetails;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getProdName() {
		return prodName;
	}

	public void setProdName(String name) {
		this.prodName = name;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	

//	public DeliveryDetails getDeliveryDetails() {
//		return deliveryDetails;
//	}
//
//	public void setDeliveryDetails(DeliveryDetails deliveryDetails) {
//		this.deliveryDetails = deliveryDetails;
//	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "OrderItem [id=" + id + ", name=" + prodName + ", quantity=" + quantity + ", price=" + price
				+ "details="+"+deliveryDetails+]";
	}
    
    

}
