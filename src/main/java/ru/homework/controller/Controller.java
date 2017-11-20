package ru.homework.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Controller {
    private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    /**
     * Метод который считывает с консоли
     * @return число которое ввел пользователь
     * @throws IOException Если возникает ошибка ввода
     */
    public int read() throws IOException {
        return Integer.parseInt(reader.readLine());
    }
}
