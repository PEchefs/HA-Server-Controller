package com.test.db.handler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;

import com.test.persistence.DBQueries;

public class RetrieveRoomNames {

	
	
	private static ArrayList<String> roomNames;
	private static StringBuffer names;

	public static StringBuffer getAllRoomNames(Connection con){

        
        
        System.out.println("using connection obj here in RetrieveRoomNames" + con);
    	ResultSet rs = DataProviderHelper.retrieveDbData(con, DBQueries.GET_ROOM_NAMES);
//       PreparedStatement ps = null;
//       ResultSet rs = null;
       try {
//           ps = con.prepareStatement(DBQueries.GET_ROOM_NAMES);
//           rs = ps.executeQuery();
    	   System.out.println("rssssss is : "+rs);
//    	   System.out.println("rssssss is : "+rs.next());
           roomNames = new ArrayList<String>();
           if(rs != null){
           while( rs.next()){
                System.out.println("Room name is : "+ rs.getString("rooms"));
                roomNames.add(rs.getString("rooms"));
           }
           names = new StringBuffer();
           for(String room: roomNames){
           	 names.append(room.toString() + ";");
           }
           System.out.println("namessssssss : " + names.toString());
           
               
           }
       } catch (SQLException e) {
           e.printStackTrace();
           System.out.println("Database connection problem");
           new Exception(e.getMessage());
       }finally{
           try {
           	System.out.println("inside servContr , closing all the resources here");
               rs.close();
//               ps.close();
           } catch (SQLException e) {
               System.out.println("SQLException in closing PreparedStatement or ResultSet");;
           }
            
       }
	return names;
       
	}
}
