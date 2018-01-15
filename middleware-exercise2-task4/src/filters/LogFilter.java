package filters;

import javax.servlet.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

public class LogFilter implements Filter {

    private PrintWriter writer;
    private String servletName;

    public void init(FilterConfig config) throws ServletException {
        try {
            File file = new File(config.getServletContext().getRealPath("/") + "/log.txt");
            FileWriter fw = new FileWriter(file, true);
            writer = new PrintWriter(fw, true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        servletName = req.getServerName();
        log("Amount: " + req.getParameter("amount"));
        log("Tax: " + req.getParameter("tax"));
        chain.doFilter(req, resp);
        log("Responded back successfully.");
    }

    private void log(String msg) {
        writer.println("[ " + new Date() + " ] " + servletName + ": " + msg);
    }

}
