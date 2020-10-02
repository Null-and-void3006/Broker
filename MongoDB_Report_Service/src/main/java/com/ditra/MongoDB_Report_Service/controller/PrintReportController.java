package com.ditra.MongoDB_Report_Service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ditra.MongoDB_Report_Service.services.PassedExamReportService;
import com.ditra.MongoDB_Report_Service.services.ProfessorPassingReportService;
import com.ditra.MongoDB_Report_Service.services.TechInSubjectReportService;

@RestController
public class PrintReportController {
	
	@Autowired
	private PassedExamReportService passedExamReportService;
	@Autowired
	private TechInSubjectReportService techInSubjectService;
	@Autowired
	private ProfessorPassingReportService professorPassingReportService;
	
	@GetMapping(path = "/print/passed/{service}")
	public String printPassed(@PathVariable (value = "Service") String service, @RequestParam ("student") String student) {
		return passedExamReportService.printReport(service, student);
	}
	
	@GetMapping(path = "/print/tech/{service}")
	public String printTech(@PathVariable (value = "Service") String service, @RequestParam ("tech") String tech) {
		return techInSubjectService.printReport(service, tech);
	}
	
	@GetMapping(path = "/print/professor/{service}")
	public String generateProfessorReport(@PathVariable(value = "service") String service, @RequestParam ("professor") String professor) {		
		return professorPassingReportService.printReport(service, professor);
	}

}
