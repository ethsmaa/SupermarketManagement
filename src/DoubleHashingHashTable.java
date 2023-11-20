public class DoubleHashingHashTable {
    private int[] table;
    private int size;

    public DoubleHashingHashTable(int capacity) {
        table = new int[capacity];
        size = 0;
    }

    public void put(int key, int value) {
        int index = hashFunction(key);
        int stepSize = doubleHashFunction(key);
        while (table[index] != 0) {
            index = (index + stepSize) % table.length; // Double Hashing
        }
        table[index] = value;
        size++;
    }

    public int get(int key) {
        int index = hashFunction(key);
        int stepSize = doubleHashFunction(key);
        while (table[index] != key) {
            index = (index + stepSize) % table.length; // Double Hashing
        }
        return table[index];
    }

    private int hashFunction(int key) {
        return key % table.length;
    }

    private int doubleHashFunction(int key) {
        return 7 - (key % 7); // Example secondary hash function
    }
}
