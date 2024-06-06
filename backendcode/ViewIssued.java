

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/viewissued")
public class ViewIssued extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
   
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		out.println("<body style=\"background-image: url('libraryback.jpg');\">");
		out.println("<html><body>");
        out.println("<h2>Database Table Data</h2>");
        out.println("<table border='1' align='center'>");

		int rollno=Integer.parseInt(request.getParameter("rollno"));
		try {
			 Class.forName("oracle.jdbc.driver.OracleDriver");
			 Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "Sudhy1234");
			 PreparedStatement pst=con.prepareStatement("select * from viewbooks where rollno=?");
		     pst.setInt(1, rollno);
		     ResultSet rs= pst.executeQuery();
		     ResultSetMetaData rsmd = rs.getMetaData();
	            int columnCount = rsmd.getColumnCount();

	            // Print column headers
	            out.println("<tr>");
	            for (int i = 1; i <= columnCount; i++) {
	                out.println("<th>" + rsmd.getColumnName(i) + "</th>");
	            }
	            out.println("</tr>");

	            // Print row data
	            while (rs.next()) {
	                out.println("<tr>");
	                for (int i = 1; i <= columnCount; i++) {
	                    out.println("<td>" + rs.getString(i) + "</td>");
	                }
	                out.println("</tr>");
	            }
		     
		   
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		
		}
	}

}
