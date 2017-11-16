package servlets;

import org.apache.log4j.Logger;
import ru.homework.model.Tree;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.Random;

@WebServlet(urlPatterns = "/servlet")
public class ActionServlet extends HttpServlet {
    public Random random = new Random();
    public Tree tree = new Tree();
    private Logger log = Logger.getLogger(ActionServlet.class);

    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        PrintWriter out = res.getWriter();
        Enumeration flds = req.getParameterNames();

        out.print("<center>");
        String action = "";
        boolean complete = true;
        int one = 0;
        int two = 0;

        while (flds.hasMoreElements()){
            String str = (String) flds.nextElement();
            if (str.equals("yes")) {
                tree.insert(random.nextInt(20) + 20);
                for (int i = 10; i > 0; i--) { tree.insert(random.nextInt(99)); }
                log.debug("пользователь заполнил дерево рандомными значениями");
            }
            else if (str.equals("one value")) {
                try {
                    one = Integer.parseInt(req.getParameter(str));
                } catch (NumberFormatException e) {
                    out.print("<b>Invalid first number entered !</b><br>");
                }
            }
            else if (str.equals("two value")) {
                try {
                    two = Integer.parseInt(req.getParameter(str));
                } catch (NumberFormatException e) {
                    out.print("<b>Invalid second number entered</b><br>");
                }
            }
            else if (str.equals("add") || str.equals("add before") || str.equals("update")
                    || str.equals("delete") || str.equals("balance")) {
                action = str;
            }

        }

        if (action.equals("add")) {
            complete = tree.insert(one);
            if (complete) { log.debug("добавили узел"); }
        }
        else if (action.equals("add before")) {
            complete = tree.insertBefore(two, one);
            if (complete) { log.debug("добавили узел"); }
        }
        else if (action.equals("update")) {
            complete = tree.update(two, one);
            if (complete) { log.debug("обновили узел"); }
        }
        else if (action.equals("delete")) {
            complete = tree.delete(one);
            if (complete) { log.debug("удалили узел"); }
        }
        else if (action.equals("balance")) {
            tree = tree.balance();
            if (complete) { log.debug("сбалансировали дерево"); }
        }

        String strTree = tree.toString().replaceAll("\n","<br>");

        if (!complete) {
            out.print("<b>Operation not complete!</b><br>");
           log.error("пользователь ввел недопустимое значение");
        }

        out.print(strTree);
        log.debug("отобразили дерево");
    }
}