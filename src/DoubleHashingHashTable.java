public class DoubleHashingHashTable<T> implements HashTable<T> {

    private static final double LOAD_FACTOR = 0.5;
    private static final int INITIAL_CAPACITY = 5; // Set your desired initial capacity

    private int capacity; // current capacity
    private int size; // current element count
    private HashEntry<T>[] table;

    public DoubleHashingHashTable() {
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

                hash = doubleHashFunction(key, time) % capacity;
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

        if (table[hash] == null) {
            table[hash] = new HashEntry<>(key, value);
            size++;
        } else {
            int time = 1;
            while (table[hash] != null) {
                hash =  doubleHashFunction(key, time) % capacity;
                time++;
            }
            table[hash] = new HashEntry<>(key, value);
            size++;
        }
    }

    public int doubleHashFunction(String key, int time) {
        int hash = hashFunction(key);
        int prime = findPrevPrime(2 * capacity); // Use findPrevPrime instead

        return time * (prime - (hash % prime));
    }

    public int doubleHashFunction(String key, int time, int newCapacity) {
        int hash = hashFunction(key);
        int prime = findPrevPrime(2 * newCapacity); // Use findPrevPrime instead

        return time * (prime - (hash % prime));
    }

    private void resize() {
        int newCapacity = findNextPrime(2 * capacity);
        HashEntry<T>[] newTable = new HashEntry[newCapacity];

        for (HashEntry<T> entry : table) {
            if (entry != null) {
                int hash = hashFunction(entry.getKey());
                int time = 1;
                while (newTable[hash] != null) {
                    hash =  doubleHashFunction(entry.getKey(), time, newCapacity) % newCapacity;
                    time++;
                }
                newTable[hash] = entry;
            }
        }

        table = newTable;
        capacity = newCapacity;
    }

    private int findNextPrime(int n) {
        while (!isPrime(n)) {
            n++;
        }
        return n;
    }

    private int findPrevPrime(int n) {
        while (!isPrime(n)) {
            n--;
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

    public int countCollisions() {
        int collisionCount = 0;

        for (int i = 0; i < capacity; i++) {
            if (table[i] != null) {
                int hash = hashFunction(table[i].getKey());

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
