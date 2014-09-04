package com.test.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;

import com.test.db.handler.RetrieveRoomNames;
import com.test.persistence.DBQueries;
import com.test.resources.PECNode;
import com.test.resources.Room;
import com.test.resources.Switch;

/**
 * Servlet implementation class AddProfilesDataProvider
 */
@WebServlet(name = "AddProfilesDataProvider", urlPatterns = { "/AddProfilesDataProvider" })
public class AddProfilesDataProvider extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private StringBuffer names = new StringBuffer();
	List<Room> roomObjects = new ArrayList<Room>();
	 static Logger logger = Logger.getLogger(AddProfilesDataProvider.class);
	 String[] roomNames;
	 HttpSession session; 
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddProfilesDataProvider() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("fetching the profile creation details");
//		
		session = request.getSession();
		Connection con = (Connection) getServletContext().getAttribute("DBConnection");
    	names  = RetrieveRoomNames.getAllRoomNames(con);
    	if(names.length()>0){
        roomNames  = names.substring(0, names.lastIndexOf(";")).split(";");
        roomObjects = getPecNodesAndCreateObjects(con);
        
        getSwitchDetailsAndConstructObjs(con/*, roomObjects*/);
        session.setAttribute("roomObjects",roomObjects);
        
        printRoomDetails(roomObjects);
		}
		
		  // 1. get received JSON data from request
        BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
        System.out.println("received JSON is" + br);
        String json = "";
        if(br != null){
            json = br.readLine();
        }
        System.out.println("received JSON is" + json);
 
        // 2. initiate jackson mapper
        ObjectMapper mapper = new ObjectMapper();
 
        // 3. Convert received JSON to Article
        Test article = mapper.readValue(json, Test.class);
 
        // 4. Set response type to JSON
        response.setContentType("application/json");            
 
        // 5. Add article to List<Article>
        List articles = new ArrayList(); 
        articles.add(article);
        
        System.out.println("sending json" + json);
 
        // 6. Send List<Article> as JSON to client
        mapper.writeValue(response.getOutputStream(), articles);
		
	}
    	
    	private void printRoomDetails(List<Room> roomObjects) {

    		System.out.println("8888888888888888888888888888888888888888888888888888888888888888888888888888");
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
    		
    		System.out.println("8888888888888888888888888888888888888888888888888888888888888888888888888888888");
    		
    	}
    	
    	private List<Room> getPecNodesAndCreateObjects(Connection con) throws ServletException {
    		logger.info("Retreiving nodes from room names and creating ROOM Objects here");
    		System.out.println("Retreiving nodes from room names and creating ROOM Objects here");
    		List<Room> roomObjects = new ArrayList<Room>();
    		PreparedStatement ps = null;
            ResultSet rs = null;
    		 try {
    		if(roomNames != null && roomNames.length > 0){
    			System.out.println("Total RoomNamessssss" + roomNames.length);
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
    		                 node.setNodeId(rs.getInt("node_id"));
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
    		            ps.setInt(1, node.getNodeId());
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

}
