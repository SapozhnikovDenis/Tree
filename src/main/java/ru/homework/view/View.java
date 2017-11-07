package ru.homework.view;

public class View {
    /**
     * Метод выводит в консоль строки
     */
    public static void show(String... strings) {
        for (String s: strings) System.out.println(s);
    }
}
