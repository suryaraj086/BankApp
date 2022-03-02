package mypack;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db.APILayer;
import db.DBLayer;
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
		 APILayer logicLayer=new APILayer();
		 DBLayer dbObj=new DBLayer();
		 if(page.equals("addaccount"))
		 {
	     int id = Integer.parseInt(request.getParameter("id"));
	     String name=request.getParameter("name");
	     String branch=request.getParameter("branch");
	     try {
			dbObj.readFromFile();
		} catch (ClassNotFoundException | IOException | CustomException e1) {
			e1.printStackTrace();
		}
	      Long accountNo=dbObj.accNo;
	      accountNo++;
	     try {
			dbObj.storeAccount(id, branch, name, accountNo, 1000, true);
		} catch (SQLException | CustomException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	     RequestDispatcher rd=request.getRequestDispatcher("adminmenu.jsp");  
         rd.forward(request, response);  
		 }
	
	}
}
