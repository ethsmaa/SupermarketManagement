
public class Main {
    public static void main(String[] args){
        FileReader fileReader = new FileReader("supermarket_dataset_5.csv");
        PurchaseHashTable hashMap = (PurchaseHashTable) fileReader.readAndParseFile();

        hashMap.print();
    }
}