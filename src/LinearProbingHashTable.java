public class LinearProbingHashTable {
    private int[] table;
    private int size;

    public LinearProbingHashTable(int capacity) {
        table = new int[capacity];
        size = 0;
    }

    public void put(int key, int value) {
        int index = hashFunction(key);
        while (table[index] != 0) {
            index = (index + 1) % table.length; // Linear Probing
        }
        table[index] = value;
        size++;
    }

    public int get(int key) {
        int index = hashFunction(key);
        while (table[index] != key) {
            index = (index + 1) % table.length; // Linear Probing
        }
        return table[index];
    }

    private int hashFunction(int key) {
        return key % table.length;
    }
}
