/**
 * 
 */
package com.test.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author a312kuma
 *
 */
public class DBConnectionManager {

	private Connection connection;
	
	public DBConnectionManager(String dbURL, String user, String pwd) throws ClassNotFoundException, SQLException{
        Class.forName("com.mysql.jdbc.Driver");
        this.connection = DriverManager.getConnection(dbURL, user, pwd);
    }
	
	public Connection getDBConnection(){
        return this.connection;
    }
}
