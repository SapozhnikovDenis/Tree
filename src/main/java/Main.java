import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Класс содержащий метод main - стартовая точка программы
 */
public class Main {
    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Tree three = new Tree();

        //Starting values
        three.insert(50);
        three.insert(25);
        three.insert(75);
        three.insert(10);
        three.insert(60);
        three.insert(80);
        three.insert(35);
        three.insert(150);
        three.insert(400);
        three.insert(-200);
        three.select();
        System.out.println("------------------------------------------------------");


        while (true) {
            try {
                System.out.println("Нажми 0 чтобы добавить число в деверво");
                System.out.println("Нажми 1 чтобы изменить число");
                System.out.println("Нажми 2 чтобы удалить число");
                System.out.println("Нажми 3 чтобы вывести дерево на экран");
                System.out.println("Нажми 4 чтобы выключить программу");
                String in = reader.readLine();
                if (Integer.parseInt(in) == 4) break;
                switch (Integer.parseInt(in)) {
                    case 0:
                        System.out.println("Введи число");
                        three.insert(Integer.parseInt(reader.readLine()));
                        break;
                    case 1:
                        System.out.println("Введи число, которое хочешь изменить, и которым хочешь заменить через Enter");
                        three.update(Integer.parseInt(reader.readLine()), Integer.parseInt(reader.readLine()));
                        break;
                    case 2:
                        System.out.println("Введи число, которое хочешь удалить");
                        three.delete(Integer.parseInt(reader.readLine()));
                        break;
                    case 3:
                        three.select();
                        break;
                }
                System.out.println("------------------------------------------------------");
            } catch (IOException e) {
                System.out.println("Вводить нужно числа!");
            }
        }

    }
}
