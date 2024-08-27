package com.chengannagari.s.dashboard.dropdowns;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity(name = "country")
public class Country {
	@Id
	private long id;

	private String name;

}
