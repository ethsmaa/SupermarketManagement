public abstract class HashTable<T> {

    protected final static int TABLE_SIZE = 128;

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

        if (table[hash] == null)
            return null;
        else
            return table[hash].getValue();

    }

    public void put(String key, T value) {

        int hash = hashFunction(key); // Calculate hash value

        if (table[hash] != null) {
            System.out.println("There is a collision!");
            return;
        }

        // collision handling burada gerçekelecek

        table[hash] = new HashEntry(key, value);

    }

    //print
    public abstract void print();
}

