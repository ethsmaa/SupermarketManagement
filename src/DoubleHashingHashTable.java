public class DoubleHashingHashTable<T> implements HashTable<T> {

    protected final static int TABLE_SIZE = 1000000;

    protected HashEntry<T>[] table = new HashEntry[TABLE_SIZE];

    public DoubleHashingHashTable() {
        for (int i = 0; i < TABLE_SIZE; i++)
            table[i] = null;
    }

    public int hashFunction(String key) {
        int hash = 0;
        for (int i = 0; i < key.length(); i++)
            hash = (31 * hash + key.charAt(i)) % TABLE_SIZE;
        return hash;
    }

    @Override
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

    @Override
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

    public int doubleHashFunction(String key, int time) {
        int prime = 31;
        int hash1 = key.hashCode() % TABLE_SIZE;
        int hash2 = prime - (key.hashCode() % prime);

        int index = (hash1 + time * hash2) % TABLE_SIZE;
        if (index < 0) {
            index = (index + TABLE_SIZE) % TABLE_SIZE;
        }

        return index;
    }

    @Override
    public void print() {
        for (int i = 0; i < TABLE_SIZE; i++) {
            if (table[i] != null) {
                Purchase purchase = (Purchase) table[i].getValue();
                System.out.printf(" %d transaction found for %s %n", purchase.getListOfProdcuts().size(), purchase.getName());
                purchase.getListOfProdcuts().print();
            }
        }
    }
}

