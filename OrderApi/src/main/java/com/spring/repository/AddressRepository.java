package com.spring.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.spring.assignment.exception.ResourceNotFoundException;
import com.spring.model.Address;

@Repository
public class AddressRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;

//	public Address getUserAddress(int userId) {
//		System.out.println("hi");
//		@SuppressWarnings("deprecation")
//		Address address = jdbcTemplate.queryForObject(
//			    "SELECT * FROM address WHERE user_id = ?",
//			    new Object[]{userId},
//			    (rs, rowNum) -> {
//			        Address add = new Address();
//			        add.setId(rs.getInt("id"));
//			        add.setStreet(rs.getString("street"));
//			        add.setCity(rs.getString("city"));
//			        add.setState(rs.getString("state"));
//			        add.setZipCode(rs.getString("zip_code"));
////			        System.out.println(add);
//			        return add;
//			    }
//			);
//		if(address==null) {
//			System.out.println("dummy");
//			throw new ResourceNotFoundException(userId);
//		}
//		else {
//			System.out.println(address);
//			return address;
//		}

	public Address getUserAddress(int userId) {
		System.out.println("hi");
		@SuppressWarnings("deprecation")
		Address address = jdbcTemplate.queryForObject("SELECT * FROM address WHERE user_id = ?",
				new Object[] { userId }, (rs, rowNum) -> {
					Address add = new Address();
					add.setId(rs.getInt("id"));
					add.setStreet(rs.getString("street"));
					add.setCity(rs.getString("city"));
					add.setState(rs.getString("state"));
					add.setZipCode(rs.getString("zip_code"));
					return add;
				});

		if (address == null) {
			System.out.println("dummy");
			throw new ResourceNotFoundException(userId);
		} else {
			System.out.println(address);
			return address;
		}

	}

}
