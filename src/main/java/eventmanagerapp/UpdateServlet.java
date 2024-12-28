package eventmanagerapp;
	import java.io.IOException;
	import java.io.PrintWriter;
	import java.sql.Connection;
	import java.sql.DriverManager;
	import java.sql.PreparedStatement;
	import java.sql.ResultSet;
	import java.sql.SQLException;

	import javax.servlet.RequestDispatcher;
	import javax.servlet.ServletException;
	import javax.servlet.ServletRequest;
	import javax.servlet.ServletResponse;
	import javax.servlet.annotation.WebServlet;
	import javax.servlet.http.HttpServlet;

	@WebServlet(urlPatterns ="/updatepage")
	public class UpdateServlet extends HttpServlet {

		@Override
		public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
			int id = Integer.parseInt(req.getParameter("id"));
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/event_manage_system", "root",
						"root");
				PreparedStatement ps = con.prepareStatement("select * from event where id=?");
				ps.setInt(1, id);
				ResultSet rs = ps.executeQuery();
				if (rs.next()) {
					req.setAttribute("rs", rs);
					RequestDispatcher rd = req.getRequestDispatcher("updatepage.jsp");
					rd.forward(req, res);
				}
				rs.close();
				ps.close();
				con.close();

			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}


