package com.test.resources;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

public class PECNode {

	private String nodeName = "Node Undefined";
	private  String nodeId;
	
	@JsonProperty("switches")
	private List<Switch> switches;
	
	public PECNode() {
		
	}
	
	
	public String getNodeName() {
		return nodeName;
	}
	
	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}
	
	public String getNodeId() {
		return nodeId;
	}
	
	public void setNodeId(String nodeId) {
		this.nodeId = nodeId;
	}
	
	public List<Switch> getSwitches() {
		if(null == switches){
			System.out.println("Switch is null");
			switches =  new ArrayList<Switch>();
		}
		return switches;
	}
	
	public void setSwitches(List<Switch> switches) {
		this.switches = switches;
	}
	
	
}
