package com.chengannagari.s.dashboard.dropdownsRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.chengannagari.s.dashboard.dropdowns.Country;
import com.chengannagari.s.dashboard.dropdowns.State;
@Repository
@EnableJpaRepositories
public interface StateRepository extends JpaRepository<State, Long> {
	List<State> findByCountryId(long id);
	
}
