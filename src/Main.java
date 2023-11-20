import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        FileReaderLP purchaseFile = new FileReaderLP("supermarket_dataset_5.csv");
        LinearProbingHashTable LINEARhashMap = (LinearProbingHashTable) purchaseFile.readSupermarket();


        FileReaderDH purchaseFile2 = new FileReaderDH("supermarket_dataset_5.csv");
        DoubleHashingHashTable DOUBLEhashMap = (DoubleHashingHashTable) purchaseFile2.readSupermarket();



        LINEARhashMap.print();
        System.out.println("---");
        DOUBLEhashMap.print();



      // FileReader customerFile = new FileReader("customer_1K.txt");
       // customerFile.customerReadandParse(LINEARhashMap);



    }



}