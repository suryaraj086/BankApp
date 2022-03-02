
package mypack;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Map;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import db.*;
import myexception.CustomException;


public class LoginController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	public LoginController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		DBLayer dbObj=new DBLayer();
		APILayer logicLayer=null;
		
		try {
			logicLayer=new APILayer();
		} catch (ClassNotFoundException | IOException | CustomException e1) {
			e1.printStackTrace();
		}
		
		PrintWriter out = response.getWriter();
        String page=request.getParameter("page");
       
        if(page.equals("login"))
        {
        	int id = Integer.parseInt(request.getParameter("id"));
 	     	String password = request.getParameter("password");
 	        HttpSession session = request.getSession();
 	        session.setAttribute("customerId", id);
		try {
			boolean role=dbObj.login(id, password);
			if (role) 
			{
			    RequestDispatcher rd=request.getRequestDispatcher("adminmenu.jsp");  
			    rd.forward(request, response);  
			}
			else
			{
			      Map<Long, AccountInfo> userMap=logicLayer.retrieveAccount(id);    
			      request.setAttribute("userMap",userMap);
				  RequestDispatcher rd=request.getRequestDispatcher("customermenu.jsp");  
			      rd.forward(request, response);  
			}	
		    }
		catch (SQLException | CustomException | ServletException | IOException e) {
					 out.println("Inavlid username and password"); 
					 e.printStackTrace();
			}
		}
        
        
		if(page.equals("Account details"))
		{
			Map<Long, Map<Long, AccountInfo>> accMap = logicLayer.cache.accountMap;
			request.setAttribute("LoginController", accMap);
	    	RequestDispatcher rd=request.getRequestDispatcher("accountdetails.jsp");  
	        rd.forward(request, response);  
			
		}
		
		if(page.equals("Customer details"))
		{
			Map<Long, CustomerInfo> cusMap = logicLayer.cache.customerMap;
			request.setAttribute("LoginController", cusMap);
			RequestDispatcher rd=request.getRequestDispatcher("customerdetails.jsp");  
	        rd.forward(request, response);  	
		}
       }	
	
	
	public void init(ServletConfig config) {
		try {
			super.init(config);
			APILayer logicLayer=new APILayer();
			config.getServletContext().setAttribute("logic", logicLayer);
		} catch (ServletException | ClassNotFoundException | IOException | CustomException e) {
			e.printStackTrace();
		}
		config.getServletContext();
	}
	
}