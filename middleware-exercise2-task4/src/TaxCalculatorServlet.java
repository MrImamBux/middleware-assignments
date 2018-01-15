import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

public class TaxCalculatorServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        double amount = Double.parseDouble(request.getParameter("amount"));
        double tax = Double.parseDouble(request.getParameter("tax"));

        tax = tax/100;

        double calculatedPct = tax*amount;

        double totalAmount = Math.floor(calculatedPct + amount);


        response.setContentType("text/plain");
        response.getWriter().print(totalAmount);
    }
}
