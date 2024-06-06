

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


@WebServlet("/userreg")
public class NewStudents extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();	
		String fname=request.getParameter("firstname");
		String lname=request.getParameter("lastname");
		String email=request.getParameter("email");
		String phnno=request.getParameter("phonenumber");
		int rno=Integer.parseInt(request.getParameter("rollno"));
		String branch=request.getParameter("branch");
		try {
			 Class.forName("oracle.jdbc.driver.OracleDriver");
			 Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "Sudhy1234");
			 PreparedStatement pst=con.prepareStatement("insert into student(rollno,firstname,lastname,email,phn_num,branch)values(?,?,?,?,?,?)");
		     pst.setString(2, fname);
		     pst.setString(3, lname);
		     pst.setString(4, email);
		     pst.setString(5, phnno);
		     pst.setInt(1,rno);
		     pst.setString(6,branch);
		     int rcnt=pst.executeUpdate();
		     if(rcnt==1)
		     {
		    	 out.println("<body style=\"background-image: url('libraryback.jpg');\">");
		    	 out.println("<h1>studentadded</h1>");
		     }
		     
		     else
		     {
		    	 out.println("<body style=\"background-image: url('libraryback.jpg');\">");
		    	 out.println("<h1>student is not added </h1>");
		     }
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		
		}
		
		
		
		
		
		
	}

}
