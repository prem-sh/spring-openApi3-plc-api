package io.github.prem_sh.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="manufacturing_unit")
public class ManufacturingUnit {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "manufacturing_unit_id")
	private Long manufacturingUnitId;
	
	@Column(name="factlity_capacity")
	private String facilityCapacity;
	
	@Column(name="factlity_address")
	private String facilityAddress;
	
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
