package io.github.prem_sh.dto;

public class DeviceIdentityInputDto {
	
	private String deviceId;
	private Long plcModelId;
	private Long manufacturingUnitId;
	private String mfd;
	private Boolean qualityCheck;
	
	
	public String getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
	public Long getPlcModelId() {
		return plcModelId;
	}
	public void setPlcModelId(Long plcModelId) {
		this.plcModelId = plcModelId;
	}
	public Long getManufacturingUnitId() {
		return manufacturingUnitId;
	}
	public void setManufacturingUnitId(Long manufacturingUnitId) {
		this.manufacturingUnitId = manufacturingUnitId;
	}
	public String getMfd() {
		return mfd;
	}
	public void setMfd(String mfd) {
		this.mfd = mfd;
	}
	public Boolean getQualityCheck() {
		return qualityCheck;
	}
	public void setQualityCheck(Boolean qualityCheck) {
		this.qualityCheck = qualityCheck;
	}
	
}
