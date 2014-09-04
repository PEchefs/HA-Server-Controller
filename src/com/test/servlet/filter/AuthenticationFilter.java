package com.test.servlet.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

/**
 * Servlet implementation class AuthenticationFilter
 */
@WebFilter("/AuthenticationFilter")
public class AuthenticationFilter implements Filter {
 
    private Logger logger = Logger.getLogger(AuthenticationFilter.class);
     
    public void init(FilterConfig fConfig) throws ServletException {
        logger.info("AuthenticationFilter initialized");
        System.out.println("AuthenticationFilter initialized");
    }
     
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
    	
    	System.out.println("do filter method is called here ");
 
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
         
        String uri = req.getRequestURI();
        logger.info("Requested Resource::"+uri);
        System.out.println("Requested Resource::"+uri);
         
        HttpSession session = req.getSession(false);
         
        System.out.println("session object : " + session);
        if(session == null && !(uri.endsWith("html") || uri.endsWith("Login"))){
            logger.error("Unauthsorized access request");
            System.out.println("Unauthorized access request");
            res.sendRedirect("login.html");
        }else{
            // pass the request along the filter chain
        	System.out.println("Filtering done hereeeee");
            chain.doFilter(request, response);
        }
         
         
    }
 
    public void destroy() {
        //close any resources here
    }
 
}