

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/reg")
public class RegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
	    response.setContentType("text/html");
			String fname=request.getParameter("firstname");
			String lname=request.getParameter("lastname");
			String email=request.getParameter("email");
			String phnno=request.getParameter("phonenumber");
			String password=request.getParameter("password");
			String con_pass=request.getParameter("confirmpassword");
			try {
				 Class.forName("oracle.jdbc.driver.OracleDriver");
				 Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "Sudhy1234");
				 PreparedStatement pst=con.prepareStatement("insert into admins(firstname,lastname,email,phn_num,password,con_password)values(?,?,?,?,?,?)");
			     pst.setString(1, fname);
			     pst.setString(2, lname);
			     pst.setString(3, email);
			     pst.setString(4, phnno);
			     pst.setString(5,password);
			     pst.setString(6, con_pass);
			     int rcnt=pst.executeUpdate();
			     if(rcnt==1)
			     {
			    	 RequestDispatcher reqdispatcher=request.getRequestDispatcher("final.html");
					 reqdispatcher.forward(request, response); 
			     }
			     
			     else
			     {
			    	 RequestDispatcher reqdispatcher=request.getRequestDispatcher("register.html");
					 reqdispatcher.forward(request, response); 
			     }
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			
			}
				
	}

}
