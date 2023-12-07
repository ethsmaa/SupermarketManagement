public class LinearProbingHashTable<T> implements HashTable<T> {

    private static final double LOAD_FACTOR = 0.5; // load factor for resizing
    private static final int INITIAL_CAPACITY = 11;

    private int capacity; // current capacity
    private int size; // current element count
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
            hash += (int) ((Math.pow(33, key.length() - (i + 1)) * key.charAt(i))% capacity);
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


    @Override // print the value of the key
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

        for (HashEntry<T> entry : table) {  // check the table
            if (entry != null) {
                int hash = hashFunctionPAF(entry.getKey(), newCapacity); // calculate new size according to new capacity
                while (newTable[hash] != null) {
                    hash = (hash + 1) % newCapacity; // if the hash is full find another place
                }
                newTable[hash] = entry; // add the entry to the new table
            }
        }
        table = newTable;
        capacity = newCapacity;
    }

    private int findNextPrime(int n) { // increase untill find the next prime number
        while (!isPrime(n)) {
            n++;
        }
        return n;
    }

    private boolean isPrime(int n) { // check whether the number is prime or not
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

    private int hashFunctionPAF(String key, int newCapacity) { // polynomial accumulation function
        int hash = 0;
        for (int i = 0; i < key.length(); i++) {
            hash += (Math.pow(33, key.length() - (i + 1)) * key.charAt(i))% newCapacity;
        }
        return hash % capacity;
    }

    private int hashFunctionSSF(String key, int tableSize) { // simple summation function
        int hash = 0;
        for (int i = 0; i < key.length(); i++) {
            hash += key.charAt(i);
        }
        return hash % tableSize;
    }

    public int countCollisions() { // count the collisions
        int collisionCount = 0;

        for (int i = 0; i < capacity; i++) {
            if (table[i] != null) {
                int hash = hashFunctionPAF(table[i].getKey());

                while (hash != i && table[hash] != null) { // if the hash is not empty and hash is not equal to i
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