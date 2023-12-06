import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        FileReaderLP purchaseFile = new FileReaderLP("supermarket_dataset_5.csv");


        LinearProbingHashTable D = (LinearProbingHashTable) purchaseFile.readSupermarket();

        D.print();
        D.printCollisionCount();
       // LINEARhashMap.print();



        // customer read and parse
       // FileReaderLP customerFile = new FileReaderLP("customer_1K.csv");
        // customerFile.customerReadandParse(D);



        // BU KISIM 1Kli customerdan search yapılırken cagırılacak

    }



}