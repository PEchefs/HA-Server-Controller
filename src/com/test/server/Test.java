package com.test.server;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Test {
	
	@JsonIgnore
	String add;
	
	public Test(){
		
	}
	
	public void set(String add){
		this.add = add;
	}
	
	public String get(String add){
		return this.add ;
	}

}
