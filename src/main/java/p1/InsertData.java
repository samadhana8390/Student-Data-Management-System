package p1;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/InsertData")
public class InsertData extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		int id = Integer.parseInt(request.getParameter("id"));

		String name = request.getParameter("uname");
		double marks = Double.parseDouble(request.getParameter("marks"));
		int res = 0;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			// Step 2 To Connect DB

			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/studentdb", "root", "root");
			String sql = "insert into student values(?,?,?)";

			PreparedStatement ps = con.prepareStatement(sql);

			ps.setInt(1, id);
			ps.setString(2, name);
			ps.setDouble(3, marks);

			res = ps.executeUpdate();

		} catch (Exception e) {

			System.out.println(e);
		}

		if (res > 0) {
			out.print("<h1> Success !!! </h1>");
		}

	}

}

