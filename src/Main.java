import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        // FileReaderLP purchaseFile = new FileReaderLP("supermarket_dataset_50K.csv");
        FileReaderDH purchaseFile = new FileReaderDH("supermarket_dataset_50K.csv");

        //  LinearProbingHashTable D = (LinearProbingHashTable) purchaseFile.readSupermarket();
        DoubleHashingHashTable D = (DoubleHashingHashTable) purchaseFile.readSupermarket();


        // this part is for searching ONE customer
        /*
        System.out.println("Search: ");
        Scanner scanner = new Scanner(System.in);
        String userId = scanner.nextLine();
        searchCustomer(D, userId);

        */

        // this part is for searching ALL customers by reading customer_1K.csv file
        FileReaderDH customerFile = new FileReaderDH("customer_1K.csv");
        customerFile.customerReadandParse(D); // read, parse and print all customers


        // collision count in hashmap
        D.printCollisionCount();
    }

    public static void searchCustomer(HashTable<Purchase> hashMap, String id) {
        Purchase purchase = (Purchase) hashMap.get(id);
        if (purchase == null) {
            System.out.println("Customer not found");
        } else {
            System.out.printf(" %d transaction found for %s %n", purchase.getListOfProdcuts().size(), purchase.getName());
            purchase.getListOfProdcuts().print();
        }
    }


}