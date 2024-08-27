package com.chengannagari.s.dashboard.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chengannagari.s.dashboard.dropdowns.Country;
import com.chengannagari.s.dashboard.dropdowns.District;
import com.chengannagari.s.dashboard.dropdowns.State;
import com.chengannagari.s.dashboard.dropdownsRepository.CountryRepository;
import com.chengannagari.s.dashboard.dropdownsRepository.DistrictRepository;
import com.chengannagari.s.dashboard.dropdownsRepository.StateRepository;

@Service
public class DropdownService {

	@Autowired
	private CountryRepository countryRepository;

	@Autowired
	private StateRepository stateRepository;

	@Autowired
	private DistrictRepository cityRepository;

	public List<Country> getCountry() {
		return countryRepository.findAll();
	}

	public List<State> getStateByCountryId(long countryId) {
		return stateRepository.findByCountryId(countryId);
	}

	public List<District> getCityByStateId(long stateId) {
		return cityRepository.findByStateId(stateId);
	}

}