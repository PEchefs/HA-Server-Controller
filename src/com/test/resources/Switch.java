package com.test.resources;

import org.codehaus.jackson.annotate.JsonProperty;

public class Switch {
	private String switchName = "Switch Undefined";
	
	@JsonProperty("switchId")
	private int switchId ;
	
	@JsonProperty("switchState")
	private boolean switchState;
	
	public Switch(String switchName, int switchId, boolean switchState) {
		super();
		this.switchName = switchName;
		this.switchId = switchId;
		this.switchState = switchState;
	}
	
	public String getSwitchName() {
		return switchName;
	}
	
	public void setSwitchName(String switchName) {
		this.switchName = switchName;
	}
	
	public int getSwitchId() {
		return switchId;
	}
	
	public void setSwitchId(int switchId) {
		this.switchId = switchId;
	}
	
	public boolean getSwitchState() {
		return switchState;
	}
	
	public void setSwitchState(boolean switchState) {
		this.switchState = switchState;
	}

}

