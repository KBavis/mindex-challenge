package com.mindex.challenge.service.impl;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.mindex.challenge.data.Employee;
import com.mindex.challenge.service.EmployeeService;
import com.mindex.challenge.service.ReportingStructureService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ReportingStuctureServiceImplTest {
	
	@MockBean
	private EmployeeService employeeService;
	
	 @Autowired
	 private ReportingStructureService reportingStructureService;
	
	@Test
	public void testCalculateUniqueReports() {
	    Employee userOne = new Employee();
	    userOne.setEmployeeId("user1");

	    Employee userTwo = new Employee();
	    userTwo.setEmployeeId("user2");

	    Employee userThree = new Employee();
	    userThree.setEmployeeId("user3");

	    Employee userFour = new Employee();
	    userFour.setEmployeeId("user4");

	    Employee userFive = new Employee();
	    userFive.setEmployeeId("user5");

	    //User One Has Direct Reports Of User Two and User Three
	    List<Employee> userOneDirectReports = new ArrayList<>();
	    userOneDirectReports.add(userTwo);
	    userOneDirectReports.add(userThree);
	    userOne.setDirectReports(userOneDirectReports);
	    
	    //User Two Has Direct Reports of User Three and User Four 
	    List<Employee> userTwoDirectReports = new ArrayList<>();
	    userTwoDirectReports.add(userThree);
	    userTwoDirectReports.add(userFour);
	    userTwo.setDirectReports(userTwoDirectReports);

	    //User Three Has Direct Reports of User Two and User Fiver  
	    List<Employee> userThreeDirectReports = new ArrayList<>();
	    userThreeDirectReports.add(userTwo);
	    userThreeDirectReports.add(userFive);
	    userThree.setDirectReports(userThreeDirectReports);
	    
	    //mock employee service 
	    when(employeeService.read("user1")).thenReturn(userOne);
	    when(employeeService.read("user2")).thenReturn(userTwo);
	    when(employeeService.read("user3")).thenReturn(userThree);
	    when(employeeService.read("user4")).thenReturn(userThree);
	    when(employeeService.read("user5")).thenReturn(userThree);
	    
	    int uniqueReports = reportingStructureService.calculateUniqueReports(userOne);
	    assertEquals(4, uniqueReports); // Expecting count of 4 (user two, user three, user four, and user five)
	}
	
	@Test
	public void testCalculateUniqueReportsWithNull() {
		 int uniqueReports = reportingStructureService.calculateUniqueReports(null);
	     assertEquals(0, uniqueReports);
	}
	
}
