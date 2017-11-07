package ru.homework.model;

import org.apache.log4j.Logger;
import ru.homework.controller.Controller;
import ru.homework.view.View;

import java.util.Random;

/**
 * Класс обрабатывающий информацию
 */
public class Model {
    private static Random random = new Random();
    private static Tree tree = new Tree();
    private static final Logger log = Logger.getLogger(Model.class);

    /**
     * Запуск
     */
    public static void work() {
        View.show(Constant.MAYBE_FULL_TREE);
        int in = Controller.readConsole();
        if (in == 1) {
            tree.insert(random.nextInt(100) + 50);
            for (int i = random.nextInt(10) + 5; i > 0; i--) tree.insert(random.nextInt(200));

        } else if (in != 0 && in != 1) {
            error();
            work();
        }
        while (true) {
            View.show(Constant.OPERATION_INSERT, Constant.OPERATION_INSERT_POINT, Constant.OPERATION_UPDATE,
                    Constant.OPERATION_DELETE, Constant.OPERATION_SELECT, Constant.OPERATION_BALANCE, Constant.OFF);

            in = Controller.readConsole();

            if (in < 0 || in > 6) error();
            else {
                switch (in) {
                    case 0:
                        View.show(Constant.ANSWER_OPERATION_INSERT);
                        if(!tree.insert(Controller.readConsole())) error();
                        break;
                    case 1:
                        View.show(Constant.ANSWER_OPERATION_INSERT_POINT);
                        if (!tree.insertPoint(Controller.readConsole(), Controller.readConsole(), Controller.readConsole())) error();
                        break;
                    case 2:
                        View.show(Constant.ANSWER_OPERATION_UPDATE);
                        if (!tree.update(Controller.readConsole(), Controller.readConsole())) error();
                        break;
                    case 3:
                        View.show(Constant.ANSWER_OPERATION_DELETE);
                        if(!tree.delete(Controller.readConsole())) error();
                        break;
                    case 4:
                        View.show(tree.toString());
                        break;
                    case 5:
                        tree = tree.balance();
                        break;
                    case 6:
                        System.exit(0);
                        break;
                }
            }
        }
    }

    /**
     * Метод пишущий ошибки в лог файл
     */
    public static void error() {
        View.show(Constant.ERROR);
        log.error("Пользователь ввел недопустимое значение");
    }
}
