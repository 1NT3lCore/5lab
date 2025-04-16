package PERSONA;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class HashTable<K, T> {
    private final List<List<T>> table;
    private final HashFunction<K> hashFunction;
    private final KeyExtractor<K, T> keyExtractor;

    public HashTable(HashFunction<K> hashFunction, KeyExtractor<K, T> keyExtractor) {
        this.hashFunction = hashFunction;
        this.keyExtractor = keyExtractor;
        this.table = new ArrayList<>(hashFunction.getTableSize());
        for (int i = 0; i < hashFunction.getTableSize(); i++) {
            table.add(new LinkedList<>());
        }
    }

    public void insert(T item) {
        K key = keyExtractor.extractKey(item);
        int index = hashFunction.hash(key);
        table.get(index).add(item);
    }

    public List<T> search(K key) {
        int index = hashFunction.hash(key);
        List<T> result = new ArrayList<>();

        for (T item : table.get(index)) {
            if (keyExtractor.extractKey(item).equals(key)) {
                result.add(item);
            }
        }
        return result;
    }

    public boolean remove(K key) {
        int index = hashFunction.hash(key);
        return table.get(index).removeIf(item -> keyExtractor.extractKey(item).equals(key));
    }

    public void printDistribution() {
        System.out.println("\nРаспределение элементов по корзинам:");
        for (int i = 0; i < table.size(); i++) {
            System.out.printf("Корзина %d: %d элементов%n", i, table.get(i).size());
        }
    }
}