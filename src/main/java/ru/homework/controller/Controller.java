package ru.homework.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Controller {
    private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    /**
     * Метод который считывает с консоли
     * @return число которое считал или Integer.MAX_VALUE если пользователь ввел некоректные данные;
     */
    public int read(){
        int ret;
        try {
            ret = Integer.parseInt(reader.readLine());
            return ret;
        } catch (Exception e) {
            return Integer.MAX_VALUE;
        }
    }
}
