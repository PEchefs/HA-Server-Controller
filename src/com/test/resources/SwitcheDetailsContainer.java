package com.test.resources;

import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

public class SwitcheDetailsContainer {
	
	@JsonProperty("roomsCollection")
	private List<Room> roomsList;

	public SwitcheDetailsContainer(List lis){
		roomsList = lis;
	}
	
	public List<Room> getRoomsList() {
		return roomsList;
	}

	public void setRoomsList(List<Room> roomsList) {
		this.roomsList = roomsList;
	}

}
