public class FileReaderLP extends AbstractFileReader {
    public FileReaderLP(String fileName) {
        super(fileName);
    }

    @Override
    protected HashTable<Purchase> createHashTable() {
        return new LinearProbingHashTable<>();
    }
}