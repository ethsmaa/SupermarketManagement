public abstract class AbstractHashTable<T> {

    protected final static int TABLE_SIZE = 1000000;

    protected HashEntry<T>[] table = new HashEntry[TABLE_SIZE];

    public AbstractHashTable() {
        for (int i = 0; i < TABLE_SIZE; i++)
            table[i] = null;
    }

    // hash function for string
    public int hashFunction(String key) {
        int hash = 0;
        for (int i = 0; i < key.length(); i++)
            hash = (31 * hash + key.charAt(i)) % TABLE_SIZE;
        return hash;
    }

    public abstract T get(String key);

    public abstract void put(String key, T value);

    // print
    public abstract void print();
}
