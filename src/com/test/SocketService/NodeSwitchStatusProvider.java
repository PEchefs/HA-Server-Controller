package com.test.SocketService;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.test.persistence.DBQueries;

/**
 * Servlet implementation class NodeSwitchStatusProvider
 */
@WebServlet("/NodeSwitchStatusProvider")
public class NodeSwitchStatusProvider extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private StringBuffer finalSwStatus = new StringBuffer();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NodeSwitchStatusProvider() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


		System.out.println("....Using socket service to fetch node switch details");
		
		String nodeIdValue = request.getParameter("node").toString();
		
	     Connection connection = (Connection) getServletContext().getAttribute("DBConnection");
	     System.out.println("using connection obj here to fetch switches of node" + connection);
	    PreparedStatement stt = null;
	    ResultSet result = null;
	    try {
	        stt = connection.prepareStatement(DBQueries.GET_SWITCH_DETAILS);
	        stt.setString(1,nodeIdValue);
	        result = stt.executeQuery();
	        if(result != null){
	        while( result.next()){
	        	 String node_id = result.getString("node_id");
	        	System.out.println("Node id is : "+ result.getString("node_id"));
	        	System.out.println("Switch id is : "+ result.getInt("switch_id"));
	        	System.out.println("Switch status is : "+ result.getBoolean("switch_status"));
	        	
	        		finalSwStatus .append(result.getBoolean("switch_status") ? 1:0);
	        }
	        
	        		System.out.println("Before reversing" + finalSwStatus);
	        		finalSwStatus = finalSwStatus.reverse();
	        		System.out.println("final string buffer after reversing is" + finalSwStatus);
	}
	}catch (SQLException e) {
	e.printStackTrace();
	System.out.println("Database connection problem");
	throw new ServletException("DB Connection problem.");
	}finally{
	try {
		finalSwStatus.setLength(0);
		System.out.println("inside servContr , closing all the resources here");
//	    result.close();
	    stt.close();
	} catch (SQLException e) {
	    System.out.println("SQLException in closing PreparedStatement or ResultSet");;
	}
		 

	}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
