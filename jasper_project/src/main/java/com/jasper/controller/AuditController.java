package com.jasper.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jasper.entity.AuditEntity;
import com.jasper.service.AuditService;

import net.sf.jasperreports.engine.JRException;

@RestController
@RequestMapping("/jasper")
public class AuditController {

	@Autowired
	private AuditService service;


	@PostMapping("/add")
	public ResponseEntity<AuditEntity> createAudit(@RequestBody AuditEntity auditEntity) {

		AuditEntity savedEntry = service.saveAuditData(auditEntity);
		System.out.println(savedEntry);
		return ResponseEntity.status(HttpStatus.CREATED).body(savedEntry);

	}

	@GetMapping("/pdf")
	public ResponseEntity<byte[]> downloadPdf(
			@RequestParam("fromDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fromDate,
			@RequestParam("toDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate toDate)
			throws JRException {

		try {
			byte[] pdfBytes = service.generatePdf(fromDate, toDate);// fromDate, toDate

			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_PDF);
			headers.setContentDispositionFormData("attachment", "report.pdf");

			return new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);

		} catch (Exception e) {

			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}

	}
}
