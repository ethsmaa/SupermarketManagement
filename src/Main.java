import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        FileReader purchaseFile = new FileReader("supermarket_dataset_50K.csv");
        PurchaseHashTable hashMap = (PurchaseHashTable) purchaseFile.readSupermarket();

        hashMap.print();

       // FileReader customerFile = new FileReader("customer_1K.txt");
       // customerFile.customerReadandParse(hashMap);



    }



}