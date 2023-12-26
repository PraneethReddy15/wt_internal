
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AdminServlet
 */
public class ShopKeeperServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShopKeeperServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		response.setContentType("text/html");
		String uname=request.getParameter("uname");
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection conn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1525:xe","system","ksp123");
			PrintWriter op=response.getWriter();
			Statement stmt=conn.createStatement();
			ResultSet rs = stmt.executeQuery("select * from student where username='"+uname+"'");
			if(rs.next()) 
			{
				if(uname.equals(rs.getString(1)))
				{			
					RequestDispatcher rd= request.getRequestDispatcher("shopkeeper.html");
					rd.include(request, response);
				}
				else 
				{
					op.println("Incorrect Credentials..");
					RequestDispatcher rd= request.getRequestDispatcher("register.html");
					rd.include(request, response);
				}	
			}
			else 
			{
				op.println("You are not an authorized user register here...");
				RequestDispatcher rd= request.getRequestDispatcher("register.html");
				rd.include(request, response);
			}
		} 
		catch (Exception e) 
		{
			
			System.out.println("Error:"+e.getMessage());
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
