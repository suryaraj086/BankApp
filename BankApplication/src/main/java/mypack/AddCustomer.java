package mypack;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import db.APILayer;
import db.CustomerInfo;
import myexception.CustomException;
import utilhelper.Utility;


public class AddCustomer extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public AddCustomer() {
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
		APILayer logicLayer=(APILayer) request.getServletContext().getAttribute("logic");
		try {
			String id=Utility.nullChecker(request.getParameter("userId"));
			char gender=Utility.nullChecker(request.getParameter("gender")).charAt(0);
			int age=Integer.parseInt(Utility.nullChecker(request.getParameter("age")));
			String name = Utility.nullChecker(request.getParameter("name"));
			name=name.trim();
			Utility.nullChecker(name);
		
		if(id.equals("null"))
		{
		try 
		{
			
		long userID=logicLayer.cache.idNo;
		userID++;
		logicLayer.persistLayer.storeCustomer(userID,name,gender,age);
		logicLayer.readFile();
		int min=100;
		int max=1000;
		int b = (int)(Math.random()*(max-min+1)+min);  
		String password=String.valueOf(b);
		logicLayer.newLogin(userID, password);
		Map<Long, CustomerInfo> cusMap = logicLayer.cache.customerMap;
		request.setAttribute("LoginController", cusMap);
		RequestDispatcher rd=request.getRequestDispatcher("customerdetails.jsp?message=customer added successfully");  
	    rd.forward(request, response); 
		}
		catch (ClassNotFoundException | SQLException | IOException | CustomException e) {
			Map<Long, CustomerInfo> cusMap = logicLayer.cache.customerMap;
			request.setAttribute("LoginController", cusMap);
			RequestDispatcher rd=request.getRequestDispatcher("customerdetails.jsp?message=Cant' add customer");  
		    rd.forward(request, response); 	
		     }
		}
		
		else 
		{ 
			long cusId= Long.parseLong(id);
			try 
			{
				logicLayer.updateCustomer(name, age, gender, cusId);
				logicLayer.readFile();
			}
			catch (SQLException | CustomException | ClassNotFoundException e)
			{
				Map<Long, CustomerInfo> cusMap = logicLayer.cache.customerMap;
				request.setAttribute("LoginController", cusMap);
				RequestDispatcher rd=request.getRequestDispatcher("customerdetails.jsp?message=Cant' update customer");  
			     rd.forward(request, response); 	
			}
			 Map<Long, CustomerInfo> cusMap = logicLayer.cache.customerMap;
			 request.setAttribute("LoginController", cusMap);
			 RequestDispatcher rd=request.getRequestDispatcher("customerdetails.jsp?message=customer updated successfully");  
		     rd.forward(request, response); 
		}
		} catch (CustomException e1) {
			Map<Long, CustomerInfo> cusMap = logicLayer.cache.customerMap;
			request.setAttribute("LoginController", cusMap);
			 RequestDispatcher rd=request.getRequestDispatcher("customerdetails.jsp?message=Invalid input");  
		     rd.forward(request, response); 
		}
		
	}
}
