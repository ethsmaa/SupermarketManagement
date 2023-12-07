import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){
       // FileReaderLP purchaseFile = new FileReaderLP("supermarket_dataset_50K.csv");
        FileReaderLP purchaseFile = new FileReaderLP("supermarket_dataset_50K.csv");

       //  LinearProbingHashTable D = (LinearProbingHashTable) purchaseFile.readSupermarket();
        LinearProbingHashTable D = (LinearProbingHashTable) purchaseFile.readSupermarket();


        System.out.println("Enter a user id: ");
        Scanner scanner = new Scanner(System.in);
        String userId = scanner.nextLine();
        Purchase purchase = (Purchase) D.get(userId);
        if (purchase == null) {
            System.out.println("Customer not found");
        } else {
            System.out.printf(" %d transaction found for %s %n", purchase.getListOfProdcuts().size(), purchase.getName());
            purchase.getListOfProdcuts().print();
        }






        /*
        // customer read and parse
        FileReaderLP customerFile = new FileReaderLP("customer_1K.csv");
        customerFile.customerReadandParse(D);

        D.printCollisionCount();


         */


    }



}