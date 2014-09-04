package com.test.db.handler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DataProviderHelper {
	
	public static ResultSet retrieveDbData(Connection con, String query){
		
		
         System.out.println("using connection obj here " + con);
        PreparedStatement ps = null;
        ResultSet rs = null;
            try {
				ps = con.prepareStatement(query);
				 rs = ps.executeQuery();
			} catch (SQLException e) {
				e.printStackTrace();
			}
//            finally{
//		           try {
//		           	System.out.println("inside servContr , closing all the resources here");
////		               rs.close();
//		               ps.close();
//		           } catch (SQLException e) {
//		               System.out.println("SQLException in closing PreparedStatement or ResultSet");;
//		           }
//		            
//		       }
			return rs;
           
        } 

}
