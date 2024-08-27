package com.chengannagari.s.dashboard.serviceImplementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chengannagari.s.dashboard.Entity.HealthCheckStatus;
import com.chengannagari.s.dashboard.Reposiyory.HealthCheckRepository;
import com.chengannagari.s.dashboard.service.HealthCheckService;

@Service
public class HealthService implements HealthCheckService{

	@Autowired
	private HealthCheckRepository hr;
	@Override
	public HealthCheckStatus addData(HealthCheckStatus hs) {
		// TODO Auto-generated method stub
		return hr.save(hs);
	}

	

	
}
