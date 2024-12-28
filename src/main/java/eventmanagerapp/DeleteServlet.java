package eventmanagerapp;

	import java.io.IOException;
	import java.io.PrintWriter;
	import java.sql.Connection;
	import java.sql.DriverManager;
	import java.sql.PreparedStatement;
	import java.sql.SQLException;

	import javax.servlet.RequestDispatcher;
	import javax.servlet.ServletException;
	import javax.servlet.ServletRequest;
	import javax.servlet.ServletResponse;
	import javax.servlet.annotation.WebServlet;
	import javax.servlet.http.HttpServlet;

	@WebServlet("/delete")
	public class DeleteServlet extends HttpServlet {
		@Override
		public void service(ServletRequest req, ServletResponse res) {
			Connection con = null;
			PreparedStatement ps = null;
			int id = Integer.parseInt(req.getParameter("id"));
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				con = DriverManager.getConnection("jdbc:mysql://localhost:3306/event_manage_system", "root", "root");
				ps = con.prepareStatement("delete from event where id=?");
				ps.setInt(1, id);
				int row = ps.executeUpdate();
				PrintWriter pw = res.getWriter();
				pw.write("<h1>Event deleted successfully</h1>");
				RequestDispatcher rd = req.getRequestDispatcher("all");
				rd.include(req, res);
				ps.close();
				con.close();
			} catch (ClassNotFoundException | SQLException | IOException | ServletException e) {
				e.printStackTrace();
			} finally {
				
			}
		}
	}


