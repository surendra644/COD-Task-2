

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out=response.getWriter();	
		String uname=request.getParameter("uname");
		String pass=request.getParameter("password");
		
		
		       if(uname.equalsIgnoreCase("surendra") && pass.equalsIgnoreCase("1234"))
		       {
		    	 RequestDispatcher reqdispatcher=request.getRequestDispatcher("final.html");
				 reqdispatcher.forward(request, response);
		       }
		     
		     else 
		     { 
		    	 
		    	 
		    	 RequestDispatcher reqdispatcher=request.getRequestDispatcher("register.html");
				 reqdispatcher.forward(request, response);
		     }
		
		
		}
		
		
	}


