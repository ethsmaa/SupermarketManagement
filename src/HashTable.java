public abstract class HashTable<T> {

    protected final static int TABLE_SIZE = 1000;

    protected HashEntry<T>[] table = new HashEntry[TABLE_SIZE];

    public HashTable() {
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

    public T get(String key) {
        int hash = hashFunction(key);

        if (table[hash] != null) {
            int time = 1;
            while (table[hash] != null) {
                if (table[hash].getKey().equals(key)) {
                    return table[hash].getValue();
                }
                hash = (hash + doubleHashFunction(key, time)) % TABLE_SIZE;
                time++;
            }
        }
        return null;
    }

    public void put(String key, T value) {
        int hash = hashFunction(key);

        if (table[hash] == null) {
            table[hash] = new HashEntry<T>(key, value);
        } else {
            int time = 1;
            while (table[hash] != null) {
                hash = (hash + doubleHashFunction(key, time)) % TABLE_SIZE;
                time++;
            }
            table[hash] = new HashEntry<T>(key, value);
        }
    }

    // print
    public abstract void print();

    public int doubleHashFunction(String key, int time) {
        return (997 - (key.hashCode() % 997)) * time; // Adjust as needed
    }
}
