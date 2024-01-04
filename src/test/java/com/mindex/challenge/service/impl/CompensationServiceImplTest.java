package com.mindex.challenge.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.mindex.challenge.dao.CompensationRepository;
import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.data.Employee;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CompensationServiceImplTest {

    @Mock
    private CompensationRepository compensationRepository;

    @InjectMocks
    private CompensationServiceImpl compensationService;

    private Compensation testCompensation;
    private String testEmployeeId = "testEmployeeId";

    @Before
    public void setup() {
    	Employee employee = new Employee();
    	employee.setEmployeeId(testEmployeeId);
        testCompensation = new Compensation(employee, 50000.0, new Date());
    }
    
    /**
     * Ensure Service Correctly Creates Compensation
     */
    @Test
    public void testCreateCompensation() {
        when(compensationRepository.save(any(Compensation.class))).thenReturn(testCompensation);

        // Call the create method
        Compensation createdCompensation = compensationService.create(testCompensation);

        // Validate 
        assertNotNull(createdCompensation);
        assertEquals(testCompensation.getSalary(), createdCompensation.getSalary(), 0.001);
    }
    
    /**
     * Ensure Service Correctly Reads Compensation By Employe Id
     */
    @Test
    public void testReadCompensation() {
        when(compensationRepository.findByEmployeeEmployeeId(testEmployeeId)).thenReturn(testCompensation);

        // Call the read method
        Compensation retrievedCompensation = compensationService.read(testEmployeeId);

        // Validate the result
        assertNotNull(retrievedCompensation);
        assertEquals(testEmployeeId, retrievedCompensation.getEmployee().getEmployeeId());
    }
}
