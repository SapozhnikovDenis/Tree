package ru.homework.model;

import org.apache.log4j.Logger;
import ru.homework.controller.Controller;
import ru.homework.model.constant.Constant;
import ru.homework.view.*;

import java.io.IOException;
import java.util.Random;

/**
 * Класс обрабатывающий информацию
 */
public class Model {
    private Random random = new Random();
    private Logger log = Logger.getLogger(Model.class);
    private Constant constant = new Constant();
    private Tree tree = new Tree();
    private Controller controller = new Controller();
    private View view = new View();

    /**
     * В методе решается заполнить деврево рандомными числами или оставить дерево пустым
     */
    public void start() {
        log.debug("запустили программу");
        view.show(constant.MAYBE_FULL_TREE);

        try {
            int in = controller.read();
            if (in == 1) {
                tree.insert(random.nextInt(15) + 45);
                for (int i = 7; i > 0; i--) {
                    tree.insert(random.nextInt(99));
                }
                log.debug("пользователь заполнил дерево рандомными значениями");
            } else if (in != 0 && in != 1) {
                error();
                start();
            }
            work();
        } catch (Exception e) {
            error();
            start();
        }
    }

    /**
     * В методе происходит вся работа
     */
    private void work() {
        while (true) {
            view.show(constant.OPERATION_INSERT, constant.OPERATION_INSERT_BEFORE, constant.OPERATION_UPDATE,
                    constant.OPERATION_DELETE, constant.OPERATION_SELECT, constant.OPERATION_BALANCE, constant.OFF);

            try {
                int in = controller.read();
                switch (in) {
                    case 0:
                        view.show(constant.ANSWER_OPERATION_INSERT);
                        if (!tree.insert(controller.read())) error();
                        else log.debug("добавили узел");
                        break;
                    case 1:
                        view.show(constant.ANSWER_OPERATION_INSERT_BEFORE);
                        if (!tree.insertBefore(controller.read(), controller.read())) error();
                        else log.debug("добавили узел");
                        break;
                    case 2:
                        view.show(constant.ANSWER_OPERATION_UPDATE);
                        if (!tree.update(controller.read(), controller.read())) error();
                        else log.debug("обновили узел");
                        break;
                    case 3:
                        view.show(constant.ANSWER_OPERATION_DELETE);
                        if (!tree.delete(controller.read())) error();
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
            } catch (Exception e) {
                error();
            }
        }
    }

    /**
     * Метод пишущий ошибки в лог файл
     */
    private void error() {
        view.show(constant.ERROR, "");
        log.error("Пользователь ввел недопустимое значение");
    }
}
