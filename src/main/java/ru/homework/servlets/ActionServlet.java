package ru.homework.servlets;

import ru.homework.model.Tree;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.Random;

@WebServlet(urlPatterns = "/tree")
public class ActionServlet extends HttpServlet {
    Random random = new Random();
    Tree tree = new Tree();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        out.print("<html><center>");
        out.print("<form method=\"POST\">");

        Enumeration flds = req.getParameterNames();
        if (!flds.hasMoreElements()) {
            out.print("<h1>" + "Do you need an empty tree?" + "</h1>");
            out.print("<br><INPUT TYPE=submit name=yes value=\"yes\">");
            out.print("<INPUT TYPE=submit name=no value=\"no\">");
        }
        else {
            boolean complete = true;
            boolean illegalArgumentOne = true;
            boolean illegalArgumentTwo = true;
            int one = 0;
            int two = 0;
            while (flds.hasMoreElements()){
                String str = (String) flds.nextElement();
                if (str.equals("yes")) {
                    tree.insert(random.nextInt(10) + 40);
                    for (int i = 10; i > 0; i--) tree.insert(random.nextInt(99));
                }
                else if (str.equals("one value")) {
                    try {
                        one = Integer.parseInt(req.getParameter(str));
                    } catch (NumberFormatException e) {
                        illegalArgumentOne = false;
                    }
                }
                else if (str.equals("two value")) {
                    try {
                        two = Integer.parseInt(req.getParameter(str));
                    } catch (NumberFormatException e) {
                        illegalArgumentTwo = false;
                    }
                }
                else if (str.equals("add")) complete = tree.insert(one);
                else if (str.equals("add before")) complete = tree.insertBefore(two, one);
                else if (str.equals("update")) complete = tree.update(two, one);
                else if (str.equals("delete")) complete = tree.delete(one);
                else if (str.equals("balance")) tree = tree.balance();
            }
            String strTree = tree.toString().replaceAll("\n","<br>");
            if (!complete) out.print("<b>Operation not complete!</b><br>");
            if (!illegalArgumentOne) out.print("<b>Invalid first number entered !</b><br>");
            if (!illegalArgumentTwo) out.print("<b>Invalid second number entered</b><br>");
            out.println(strTree);
            out.print("<br><b>Enter the number you want to insert/delete</b>");
            out.print("<br><br><h7>One value:</h7> <input type=\"text\" size=\"5\" name=\"one value\" value=\"0\"><br>");
            out.print("<br><b>Enter the number you want to update or before which you want to insert</b>");
            out.print("<br><h7>Two value:</h7> <input type=\"text\" size=\"5\" name=\"two value\" value=\"0\"><br>");
            out.print("<br><INPUT TYPE=submit name=add value=\"add\">");
            out.print("<INPUT TYPE=submit name=add before value=\"add before\">");
            out.print("<INPUT TYPE=submit name=update value=\"update\">");
            out.print("<INPUT TYPE=submit name=delete value=\"delete\">");
            out.print("<INPUT TYPE=submit name=balance value=\"balance\">");
        }
        out.print("</center></form></html>");
        out.close();
    }
}
