package com.test.server;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.test.persistence.DBQueries;

/**
 * Servlet implementation class ProfilesRenderer
 */
@WebServlet("/ProfilesRenderer")
public class ProfilesRenderer extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private List<String> profileNames = new ArrayList<String>();
	private StringBuffer profiles;
	private HttpSession session;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProfilesRenderer() {
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
		System.out.println("inside profiles renderer");
		 session = request.getSession();
		 Connection con = (Connection) getServletContext().getAttribute("DBConnection");
         System.out.println("using connection obj here in profile renderer " + con);
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = con.prepareStatement(DBQueries.GET_PROFILE_NAMES);
            rs = ps.executeQuery();
            System.out.println("rsssssssss in profile renderer is :" +  rs.next());
            if(rs != null){
            while( rs.next()){
                 System.out.println("Profile name is : "+ rs.getString("profile_name"));
                 profileNames.add(rs.getString("rooms"));
            }
            profiles = new StringBuffer();
            if(!profileNames.isEmpty()){
            for(String profName: profileNames){
            	profiles.append(profName.toString() + ";");
            }
            }
            session.setAttribute("profile_name",profiles.toString());
            RequestDispatcher rd = request.getRequestDispatcher("/profileNames.jsp");
    		System.out.println("forwarding");
    		rd.forward(request, response);
            }
        } catch (SQLException e) {
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

}
