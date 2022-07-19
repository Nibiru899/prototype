package Servlets;

import data.Blank;
import controllers.BlankController;
import controllers.ThemeController;
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
            blank = new Blank();
            blank.setName(name);
            BlankController.saveBlank(blank);
        }
        boolean changed = false;
        for (String themeName : blank.getThemeMap().keySet()){
            if (!ThemeController.isExist(themeName)){
                changed = true;
                blank.getThemeMap().remove(themeName);
            }
        }
        if (changed){
            BlankController.delBlank(blank.getName());
            BlankController.saveBlank(blank);
        }
        goBack(req,resp,blank,false);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        resp.setCharacterEncoding("UTF-8");
        String first = req.getParameter("firstName");
        String second =getStr(req.getParameter("newName"));
        if (BlankController.isExist(second) && !first.equals(second)){
            goBack(req,resp,BlankController.getBlank(first),true);
            return;
        }
        //В данном контексте нет смысла сохранять старый план
        BlankController.delBlank(first);

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

        goBack(req,resp,blank,false);
    }

    private void goBack(HttpServletRequest req, HttpServletResponse resp, Blank blank,boolean exists) throws ServletException, IOException {
        req.setAttribute("blank",blank);
        req.setAttribute("themes",ThemeController.getNames());
        req.setAttribute("exists",exists);
        RequestDispatcher dispatcher = req.getRequestDispatcher("blankWork.jsp");
        dispatcher.forward(req,resp);
    }

    private  String getStr(String str) throws UnsupportedEncodingException {
        return new String(str.getBytes("ISO-8859-1"));
    }


}

