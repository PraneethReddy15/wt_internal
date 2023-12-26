

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

/**
 * Servlet implementation class RegisterServlet
 */
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try 
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection conn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","ksp123");
			PreparedStatement pstmt=conn.prepareStatement("insert into student values(?,?,?)");
			
			BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			while(true)
			{
				System.out.println("Enter username:");
				String ename=br.readLine();
				System.out.println("Enter phno:");
				int ephno=Integer.parseInt(br.readLine());
				System.out.println("enter id");
				int eid=Integer.parseInt(br.readLine());
				
				pstmt.setString(1, ename);
				pstmt.setInt(2, ephno);
				pstmt.setInt(3, eid);
				int count=pstmt.executeUpdate();
				
				if(count>0)
				{
					System.out.println(count + " rows inserted.");
				}
				else {
					System.out.println("no rows inserted");
				}
				System.out.println("Want to insert more rows[Yes/No]:");
				String ch=br.readLine();
				if(ch.equalsIgnoreCase("no")|| ch.equalsIgnoreCase("No"))
				{
					System.out.println("Exit");
					break;
				}
				
			}

		}
		catch(Exception e)
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
