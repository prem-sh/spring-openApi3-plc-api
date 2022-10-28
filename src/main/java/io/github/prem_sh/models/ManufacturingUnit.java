package io.github.prem_sh.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name="manufacturing_unit")
public class ManufacturingUnit {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "manufacturing_unit_id")
	private Long manufacturingUnitId;
	
	@Size(min=1, max=15)
	@Column(name="factlity_capacity", length = 15)
	private String facilityCapacity;
	
	@Size(min=3)
	@Column(name="factlity_address")
	private String facilityAddress;
	
	@NotNull
	@Column(name="country")
	private String country;

	public Long getManufacturingUnitId() {
		return manufacturingUnitId;
	}

	public void setManufacturingUnitId(Long manufacturingUnitId) {
		this.manufacturingUnitId = manufacturingUnitId;
	}

	public String getFacilityCapacity() {
		return facilityCapacity;
	}

	public void setFacilityCapacity(String facilityCapacity) {
		this.facilityCapacity = facilityCapacity;
	}

	public String getFacilityAddress() {
		return facilityAddress;
	}

	public void setFacilityAddress(String facilityAddress) {
		this.facilityAddress = facilityAddress;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

}
