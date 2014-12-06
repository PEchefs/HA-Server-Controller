package com.test.server;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import com.test.persistence.DBQueries;
import com.test.resources.PECNode;
import com.test.resources.Room;
import com.test.resources.Switch;

/**
 * Servlet implementation class SwitcheDetails
 */
@WebServlet(name = "SwitchDetails", urlPatterns = { "/SwitchDetails" })
public class SwitchDetailsProvider extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static Logger logger = Logger.getLogger(SwitchDetailsProvider.class);
	HttpSession session;
	List nodeIds;
	private ArrayList<String> swts;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SwitchDetailsProvider() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println(" inside Switch Get method \n");
		doPost(request,response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		session = request.getSession();
		
		System.out.println("Here inside Switch details provider \n");
		System.out.println("and session value is : " +   session.getAttribute("switchesDetail"));
		System.out.println("request.getParameter(button1) is : " + request.getParameter("button1"));
		String room = request.getParameter("button1");
		
		if(room == null){
			 room = (String) session.getAttribute("switchesDetail");
		}else{
			 session.setAttribute("switchesDetail", room);
		}
		
		logger.info("Inside fetching switches class  ");
//    	String room = request.getParameter("button1");
    
    	
    	
      
         Connection con = (Connection) getServletContext().getAttribute("DBConnection");
         System.out.println("using connection obj here to fetch node switches" + con);
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = con.prepareStatement(DBQueries.GET_NODES_DETAILS);
            ps.setString(1,room.trim());
            rs = ps.executeQuery();
            nodeIds = new ArrayList();
            if(rs != null){
            while( rs.next()){
            	  System.out.println("Room name is : "+ rs.getString("rooms"));
	                 System.out.println("Room id is : "+ rs.getInt("room_id"));
	                 
	                 System.out.println("Node name is : "+ rs.getString("node_name"));
	                 System.out.println("Node id isssssssssssssss : "+ rs.getString("node_id"));
	                 nodeIds.add(rs.getString("node_id"));
            }
            getSWDetails(con);

            session.setAttribute("swts", swts);
            
            response.addHeader("Refresh", "5");
            
            RequestDispatcher rd = request.getRequestDispatcher("/SwDisplay.jsp");
    		System.out.println("forwarding");
    		rd.forward(request, response);
	}

}catch (SQLException e) {
    e.printStackTrace();
    System.out.println("Database connection problem");
    throw new ServletException("DB Connection problem.");
}finally{
    try {
    	System.out.println("inside servContr , closing all the resources here");
        rs.close();
        ps.close();
    } catch (SQLException e) {
        System.out.println("SQLException in closing PreparedStatement or ResultSet");;
    }
}
	}
	
	
	private void getSWDetails(Connection con) throws ServletException {

		logger.info("Retreiving switch details from node ids");
		System.out.println("Retreiving switch details from node ids");
		PreparedStatement ps = null;
        ResultSet rs = null;
		 try {
			 swts = new ArrayList<String>();
		if(nodeIds != null && nodeIds.size() > 0){
		for(Object node : nodeIds){
						
		            ps = con.prepareStatement(DBQueries.GET_SWITCH_DETAILS);
		            ps.setString(1, node.toString());
		            rs = ps.executeQuery();
		           
		            if(rs != null){
		            while( rs.next()){
		                 System.out.println("Node id is : "+ rs.getString("node_id"));
		                 System.out.println("Switch id is : "+ rs.getInt("switch_id"));
		                 System.out.println("Switch name is : "+ rs.getString("switch_name"));
		                 System.out.println("Switch status is : "+ rs.getBoolean("switch_status"));
		                 boolean bool = rs.getBoolean("switch_status");
		                 String str = rs.getString("switch_name") +  ";"+ rs.getString("node_id") +  ";"+ rs.getInt("switch_id") +  ";" + (rs.getBoolean("switch_status") ? "ON": "OFF");
		                 swts.add(str);
		                 
		            }
		            System.out.println("Switche objects updated");
		            }
					}
		 System.out.println("Total switchess : " +  swts);
		}
		}catch (SQLException e) {
			            e.printStackTrace();
			            System.out.println("Database connection problem");
			            throw new ServletException("DB Connection problem.");
			        }
			             
			        }
	}
	

	