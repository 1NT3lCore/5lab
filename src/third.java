import java.util.Scanner;

public class third {
    private final second bag;

    public third(int capacity) {
        this.bag = new second(capacity);
    }

    public void addPair(first.Pair<?, ?> pair) {
        bag.add(pair);
    }

    public first.Pair<?, ?> removePair() {
        return (first.Pair<?, ?>) bag.remove();
    }

    public final first.Pair<?, ?> getPair() {
        return (first.Pair<?, ?>) bag.get();
    }

    public final int size() {
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
        return "Third: " + bag.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Тестирование класса Third ===");
        System.out.print("Введите размер мешка для пар: ");
        int capacity = scanner.nextInt();
        scanner.nextLine();

        third third = new third(capacity);

        while (true) {
            System.out.println("\nТекущее содержимое: " + third);
            System.out.println("1. Добавить пару");
            System.out.println("2. Удалить пару");
            System.out.println("3. Просмотреть случайную пару");
            System.out.println("4. Проверить размер");
            System.out.println("5. Выход");
            System.out.print("Выберите действие: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    if (third.isFull()) {
                        System.out.println("Мешок полон!");
                        break;
                    }
                    System.out.println("Выберите тип пары:");
                    System.out.println("1. Число - Число");
                    System.out.println("2. Число - Строка");
                    System.out.println("3. Строка - Строка");
                    System.out.print("Ваш выбор: ");
                    int pairType = scanner.nextInt();
                    scanner.nextLine();

                    first.Pair<?, ?> pair;
                    switch (pairType) {
                        case 1:
                            System.out.print("Введите первое число: ");
                            int firstInt = scanner.nextInt();
                            System.out.print("Введите второе число: ");
                            int secondInt = scanner.nextInt();
                            scanner.nextLine();
                            pair = new first.Pair<>(firstInt, secondInt);
                            break;
                        case 2:
                            System.out.print("Введите число: ");
                            int num = scanner.nextInt();
                            scanner.nextLine();
                            System.out.print("Введите строку: ");
                            String str = scanner.nextLine();
                            pair = new first.Pair<>(num, str);
                            break;
                        case 3:
                            System.out.print("Введите первую строку: ");
                            String firstStr = scanner.nextLine();
                            System.out.print("Введите вторую строку: ");
                            String secondStr = scanner.nextLine();
                            pair = new first.Pair<>(firstStr, secondStr);
                            break;
                        default:
                            System.out.println("Неверный выбор типа пары!");
                            continue;
                    }
                    third.addPair(pair);
                    System.out.println("Пара добавлена: " + pair);
                    break;

                case 2:
                    if (third.isEmpty()) {
                        System.out.println("Мешок пуст!");
                        break;
                    }
                    first.Pair<?, ?> removed = third.removePair();
                    System.out.println("Удалена пара: " + removed);
                    break;

                case 3:
                    if (third.isEmpty()) {
                        System.out.println("Мешок пуст!");
                        break;
                    }
                    System.out.println("Случайная пара: " + third.getPair());
                    break;

                case 4:
                    System.out.println("Текущий размер: " + third.size());
                    break;

                case 5:
                    System.out.println("Завершение работы...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Неверный выбор!");
            }
        }
    }
}