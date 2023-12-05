import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;
public abstract class AbstractFileReader {
    protected String fileName;




    public AbstractFileReader(String fileName) {
        this.fileName = fileName;
    }

    protected abstract HashTable<Purchase> createHashTable();


    // buradaki fonksiyon supermarket_dataset_5.csv dosyasını okuyup, hash map oluşturuyor
    public HashTable<Purchase> readSupermarket() {

        HashTable<Purchase> map = createHashTable();

        try {
            File myObj = new File(fileName);
            Scanner myReader = new Scanner(myObj);
            myReader.nextLine(); // ilk satırı atla cunku header

            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] arrOfStr = data.split(",");

                String userId = arrOfStr[0];


                DataValidator.validateUserId(userId);
                DataValidator.validateUserIdLength(userId);
                DataValidator.validateFileLine(arrOfStr);

                int year = Integer.parseInt(arrOfStr[2].split("-")[0]);
                int month = Integer.parseInt(arrOfStr[2].split("-")[1]);
                int day = Integer.parseInt(arrOfStr[2].split("-")[2]);

                DataValidator.validateDate(year, month, day);

                String productName = arrOfStr[3];

                Date date = new Date(year, month, day);
                Product product = new Product(date, productName);

                if (map.get(userId) != null) {
                    map.get(userId).addToLinkedList(product);
                } else {
                    Purchase purchase = new Purchase(userId, arrOfStr[1]);
                    purchase.addToLinkedList(product);
                    map.put(userId, purchase);
                }
            }

            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        return map;
    }


    // buradaki fonksiyon customer_1K.txt dosyasını , hashmapte arayıp bulduğu müşterileri ekrana yazdırıyor.
    public void customerReadandParse(HashTable<Purchase> hashMap) {
        try {
            File myObj = new File("customer_1K.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String id = myReader.nextLine();

                long startTime = System.nanoTime();
                hashMap.contains(id);
                long endTime = System.nanoTime();
                long duration = (endTime - startTime);

                System.out.println("Time elapsed: " + duration + " nanoseconds");
            }

            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }


    // bu fonksiyon ne ise yariyodu aq
    public void searchAndPrint(String id, HashTable<Purchase> hashMap) {
        Purchase purchase = hashMap.get(id);

        if (purchase == null) {
            System.out.println("Customer not found");
        } else {
            System.out.printf(" %d transaction found for %s %n", purchase.getListOfProdcuts().size(), purchase.getName());
            purchase.getListOfProdcuts().print();
        }






    }



}
