import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

interface KeyContainer<K> {
    boolean contains(K key);
    K getKey();  // Добавляем метод для получения ключа
}

abstract class HashFunction<K> {
    protected final int tableSize;

    public HashFunction(int tableSize) {
        if (tableSize <= 0) {
            throw new IllegalArgumentException("Table size must be positive");
        }
        this.tableSize = tableSize;
    }

    public abstract int hash(K key);

    public int getTableSize() {
        return tableSize;
    }
}

class StringHashFunction extends HashFunction<String> {
    public StringHashFunction(int tableSize) {
        super(tableSize);
    }

    @Override
    public int hash(String key) {
        if (key == null) {
            throw new IllegalArgumentException("Key cannot be null");
        }
        int hash = 0;
        for (int i = 0; i < key.length(); i++) {
            hash = (31 * hash + key.charAt(i)) % tableSize;
        }
        return hash;
    }
}

class HashTable<K, T extends KeyContainer<K>> {
    private final List<List<T>> table;
    private final HashFunction<K> hashFunction;

    public HashTable(HashFunction<K> hashFunction) {
        this.hashFunction = hashFunction;
        this.table = new ArrayList<>(hashFunction.getTableSize());
        for (int i = 0; i < hashFunction.getTableSize(); i++) {
            table.add(new LinkedList<>());
        }
    }

    public void insert(T item) {
        if (item == null) {
            throw new IllegalArgumentException("Item cannot be null");
        }
        int index = hashFunction.hash(item.getKey());  // Используем getKey()
        table.get(index).add(item);
    }

    public List<T> search(K key) {
        int index = hashFunction.hash(key);
        List<T> bucket = table.get(index);
        List<T> result = new ArrayList<>();

        for (T item : bucket) {
            if (item.contains(key)) {
                result.add(item);
            }
        }
        return result;
    }

    public boolean remove(K key) {
        int index = hashFunction.hash(key);
        List<T> bucket = table.get(index);

        return bucket.removeIf(item -> item.contains(key));
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < table.size(); i++) {
            sb.append("Bucket ").append(i).append(": ").append(table.get(i)).append("\n");
        }
        return sb.toString();
    }
}

class Person implements KeyContainer<String> {
    private final String name;
    private final String phone;

    public Person(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }

    @Override
    public boolean contains(String key) {
        return name.equals(key);
    }

    @Override
    public String getKey() {
        return name;  // Реализуем метод получения ключа
    }

    @Override
    public String toString() {
        return name + " (" + phone + ")";
    }
}

public class Main {
    public static void main(String[] args) {
        HashFunction<String> hashFunction = new StringHashFunction(10);
        HashTable<String, Person> phoneBook = new HashTable<>(hashFunction);

        phoneBook.insert(new Person("Иванов", "123-456"));
        phoneBook.insert(new Person("Петров", "234-567"));
        phoneBook.insert(new Person("Сидоров", "345-678"));
        phoneBook.insert(new Person("Иванов", "456-789"));

        System.out.println("Хеш-таблица после добавления:");
        System.out.println(phoneBook);

        System.out.println("\nПоиск 'Иванов':");
        phoneBook.search("Иванов").forEach(System.out::println);

        System.out.println("\nУдаление 'Сидоров': " + phoneBook.remove("Сидоров"));
        System.out.println("\nХеш-таблица после удаления:");
        System.out.println(phoneBook);
    }
}