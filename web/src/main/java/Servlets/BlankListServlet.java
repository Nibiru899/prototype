package Servlets;

import data.Blank;
import controllers.BlankController;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

public class BlankListServlet extends HttpServlet {

    public void init(ServletConfig servletConfig) {
        try {
            super.init(servletConfig);
        } catch (ServletException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        List<Blank> blanks = BlankController.getAllBlanks();
        req.setAttribute("blanks",blanks);
        RequestDispatcher dispatcher = req.getRequestDispatcher("blankList.jsp");
        dispatcher.forward(req,resp);

    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setCharacterEncoding("UTF-8");
        String name = getStr(req.getParameter("name"));
        BlankController.delBlank(name);
        resp.sendRedirect("/blanks");
    }

    private  String getStr(String str) throws UnsupportedEncodingException {
        return new String(str.getBytes("ISO-8859-1"));
    }
}

