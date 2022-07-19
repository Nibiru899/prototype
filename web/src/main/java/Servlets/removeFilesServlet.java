package Servlets;

import controllers.ThemeController;
import data.Theme;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;

public class removeFilesServlet extends HttpServlet {

    public void init(ServletConfig servletConfig) {

        try {
            super.init(servletConfig);
        } catch (ServletException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        File dir = new File("themes/");
        File dir2 = new File("blanks/");
        remove(dir);
        remove(dir2);

        resp.sendRedirect("/");
    }

    private void remove(File dir){
        for (File file : dir.listFiles()){
            if (file.isDirectory()){
                remove(file);
            }
            else {
                file.delete();
            }
        }
    }



}

