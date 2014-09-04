package com.test.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.test.db.handler.DBHandlerUtil;
import com.test.db.handler.DataProviderHelper;
import com.test.persistence.DBQueries;
import com.test.util.SwitchStructureHelperUtil;

/**
 * Servlet implementation class ActionRenderer
 */
@WebServlet("/ActionRenderer")
public class SwitchClickServiceProvider extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static Logger logger = Logger.getLogger(SwitchDetailsProvider.class);
	HttpSession session;
	String switchDetailRequested;
	String[] swtDetailsList;
	String masterId= null;
	StringBuilder finalSwStatus = new StringBuilder("");
	int rowSize= 0;
	private String node_id;
	private String switchStructure;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SwitchClickServiceProvider() {
        super();
        // TODO Auto-generated constructor stub
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

		System.out.println(".............I am inside getting the action done when SW is clicked");
		
		finalSwStatus.delete(0, finalSwStatus.length());
		
		  Connection con = (Connection) getServletContext().getAttribute("DBConnection");
		ResultSet rs = DataProviderHelper.retrieveDbData(con, DBQueries.GET_MASTER_ID);
		
		
		if(rs != null){
			try {
				rs.next();
				masterId = rs.getString("masterid");
			} catch (SQLException e) {
				System.out.println("Exception while excuting database query");
				e.printStackTrace();
			}
			finally{
		           try {
		           	System.out.println("inside servContr , closing all the resources here");
		               rs.close();
//		               ps.close();
		           } catch (SQLException e) {
		               System.out.println("SQLException in closing PreparedStatement or ResultSet");;
		           }
		            
		       }
		}
		System.out.println("connection and result set is: " + con  + " " + rs + " "+ masterId);
		
		logger.info("Inside switche action class  ");
		System.out.println("swiiiiiittttttttttttttch:" + request.getParameter("swit"));
		switchDetailRequested = request.getParameter("swit");
		swtDetailsList = switchDetailRequested.split(";");
    	System.out.println(request.getParameter("swit"));
    	
    	 session = request.getSession();
    	 
      
         Connection connection = (Connection) getServletContext().getAttribute("DBConnection");
         System.out.println("using connection obj here to fetch switches of node" + con);
        PreparedStatement stt = null;
        ResultSet result = null;
        try {
            stt = connection.prepareStatement(DBQueries.GET_SWITCH_DETAILS);
            stt.setString(1,swtDetailsList[0].toString());
//            stt.setString(1,swtDetailsList[0].trim());
            result = stt.executeQuery();
            if(result != null){
//            	  result.beforeFirst();  
//            	  result.last();  
//            	  rowSize = result.getRow();  
//            	  System.out.println("Row size is: "  + rowSize);
            while( result.next() /*&& rowSize > 0*/){ 
            	node_id = result.getString("node_id");
            	System.out.println("Node id is : "+ result.getString("node_id"));
            	System.out.println("Switch id is : "+ result.getInt("switch_id"));
            	System.out.println("Switch status is : "+ result.getBoolean("switch_status"));
            	
            	if(result.getInt("switch_id") == 1){
            		
            		finalSwStatus.append(swtDetailsList[2].contains("ON") && swtDetailsList[1].contains("1") ? 1:0);
            		System.out.println("Switch is 1: and string is " + finalSwStatus);
            	}
            	if(result.getInt("switch_id") == 2){
            		finalSwStatus.append(swtDetailsList[2].contains("ON") && swtDetailsList[1].contains("2") ? 1:0);
            		System.out.println("Switch is 2: and string is " + finalSwStatus);
            	}
            	if(result.getInt("switch_id") == 3){
            		finalSwStatus.append(swtDetailsList[2].contains("ON") && swtDetailsList[1].contains("3") ? 1:0);
            		System.out.println("Switch is 3: and string is " + finalSwStatus);
            	}
            	if(result.getInt("switch_id") == 4){
            		finalSwStatus.append(swtDetailsList[2].contains("ON") ? 1:0);
            		System.out.println(swtDetailsList[2].contains("OFF") && swtDetailsList[1].contains("4") ? 1:0);
            	}
            	
            	}
            finalSwStatus = finalSwStatus.reverse();
            System.out.println("final string buffer is" + finalSwStatus);
//            getSWDetails(con);
//
//            session.setAttribute("swts", swts);
//            RequestDispatcher rd = request.getRequestDispatcher("/SwDisplay.jsp");
//    		System.out.println("forwarding");
//    		rd.forward(request, response);
	}
}catch (SQLException e) {
    e.printStackTrace();
    System.out.println("Database connection problem");
    throw new ServletException("DB Connection problem.");
}finally{
    try {
    	System.out.println("inside servContr , closing all the resources here");
//        result.close();
        stt.close();
    } catch (SQLException e) {
        System.out.println("SQLException in closing PreparedStatement or ResultSet");;
    }
    	 
    
}
    	
    	 try {
    		 System.out.println("Calling shell script hereeeeee");
    		 
    		 System.out.println("finalSwStatusssssssss +" + "0"+finalSwStatus);
    		 
    			 switchStructure = SwitchStructureHelperUtil.getSwitchStructure(("0"+finalSwStatus).toString().trim());
    		 
    		 System.out.println("the final paramsssssssssssssssssssssss: " + "MSID="+masterId+ " " + "NDID="+node_id+ " " + "CMID=0x30"+ " "+ "STST="+switchStructure);
    		 
             Process proc = Runtime.getRuntime().exec(new String[]{"/home/pi/HomeAuto/src/srv/serverTx/sendToRF.o", "MSID="+masterId, "NDID="+node_id,"CMID=0x30", "STST="+switchStructure});
             BufferedReader read = new BufferedReader(new InputStreamReader(
                     proc.getInputStream()));
             
             System.out.println("The shell script is calleddddddd ");
             try {
                 proc.waitFor();
             } catch (InterruptedException e) {
                 System.out.println(e.getMessage());
             }
             while (read.ready()) {
                 System.out.println(read.readLine());
             }
         } catch (Exception e) {
             System.out.println("inside exception : " + e.getMessage());
         }
    	 
//    	 try {
//             // Run the process
//             Process p = Runtime.getRuntime().exec("javaSystemCall.sh");
//             // Get the input stream
//             InputStream is = p.getInputStream();
//   
//             // Read script execution results
//             int i = 0;
//             StringBuffer sb = new StringBuffer();
//             while ( (i = is.read()) != -1)
//                 sb.append((char)i);
//   
//             System.out.println(sb.toString());
//   
//         } catch (IOException e) {
//             e.printStackTrace();
//         }
	  
    	 System.out.println(" script calledddddddddddddd");
    	 
    	 session.setAttribute("actionren", "Yay i am clicked ");
//         RequestDispatcher rd = request.getRequestDispatcher("/final.jsp");
 		System.out.println("checking update operation to DBbbbbbbbbb");
// 		rd.forward(request, response);
 		
// 		DBHandlerUtil.updateSwitchStatus("0x47", "000");
 		
 		
}
	

}
