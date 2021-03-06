package calc;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import calc.Check;

@WebServlet(urlPatterns = {"/calc/expression"})
public class ExpressServlet extends HttpServlet {
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String expression = req.getReader().readLine();
        if(Check.checkExpression(expression)){
            if(session.getAttribute("expression")==null)
                resp.setStatus(201);
            else
                resp.setStatus(200);
            session.setAttribute("expression",expression);
        }
        else
            resp.sendError(400,"Wrong Format");
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        session.setAttribute("expression",null);
        resp.setStatus(204);
    }
}
