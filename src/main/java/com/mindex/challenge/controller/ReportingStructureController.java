package com.mindex.challenge.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mindex.challenge.data.Employee;
import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.service.EmployeeService;
import com.mindex.challenge.service.ReportingStructureService;

@RestController
@RequestMapping("/reportingStructure")
public class ReportingStructureController {
	
	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private ReportingStructureService reportingStructureService;
	
	/**
	 * Fetch ReportingStructure of Employee
	 * 
	 * @param employeeId
	 * @return
	 */
	@GetMapping("/{employeeId}")
	public ReportingStructure getReportingStrucutre(@PathVariable String employeeId) {
		Employee employee = employeeService.read(employeeId);
		if(employee == null) {
			return null;
		}

		int numberOfReports = reportingStructureService.calculateUniqueReports(employee); //fetch number of unique reports
		return new ReportingStructure(employee, numberOfReports);
	}
}
