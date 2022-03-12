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


public class Deactivate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public Deactivate() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
		APILayer logicLayer=(APILayer)request.getServletContext().getAttribute("logic");
		String[] arr=request.getParameterValues("name");
	
		if(arr==null)
		{
			try {
				logicLayer.readFile();
			} catch (ClassNotFoundException | IOException | CustomException e) {
				e.printStackTrace();
			}
			Map<Long, Map<Long, AccountInfo>> accMap = logicLayer.cache.accountMap;
			request.setAttribute("LoginController", accMap);
			RequestDispatcher rd=request.getRequestDispatcher("accountdetails.jsp?message=please select an account");  
		    rd.forward(request, response);
		}
		else {
		for(int i=0;i<arr.length;i++)
		{
			try 
			{
				logicLayer.deactivateAccount(Long.parseLong(arr[i]));
			}  
			catch (NumberFormatException | SQLException | CustomException e) 
			{
				RequestDispatcher rd=request.getRequestDispatcher("accountdetails.jsp?message=Error!can't deactivate account");  
			    rd.forward(request, response);
			}
		}
		try 
		{
			logicLayer.readFile();
		}
		catch (ClassNotFoundException | IOException | CustomException e1) {
			RequestDispatcher rd=request.getRequestDispatcher("accountdetails.jsp?message=Error!can't retrieve account");  
		    rd.forward(request, response);
		}
		Map<Long, Map<Long, AccountInfo>> accMap = logicLayer.cache.accountMap;
		request.setAttribute("LoginController", accMap);
		RequestDispatcher rd=request.getRequestDispatcher("accountdetails.jsp");  
	    rd.forward(request, response);
	}
	}

}
