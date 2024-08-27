package com.chengannagari.s.dashboard.dropdownsRepository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.chengannagari.s.dashboard.dropdowns.District;
@Repository
@EnableJpaRepositories
public interface DistrictRepository extends JpaRepository<District, Long> {
	List<District> findByStateId(long id);
	
}
