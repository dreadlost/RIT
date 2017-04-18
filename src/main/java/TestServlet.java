
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class TestServlet extends HttpServlet {

    private static Logger log = LogManager.getLogger(TestServlet.class);

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("Работает нахуй");
        log.debug("Заебись");
        req.setAttribute("name", "Дима Главатских");
        req.getRequestDispatcher("mypage.jsp").forward(req, resp);
    }
}
