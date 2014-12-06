package com.test.server;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Test {
	
	@JsonProperty("add")
	String add;
	
	public Test(){
		
	}
	
	public void set(String add){
		this.add = add;
	}
	
	public String get(String add){
		return this.add ;
	}

	@Override
	public String toString(){
		return add;
		
	}
}
