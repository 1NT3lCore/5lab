import java.util.Scanner;

public class first {
    // Обобщенный класс Pair
    public static class Pair<T, U> {
        public T first;
        public U second;

        // Конструкторы
        public Pair() {
            this.first = null;
            this.second = null;
        }

        public Pair(T first, U second) {
            this.first = first;
            this.second = second;
        }

        // Статический метод для создания пары
        public static <T, U> Pair<T, U> makePair(T first, U second) {
            return new Pair<>(first, second);
        }

        @Override
        public String toString() {
            return "(" + first + ", " + second + ")";
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Пример 1: Пара "Имя - Возраст"
        System.out.println("Пример 1: Создание пары 'Имя - Возраст'");
        System.out.print("Введите имя: ");
        String name = scanner.nextLine();

        System.out.print("Введите возраст: ");
        int age = scanner.nextInt();
        scanner.nextLine(); // очистка буфера

        Pair<String, Integer> nameAgePair = new Pair<>(name, age);
        System.out.println("Ваша пара: " + nameAgePair);
    }
}