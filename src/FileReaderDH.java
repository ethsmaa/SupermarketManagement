public class FileReaderDH extends AbstractFileReader {
    public FileReaderDH(String fileName) {
        super(fileName);
    }

    @Override
    protected HashTable<Purchase> createHashTable() {
        return new DoubleHashingHashTable<>();
    }
}