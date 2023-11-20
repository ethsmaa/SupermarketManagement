public class LinearProbingHashTable<T> extends AbstractHashTable<T> {


    protected final static int TABLE_SIZE = 1000000;

    protected HashEntry<T>[] table = new HashEntry[TABLE_SIZE];

    public LinearProbingHashTable() {
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

        if (table[hash] != null) {
            int time = 1;
            while (table[hash] != null) {
                if (table[hash].getKey().equals(key)) {
                    return table[hash].getValue();
                }
                hash = (hash + 1) % TABLE_SIZE;
                time++;
            }
        }
        return null;
    }

    public void put(String key, T value) {
        int hash = hashFunction(key);

        while (table[hash] != null) {
            hash = (hash + 1) % TABLE_SIZE; // linear probing: increment by 1 until empty slot is found
        }

        table[hash] = new HashEntry<T>(key, value); // Bu satır eklendi
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
