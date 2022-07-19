package Servlets;

import data.blank.Blank;
import data.blank.BlankController;
import data.themes.ThemeController;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class BlankWorkServlet extends HttpServlet {

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
        String mode  = req.getParameter("mode");
        String name = "";
        Blank blank = null;
        if (mode!=null && mode.equals("check")){
            name = req.getParameter("name");
            blank = BlankController.getBlank(name);
        }
        else {
            name= String.valueOf(new Date());
            blank = BlankController.newBlank(name);
        }
        req.setAttribute("blank",blank);
        req.setAttribute("themes",ThemeController.getNames());
        RequestDispatcher dispatcher = req.getRequestDispatcher("blankWork.jsp");
        dispatcher.forward(req,resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        resp.setCharacterEncoding("UTF-8");
        String first = req.getParameter("firstName");
        //В данном контексте нет смысла сохранять старый план
        BlankController.delBlank(first);
        String second =getStr(req.getParameter("newName"));
        String str = req.getParameter("start");
        LocalDateTime start = LocalDateTime.parse(str);
        str = req.getParameter("end");
        LocalDateTime end = LocalDateTime.parse(str);

        Blank blank = new Blank();
        blank.setName(second);
        blank.setStart(start);
        blank.setEnd(end);
        int duration = Integer.parseInt(req.getParameter("duration"));
        blank.setDuration(duration);
        Map<String, Integer> map = new HashMap<>();
        for (Map.Entry<String,String[]> param : req.getParameterMap().entrySet()){
            String key = getStr(param.getKey());
            if (key.startsWith("forTheme:")){
                String value = param.getValue()[0];
                key = key.substring(9);
                map.put(key, Integer.valueOf(value));
            }
        }
        blank.setThemeMap(map);
        BlankController.saveBlank(blank);

        req.setAttribute("blank",blank);
        req.setAttribute("themes",ThemeController.getNames());
        RequestDispatcher dispatcher = req.getRequestDispatcher("blankWork.jsp");
        dispatcher.forward(req,resp);
    }


    private  String getStr(String str) throws UnsupportedEncodingException {
        return new String(str.getBytes("ISO-8859-1"));
    }


}

