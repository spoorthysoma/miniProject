package com.partner.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.partner.entity.PartnerEntity;


public interface PartnerService {

	public PartnerEntity createPartner(PartnerEntity entity);

	public List<PartnerEntity> fetchPartner();	

	public PartnerEntity updatePartner(Long partnerId, PartnerEntity update);
	
	public void deletePartner(Long partnerId);
}
