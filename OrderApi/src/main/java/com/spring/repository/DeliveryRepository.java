package com.spring.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.spring.assignment.exception.ResourceNotFoundException;
import com.spring.controller.DeliveryController;
import com.spring.model.Address;
import com.spring.model.DeliveryDetails;
import com.spring.model.OrderItem;

@Repository
public class DeliveryRepository {

	@Autowired
    private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private AddressRepository addressRepository;
	
	public DeliveryRepository(JdbcTemplate jdbcTemplate, AddressRepository addressRepository) {
		super();
		this.jdbcTemplate = jdbcTemplate;
		this.addressRepository = addressRepository;
	}

	public DeliveryRepository() {
		super();
		// TODO Auto-generated constructor stub
	}
	Logger logger = LoggerFactory.getLogger(DeliveryController.class);
	
	
	public int createDelivery(DeliveryDetails deliveryDetails,int userId) {
		System.out.println(userId);
		Address address = addressRepository.getUserAddress(userId);
		System.out.println(address);
		String sql = "insert into delivery_details values(?,?,?,?,?)";
		System.out.println(sql);
		deliveryDetails.setOrderDate(new Date());
		deliveryDetails.setDeliveryDate(new Date(deliveryDetails.getOrderDate().getTime() + (5 * 24 * 60 * 60 * 1000)));
		deliveryDetails.setDeliveryStatus(0);
		deliveryDetails.setDeliveryAddress(address);
		int result=0;
		try {
			
			result = jdbcTemplate.update(sql,deliveryDetails.getDeliveryId(),
					deliveryDetails.getDeliveryDate(),
					deliveryDetails.getDeliveryStatus(),
					deliveryDetails.getOrderDate(),
					deliveryDetails.getDeliveryAddress().getId()
					);
			System.out.println(result);
					return 1;
		} catch (DataAccessException e) {
			logger.error("Error with syntax or primary key",e);
			return 1;
		}
		
		
	}
	
	@SuppressWarnings("deprecation")
	public List<OrderItem> getOrders(int userId){
		  String sql = "SELECT * FROM order_item where user_id=?";
	        List<OrderItem> orderItems = jdbcTemplate.query(sql, new Object[]{userId},new RowMapper<OrderItem>() {
	            @Override
	            public OrderItem mapRow(ResultSet resultSet, int i) throws SQLException {
	                OrderItem orderItem = new OrderItem();
	                orderItem.setUserId(resultSet.getInt("user_id"));;
	                orderItem.setId(resultSet.getInt("id"));
	                orderItem.setProdName(resultSet.getString("prod_name"));
	                orderItem.setQuantity(resultSet.getInt("qty"));
	                orderItem.setPrice(resultSet.getDouble("price"));
	                return orderItem;
	            }
	        });
	        return orderItems;
	}

	
	
	public int updateDelivery(DeliveryDetails deliveryDetails) {
		try {
			DeliveryDetails details = getOrder(deliveryDetails);
			deliveryDetails.setOrderDate(details.getOrderDate());
			deliveryDetails.setDeliveryDate(details.getDeliveryDate());
			
		} catch (Exception e) {
			logger.error("+++++++++++++ Id not found+++++++++++++ ");
			throw new ResourceNotFoundException(deliveryDetails.getDeliveryId());
		}
		
		String sql ="UPDATE delivery_details SET delivery_status = ? WHERE delivery_id = ?";
//		Date checkDate = new Date();
		
		Date checkDate = new Date(deliveryDetails.getOrderDate().getTime() + (6 * 24 * 60 * 60 * 1000));
		if(checkDate.after(deliveryDetails.getDeliveryDate())) {
			deliveryDetails.setDeliveryStatus(1);
		}
		else {
			deliveryDetails.setDeliveryStatus(0);
		}
//		deliveryDetails.setDeliveryStatus(1);
		int result = jdbcTemplate.update(sql,deliveryDetails.getDeliveryStatus(),
				deliveryDetails.getDeliveryId());
		
		return result;
	}
	
	@SuppressWarnings("null")
	public DeliveryDetails getOrder(DeliveryDetails details) {
		String sql = "select * from delivery_details where delivery_id = ?";
		int deliveryId = details.getDeliveryId();
		@SuppressWarnings("deprecation")
		DeliveryDetails deliveryDetails = jdbcTemplate.queryForObject(
		    sql,
		    new Object[]{deliveryId},
		    (rs, rowNum) -> {
		        DeliveryDetails dd = new DeliveryDetails();
		        dd.setDeliveryId(rs.getInt("delivery_id"));
		        dd.setDeliveryStatus(rs.getInt("delivery_status"));
		        dd.setDeliveryDate(rs.getDate("delivery_date"));
		        dd.setOrderDate(rs.getDate("order_date"));
		        Address deliveryAddress = new Address();
	            deliveryAddress.setId(rs.getInt("delivery_address_id"));
		        return dd;
		    });
			
			if(deliveryDetails==null)
			{
				throw new ResourceNotFoundException(deliveryDetails.getDeliveryId());
			}
			else
		    return deliveryDetails;
	}
	
	
	
	
	
	
	
	
}
