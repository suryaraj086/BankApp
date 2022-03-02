package mypack;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import db.APILayer;
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
		APILayer logicLayer=null;
		String id=request.getParameter("userId");
		String name=request.getParameter("name");
		char gender=request.getParameter("gender").charAt(0);
		int age=Integer.parseInt(request.getParameter("age"));
		try 
		{
	        logicLayer = new APILayer();
		} catch (ClassNotFoundException | IOException | CustomException e1) {
			e1.printStackTrace();
		}
		
		if(id.equals("null"))
		{
		try 
		{
		long userID=logicLayer.cache.idNo;
		userID++;
		logicLayer.persistLayer.storeCustomer(userID,name,gender,age);
		RequestDispatcher rd=request.getRequestDispatcher("adminmenu.jsp");  
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
			} catch (SQLException | CustomException e) {
				e.printStackTrace();
			}
			 RequestDispatcher rd=request.getRequestDispatcher("adminmenu.jsp");  
		     rd.forward(request, response); 
		}
			
		
	}
}
