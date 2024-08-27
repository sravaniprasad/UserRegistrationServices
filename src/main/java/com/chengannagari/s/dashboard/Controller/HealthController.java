package com.chengannagari.s.dashboard.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chengannagari.s.dashboard.Entity.HealthCheckStatus;
import com.chengannagari.s.dashboard.Entity.UserDTO;
import com.chengannagari.s.dashboard.service.HealthCheckService;

@RestController
@CrossOrigin
@RequestMapping("/api/health")
public class HealthController {

	@Autowired
	private HealthCheckService healthService;
	
	//Create status
		@PostMapping("/save")
		public ResponseEntity<HealthCheckStatus> saveEmployee(@RequestBody HealthCheckStatus status){
			
			return new ResponseEntity<HealthCheckStatus>(healthService.addData(status),HttpStatus.CREATED);
		}
}
