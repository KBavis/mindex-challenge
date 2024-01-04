package com.mindex.challenge.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mindex.challenge.data.Employee;
import com.mindex.challenge.service.EmployeeService;
import com.mindex.challenge.service.ReportingStructureService;

@Service
public class ReportingStrucutreServiceImpl implements ReportingStructureService{
	private static final Logger LOG = LoggerFactory.getLogger(EmployeeServiceImpl.class);
	
	@Autowired
    private EmployeeService employeeService;
	
	/**
	 * DFS Algorithm To Calculate Total Number of Unique Reports
	 */
	public int calculateUniqueReports(Employee employee) {
		if(employee == null || employee.getDirectReports() == null || employee.getDirectReports().isEmpty()) {
			return 0;
		}
		LOG.debug("Calculating Number of Unique Reports for Employee with id [{}]", employee.getEmployeeId());
		Set<String> uniqueReports = new HashSet<>(); //set to ensure unique employees
		Stack<String> stack = new Stack<>();
		stack.push(employee.getEmployeeId()); //init stack with original employee
		
		while(!stack.isEmpty()) {
			String currEmployeeId = stack.pop();
			uniqueReports.add(currEmployeeId); //add curr employee to set
			
			Employee curr = employeeService.read(currEmployeeId);//fetch full employee details 
			List<Employee> directReports = curr.getDirectReports();
			
			if(directReports == null || directReports.isEmpty()) { //continue if nothing to report
				continue;
			}
			
			for(Employee e: directReports) {
				String employeeId = e.getEmployeeId();
				if(e != null && !uniqueReports.contains(employeeId)) { //ensure non-null and distinct
					stack.push(employeeId);
				}
			}
		}
		
		return uniqueReports.size() - 1; //deduct one for original employee
	}
}
