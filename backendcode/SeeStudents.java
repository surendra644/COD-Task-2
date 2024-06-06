

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

/**
 * Servlet implementation class SeeStudents
 */
@WebServlet("/seestudents")
public class SeeStudents extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();	
		out.println("<body style=\"background-image: url('libraryback.jpg');\">");
		out.println("<html><body>");
        out.println("<h2>Database Table Data</h2>");
        out.println("<table border='1' align='center'>");
		try {
			 Class.forName("oracle.jdbc.driver.OracleDriver");
			 Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "Sudhy1234");
			PreparedStatement pst=con.prepareStatement("select * from student");
			ResultSet rs=pst.executeQuery();
			 ResultSetMetaData rsmd = rs.getMetaData();
			  int columnCount = rsmd.getColumnCount();
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
			
			/*while(rs.next())
			{
				
				
				out.println("<table border=\"1\">");
				out.println("<tr>");
				out.println("<td>");
				out.println(rs.getInt("rollno")+"\t");
				out.println("</td>");
				out.println("<td>");
				out.println(rs.getString("firstname")+"\t");
				out.println("</td>");
				out.println("<td>");
				out.println(rs.getString("lastname")+"\t");
				out.println("</td>");
				out.println("<td>");
				out.println(rs.getString("email")+"\t");
				out.println("</td>");
				out.println("<td>");
				out.println(rs.getString("phn_num")+"\t");
				out.println("</td>");
				out.println("<td>");
				out.println(rs.getString("branch")+"\t");
				out.println("</td>");
				out.println("</tr>");
				out.println("</table>");	
			}*/
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		
		}
		 out.println("</table>");
	        out.println("</body></html>");
	}
		
	

}
