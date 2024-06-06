

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/issue")
public class IssueBook extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		out.println("<body style=\"background-image: url('libraryback.jpg');\">");
		int bookno=Integer.parseInt(request.getParameter("issuename"));
		int rollno=Integer.parseInt(request.getParameter("rollno"));
		String bookname=request.getParameter("bookname");
		try {
			 Class.forName("oracle.jdbc.driver.OracleDriver");
			 Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "Sudhy1234");
			PreparedStatement pst=con.prepareStatement("insert into issuebook(bookid,rollno,bookname)values(?,?,?)");
			PreparedStatement pst1=con.prepareStatement("insert into viewbooks(rollno,bookname,bookid) values(?,?,?)");
			PreparedStatement pst2=con.prepareStatement("insert into librarybooks(bookid,bookname) values(?,?)");
			pst.setInt(1,bookno);
			pst.setInt(2, rollno);
			pst.setString(3, bookname);
			pst1.setInt(1, rollno);
			pst1.setString(2, bookname);
			pst1.setInt(3, bookno);
			pst2.setInt(1, bookno);
			pst2.setString(2, bookname);
			int rcnt=pst.executeUpdate();
			int rcnt1=pst1.executeUpdate();
			int rcnt2=pst2.executeUpdate();
			if(rcnt==1 && rcnt1==1 && rcnt2==1)
			{
				out.print("<h1>book issued successfully</h1>");
			}
			else
			{
				out.print("<h1>book not issued successfully</h1>");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		
		}
			
		out.println("<body style=\"background-image: url('libraryback.jpg');\">");
		
	}

}
