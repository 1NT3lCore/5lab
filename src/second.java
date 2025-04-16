import java.util.Arrays;
import java.util.Scanner;

public final class second {
    private final Object[] items;
    private int size;

    public second(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Capacity must be positive");
        }
        this.items = new Object[capacity];
        this.size = 0;
    }

    public void add(Object item) {
        if (size >= items.length) {
            throw new IllegalStateException("Bag is full");
        }

        int position = getRandomPosition(size + 1);
        if (position < size) {
            System.arraycopy(items, position, items, position + 1, size - position);
        }

        items[position] = item;
        size++;
    }

    public Object remove() {
        if (size == 0) {
            throw new IllegalStateException("Bag is empty");
        }

        int position = getRandomPosition(size);
        Object removedItem = items[position];

        System.arraycopy(items, position + 1, items, position, size - position - 1);
        items[size - 1] = null;
        size--;

        return removedItem;
    }

    public final Object get() {
        if (size == 0) {
            throw new IllegalStateException("Bag is empty");
        }
        return items[getRandomPosition(size)];
    }

    public final int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == items.length;
    }

    private int getRandomPosition(int bound) {
        return (int)Math.round(Math.random() * (bound - 1));
    }

    @Override
    public String toString() {
        return "Bag" + Arrays.toString(Arrays.copyOf(items, size));
    }

    public final Object[] toArray() {
        return Arrays.copyOf(items, size);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Тестирование класса Bag ===");
        System.out.print("Введите размер мешка: ");
        int capacity = scanner.nextInt();
        scanner.nextLine(); // Очистка буфера

        second bag = new second(capacity);

        while (true) {
            System.out.println("\nТекущее содержимое: " + bag);
            System.out.println("1. Добавить элемент");
            System.out.println("2. Удалить элемент");
            System.out.println("3. Просмотреть случайный элемент");
            System.out.println("4. Проверить размер");
            System.out.println("5. Выход");
            System.out.print("Выберите действие: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Очистка буфера

            switch (choice) {
                case 1:
                    if (bag.isFull()) {
                        System.out.println("Мешок полон!");
                        break;
                    }
                    System.out.print("Введите элемент для добавления: ");
                    String item = scanner.nextLine();
                    bag.add(item);
                    System.out.println("Элемент добавлен: " + item);
                    break;

                case 2:
                    if (bag.isEmpty()) {
                        System.out.println("Мешок пуст!");
                        break;
                    }
                    Object removed = bag.remove();
                    System.out.println("Удален элемент: " + removed);
                    break;

                case 3:
                    if (bag.isEmpty()) {
                        System.out.println("Мешок пуст!");
                        break;
                    }
                    System.out.println("Случайный элемент: " + bag.get());
                    break;

                case 4:
                    System.out.println("Текущий размер мешка: " + bag.size());
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
