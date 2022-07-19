package Servlets;

import data.themes.Theme;
import data.themes.ThemeController;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class ThemeListServlet extends HttpServlet {



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
        String[] themes = ThemeController.getNames();
        req.setAttribute("themes",themes);
        RequestDispatcher dispatcher = req.getRequestDispatcher("themeList.jsp");
        dispatcher.forward(req,resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setCharacterEncoding("UTF-8");
        String name = getStr(req.getParameter("name"));
        ThemeController.delTheme(name);
        resp.sendRedirect("/themes");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        resp.setCharacterEncoding("UTF-8");
        Theme theme = ThemeController.newTheme(getStr(req.getParameter("name")));
        req.setAttribute("theme",theme);
        RequestDispatcher dispatcher = req.getRequestDispatcher("themeWork.jsp");
        dispatcher.forward(req,resp);
    }


    private  String getStr(String str) throws UnsupportedEncodingException {
        return new String(str.getBytes("ISO-8859-1"));
    }
}

