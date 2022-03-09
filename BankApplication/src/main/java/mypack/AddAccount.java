package mypack;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import db.APILayer;
import myexception.CustomException;

public class AddAccount extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public AddAccount() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    	 String page=request.getParameter("page");
    	 APILayer logicLayer=null;
		 try 
		 {
			 logicLayer=new APILayer();
	     }
		 catch (ClassNotFoundException | IOException | CustomException e2) 
		 {
			e2.printStackTrace();
		 }

		 if(page.equals("addaccount"))
		 {
	     int id = Integer.parseInt(request.getParameter("id"));
	     String name=request.getParameter("name");
	     String branch=request.getParameter("branch");
	     Long accountNo=logicLayer.cache.accNo;
	     accountNo++;
	
	     try 
	     {
	         HttpSession session = request.getSession();
		        if (session.getAttribute("customerId") == null) {
		        	throw new CustomException("session expired");
		            
		        }
			logicLayer.persistLayer.storeAccount(id, branch, name, accountNo, 1000, true);
			RequestDispatcher rd=request.getRequestDispatcher("adminmenu.jsp");  
		    rd.forward(request, response);  
		 }
	     catch (SQLException | CustomException e) 
	     {
	    	 response.sendRedirect(request.getContextPath() + "/login.jsp");		 }
	  
		 }	
	}
}
