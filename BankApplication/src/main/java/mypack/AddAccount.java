package mypack;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import db.APILayer;
import db.AccountInfo;
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
    	 String updateId= request.getParameter("id");
         String name=request.getParameter("name");
	     String branch=request.getParameter("branch");
	     String accNo=request.getParameter("accountnumber");
    	 APILayer logicLayer=(APILayer)request.getServletContext().getAttribute("logic");
    	 HttpSession session = request.getSession();
	     if (session.getAttribute("customerId") == null) {
	    	 response.sendRedirect(request.getContextPath() + "/login.jsp");		 
	     }
    	 
		 try 
		 {
			 logicLayer.readFile();
	     }
		 catch (ClassNotFoundException | IOException | CustomException e2) 
		 {
			e2.printStackTrace();
		 }
		 
		 
		if(accNo==null || accNo.equals("null") )
		{	
		 if(page.equals("addaccount"))
		 {
	     int id = Integer.parseInt(request.getParameter("id"));
	     Long accountNo=logicLayer.cache.accNo;
	     accountNo++;
	     try 
	     {
			logicLayer.persistLayer.storeAccount(id, branch, name, accountNo, 1000, true);
			logicLayer.readFile();
			Map<Long, Map<Long, AccountInfo>> accMap = logicLayer.cache.accountMap;
			request.setAttribute("LoginController", accMap);
			RequestDispatcher rd=request.getRequestDispatcher("accountdetails.jsp?message=Account added succesfully");  
		    rd.forward(request, response);   
		 }
	     catch (SQLException | CustomException | ClassNotFoundException e) 
	     {
	    		RequestDispatcher rd=request.getRequestDispatcher("accountdetails.jsp?message=Can't add account");  
			    rd.forward(request, response);  	     }
		 }
			}
			else {
				long acc=Long.parseLong(accNo);
				long uId=Long.parseLong(updateId);
				try {
					logicLayer.persistLayer.updateAccount(name, acc, branch, uId);
					logicLayer.readFile();
				} catch (SQLException | CustomException | ClassNotFoundException e) {
					e.printStackTrace();
				}
				Map<Long, Map<Long, AccountInfo>> accMap = logicLayer.cache.accountMap;
				request.setAttribute("LoginController", accMap);
				RequestDispatcher rd=request.getRequestDispatcher("accountdetails.jsp?message=Account updated succesfully");  
			    rd.forward(request, response);  
				
			}
	}
}