package com.test.server;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.test.db.handler.RetrieveRoomNames;
import com.test.persistence.DBQueries;
import com.test.resources.PECNode;
import com.test.resources.Room;
import com.test.resources.Switch;

/**
 * Servlet implementation class ServerController
 */
@WebServlet(name = "ServerController", urlPatterns = { "/ServerController" })
public class RoomNamesProvider extends HttpServlet {
	HttpSession session;
    private static final long serialVersionUID = 1L;
    
    List<String> roomNames ;
    List<Room> roomObjects = new ArrayList<Room>();
    StringBuffer names = new StringBuffer();
//	private String name = null;
 
    static Logger logger = Logger.getLogger(RoomNamesProvider.class);
     
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
    
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       
    	System.out.println("Inside server controller class  ");
    	logger.info("Inside server controller class  ");
    	String password = /*request.getParameter("password");*/ "1";
    	System.out.println(password);
    	
    	String errorMsg = authenticateUser(request, password);
    	 session = request.getSession();
        if(errorMsg != null){
            displayInvalidPasswordAndLoginAgain(request, response, errorMsg);
        }else{
        	Connection con = (Connection) getServletContext().getAttribute("DBConnection");
        	names = RetrieveRoomNames.getAllRoomNames(con);
        	if(names.length()>0){
        	String name  = null;
            name = names.substring(0, names.lastIndexOf(";"));
            
            System.out.println("namessssssss : " + name.toString());
//            response.setContentType("text/plain");
//    		PrintWriter out = response.getWriter();
//    		out.write(name.toString());
//    		out.flush();
//    		out.close();
//    		 System.out.println("namessssssss : " + name.toString());
//    		 System.out.println("namessssssss : " + name.toString());
//    		 System.out.println("namessssssss : " + name.toString());
              /*  User user = new User(rs.getString("name"), rs.getString("email"), rs.getString("country"), rs.getInt("id"));*/
//                logger.info("User found with details="+user);
//            roomObjects = getPecNodesAndCreateObjects(con);
           
//            getSwitchDetailsAndConstructObjs(con/*, roomObjects*/);
            session.setAttribute("rooms",name);
            
//            printRoomDetails(roomObjects);
            
                registerSessionAndDisplayRoomNames(request, response, password, name);
        	}else{
                showNoRoomsFound(request, response);
        	}
        }
    }

	private void printRoomDetails(List<Room> roomObjects) {

		System.out.println("***************************************************************************");
		int i = 1;
		for(Room room : roomObjects){
			System.out.println("Room no : " + i++);
			System.out.println("RoomName : " +room.getRoomName());
			System.out.println("RoomId : " +room.getRoomId());
			int j = 1;
			for(PECNode node : room.getNodes()){
				System.out.println("Node no : " + j++);
				System.out.println("NodeName : " +node.getNodeName());
				System.out.println("NodeId : " +node.getNodeId());
				System.out.println("Total switches : " + node.getSwitches().size());
				int k= 1;
					for(Switch swt : node.getSwitches()){
						System.out.println("SW no : " + k++);
						System.out.println("SWName : " +swt.getSwitchName());
						System.out.println("SWid : " +swt.getSwitchId());
						System.out.println("SW status : " + swt.getSwitchState());
					}
			}
		}
		
		System.out.println("********************************************************************8");
		
	}

	private void getSwitchDetailsAndConstructObjs(Connection con/*, List<Room> roomObjects*/) throws ServletException {

		logger.info("Retreiving switch details from node ids and creating switch Objects here");
		System.out.println("Retreiving switch details from node ids and creating switch Objects here");
		PreparedStatement ps = null;
        ResultSet rs = null;
		 try {
		if(roomObjects != null && roomObjects.size() > 0){
			System.out.println("Total roomssssssssssssssssssss = " + roomObjects.size());
		for(Room roomObj : roomObjects){
			
			List<PECNode> nodeObjs = roomObj.getNodes();
					for(PECNode node : nodeObjs){
											
		            ps = con.prepareStatement(DBQueries.GET_SWITCH_DETAILS);
		            ps.setString(1, node.getNodeId());
		            rs = ps.executeQuery();
		            if(rs != null){
		            while( rs.next()){
		                 System.out.println("Node id is : "+ rs.getString("node_id"));
		                 System.out.println("Switch id is : "+ rs.getInt("switch_id"));
		                 System.out.println("Switch name is : "+ rs.getString("switch_name"));
		                 System.out.println("Switch status is : "+ rs.getBoolean("switch_status"));
		                 Switch switchObj = new Switch(rs.getString("switch_name"), rs.getInt("switch_id"), rs.getBoolean("switch_status"));
		                 node.getSwitches().add(switchObj);
		                 System.out.println("Total switchess : " + node.getSwitches().size());
		            }
		            System.out.println("Switche objects updated");
		            }
					}
		}
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
			                System.out.println("SQLException in closing PreparedStatement or ResultSet");
			            }
			             
			        }
	}

	private List<Room> getPecNodesAndCreateObjects(Connection con) throws ServletException {
		logger.info("Retreiving nodes from room names and creating ROOM Objects here");
		System.out.println("Retreiving nodes from room names and creating ROOM Objects here");
		List<Room> roomObjects = new ArrayList<Room>();
		PreparedStatement ps = null;
        ResultSet rs = null;
		 try {
		if(roomNames != null && roomNames.size() > 0){
			System.out.println("Total RoomNamessssss" + roomNames.size());
		for(String roomName : roomNames){
		            ps = con.prepareStatement(DBQueries.GET_NODES_DETAILS);
		            ps.setString(1, roomName);
		            rs = ps.executeQuery();
		           
	                 Room roomObj = new Room();
	                
		            if(rs != null){
		            	
		            while( rs.next()){
		            	
		            	 roomObj.setRoomName(rs.getString("rooms"));
		                 roomObj.setRoomId(rs.getInt("room_id"));
			            System.out.println("Room name is : "+ rs.getString("rooms"));
		                 System.out.println("Room id is : "+ rs.getInt("room_id"));
		                 
		                 System.out.println("Node name is : "+ rs.getString("node_name"));
		                 System.out.println("Node id is : "+ rs.getInt("node_id"));
		                
		                 PECNode node = new PECNode();
		                 node.setNodeName(rs.getString("node_name"));
		                 node.setNodeId(rs.getString("node_id"));
		                 roomObj.getNodes().add(node);
		                 System.out.println("node created");
		            }
		            roomObjects.add(roomObj);
		            System.out.println("room obj created");
		            }
		}
		}
		}catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Database connection problem");
            throw new ServletException("DB Connection problem.");
        }/*finally{
            try {
            	System.out.println("inside servContr , closing all the resources here");
                rs.close();
                ps.close();
            } catch (SQLException e) {
                System.out.println("SQLException in closing PreparedStatement or ResultSet");;
            }
             
        }*/
		return roomObjects;
	}

	private void showNoRoomsFound(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.html");
		PrintWriter out= response.getWriter();
		System.out.println("No rooms found");
		out.println("<font color=red>No rooms found .</font>");
		rd.include(request, response);
	}

	private void registerSessionAndDisplayRoomNames(HttpServletRequest request,
			HttpServletResponse response, String password, String name) throws IOException, ServletException {
		
//		 session = request.getSession();
		session.setAttribute("User", password);
//		session.setAttribute("roomNames", roomNames);
		request.setAttribute("roomNames", roomNames);
//		response.setHeader("names", names.toString());
		
		logger.info ("List : " + roomNames);
		System.out.println("List : " + roomNames);
		System.out.println("redirecting to home page nowwwwwwwwwwwwwwwwwwww");
		
		RequestDispatcher rd = request.getRequestDispatcher("/home.jsp");
		System.out.println("forwarding");
		rd.forward(request, response);
		
//		 response .setContentType("java.lang.String");
// 		PrintWriter out = response.getWriter();
// 		out.println(name.toString());
// 		try {
//			rd.include(request, response);
//		} catch (ServletException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
 		 System.out.println("namessssssss : " + name);
		 System.out.println("namessssssss : " + name);
//		System.out.println("namessssssss : " + name.toString());
//		name = null;
//		response.sendRedirect("home.jsp");
//		PrintWriter out = response.getWriter();
//		out.se
	}

	private void displayInvalidPasswordAndLoginAgain(
			HttpServletRequest request, HttpServletResponse response,
			String errorMsg) throws IOException, ServletException {
		RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.html");
		PrintWriter out= response.getWriter();
		out.println("<font color=red>"+errorMsg+"</font>");
		rd.include(request, response);
	}

	private String authenticateUser(HttpServletRequest request, String password) {
        String errorMsg = null;
        if(password == null || password.trim().equals("") || !(password.equalsIgnoreCase("1"))){
            errorMsg = "Please check the password you have entered";
        }
        return errorMsg;
		
	}
 
}