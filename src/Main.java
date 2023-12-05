import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){

        PerformanceMonitor monitor = new PerformanceMonitor();

        monitor.startIndexing();
        FileReaderLP purchaseFile = new FileReaderLP("supermarket_dataset_5.csv");
        LinearProbingHashTable LINEARhashMap = (LinearProbingHashTable) purchaseFile.readSupermarket();
        monitor.endIndexing();
        monitor.printPerformanceMetrics();
        System.out.println();
        

        monitor.startIndexing();
        FileReaderDH purchaseFile2 = new FileReaderDH("supermarket_dataset_5.csv");
        DoubleHashingHashTable DOUBLEhashMap = (DoubleHashingHashTable) purchaseFile2.readSupermarket();
        monitor.endIndexing();


        LINEARhashMap.print();
        System.out.println("---");
        monitor.printPerformanceMetrics();
        System.out.println();
        DOUBLEhashMap.print();


    }



}