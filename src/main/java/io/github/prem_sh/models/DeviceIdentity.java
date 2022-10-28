package io.github.prem_sh.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="device_identity")
public class DeviceIdentity {
	@Id
	@Column(name = "plc_id")
	private String deviceId;
	
	@ManyToOne
	@JoinColumn(name = "plc_model_id", referencedColumnName = "plc_id")
	private Plc plcModel;
	
	@ManyToOne
	@JoinColumn(name = "manufacturing_unit_id", referencedColumnName = "manufacturing_unit_id")
	private ManufacturingUnit manufacturingUnit;
	
	@Column(name = "mfd")
	@Temporal(TemporalType.DATE)
	private Date mfd;
	
	@Column(name = "qc", columnDefinition = "boolean default false")
	private Boolean qualityCheck;

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public Plc getPlcModel() {
		return plcModel;
	}

	public void setPlcModel(Plc plcModel) {
		this.plcModel = plcModel;
	}

	public ManufacturingUnit getManufacturingUnit() {
		return manufacturingUnit;
	}

	public void setManufacturingUnit(ManufacturingUnit manufacturingUnit) {
		this.manufacturingUnit = manufacturingUnit;
	}

	public Date getMfd() {
		return mfd;
	}

	public void setMfd(Date mfd) {
		this.mfd = mfd;
	}

	public Boolean getQualityCheck() {
		return qualityCheck;
	}

	public void setQualityCheck(Boolean qualityCheck) {
		this.qualityCheck = qualityCheck;
	}
}
