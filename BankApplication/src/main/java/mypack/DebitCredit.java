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
import db.AccountInfo;
import myexception.CustomException;
import utilhelper.Utility;

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

		String debtCrdt=null;
		APILayer logicLayer=(APILayer)request.getServletContext().getAttribute("logic");
		try 
		{
	    debtCrdt= Utility.nullChecker(request.getParameter("debitorcredit"));
		logicLayer.readFile();
		long accNumber=Long.parseLong(Utility.nullChecker(request.getParameter("accountnumber")));
		long userId=logicLayer.persistLayer.getId(accNumber);
		long amount=Long.parseLong(Utility.nullChecker(request.getParameter("amount")));
		Map<Long, Map<Long, AccountInfo>> accMap = logicLayer.cache.accountMap;
		request.setAttribute("LoginController", accMap);
		
		if(debtCrdt.equals("deposit"))
		{
		try {
		    logicLayer.deposit(accNumber, userId, amount);
			request.setAttribute("LoginController", accMap);
            RequestDispatcher rd=request.getRequestDispatcher("accountdetails.jsp?message=Transaction Successful");  
		     rd.forward(request, response); 
            
		}
		catch (Exception e) 
		{
			 request.setAttribute ("errorMessage", e.getMessage());
			 RequestDispatcher rd=request.getRequestDispatcher("debitorcredit.jsp");  
		     rd.forward(request, response); 
		}
		}
		else {
		    try 
		    {
				logicLayer.withdrawal(accNumber,userId, amount);
				 RequestDispatcher rd=request.getRequestDispatcher("accountdetails.jsp?message=Transaction Successful");  
		         rd.forward(request, response);  
			} 
		    catch (ClassNotFoundException | SQLException | CustomException e) {
				 request.setAttribute ("errorMessage", e.getMessage());
					RequestDispatcher rd=request.getRequestDispatcher("debitorcredit.jsp");  
			        rd.forward(request, response); 
			}
		}
		
		
		
	}
		catch (Exception e) {
			request.setAttribute ("errorMessage", "The string cannot be null");
			RequestDispatcher rd=request.getRequestDispatcher("login.jsp");  
	        rd.forward(request, response); 
		}

 }
}
