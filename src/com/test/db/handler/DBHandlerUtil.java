package com.test.db.handler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import com.test.persistence.DBConnectionManager;
import com.test.persistence.DBQueries;

public final class DBHandlerUtil {
	
	
//	public static byte updateSwitchStatus(String nodeId, String statusInfo){
	public static byte main(String[] args) {
		
		for(int z= 0; z< args.length; z++){
			System.out.println("the arguments are : " + args[z]);
		}
		
		String nodeId = args[0];
		String statusInfo = args[1];
		
		Connection dbConnection = getConnection();
		System.out.println("Connection object inside DBHandlerUtil : " + dbConnection);
		
		byte success = 0;
		if(dbConnection != null){
		PreparedStatement ps = null;
	    ResultSet rs = null;
	        try {
	            ps = dbConnection.prepareStatement(DBQueries.GET_SWITCH_DETAILS);
	            ps.setString(1,nodeId.trim());
	            rs = ps.executeQuery();
	            if(rs != null){
	            	int i = statusInfo.length();
	            	int size = statusInfo.length();
	            	int j =1;
	            	
	                while( rs.next() && i >=0){
	                	 System.out.println("Node id is : "+ rs.getString("node_id"));
		                 System.out.println("Switch id is : "+ rs.getInt("switch_id"));
		                 System.out.println("Switch status is : "+ rs.getBoolean("switch_status"));
		                 
//		                 if(rs.getInt("switch_id") == 1){
		                	 int status = Character.getNumericValue(statusInfo.charAt(size-rs.getInt("switch_id")));
		                	 System.out.println("the status from statusInfo is : " + Character.getNumericValue(statusInfo.charAt(size-rs.getInt("switch_id"))));
		                	 PreparedStatement prepStt =  dbConnection.prepareStatement("UPDATE nodes SET switch_status="+ status +" WHERE switch_id=" + rs.getInt("switch_id") + " AND node_id=" + rs.getString("node_id"));
		                	 success = (byte) prepStt.executeUpdate();
//		                 }
		                 i--;
		                 
		                 
	                }
	            }
	            success = 1;
	        }catch (SQLException e) {
	            e.printStackTrace();
	            System.out.println("Database connection problem");
	        }finally{
	            try {
	            	System.out.println("inside servContr , closing all the resources here");
	                rs.close();
	                ps.close();
	            } catch (SQLException e) {
	                System.out.println("SQLException in closing PreparedStatement or ResultSet");
	            }
	             
	        }
	            
		}else{
			success = 0;
		}
		return success;
	}
	
	
	
	private static Connection getConnection(){
	 Connection con = null;
	try {
		DBConnectionManager connectionManager = new DBConnectionManager("jdbc:mysql://localhost:3306/pec", "root", "root");
		 con = connectionManager.getDBConnection();
	} catch (ClassNotFoundException e) {
		e.printStackTrace();
	} catch (SQLException e) {
		e.printStackTrace();
	}
	 
	return con;
	}

}
