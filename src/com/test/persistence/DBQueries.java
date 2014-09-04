package com.test.persistence;

public interface DBQueries {

	String GET_ROOM_NAMES = "select distinct rooms from rooms";
	String GET_NODES_DETAILS = "select * from rooms where rooms = ?";
	String GET_SWITCH_DETAILS = "select * from nodes where node_id = ?";
	String GET_MASTER_ID = "select * from masterid;";
	String GET_PROFILE_NAMES = "select distinct profile_name from profiles";
}
