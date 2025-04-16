package PERSONA;

@FunctionalInterface
public interface KeyExtractor<K, T> {
    K extractKey(T item);
}