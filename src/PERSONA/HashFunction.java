package PERSONA;

public abstract class HashFunction<K> {
    protected final int tableSize;

    public HashFunction(int tableSize) {
        if (tableSize <= 0) {
            throw new IllegalArgumentException("Размер таблицы должен быть положительным");
        }
        this.tableSize = tableSize;
    }

    public abstract int hash(K key);

    public int getTableSize() {
        return tableSize;
    }
}