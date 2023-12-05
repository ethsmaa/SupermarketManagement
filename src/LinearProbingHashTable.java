public class LinearProbingHashTable<T> implements HashTable<T> {

    private static final double LOAD_FACTOR = 0.5;
    private static final int INITIAL_CAPACITY = 127; // asal olmalı

    private int capacity; // mevcut kapasite
    private int size; // mevcut eleman sayısı
    private HashEntry<T>[] table;

    public LinearProbingHashTable() {
        this.capacity = INITIAL_CAPACITY;
        this.size = 0;
        this.table = new HashEntry[capacity];
    }

    public int hashFunction(String key) {
        int hash = 0;
        for (int i = 0; i < key.length(); i++)
            hash = (31 * hash + key.charAt(i)) % capacity;
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
                hash = (hash + 1) % capacity;
                time++;
            }
        }
        return null;
    }

    @Override
    public void put(String key, T value) {
        if ((double) size / capacity > LOAD_FACTOR) {
            resize();
        }

        int hash = hashFunction(key);

        while (table[hash] != null) {
            hash = (hash + 1) % capacity;
        }

        if (table[hash] == null) {
            table[hash] = new HashEntry<>(key, value);
            size++;
        }
    }

    @Override
    public void print() {

        for (int i = 0; i < capacity; i++) {
            if (table[i] != null) {
                Purchase purchase = (Purchase) table[i].getValue();
                System.out.printf(" %d transaction found for %s %n", purchase.getListOfProdcuts().size(), purchase.getName());
                purchase.getListOfProdcuts().print();
            }
        }
    }


    @Override
    public boolean contains(String key) {
        return get(key) != null;
    }

    private void resize() {
        int newCapacity = findNextPrime(2 * capacity);
        HashEntry<T>[] newTable = new HashEntry[newCapacity];

        for (HashEntry<T> entry : table) { // table'daki entryler için
            if (entry != null) {
                int hash = hashFunction(entry.getKey(), newCapacity); // yeni size'a göre hash değeri hesapla
                while (newTable[hash] != null) {
                    hash = (hash + 1) % newCapacity; // mevcut yer doluysa bir sonraki yeri dene
                }
                newTable[hash] = entry; // yeni table'a ekle
            }
        }

        table = newTable;
        capacity = newCapacity;
    }

    private int findNextPrime(int n) { // asal sayı gelene kadar arttır
        while (!isPrime(n)) {
            n++;
        }
        return n;
    }

    private boolean isPrime(int n) {
        if (n <= 1) {
            return false;
        }
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    private int hashFunction(String key, int tableSize) {
        int hash = 0;
        for (int i = 0; i < key.length(); i++) {
            hash = (31 * hash + key.charAt(i)) % tableSize;
        }
        return hash;
    }
    
}
