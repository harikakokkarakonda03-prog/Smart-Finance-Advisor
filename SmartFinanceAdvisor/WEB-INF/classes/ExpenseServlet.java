import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.*;
import java.sql.*;

public class ExpenseServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String name = request.getParameter("name");
        String amount = request.getParameter("amount");

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/finance_db",
                    "root",
                    "Finance@123"
            );

            String query = "INSERT INTO expenses(name, amount) VALUES (?, ?)";

            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, name);
            ps.setString(2, amount);

            ps.executeUpdate();

            out.println("<h2>Expense Saved Successfully</h2>");
            out.println("<a href='/SmartFinanceAdvisor/frontend/expense.html'>Add Another Expense</a>");
            out.println("<br><br>");
            out.println("<a href='/SmartFinanceAdvisor/viewExpenses'>View All Expenses</a>");

            con.close();

        } catch(Exception e) {

            out.println("Error: " + e);

        }

    }
}