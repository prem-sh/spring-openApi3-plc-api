package io.github.prem_sh.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="plc")
public class Plc {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "plc_id")
	private Long plcId;
	
	@NotNull
	@Column(name = "model_name")
	private String model;
	
	@Column(name = "inputs")
	private Integer inputs;
	
	@Column(name = "output")
	private Integer output;

	public Long getPlcId() {
		return plcId;
	}

	public void setPlcId(Long plcId) {
		this.plcId = plcId;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public Integer getInputs() {
		return inputs;
	}

	public void setInputs(Integer inputs) {
		this.inputs = inputs;
	}

	public Integer getOutput() {
		return output;
	}

	public void setOutput(Integer output) {
		this.output = output;
	}
	
	
}
