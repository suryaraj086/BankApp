package mypack;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db.APILayer;
import myexception.CustomException;

public class DebitCredit extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public DebitCredit() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String debtCrdt= request.getParameter("debitorcredit");
		PrintWriter out = response.getWriter();
		APILayer logicLayer=null;
		try {
			logicLayer = new APILayer();
		} catch (ClassNotFoundException | IOException | CustomException e1) {
			e1.printStackTrace();
		}
		long userId=Long.parseLong(request.getParameter("id"));
		long accNumber=Long.parseLong(request.getParameter("accountnumber"));
		long amount=Long.parseLong(request.getParameter("amount"));
		if(debtCrdt.equals("deposit"))
		{
		try {
		    logicLayer.deposit(accNumber, userId, amount);
			 RequestDispatcher rd=request.getRequestDispatcher("adminmenu.jsp");  
	         rd.forward(request, response);  
		} 
		catch (Exception e) {
			out.print("deposit amount can not be negative or zero");		}
		}
		else {
		    try {
				logicLayer.withdrawal(accNumber,userId, amount);
				 RequestDispatcher rd=request.getRequestDispatcher("adminmenu.jsp");  
		         rd.forward(request, response);  
			} catch (ClassNotFoundException | SQLException | CustomException e) {
				out.print("insufficient balance");
			}
			
		
		}
		
	}

}
