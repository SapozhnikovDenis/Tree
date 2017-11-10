package ru.homework.model;

import org.apache.log4j.Logger;
import ru.homework.controller.Controller;
import ru.homework.model.constant.Constant;
import ru.homework.view.View;

import java.util.Random;

/**
 * Класс обрабатывающий информацию
 */
public class Model {
    private Random random = new Random();
    private final Logger log = Logger.getLogger(Model.class);
    private Constant constant = new Constant();
    private Tree tree = new Tree();
    private View view = new View();
    private Controller controller = new Controller();

    /**
     * В методе решается заполнить деврево рандомными числами или с консоли
     */
    public void start() {
        view.show(constant.MAYBE_FULL_TREE);
        int in = controller.readConsole();
        if (in == 1) {
            tree.insert(random.nextInt(100) + 50);
            for (int i = 10; i > 0; i--) tree.insert(random.nextInt(200));

        } else if (in != 0 && in != 1) {
            error();
            start();
        }
        work();
    }

    /**
     * В методе происходит вся работа
     */
    public void work() {
        while (true) {
            view.show(constant.OPERATION_INSERT, constant.OPERATION_INSERT_BEFORE, constant.OPERATION_UPDATE,
                    constant.OPERATION_DELETE, constant.OPERATION_SELECT, constant.OPERATION_BALANCE, constant.OFF);

            int in = controller.readConsole();

            switch (in) {
                case 0:
                    view.show(constant.ANSWER_OPERATION_INSERT);
                    if (!tree.insert(controller.readConsole())) error();
                    else log.debug("добавили узел");
                    break;
                case 1:
                    view.show(constant.ANSWER_OPERATION_INSERT_BEFORE);
                    if (!tree.insertBefore(controller.readConsole(), controller.readConsole())) error();
                    else log.debug("добавили узел");
                    break;
                case 2:
                    view.show(constant.ANSWER_OPERATION_UPDATE);
                    if (!tree.update(controller.readConsole(), controller.readConsole())) error();
                    else log.debug("обновили узел");
                    break;
                case 3:
                    view.show(constant.ANSWER_OPERATION_DELETE);
                    if (!tree.delete(controller.readConsole())) error();
                    else log.debug("удалили узел");
                    break;
                case 4:
                    view.show(tree.toString());
                    log.debug("отобразили дерево");
                    break;
                case 5:
                    tree = tree.balance();
                    log.debug("сбалансировали дерево");
                    break;
                case 6:
                    log.debug("выключили программу");
                    System.exit(0);
                    break;
                default:
                    error();
                    break;
            }
        }
    }

    /**
     * Метод пишущий ошибки в лог файл
     */
    public void error() {
        view.show(constant.ERROR, "");
        log.error("Пользователь ввел недопустимое значение");
    }
}
