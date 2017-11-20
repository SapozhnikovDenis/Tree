package ru.homework.view;

/**
 * Класс вывода в консоль
 */
public class View{
    /**
     * Метод вывода в консоль
     * @param strings строки которые нужно вывести
     */
    public void show(String... strings) {
        for (String s: strings) System.out.println(s);
    }
}
