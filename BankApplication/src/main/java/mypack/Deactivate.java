package mypack;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


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
		String[] arr=request.getParameterValues("name");
		for(int i=0;i<arr.length;i++)
		{
			System.out.println(arr[i]);
		}
		RequestDispatcher rd=request.getRequestDispatcher("adminmenu.jsp");  
	    rd.forward(request, response);
	}

}
