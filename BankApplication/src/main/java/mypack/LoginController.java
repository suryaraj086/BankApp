
package mypack;

import java.io.IOException;
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

		APILayer logicLayer=null;
		try {
			logicLayer = new APILayer();
		} catch (ClassNotFoundException | IOException | CustomException e1) {
			e1.printStackTrace();
		}
        String page=request.getParameter("page");
       
        if(page.equals("login"))
        {
        	Long id = Long.parseLong(request.getParameter("id"));
 	     	String password = request.getParameter("password");
 	        HttpSession session = request.getSession();
 	        
		try {
			
			boolean role=logicLayer.persistLayer.login(id, password);
			session.setAttribute("customerId", id);
			
			if (role) 
			{
				Map<Long, Map<Long, AccountInfo>> accMap = logicLayer.cache.accountMap;
				request.setAttribute("LoginController", accMap);
		    	RequestDispatcher rd=request.getRequestDispatcher("accountdetails.jsp");  
		        rd.forward(request, response);  
			}
			else
			{	
				  logicLayer.readFile();
			      Map<Long, AccountInfo> userMap=logicLayer.retrieveAccount(id);    
			      request.setAttribute("userMap",userMap);
			      session.setAttribute("acc", userMap);
				  RequestDispatcher rd=request.getRequestDispatcher("customeraccount.jsp");  
			      rd.forward(request, response);  
			}	
		    }
		catch (CustomException | ServletException | IOException | SQLException e) {
					 request.setAttribute ("errorMessage", "Invalid username or password");
					  RequestDispatcher rd=request.getRequestDispatcher("login.jsp");  
				      rd.forward(request, response);  
					 e.printStackTrace();
			} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
        else {
        HttpSession session = request.getSession();
        if (session.getAttribute("customerId") == null) {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
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
		
		if(page.equals("Customer menu"))
		{
		
			  Map<Long, AccountInfo> userMap = null;
			try {
				userMap = logicLayer.retrieveAccount((long)session.getAttribute("customerId"));
			} catch (CustomException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}    
		      request.setAttribute("userMap",userMap);
		      session.setAttribute("acc", userMap);
			  RequestDispatcher rd=request.getRequestDispatcher("customeraccount.jsp");  
		      rd.forward(request, response);  	
		}
		
		if(page.equals("Deposit/Withdraw"))
		{
			RequestDispatcher rd=request.getRequestDispatcher("debitorcredit.jsp");  
	        rd.forward(request, response); 
		}
		
		if(page.equals("Transfer to another account"))
		{
			RequestDispatcher rd=request.getRequestDispatcher("banktransferadmin.jsp");  
	        rd.forward(request, response); 
		}
		
		if(page.equals("logout"))
		{
	        session.invalidate();
			RequestDispatcher rd=request.getRequestDispatcher("login.jsp");  
	        rd.forward(request, response);  	
		}
        }
        }
     	
	
	
	public void init(ServletConfig config) {
		try 
		{
			super.init(config);
			APILayer logicLayer=new APILayer();
			config.getServletContext().setAttribute("logic", logicLayer);
		}
		catch (ServletException | ClassNotFoundException | IOException | CustomException e) 
		{
			e.printStackTrace();
		}
		config.getServletContext();
	}	
}