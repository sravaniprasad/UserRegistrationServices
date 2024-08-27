package com.chengannagari.s.dashboard.dropdowns;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity(name = "district")
public class District {
	@Id
	private long id;

	private String name;
	
	@Column(name = "state_id")
	private long stateId;
	
	

}