package com.partner.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.partner.entity.PartnerEntity;

@Repository
public interface PartnerRepo extends JpaRepository<PartnerEntity, Integer>{

	PartnerEntity findById(Long partnerId);

	void deleteById(Long partnerId);

	
}
