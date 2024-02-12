package com.jasper.service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jasper.entity.AuditEntity;
import com.jasper.repository.AuditRepository;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Service
public class AuditService {

	@Autowired
	private AuditRepository repo;

//	public AuditService(AuditRepository repo) {
//		this.repo = repo;
//	}

	public AuditEntity saveAuditData(AuditEntity auditEntity) {
		System.out.println("inside service");
		return repo.save(auditEntity);
	}

//	
//	public AuditService() {
//		super();
//		// TODO Auto-generated constructor stub
//	}

	public byte[] generatePdf(LocalDate fromDate, LocalDate toDate) throws JRException {// LocalDate fromDate, LocalDate
																						// toDate

		List<AuditEntity> audit = repo.findAllByCreatedTimeBetween(fromDate.atStartOfDay(), toDate.atTime(23, 59, 59)); 

		String templatePath = getClass().getResource("/JasperFile.jrxml").getPath();
		JasperCompileManager.compileReportToFile(templatePath);

		String compiledTemplatePath = getClass().getResource("/JasperFile.jasper").getPath();
		JasperPrint jasperPrint = JasperFillManager.fillReport(compiledTemplatePath, new HashMap<>(),
				new JRBeanCollectionDataSource(audit));

		byte[] pdfBytes = JasperExportManager.exportReportToPdf(jasperPrint);

		return pdfBytes;
	}

}
