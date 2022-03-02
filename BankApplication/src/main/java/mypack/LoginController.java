
package mypack;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
		Cache temp=new Cache();
		DBLayer dbObj=new DBLayer();
		PrintWriter out = response.getWriter();
        String page=request.getParameter("page");
        
        if(page.equals("login"))
        {
        	int id = Integer.parseInt(request.getParameter("id"));
 	     	String password = request.getParameter("password");
		try {
			if (dbObj.login(id, password)) {
			    RequestDispatcher rd=request.getRequestDispatcher("adminmenu.jsp");  
			    rd.forward(request, response);  
			} else
				try {
					if(!dbObj.login(id, password)){
						   RequestDispatcher rd=request.getRequestDispatcher("customermenu.jsp");  
					        rd.forward(request, response);  
					}
					
					else
					{
						 out.println("error"); 	
					}
				} catch (SQLException | CustomException | ServletException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		} catch (SQLException | CustomException | ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        }
        
		if(page.equals("Account details"))
		{
			Map<Long, Map<Long, AccountInfo>> accMap=new HashMap<Long, Map<Long,AccountInfo>>();
				try {
					temp = dbObj.readFromFile();
				} catch (Exception e) {
					e.printStackTrace();
				}
				accMap = temp.accountMap;
				request.setAttribute("LoginController", accMap);
				
			 RequestDispatcher rd=request.getRequestDispatcher("accountdetails.jsp");  
	            rd.forward(request, response);  
			
		}
		
		if(page.equals("Customer details"))
		{
			Map<Long, CustomerInfo> accMap;
			try 
			{	
				temp = dbObj.readFromFile();	
			} 
			catch (Exception e) 
			{
					e.printStackTrace();
			}
			accMap = temp.customerMap;
			request.setAttribute("LoginController", accMap);
			RequestDispatcher rd=request.getRequestDispatcher("customerdetails.jsp");  
	        rd.forward(request, response);  	
		}
       }	
	
	
	public void init(ServletConfig config) {
		try {
			super.init(config);
			APILayer logicLayer=new APILayer();
			config.getServletContext().setAttribute("logic", logicLayer);
		} catch (ServletException e) {
			e.printStackTrace();
		}
		config.getServletContext();
	}
	
}