package com.test.resources;

import java.util.ArrayList;
import java.util.List;

public class Room {
	
	private String roomName = "Room Name undefined";
	private int roomId;
	private List<PECNode> nodes;
	
	public Room() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getRoomName() {
		return roomName;
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}

	public int getRoomId() {
		return roomId;
	}

	public void setRoomId(int roomId) {
		this.roomId = roomId;
	}

	public List<PECNode> getNodes() {
		if(nodes == null){
			nodes = new ArrayList<PECNode>();
		}
		return nodes;
	}

	public void setNodes(List<PECNode> nodes) {
		this.nodes = nodes;
	}
	
	
	

}
