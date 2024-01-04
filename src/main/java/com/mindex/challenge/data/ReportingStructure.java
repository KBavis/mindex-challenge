package com.mindex.challenge.data;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

public class ReportingStructure {
	private Employee employee;
	private int numberOfReports;
	
	public ReportingStructure(Employee employee) {
		this.employee = employee;
		this.numberOfReports = calculateUniqueReports(employee);
	}
	
	public Employee getEmployee() {
		return employee;
	}
	
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	
	public int getNumberOfReports() {
		return numberOfReports;
	}
	
	public void setNumberOfReports(int numberOfReports) {
		this.numberOfReports = numberOfReports;
	}
	
	/**
	 * DFS Algorithm to determine the unique number of reports for a given employee 
	 * 
	 * @param employee
	 * @return
	 */
	public int calculateUniqueReports(Employee employee) {
		if(employee == null || employee.getDirectReports() == null || employee.getDirectReports().isEmpty()) {
			return 0;
		}
		
		Set<String> uniqueReports = new HashSet<>(); //set to ensure unique employees
		Stack<Employee> stack = new Stack<>();
		stack.push(employee); //init stack with original employee
		
		while(!stack.isEmpty()) {
			Employee curr = stack.pop();
			uniqueReports.add(curr.getEmployeeId()); //add curr employee to set
			
			List<Employee> directReports = curr.getDirectReports();
			if(directReports == null || directReports.isEmpty()) { //continue if nothing to report
				continue;
			}
			
			for(Employee e: directReports) {
				if(e != null && !uniqueReports.contains(e.getEmployeeId())) { //ensure non-null and distinct
					stack.push(e);
				}
			}
		}
		
		return uniqueReports.size() - 1; //deduct one for original employee
	}
	
}
