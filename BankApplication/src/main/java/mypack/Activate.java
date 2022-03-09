package mypack;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db.APILayer;
import db.AccountInfo;
import db.Cache;
import myexception.CustomException;


public class Activate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
    public Activate() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);		
		APILayer logicLayer = null;
		try {
			logicLayer = new APILayer();
		} catch (ClassNotFoundException | IOException | CustomException e2) {
			e2.printStackTrace();
		}
		String[] arr=request.getParameterValues("activate");
		String page=request.getParameter("page");
	if(page!=null)
	{
		Map map=null;
	   try {
		  map=logicLayer.persistLayer.readInactive();
	} catch (ClassNotFoundException | IOException | CustomException | SQLException e) {
		e.printStackTrace();
	}
	    request.setAttribute("activeacc", map);
		RequestDispatcher rd=request.getRequestDispatcher("activate.jsp");  
	    rd.forward(request, response);
	}
	else {
		if(arr==null)
		{
			Map<Long, Map<Long, AccountInfo>> accMap = logicLayer.cache.accountMap;
			request.setAttribute("activeacc", accMap);
			RequestDispatcher rd=request.getRequestDispatcher("accountdetails.jsp");  
		    rd.forward(request, response);
		}
		else {
		for(int i=0;i<arr.length;i++)
		{
			try 
			{
				logicLayer.activateAccount(Long.parseLong(arr[i]));
			}  
			catch (NumberFormatException | SQLException | CustomException e) 
			{
				e.printStackTrace();
			}
		}
		try {
			logicLayer.readFile();
		} catch (ClassNotFoundException | IOException | CustomException e1) {
			e1.printStackTrace();
		}
		Map<Long, Map<Long, AccountInfo>> accMap = logicLayer.cache.accountMap;
		request.setAttribute("LoginController", accMap);
		RequestDispatcher rd=request.getRequestDispatcher("accountdetails.jsp");  
	    rd.forward(request, response);
	}
	}
	}

}
