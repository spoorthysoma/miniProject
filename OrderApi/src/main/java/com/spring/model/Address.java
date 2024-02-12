package com.spring.model;

import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "address")
public class Address {

	@Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
	@Column(nullable =false)
    private String street;
	@Column(nullable =false)
    private String city;
	@Column(nullable =false)
    private String state;
	@Column(nullable =false)
    private String zipCode;
	@Column(nullable=false)
	private int userId;
	
    @OneToMany(mappedBy="deliveryAddress")
    private List<DeliveryDetails> deliveryDetails = new ArrayList<>();

	
	public Address() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Address(int id, String street, String city, String state, String zipCode, int userId,
			List<DeliveryDetails> deliveryDetails) {
		super();
		this.id = id;
		this.street = street;
		this.city = city;
		this.state = state;
		this.zipCode = zipCode;
		this.userId = userId;
		this.deliveryDetails = deliveryDetails;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getStreet() {
		return street;
	}


	public void setStreet(String street) {
		this.street = street;
	}


	public String getCity() {
		return city;
	}


	public void setCity(String city) {
		this.city = city;
	}


	public String getState() {
		return state;
	}


	public void setState(String state) {
		this.state = state;
	}


	public String getZipCode() {
		return zipCode;
	}


	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}


	public int getUserId() {
		return userId;
	}


	public void setUserId(int userId) {
		this.userId = userId;
	}


	public List<DeliveryDetails> getDeliveryDetails() {
		return deliveryDetails;
	}


	public void setDeliveryDetails(List<DeliveryDetails> deliveryDetails) {
		this.deliveryDetails = deliveryDetails;
	}


	@Override
	public String toString() {
		return "Address [id=" + id + ", street=" + street + ", city=" + city + ", state=" + state + ", zipCode="
				+ zipCode + ", userId=" + userId + ", deliveryDetails=" + deliveryDetails + "]";
	}


    
    
}
