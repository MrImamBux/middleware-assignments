import javax.servlet.GenericServlet;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

public class Logger {

    private PrintWriter writer;
    private String servletName;

    public Logger(GenericServlet servlet) {
        servletName = servlet.getServletName();
        try {
            File file = new File(servlet.getServletContext().getRealPath("/") + "/log.txt");
            FileWriter fw = new FileWriter(file, true);
            writer = new PrintWriter(fw, true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        log("\n\n");
    }

    public void log(String msg) {
        writer.println("[ " + new Date() + " ] " + servletName + ": " + msg);
    }
}
