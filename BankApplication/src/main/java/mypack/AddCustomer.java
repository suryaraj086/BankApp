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
		String id=request.getParameter("userId");
		String name=request.getParameter("name");
		char gender=request.getParameter("gender").charAt(0);
		int age=Integer.parseInt(request.getParameter("age"));
		
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
		RequestDispatcher rd=request.getRequestDispatcher("customerdetails.jsp");  
	    rd.forward(request, response); 
		}
		catch (ClassNotFoundException | SQLException | IOException | CustomException e) {
			e.printStackTrace();
		}
		}
		
		else 
		{ 
			long cusId= Long.parseLong(id);
			try {
				logicLayer.updateCustomer(name, age, gender, cusId);
				logicLayer.readFile();
			} catch (SQLException | CustomException | ClassNotFoundException e) {
				e.printStackTrace();
			}
			 Map<Long, CustomerInfo> cusMap = logicLayer.cache.customerMap;
			 request.setAttribute("LoginController", cusMap);
			 RequestDispatcher rd=request.getRequestDispatcher("customerdetails.jsp");  
		     rd.forward(request, response); 
		}
			
		
	}
}
