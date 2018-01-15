import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

public class WebCounterServlet extends HttpServlet {

    private int count;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        fetchWebCount();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(request.getServletContext().getAttribute("counter") != null) {
            count = Integer.parseInt(request.getServletContext().getAttribute("counter").toString());
        }
        if(request.getSession().isNew()) {
            count++;
        }
        request.getServletContext().setAttribute("counter", count);

        response.setContentType("plain/text");
        response.getWriter().print(count);
    }

    public void fetchWebCount() {
        try {
            File file = new File(this.getServletContext().getRealPath("/") + "/webcounter.txt");
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            count = Integer.parseInt(br.readLine());
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void destroy() {
        super.destroy();
        updateWebCounter();
    }

    private void updateWebCounter() {
        try {
            File file = new File(this.getServletContext().getRealPath("/") + "/webcounter.txt");
            FileWriter fw = new FileWriter(file);
            fw.write(count + "");
            fw.flush();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
