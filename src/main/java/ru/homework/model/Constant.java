package ru.homework.model;

/**
 * Класс с константами
 */
abstract class Constant {
    static final String MAYBE_FULL_TREE = "Хочешь пустое дерево или заполнить рандомными значениями?\n" +
            "0 если пустое, 1 если полное";
    static final String OPERATION_INSERT = "Нажми 0 чтобы добавить число в дерeво";
    static final String OPERATION_INSERT_POINT = "Нажми 1 чтобы добавить число в конкретное место, в дереве";
    static final String OPERATION_UPDATE = "Нажми 2 чтобы изменить число";
    static final String OPERATION_DELETE = "Нажми 3 чтобы удалить число";
    static final String OPERATION_SELECT = "Нажми 4 чтобы вывести дерево на экран";
    static final String OPERATION_BALANCE = "Нажми 5 чтобы сбалансировать дерево";
    static final String OFF = "Нажми 6 чтобы выключить программу";
    static final String ANSWER_OPERATION_INSERT = "Введи число, которое хочешь добавить";
    static final String ANSWER_OPERATION_INSERT_POINT = "Введи числа: узел родителя, после которого хочешь вставить число, узел ребенка, перед которым хочешь вставить число и само число через Enter";
    static final String ANSWER_OPERATION_UPDATE = "Введи число, которое хочешь изменить, и которым хочешь заменить через Enter";
    static final String ANSWER_OPERATION_DELETE = "Введи число, которое хочешь удалить";
    static final String ERROR = "Некорректное значение! Возвращаюсь в меню";
}
