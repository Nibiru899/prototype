package Servlets;

import data.Theme;
import controllers.ThemeController;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;

public class ThemeWorkServlet extends HttpServlet {

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
        String mode = req.getParameter("create");
        Theme theme = null;
        if (mode.equals("true")){
            theme = ThemeController.newTheme(String.valueOf(new Date()));
        }
        else {
            theme = ThemeController.getTheme(req.getParameter("name"));
        }

        req.setAttribute("theme",theme);
        RequestDispatcher dispatcher = req.getRequestDispatcher("themeWork.jsp");
        dispatcher.forward(req,resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        resp.setCharacterEncoding("UTF-8");
        String name = getStr(req.getParameter("name"));
        String indx = getStr(req.getParameter("index"));
        ThemeController.delQuestion(name,indx);

        Theme theme = ThemeController.getTheme(name);
        goBack(req,resp,theme,true);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        resp.setCharacterEncoding("UTF-8");
        String name = getStr(req.getParameter("name"));
        ThemeController.addQuestion(name);

        Theme theme = ThemeController.getTheme(name);
        goBack(req,resp,theme,true);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        String first = getStr(req.getParameter("first"));
        String second = getStr(req.getParameter("second"));
        boolean cond = !ThemeController.isExist(second)  && !first.equals(second);
        Theme theme = null;
        if (cond){
            ThemeController.renameTheme(first,second);
            theme =  ThemeController.getTheme(second);
        } else {
            theme = ThemeController.getTheme(first);
        }


       goBack(req,resp,theme,cond);
    }

    private void goBack(HttpServletRequest req, HttpServletResponse resp, Theme theme, boolean cond) throws ServletException, IOException {
        req.setAttribute("theme",theme);
        req.setAttribute("exist",!cond);
        RequestDispatcher dispatcher = req.getRequestDispatcher("themeWork.jsp");
        dispatcher.forward(req,resp);
    }

    private  String getStr(String str) throws UnsupportedEncodingException {
        return new String(str.getBytes("ISO-8859-1"));
    }


}

