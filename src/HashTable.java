public interface HashTable<T> {
    T get(String key);
    void put(String key, T value);
    void print();
}