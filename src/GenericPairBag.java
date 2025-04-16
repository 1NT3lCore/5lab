import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class GenericPairBag<T1, T2> {
    private final List<Pair<T1, T2>> pairs;
    private final int maxCapacity;
    private final Random random;

    public GenericPairBag(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Capacity must be positive");
        }
        this.pairs = new ArrayList<>();
        this.maxCapacity = capacity;
        this.random = new Random();
    }

    public void addPair(Pair<T1, T2> pair) {
        if (isFull()) {
            throw new IllegalStateException("Bag is full");
        }
        // Добавляем пару в случайную позицию
        int position = random.nextInt(pairs.size() + 1);
        pairs.add(position, pair);
    }

    public Pair<T1, T2> removeRandomPair() {
        if (isEmpty()) {
            throw new IllegalStateException("Bag is empty");
        }
        int position = random.nextInt(pairs.size());
        return pairs.remove(position);
    }

    public Pair<T1, T2> getRandomPair() {
        if (isEmpty()) {
            throw new IllegalStateException("Bag is empty");
        }
        return pairs.get(random.nextInt(pairs.size()));
    }

    public int size() {
        return pairs.size();
    }

    public boolean isEmpty() {
        return pairs.isEmpty();
    }

    public boolean isFull() {
        return pairs.size() >= maxCapacity;
    }

    @Override
    public String toString() {
        return "GenericPairBag" + pairs.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.println("=== Тестирование GenericPairBag ===");
            System.out.println("1. Мешок пар <Integer, String>");
            System.out.println("2. Мешок пар <Double, Boolean>");
            System.out.print("Выберите тип: ");

            int choice = getIntInput(scanner, 1, 2);

            System.out.print("Введите размер мешка: ");
            int capacity = getPositiveInt(scanner);

            switch (choice) {
                case 1 -> testIntegerStringBag(scanner, capacity);
                case 2 -> testDoubleBooleanBag(scanner, capacity);
            }
        } finally {
            scanner.close();
        }
    }

    private static void testIntegerStringBag(Scanner scanner, int capacity) {
        GenericPairBag<Integer, String> bag = new GenericPairBag<>(capacity);

        while (true) {
            System.out.println("\nТекущее содержимое: " + bag);
            System.out.println("1. Добавить пару <Integer, String>");
            System.out.println("2. Удалить пару");
            System.out.println("3. Просмотреть случайную пару");
            System.out.println("4. Проверить размер");
            System.out.println("5. Выход");
            System.out.print("Выберите действие: ");

            int action = getIntInput(scanner, 1, 5);

            switch (action) {
                case 1 -> {
                    if (bag.isFull()) {
                        System.out.println("Мешок полон!");
                        continue;
                    }
                    System.out.print("Введите целое число: ");
                    int number = getIntInput(scanner);
                    scanner.nextLine(); // Очистка буфера

                    System.out.print("Введите строку: ");
                    String text = scanner.nextLine();

                    bag.addPair(new Pair<>(number, text));
                    System.out.println("Пара добавлена!");
                }
                case 2 -> {
                    if (bag.isEmpty()) {
                        System.out.println("Мешок пуст!");
                        continue;
                    }
                    Pair<Integer, String> removed = bag.removeRandomPair();
                    System.out.println("Удалена пара: " + removed);
                }
                case 3 -> {
                    if (bag.isEmpty()) {
                        System.out.println("Мешок пуст!");
                        continue;
                    }
                    System.out.println("Случайная пара: " + bag.getRandomPair());
                }
                case 4 -> {
                    System.out.println("Текущий размер: " + bag.size());
                }
                case 5 -> {
                    return;
                }
            }
        }
    }

    private static void testDoubleBooleanBag(Scanner scanner, int capacity) {
        GenericPairBag<Double, Boolean> bag = new GenericPairBag<>(capacity);

        while (true) {
            System.out.println("\nТекущее содержимое: " + bag);
            System.out.println("1. Добавить пару <Double, Boolean>");
            System.out.println("2. Удалить пару");
            System.out.println("3. Просмотреть случайную пару");
            System.out.println("4. Проверить размер");
            System.out.println("5. Выход");
            System.out.print("Выберите действие: ");

            int action = getIntInput(scanner, 1, 5);

            switch (action) {
                case 1 -> {
                    if (bag.isFull()) {
                        System.out.println("Мешок полон!");
                        continue;
                    }
                    System.out.print("Введите число: ");
                    double number = getDoubleInput(scanner);
                    scanner.nextLine(); // Очистка буфера

                    System.out.print("Введите true/false: ");
                    boolean flag = getBooleanInput(scanner);
                    scanner.nextLine(); // Очистка буфера

                    bag.addPair(new Pair<>(number, flag));
                    System.out.println("Пара добавлена!");
                }
                case 2 -> {
                    if (bag.isEmpty()) {
                        System.out.println("Мешок пуст!");
                        continue;
                    }
                    Pair<Double, Boolean> removed = bag.removeRandomPair();
                    System.out.println("Удалена пара: " + removed);
                }
                case 3 -> {
                    if (bag.isEmpty()) {
                        System.out.println("Мешок пуст!");
                        continue;
                    }
                    System.out.println("Случайная пара: " + bag.getRandomPair());
                }
                case 4 -> {
                    System.out.println("Текущий размер: " + bag.size());
                }
                case 5 -> {
                    return;
                }
            }
        }
    }

    // Вспомогательные методы для ввода
    private static int getIntInput(Scanner scanner) {
        while (true) {
            try {
                return scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.print("Ошибка! Введите целое число: ");
                scanner.nextLine();
            }
        }
    }

    private static int getIntInput(Scanner scanner, int min, int max) {
        while (true) {
            int value = getIntInput(scanner);
            if (value >= min && value <= max) {
                return value;
            }
            System.out.printf("Ошибка! Введите число от %d до %d: ", min, max);
        }
    }

    private static int getPositiveInt(Scanner scanner) {
        while (true) {
            int value = getIntInput(scanner);
            if (value > 0) {
                return value;
            }
            System.out.print("Ошибка! Введите положительное число: ");
        }
    }

    private static double getDoubleInput(Scanner scanner) {
        while (true) {
            try {
                return scanner.nextDouble();
            } catch (InputMismatchException e) {
                System.out.print("Ошибка! Введите число: ");
                scanner.nextLine();
            }
        }
    }

    private static boolean getBooleanInput(Scanner scanner) {
        while (true) {
            String input = scanner.next().toLowerCase();
            if (input.equals("true")) return true;
            if (input.equals("false")) return false;
            System.out.print("Ошибка! Введите true/false: ");
        }
    }
}

// Класс Pair из первого задания (адаптированный)
class Pair<T1, T2> {
    private final T1 first;
    private final T2 second;

    public Pair(T1 first, T2 second) {
        this.first = first;
        this.second = second;
    }

    public T1 getFirst() {
        return first;
    }

    public T2 getSecond() {
        return second;
    }

    @Override
    public String toString() {
        return "(" + first + ", " + second + ")";
    }
}