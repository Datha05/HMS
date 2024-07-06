

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
   	{
   		String name=request.getParameter("name");
   		String psw=request.getParameter("psw");
   		PrintWriter out=response.getWriter();
   		try {
   			Class.forName("com.mysql.jdbc.Driver");
   			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital","root","datha123");
   			PreparedStatement pstmt=con.prepareStatement("select * from users where name=? and psw=?");	
   			pstmt.setString(1, name);
   			pstmt.setString(2, psw);
   			ResultSet res=pstmt.executeQuery();
   			if(res.next()) {
   				response.sendRedirect("index.html");
   			}
   			else {
   				out.println("Invalid details");
   			}
   		}
   		catch(Exception e){
   			out.println("Exception is "+e);
   		}
   	}
   }