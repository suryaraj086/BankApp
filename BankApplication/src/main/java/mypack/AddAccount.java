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
import utilhelper.Utility;

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
	     String branch = null;
	     String name = null;
	     String accNo=request.getParameter("accountnumber");
    	 APILayer logicLayer=(APILayer)request.getServletContext().getAttribute("logic");
    	 HttpSession session = request.getSession();
    	 
	     if (session.getAttribute("customerId") == null) {
	    	 response.sendRedirect(request.getContextPath() + "/login.jsp");		 
	     }
    	 
		 try 
		 {
			 name=Utility.nullChecker(request.getParameter("name")) ;
		     name=name.trim();
		     Utility.nullChecker(name);
			 branch = Utility.nullChecker(request.getParameter("branch"));
			 logicLayer.readFile();
	     }
		 catch (ClassNotFoundException | IOException | CustomException e2) 
		 {
			 Map<Long, Map<Long, AccountInfo>> accMap = logicLayer.cache.accountMap;
			 request.setAttribute("LoginController", accMap);
			 RequestDispatcher rd=request.getRequestDispatcher("accountdetails.jsp?message=Can't add account");  
			 rd.forward(request, response); 
			 return;
		 }
		 
		 
		if(accNo==null || accNo.equals("null") )
		{	
		 if(page.equals("addaccount"))
		 {
	     try 
	     {
	    	int id = Integer.parseInt(Utility.nullChecker(request.getParameter("id")));
	 	    Long accountNo=logicLayer.cache.accNo;
	 	    accountNo++;
	 	    AccountInfo accInfo=new AccountInfo();
	 	    accInfo.setAccountNumber(accountNo);
	 	    accInfo.setId(id);
	 	   	accInfo.setBalance(1000);
	 	  	accInfo.setName(name);
	 	    accInfo.setBranch(branch);
	 		accInfo.setStatus(true);
	 	    logicLayer.addAccount(accInfo, id, accountNo);
			logicLayer.persistLayer.storeAccount(id, branch, name, accountNo, 1000, true);
			logicLayer.readFile();
			Map<Long, Map<Long, AccountInfo>> accMap = logicLayer.cache.accountMap;
			request.setAttribute("LoginController", accMap);
			RequestDispatcher rd=request.getRequestDispatcher("accountdetails.jsp?message=Account added succesfully");  
		    rd.forward(request, response);   
		 }
	     catch (SQLException | CustomException | ClassNotFoundException e) 
	     {
	    	    Map<Long, Map<Long, AccountInfo>> accMap = logicLayer.cache.accountMap;
				request.setAttribute("LoginController", accMap);
	    		RequestDispatcher rd=request.getRequestDispatcher("accountdetails.jsp?message="+e.getMessage());  
			    rd.forward(request, response);  	    
	     }
		 }
		}
		else 
		{
			long acc=Long.parseLong(accNo);
			long uId=Long.parseLong(updateId);
			try 
			{
				logicLayer.persistLayer.updateAccount(name, acc, branch, uId);
				logicLayer.readFile();
			} 
			catch (SQLException | CustomException | ClassNotFoundException e) {
				e.printStackTrace();
			}
			Map<Long, Map<Long, AccountInfo>> accMap = logicLayer.cache.accountMap;
			request.setAttribute("LoginController", accMap);
			RequestDispatcher rd=request.getRequestDispatcher("accountdetails.jsp?message=Account updated succesfully");  
			rd.forward(request, response);  
				
		}
	}
}