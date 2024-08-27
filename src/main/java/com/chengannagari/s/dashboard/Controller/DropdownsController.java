package com.chengannagari.s.dashboard.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.chengannagari.s.dashboard.dropdowns.Country;
import com.chengannagari.s.dashboard.dropdowns.District;
import com.chengannagari.s.dashboard.dropdowns.State;
import com.chengannagari.s.dashboard.dropdownsRepository.CountryRepository;
import com.chengannagari.s.dashboard.dropdownsRepository.DistrictRepository;
import com.chengannagari.s.dashboard.dropdownsRepository.StateRepository;
import com.chengannagari.s.dashboard.service.DropdownService;

@RestController
@RequestMapping("api/user")
@CrossOrigin("http://localhost:4200")  
public class DropdownsController {

	
	 @Autowired
	    private CountryRepository countryRepository;
	 @Autowired
	    private StateRepository stateRepository;
	 @Autowired
	    private DistrictRepository districtRepository;
	    
	    
	 @Autowired
		private DropdownService dropdownService;
		
		@GetMapping("/getCountry")
		public List<Country> getCountry() {
			return dropdownService.getCountry();
		}

		@GetMapping("/getStateByCountryId")
		public List<State> getStateByCountryId(@RequestParam(name = "country_id") long countryId) {
			return dropdownService.getStateByCountryId(countryId);
		}

		@GetMapping("/getDistrictByStateId")
		public List<District> getCityByStateId(@RequestParam(name = "state_id") long stateId) {
			return dropdownService.getCityByStateId(stateId);
		}
}
