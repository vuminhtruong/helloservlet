package calc;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = {"/calc/*"})
public class VariableServlet extends HttpServlet {
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String var = req.getRequestURI().substring(6);
        String value = req.getReader().readLine();
        if(Check.checkVariable(value)){
            if(session.getAttribute(var)==null)
                resp.setStatus(201);
            else
                resp.setStatus(200);
            session.setAttribute(var,value);
        }
        else
            resp.setStatus(403);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String var = req.getRequestURI().substring(6);
        session.setAttribute(var,null);
        resp.setStatus(204);
    }
}
