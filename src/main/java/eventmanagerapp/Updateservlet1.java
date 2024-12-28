package eventmanagerapp;


	import java.io.IOException;
	import java.io.PrintWriter;
	import java.sql.Connection;
	import java.sql.DriverManager;
	import java.sql.PreparedStatement;
	import java.sql.ResultSet;
	import java.sql.SQLException;

	import javax.print.attribute.HashPrintRequestAttributeSet;
	import javax.servlet.RequestDispatcher;
	import javax.servlet.ServletException;
	import javax.servlet.ServletRequest;
	import javax.servlet.ServletResponse;
	import javax.servlet.annotation.WebServlet;
	import javax.servlet.http.HttpServlet;
	import javax.servlet.http.HttpServletRequest;
	import javax.servlet.http.HttpServletResponse;

	@WebServlet(urlPatterns = "/update")
	public class Updateservlet1 extends HttpServlet {
		@Override
		public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
			int id = Integer.parseInt(req.getParameter("id"));
			String title = req.getParameter("title");
			String loc = req.getParameter("loc");
			String date = req.getParameter("date");
			String guest = req.getParameter("guest");
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/event_manage_system", "root",
						"root");
				PreparedStatement ps = con.prepareStatement("update event set title=?,date=?,guest=?, loc=? where id=?");
				ps.setString(1, title);
				ps.setString(2, date);
				ps.setString(3, guest);
				ps.setString(4, loc);
				ps.setInt(5, id);
				int row = ps.executeUpdate();
				PrintWriter pw = res.getWriter();
				pw.write("<html><body><h1>event updated successfully</h1></body></html>");
				RequestDispatcher rd = req.getRequestDispatcher("all");
				rd.include(req, res);
				ps.close();
				con.close();
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
		}

	}

