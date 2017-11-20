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

/**
 * Класс сервлета с адресом /servlet
 */
@WebServlet(urlPatterns = "/servlet")
public class ActionServlet extends HttpServlet {
    private Random random = new Random();
    private Tree tree = new Tree();
    private Logger log = Logger.getLogger(ActionServlet.class);
    private int oneValue;
    private int twoValue;

    /**
     * Метод обрабатывает POST запрос, который приходит с tree.jsp
     */
    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        PrintWriter out = res.getWriter();
        out.print("<html lang=\"ru\"><center>");

        boolean complete = true;
        try {
            if (req.getParameter("one value") != null) {
                oneValue = Integer.parseInt(req.getParameter("one value"));
            }
            if (req.getParameter("two value") != null) {
                twoValue = Integer.parseInt(req.getParameter("two value"));
            }
        } catch (NumberFormatException e) {
            out.print("<b>Не верный ввод</b><br>");
            complete = false;
        }

        if (complete) {
            complete = action(req.getParameterNames());
        }

        if (!complete) {
            out.print("<b>Операция не выполнена!</b><br>");
           log.error("пользователь ввел недопустимое значение");
        }

        String strTree = tree.toString().replaceAll("\n","<br>");
        out.print(strTree);
        log.debug("отобразили дерево");
    }

    /**
     * Метод производит действия с деревом
     * @return true or false в зависимости от успеха операции
     */
    private boolean action(Enumeration flds) {
        boolean completeAction = true;
        while (flds.hasMoreElements()) {
            String str = (String) flds.nextElement();
            switch (str) {
                case "yes":
                    tree.insert(random.nextInt(15) + 45);
                    for (int i = 7; i > 0; i--) {
                        tree.insert(random.nextInt(99));
                    }
                    log.debug("пользователь заполнил дерево рандомными значениями");
                    break;
                case "add":
                    completeAction = tree.insert(oneValue);
                    if (completeAction) { log.debug("добавили узел"); }
                    break;
                case "add before":
                    completeAction = tree.insertBefore(twoValue, oneValue);
                    if (completeAction) { log.debug("добавили узел"); }
                    break;
                case "update":
                    completeAction = tree.update(twoValue, oneValue);
                    if (completeAction) { log.debug("обновили узел"); }
                    break;
                case "delete":
                    completeAction = tree.delete(oneValue);
                    if (completeAction) { log.debug("удалили узел"); }
                    break;
                case "balance":
                    tree = tree.balance();
                    if (completeAction) { log.debug("сбалансировали дерево"); }
                    break;
            }
        }
        return completeAction;
    }
}