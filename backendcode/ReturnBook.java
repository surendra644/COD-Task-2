

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/return")
public class ReturnBook extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		out.println("<body style=\"background-image: url('libraryback.jpg');\">");
		int bookno=Integer.parseInt(request.getParameter("returnno"));
		try {
			 Class.forName("oracle.jdbc.driver.OracleDriver");
			 Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "Sudhy1234");
			PreparedStatement pst=con.prepareStatement("delete from issuebook where bookid=?");
			pst.setInt(1,bookno);
			PreparedStatement pst1=con.prepareStatement("delete from viewbooks where bookid=?");
			pst1.setInt(1, bookno);
			PreparedStatement pst2=con.prepareStatement("delete from librarybooks where bookid=?");
			pst2.setInt(1, bookno);
			int rcnt=pst.executeUpdate();
			int rcnt2=pst1.executeUpdate();
			int rcnt3=pst2.executeUpdate();
			if(rcnt==1 &&  rcnt2==1 && rcnt3==1)
			{
				out.print("<h1>book successfully returned</h1>");
			}
			else
			{
				out.print("<h1>wrong book id</h1>");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		
		}
			
		
	}

}
