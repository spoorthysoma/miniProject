package com.partner.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.partner.entity.PartnerEntity;
import com.partner.service.PartnerService;


@RestController
@RequestMapping(path ="/partner")
public class PartnerController {

	@Autowired
	private PartnerService partnerService;

	
	@PostMapping("/createParthers")
	public PartnerEntity createParthers(@RequestBody PartnerEntity partner) {
		PartnerEntity createPartner = partnerService.createPartner(partner);
		return createPartner;
		
	}
	
	@GetMapping("/fetchPartners")
	public List<PartnerEntity> fetchPartners(){
		List<PartnerEntity> fetch = partnerService.fetchPartner();
		return fetch;	
	}
	
	 @PutMapping("/{partnerId}")
	    public ResponseEntity<PartnerEntity> updatePartner( @PathVariable Long partnerId, @RequestBody PartnerEntity updatedPartner) {
	        PartnerEntity updatedEntity = partnerService.updatePartner(partnerId, updatedPartner);
	        return ResponseEntity.ok(updatedEntity);
	    }
	 
	 @DeleteMapping("/delete/{partnerId}")
	    public ResponseEntity<String> deletePartner(@PathVariable Long partnerId) {
	        partnerService.deletePartner(partnerId);
	        return ResponseEntity.ok("Partner deleted successfully");
	    }
}
