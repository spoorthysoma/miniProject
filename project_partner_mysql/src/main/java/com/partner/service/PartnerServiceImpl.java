package com.partner.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.partner.entity.PartnerEntity;
import com.partner.repo.PartnerRepo;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class PartnerServiceImpl implements PartnerService {
	
	@Autowired
	PartnerRepo repo;

	@Override
	public PartnerEntity createPartner(PartnerEntity entity) {
		return repo.save(entity);
	}

	@Override
	public List<PartnerEntity> fetchPartner() {
		return repo.findAll();
	}

	
	@Override
	public PartnerEntity updatePartner(Long partnerId, PartnerEntity update) {
		PartnerEntity existingPartner = repo.findById(partnerId);
		existingPartner.setName(update.getName());
		existingPartner.setPhoneNumber(update.getPhoneNumber());
		existingPartner.setEmailId(update.getEmailId());
		return repo.save(existingPartner);
	}
	
	 public void deletePartner(Long partnerId) {
	        repo.deleteById(partnerId);
	    }
	

	
}
