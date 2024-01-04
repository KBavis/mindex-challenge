package com.mindex.challenge.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.service.CompensationService;

@RestController
@RequestMapping("/compensation")
public class CompensationController {
	
	@Autowired
    private CompensationService compensationService;

	
	/**
	 * Create Compensation
	 * 
	 * @param compensation
	 * @return
	 */
    @PostMapping
    public Compensation createCompensation(@RequestBody Compensation compensation) {
        return compensationService.create(compensation);
    }
    
    /**
     * Read Compensation Based on Employee Id
     * 
     * @param employeeId
     * @return
     */
    @GetMapping("/{employeeId}")
    public Compensation readCompensation(@PathVariable String employeeId) {
        return compensationService.read(employeeId);
    }
}
