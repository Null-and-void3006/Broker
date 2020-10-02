package com.ditra.MongoDB_Report_Service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ditra.MongoDB_Report_Service.services.PassedExamReportService;
import com.ditra.MongoDB_Report_Service.services.ProfessorPassingReportService;
import com.ditra.MongoDB_Report_Service.services.TechInSubjectReportService;

@RestController
public class GenerateReportController {
	
	@Autowired
	private PassedExamReportService passedExamReportService;
	@Autowired
	private TechInSubjectReportService techInSubjectServicel;
	@Autowired
	private ProfessorPassingReportService professorPassingReportService;
	
	@GetMapping(path = "/generate/passed/{service}")
	public String generatePassedReport(@PathVariable(value = "service") String service, @RequestParam ("student") String student) {		
		return passedExamReportService.generateReport(service, student);
	}
	
	@GetMapping(path = "/generate/tech/{service}")
	public String generateTechReport(@PathVariable(value = "service") String service, @RequestParam ("tech") String tech) {		
		return techInSubjectServicel.generateReport(service, tech);
	}
	
	@GetMapping(path = "/generate/professor/{service}")
	public String generateProfessorReport(@PathVariable(value = "service") String service, @RequestParam ("professor") String professor) {		
		return professorPassingReportService.generateReport(service, professor);
	}

}
