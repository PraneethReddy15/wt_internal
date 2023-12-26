import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import wt_internal_2.Product;

/**
 * Servlet implementation class DataServlet
 */
public class DataServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DataServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        try 
	        {
	        	List<Product> productList = fetchProductList(); 
	            request.setAttribute("productList", productList);
	        	Connection conn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","ksp123");
	        	Statement stmt=conn.createStatement();
	        	HttpSession session =  request.getSession();
                ResultSet products = stmt.executeQuery("select * from produts");
                session.setAttribute("products", products);
                session.setAttribute("connection", conn);

	            RequestDispatcher dispatcher = request.getRequestDispatcher("admin.jsp");
	            dispatcher.forward(request, response);
	        	Class.forName("oracle.jdbc.driver.OracleDriver");
	            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO products VALUES (?, ?, ?, ?, ?)");
	            pstmt.setString(1, request.getParameter("productid"));
	            pstmt.setString(2, request.getParameter("productname"));
	            pstmt.setString(3, request.getParameter("description"));
	            pstmt.setString(4, request.getParameter("price"));
	            pstmt.setString(5, request.getParameter("availableqty"));

	            pstmt.executeUpdate();
	        } 
	        catch (Exception e) 
	        {
	            e.printStackTrace();
	        }
	      }

	private List<Product> fetchProductList() {
		List<Product> p= new ArrayList<>();
		
		return p;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
