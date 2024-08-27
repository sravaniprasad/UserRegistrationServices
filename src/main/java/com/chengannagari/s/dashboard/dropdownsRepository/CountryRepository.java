package com.chengannagari.s.dashboard.dropdownsRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.chengannagari.s.dashboard.dropdowns.Country;

@EnableJpaRepositories
@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {

}
