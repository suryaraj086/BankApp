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
		// TODO Auto-generated method stub
		String debtCrdt= request.getParameter("debitorcredit");
		APILayer logicLayer=new APILayer();
		long userId=Long.parseLong(request.getParameter("id"));
		long accNumber=Long.parseLong(request.getParameter("accountnumber"));
		long amount=Long.parseLong(request.getParameter("amount"));
		if(debtCrdt.equals("deposit"))
		{
		try {
			logicLayer.readFile();
		    logicLayer.deposit(accNumber, userId, amount);
			 RequestDispatcher rd=request.getRequestDispatcher("adminmenu.jsp");  
	         rd.forward(request, response);  
		} catch (CustomException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		else {
		    try {
		    	logicLayer.readFile();
				logicLayer.withdrawal(accNumber,userId, amount);
			} catch (ClassNotFoundException | SQLException | CustomException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 RequestDispatcher rd=request.getRequestDispatcher("adminmenu.jsp");  
	         rd.forward(request, response);  
		
		}
		
	}

}
