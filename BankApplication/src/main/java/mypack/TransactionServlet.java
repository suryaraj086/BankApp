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


public class TransactionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public TransactionServlet() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	doGet(request, response);
	long fromId=Long.parseLong(request.getParameter("fromid"));
	long fromAcc=Long.parseLong(request.getParameter("fromaccount"));
	long toId=Long.parseLong(request.getParameter("toid"));
	long toAcc= Long.parseLong(request.getParameter("toaccount"));
	long amount=Long.parseLong(request.getParameter("amount"));
    String cust=request.getParameter("customer");
	APILayer logicLayer=(APILayer) request.getServletContext().getAttribute("logic");
	try 
	{
	
		logicLayer.deposit(toAcc, toId, amount);
		logicLayer.withdrawal(fromAcc, fromId, amount);
		
		if(cust==null ||cust.equals("null"))
		{
		RequestDispatcher rd=request.getRequestDispatcher("adminmenu.jsp");  
        rd.forward(request, response);  
		}
		else
		{
			RequestDispatcher rd=request.getRequestDispatcher("customermenu.jsp");  
	        rd.forward(request, response);  
		}
	}
	catch (ClassNotFoundException | CustomException | SQLException e) 
	{
		e.printStackTrace();
	}
	}
}
