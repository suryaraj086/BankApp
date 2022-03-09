package mypack;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db.APILayer;
import db.AccountInfo;
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

		PrintWriter out=response.getWriter();
		String debtCrdt= request.getParameter("debitorcredit");
		APILayer logicLayer=null;
		try {
			logicLayer = new APILayer();
		} catch (ClassNotFoundException | IOException | CustomException e1) {
			e1.printStackTrace();
		}
		long userId=Long.parseLong(request.getParameter("id"));
		long accNumber=Long.parseLong(request.getParameter("accountnumber"));
		long amount=Long.parseLong(request.getParameter("amount"));
		Map<Long, Map<Long, AccountInfo>> accMap = logicLayer.cache.accountMap;
		request.setAttribute("LoginController", accMap);
		if(debtCrdt.equals("deposit"))
		{
		try {
		    logicLayer.deposit(accNumber, userId, amount);
		    
			 RequestDispatcher rd=request.getRequestDispatcher("accountdetails.jsp");  
	         rd.forward(request, response);  
		}
		catch (Exception e) 
		{
			 request.setAttribute ("errorMessage", e);
			 RequestDispatcher rd=request.getRequestDispatcher("debitorcredit.jsp");  
		     rd.forward(request, response); 
		}
		}
		else {
		    try 
		    {
				logicLayer.withdrawal(accNumber,userId, amount);
				 RequestDispatcher rd=request.getRequestDispatcher("accountdetails.jsp");  
		         rd.forward(request, response);  
			} 
		    catch (ClassNotFoundException | SQLException | CustomException e) {
				 request.setAttribute ("errorMessage", "Insufficient Balance");
					RequestDispatcher rd=request.getRequestDispatcher("debitorcredit.jsp");  
			        rd.forward(request, response); 
			}
			
		
		}
		
	}

}
