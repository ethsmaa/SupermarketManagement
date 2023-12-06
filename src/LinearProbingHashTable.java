public class LinearProbingHashTable<T> implements HashTable<T> {

    private static final double LOAD_FACTOR = 0.5;
    private static final int INITIAL_CAPACITY = 10;

    private int capacity; // mevcut kapasite
    private int size; // mevcut eleman sayısı
    private HashEntry<T>[] table;

    public LinearProbingHashTable() {
        this.capacity = INITIAL_CAPACITY;
        this.size = 0;
        this.table = new HashEntry[capacity];
    }

    // simple summation function
    public int hashFunctionSSF(String key) {
        int hash = 0;
        for (int i = 0; i < key.length(); i++)
            hash += key.charAt(i);
        return hash % capacity;
    }

    // polynomial accumulation function
    public int hashFunctionPAF(String key) {
        int hash = 0;
        for (int i = 0; i < key.length(); i++) {
            hash += (Math.pow(33, key.length() - (i + 1)) * key.charAt(i))% capacity;
        }
        return hash % capacity;
    }

    @Override
    public T get(String key) {
        int hash = hashFunctionPAF(key);

        if (table[hash] != null) {
            while (table[hash] != null) {
                if (table[hash].getKey().equals(key)) {
                    return table[hash].getValue();
                }
                hash = (hash + 1) % capacity; // linear probing
            }
        }
        return null;
    }

    @Override
    public void put(String key, T value) {
        if ((double) size / capacity > LOAD_FACTOR) {
            rehash();
        }

        int hash = hashFunctionPAF(key);

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

    private void rehash() {
        int newCapacity = findNextPrime(2 * capacity);
        HashEntry<T>[] newTable = new HashEntry[newCapacity];

        for (HashEntry<T> entry : table) {  // mevcut table'ı gez
            if (entry != null) {
                int hash = hashFunctionPAF(entry.getKey(), newCapacity); // yeni size'a göre hash değeri hesapla
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

    private int hashFunctionPAF(String key, int newCapacity) {
        int hash = 0;
        for (int i = 0; i < key.length(); i++) {
            hash += (Math.pow(33, key.length() - (i + 1)) * key.charAt(i))% newCapacity;
        }
        return hash % capacity;
    }

    private int hashFunctionSSF(String key, int tableSize) {
        int hash = 0;
        for (int i = 0; i < key.length(); i++) {
            hash += key.charAt(i);
        }
        return hash % tableSize;
    }

    public int countCollisions() {
        int collisionCount = 0;

        for (int i = 0; i < capacity; i++) {
            if (table[i] != null) {
                int hash = hashFunctionPAF(table[i].getKey());

                while (hash != i && table[hash] != null) {
                    collisionCount++;
                    hash = (hash + 1) % capacity;
                }
            }
        }

        return collisionCount;
    }

    public void printCollisionCount() {
        int collisionCount = countCollisions();
        System.out.println("Collision Count: " + collisionCount);
    }


}


