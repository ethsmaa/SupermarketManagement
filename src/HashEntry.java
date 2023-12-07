public class HashEntry<T> {
    private String key;
    private T value;

    HashEntry(String key, T value) {
        this.key = key;
        this.value =  value;
    }
    public String getKey() {
        return key;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }
}
