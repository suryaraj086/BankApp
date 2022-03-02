package mypack;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db.APILayer;
import db.Cache;
import db.DBLayer;
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
		DBLayer dbObj=new DBLayer();
		String id=request.getParameter("userId");
		String name=request.getParameter("name");
		char gender=request.getParameter("gender").charAt(0);
		int age=Integer.parseInt(request.getParameter("age"));
	    System.out.println(id);
		Cache cache = null;
		if(id==null || id.isEmpty())
		{
		try 
		{
		cache = dbObj.readFromFile();
		long userID=cache.idNo;
		userID++;
		dbObj.storeCustomer(userID,name,gender,age);
		   RequestDispatcher rd=request.getRequestDispatcher("adminmenu.jsp");  
	         rd.forward(request, response); 
		}
		 catch (ClassNotFoundException | SQLException | IOException | CustomException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		else 
		{      
			dbObj.storeCustomer();
			   RequestDispatcher rd=request.getRequestDispatcher("adminmenu.jsp");  
		         rd.forward(request, response); 
		}
			
		
	}
}
