public class DoubleHashingHashTable<T> implements HashTable<T> {

    private static final double LOAD_FACTOR = 0.5;  // load factor for resizing
    private static final int INITIAL_CAPACITY = 11;  // initial capacity of the hash table
    private int capacity; // current capacity
    private int size; // current element count
    private HashEntry<T>[] table;

    public DoubleHashingHashTable() {
        this.capacity = INITIAL_CAPACITY;
        this.size = 0;
        this.table = new HashEntry[capacity];
    }

    public int hashFunctionPAF(String key) {  // the pollinomial accumulatıon functıon
        int hash = 0;
        for (int i = 0; i < key.length(); i++) {
            hash += (int) ((Math.pow(33, key.length() - (i + 1)) * key.charAt(i))% capacity);
        }
        return hash % capacity;
    }

    public int hashFunctionSSF(String key) {  // the simple summation function
        int hash = 0;
        for (int i = 0; i < key.length(); i++)
            hash += key.charAt(i);
        return hash % capacity;
    }

    public int hashFunctionPAF(String key, int newCapacity) { // while resizing this method will be used
        int hash = 0;
        for (int i = 0; i < key.length(); i++) {
            hash += (int) ((Math.pow(33, key.length() - (i + 1)) * key.charAt(i))% newCapacity);
        }
        return hash % capacity;
    }

    public int hashFunctionSSF(String key, int newCapacity) { // while resizing this method will be used
        int hash = 0;
        for (int i = 0; i < key.length(); i++)
            hash += key.charAt(i);
        return hash % newCapacity;
    }

    @Override
    public T get(String key) { // get the value of the key
        int hash = hashFunctionPAF(key); // it changes according to which one we prefer to use

        if (table[hash] != null) {
            int time = 1;
            while (table[hash] != null) {
                if (table[hash].getKey().equals(key)) {
                    return table[hash].getValue();
                }
                hash = doubleHashFunction(key, time);
                time++;         //to search the next index, it increases
            }
        }
        return null;
    }

    @Override
    public void put(String key, T value) {  // put the value to the hash table
        if ((double) size / capacity > LOAD_FACTOR) {
            resize();
        }
        int hash = hashFunctionPAF(key);
        if (table[hash] != null) {
            int time = 1;
            while (table[hash] != null) {
                if(hash<0)
                    break;
                hash = doubleHashFunction(key, time);
                time++;
            }
        }
        table[hash] = new HashEntry<>(key, value);
        size++;   //
    }

    public int doubleHashFunction(String key, int time) { // it is the original double hash function
        int hash = hashFunctionPAF(key);
        int prime = findPrevPrime(capacity);

        return Math.abs((hash + time * (prime - (hash % prime))) % capacity);
    }

    public int doubleHashFunction(String key, int time, int newCapacity) { // while resizing this method will be used
        int hash = hashFunctionPAF(key);
        int prime = findPrevPrime(newCapacity);

        return Math.abs((hash + time * (prime - (hash % prime))) % capacity);
    }

    private void resize() { // it resizes the hash table according to the load factor
        int newCapacity = findNextPrime(2 * capacity);
        HashEntry<T>[] newTable = new HashEntry[newCapacity];

        for (HashEntry<T> entry : table) {
            if (entry != null) {
                int hash = hashFunctionPAF(entry.getKey(), newCapacity);
                int time = 1;
                while (newTable[hash] != null) {
                    hash =  doubleHashFunction(entry.getKey(), time, newCapacity);
                    time++;
                }
                newTable[hash] = entry;
            }
        }

        table = newTable;
        capacity = newCapacity;
    }

    private int findNextPrime(int n) { // it finds the next prime number
        while (!isPrime(n)) {
            n++;
        }
        return n;
    }

    private int findPrevPrime(int n) {   // it finds the previous prime number
        while (!isPrime(n)) {
            n--;
        }
        return n;
    }

    private boolean isPrime(int n) {    //it checks whether the number is prime or not
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
    public void print() {   // it prints the hash table
        for (int i = 0; i < capacity; i++) {
            if (table[i] != null) {
                Purchase purchase = (Purchase) table[i].getValue();
                System.out.printf(" %d transaction found for %s %n", purchase.getListOfProdcuts().size(), purchase.getName());
                purchase.getListOfProdcuts().print();
            }
        }
    }

    @Override   // it checks whether the key is in the hash table or not
    public boolean contains(String key) {
        return get(key) != null;
    }

    public int countCollisions() {  // it counts the collisions
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