import java.util.InputMismatchException;
import java.util.Scanner;

public class Fourth<T1, T2> {
    private final second bag;

    public Fourth(int capacity) {
        this.bag = new second(capacity);
    }

    public void addPair(first.Pair<T1, T2> pair) {
        bag.add(pair);
    }

    public first.Pair<T1, T2> removePair() {
        return (first.Pair<T1, T2>) bag.remove();
    }

    public first.Pair<T1, T2> getPair() {
        return (first.Pair<T1, T2>) bag.get();
    }

    public int size() {
        return bag.size();
    }

    public boolean isEmpty() {
        return bag.isEmpty();
    }

    public boolean isFull() {
        return bag.isFull();
    }

    @Override
    public String toString() {
        return "Fourth" + bag.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.println("=== Тестирование Fourth ===");
            System.out.println("1. Создать мешок для пар <Integer, Integer>");
            System.out.println("2. Создать мешок для пар <String, Double>");
            System.out.print("Выберите тип мешка: ");

            int choice = getIntInput(scanner);

            System.out.print("Введите размер мешка: ");
            int capacity = getIntInput(scanner);

            if (choice == 1) {
                testIntegerIntegerBag(scanner, capacity);
            } else if (choice == 2) {
                testStringDoubleBag(scanner, capacity);
            } else {
                System.out.println("Неверный выбор!");
            }
        } finally {
            scanner.close();
        }
    }

    private static int getIntInput(Scanner scanner) {
        while (true) {
            try {
                int value = scanner.nextInt();
                scanner.nextLine(); // Очистка буфера
                return value;
            } catch (InputMismatchException e) {
                System.out.print("Ошибка! Введите целое число: ");
                scanner.nextLine(); // Очистка некорректного ввода
            }
        }
    }

    private static double getDoubleInput(Scanner scanner) {
        while (true) {
            try {
                double value = scanner.nextDouble();
                scanner.nextLine(); // Очистка буфера
                return value;
            } catch (InputMismatchException e) {
                System.out.print("Ошибка! Введите число: ");
                scanner.nextLine(); // Очистка некорректного ввода
            }
        }
    }

    private static void testIntegerIntegerBag(Scanner scanner, int capacity) {
        Fourth<Integer, Integer> bag = new Fourth<>(capacity);

        while (true) {
            try {
                System.out.println("\nТекущее содержимое: " + bag);
                System.out.println("1. Добавить пару чисел");
                System.out.println("2. Удалить пару");
                System.out.println("3. Просмотреть случайную пару");
                System.out.println("4. Проверить размер");
                System.out.println("5. Выход");
                System.out.print("Выберите действие: ");

                int action = getIntInput(scanner);

                switch (action) {
                    case 1:
                        if (bag.isFull()) {
                            System.out.println("Мешок полон!");
                            break;
                        }
                        try {
                            System.out.print("Введите первое число: ");
                            int first = getIntInput(scanner);

                            System.out.print("Введите второе число: ");
                            int second = getIntInput(scanner);

                            first.Pair<Integer, Integer> pair = new first.Pair<>(first, second);
                            bag.addPair(pair);
                            System.out.println("Пара добавлена: " + pair);
                        } catch (Exception e) {
                            System.out.println("Ошибка при создании пары: " + e.getMessage());
                        }
                        break;

                    case 2:
                        if (bag.isEmpty()) {
                            System.out.println("Мешок пуст!");
                            break;
                        }
                        first.Pair<Integer, Integer> removed = bag.removePair();
                        System.out.println("Удалена пара: " + removed);
                        break;

                    case 3:
                        if (bag.isEmpty()) {
                            System.out.println("Мешок пуст!");
                            break;
                        }
                        System.out.println("Случайная пара: " + bag.getPair());
                        break;

                    case 4:
                        System.out.println("Текущий размер: " + bag.size());
                        break;

                    case 5:
                        return;

                    default:
                        System.out.println("Неверный выбор! Введите число от 1 до 5");
                }
            } catch (Exception e) {
                System.out.println("Произошла ошибка: " + e.getMessage());
            }
        }
    }

    private static void testStringDoubleBag(Scanner scanner, int capacity) {
        Fourth<String, Double> bag = new Fourth<>(capacity);

        while (true) {
            try {
                System.out.println("\nТекущее содержимое: " + bag);
                System.out.println("1. Добавить пару <String, Double>");
                System.out.println("2. Удалить пару");
                System.out.println("3. Просмотреть случайную пару");
                System.out.println("4. Проверить размер");
                System.out.println("5. Выход");
                System.out.print("Выберите действие: ");

                int action = getIntInput(scanner);

                switch (action) {
                    case 1:
                        if (bag.isFull()) {
                            System.out.println("Мешок полон!");
                            break;
                        }
                        try {
                            System.out.print("Введите строку: ");
                            String str = scanner.nextLine();

                            System.out.print("Введите число: ");
                            double num = getDoubleInput(scanner);

                            first.Pair<String, Double> pair = new first.Pair<>(str, num);
                            bag.addPair(pair);
                            System.out.println("Пара добавлена: " + pair);
                        } catch (Exception e) {
                            System.out.println("Ошибка при создании пары: " + e.getMessage());
                        }
                        break;

                    case 2:
                        if (bag.isEmpty()) {
                            System.out.println("Мешок пуст!");
                            break;
                        }
                        first.Pair<String, Double> removed = bag.removePair();
                        System.out.println("Удалена пара: " + removed);
                        break;

                    case 3:
                        if (bag.isEmpty()) {
                            System.out.println("Мешок пуст!");
                            break;
                        }
                        System.out.println("Случайная пара: " + bag.getPair());
                        break;

                    case 4:
                        System.out.println("Текущий размер: " + bag.size());
                        break;

                    case 5:
                        return;

                    default:
                        System.out.println("Неверный выбор! Введите число от 1 до 5");
                }
            } catch (Exception e) {
                System.out.println("Произошла ошибка: " + e.getMessage());
            }
        }
    }
}