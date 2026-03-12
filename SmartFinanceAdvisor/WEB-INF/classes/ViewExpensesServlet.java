import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.*;
import java.sql.*;

public class ViewExpensesServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("application/json");
        PrintWriter out = response.getWriter();

        StringBuilder json = new StringBuilder();
        json.append("[");

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/finance_db",
                    "root",
                    "Finance@123"
            );

            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM expenses");

            boolean first = true;

            while (rs.next()) {

                if (!first) {
                    json.append(",");
                }

                json.append("{");
                json.append("\"name\":\"").append(rs.getString("name")).append("\",");
                json.append("\"amount\":").append(rs.getInt("amount"));
                json.append("}");

                first = false;
            }

            json.append("]");

            con.close();

        } catch (Exception e) {
            out.print("{\"error\":\"" + e.getMessage() + "\"}");
            return;
        }

        out.print(json.toString());
    }
}