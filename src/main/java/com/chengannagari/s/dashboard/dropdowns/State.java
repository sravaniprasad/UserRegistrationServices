package com.chengannagari.s.dashboard.dropdowns;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity(name = "state")
public class State {
	@Id
	private long id;

	@Column(name = "name")
	private String name;

	@Column(name = "country_id")
	private long countryId;

	@Column(name = "country_code")
	private String countryCode;

}
