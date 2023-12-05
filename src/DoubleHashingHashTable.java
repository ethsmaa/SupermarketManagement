import java.awt.*;
import java.io.*;
public class DoubleHashingHashTable<T> implements HashTable<T> {
    protected final static int TABLE_SIZE = 1000000;
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED_BACKGROUND = "\u001B[45m";
    public int doubleHashCollisions = 0;


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
                doubleHashCollisions++;
            }
            table[hash] = new HashEntry<T>(key, value);
        }
    }

    public int doubleHashFunction(String key, int time) {
        int hash = 0;
        for (int i = 0; i < key.length(); i++)
            hash = (29 * hash + key.charAt(i)) % TABLE_SIZE;
        return time * (TABLE_SIZE - (hash % TABLE_SIZE));
    }

    @Override
    public void print() {
        for (int i = 0; i < TABLE_SIZE; i++) {
            if (table[i] != null) {
                Purchase purchase = (Purchase) table[i].getValue();
                System.out.printf(" %d transaction found for %s %n", purchase.getListOfProdcuts().size(), purchase.getName());
                purchase.getListOfProdcuts().print();
                System.out.println(ANSI_RED_BACKGROUND+ "It belongs to Double Hashing" + ANSI_RESET);
                System.out.println("Number of Collisions: " + doubleHashCollisions);
                System.out.println();

            }
        }
    }

    @Override
    public boolean contains(String key) {
        return get(key) != null;
    }
}

