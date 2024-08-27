package com.chengannagari.s.dashboard.Reposiyory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.chengannagari.s.dashboard.Entity.HealthCheckStatus;
import com.chengannagari.s.dashboard.service.HealthCheckService;

@Repository
public interface HealthCheckRepository extends JpaRepository<HealthCheckStatus, Integer> {

	//String save(HealthCheckService healthCheck);

}
