package com.mindex.challenge.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mindex.challenge.dao.CompensationRepository;
import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.service.CompensationService;

@Service
public class CompensationServiceImpl implements CompensationService{
	
	private static final Logger LOG = LoggerFactory.getLogger(EmployeeServiceImpl.class);

    @Autowired
    private CompensationRepository compensationRepository;
    
    /**
     * Creating New Compensation
     */
    @Override
    public Compensation create(Compensation compensation) {
    	LOG.debug("Creating compensation [{}]", compensation);
    	
    	return compensationRepository.save(compensation);
    }
    
    /**
     * Reading Compensation By Employee Id
     */
    @Override
    public Compensation read(String id) {
        LOG.debug("Creating compensation with id [{}]", id);
        
        //Find Compensation By Employee Id
        Compensation compensation = compensationRepository.findByEmployeeEmployeeId(id);

        if (compensation == null) {
            throw new RuntimeException("Invalid employeeId: " + id);
        }

        return compensation;
    }
}
