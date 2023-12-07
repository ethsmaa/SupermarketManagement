import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;
public abstract class AbstractFileReader {
    protected String fileName;
    protected LinkedList searchTimeList = new LinkedList(); // to find average max and min time
    protected LinkedList indexTime = new LinkedList(); // to find indexing time

    public AbstractFileReader(String fileName) {
        this.fileName = fileName;
    }

    protected abstract HashTable<Purchase> createHashTable(); // polymorphism

    // that function reads supermarket_dataset_5.csv and uses hashmaps to store data
    public HashTable<Purchase> readSupermarket() {

        HashTable<Purchase> map = createHashTable();

        try {
            File myObj = new File(fileName);
            Scanner myReader = new Scanner(myObj);
            myReader.nextLine(); // it skips first line because it is header

           // long startTime = System.nanoTime();  // to find indexing time

            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] arrOfStr = data.split(",");

                String userId = arrOfStr[0];

                DataValidator.validateUserId(userId);
                DataValidator.validateUserIdLength(userId);       // to check if user id
                DataValidator.validateFileLine(arrOfStr);

                int year = Integer.parseInt(arrOfStr[2].split("-")[0]);
                int month = Integer.parseInt(arrOfStr[2].split("-")[1]);
                int day = Integer.parseInt(arrOfStr[2].split("-")[2]);

                DataValidator.validateDate(year, month, day);  // to check if date is valid

                String productName = arrOfStr[3];

                Date date = new Date(year, month, day);
                Product product = new Product(date, productName);



                if (map.get(userId) != null) {   // if user id is already in the hashmap
                    map.get(userId).addToLinkedList(product);   // add product to linkedlist
                } else { // first purchase transaction
                    Purchase purchase = new Purchase(userId, arrOfStr[1]);   // create new purchase object
                    purchase.addToLinkedList(product);
                    map.put(userId, purchase);
                }
            }

           /* long endTime = System.nanoTime();    // end of indexing time
            long duration = endTime- startTime;
            System.out.println("Indexing Time: " + duration);*/

            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        return map;
    }

    // sum of all index time
    public void findTotalIndexTime() {
        long total = 0;
        for (int i = 0; i < indexTime.size(); i++) {
            total += (long) indexTime.get(i);
        }
        System.out.println("Total index time: " + total);
    }

    // that function writes customer_1K.txt and writes the customer which has been searched in hashmap.
    public void customerReadandParse(HashTable<Purchase> hashMap) {
        try {
            File myObj = new File("customer_1K.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String id = myReader.nextLine();
                DataValidator.validateUserId(id);

                searchAndPrint(id,hashMap); // search and time calculations
            }

            findMaxTime();
            findMinTime();
            findAverageTime();

            myReader.close();

        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    // find max time in searchTimeList
    public void findMaxTime() {
        long max = (long) searchTimeList.get(0);
        for (int i = 1; i < searchTimeList.size(); i++) {
            if ((long) searchTimeList.get(i) > max) {
                max = (long) searchTimeList.get(i);
            }
        }
        System.out.println("Max time: " +  max);
    }

    // find min time in searchTimeList
    public void findMinTime() {
        long min = (long) searchTimeList.get(0);
        for (int i = 1; i < searchTimeList.size(); i++) {
            if ((long) searchTimeList.get(i) < min) {
                min = (long) searchTimeList.get(i);
            }
        }
        System.out.println("Min time: " +  min);
    }

    // find average time in searchTimeList
    public void findAverageTime() {
        long sum = 0;
        for (int i = 0; i < searchTimeList.size(); i++) {
            sum += (long) searchTimeList.get(i);
        }
        System.out.println("Average time: " + (sum / searchTimeList.size() ));
    }


    // that function searches the customer according to its id in hashmap and prints the customer's transactions
    public void searchAndPrint(String id, HashTable<Purchase> hashMap) {
        long startTime = System.nanoTime();
        Purchase purchase = hashMap.get(id);
        long endTime = System.nanoTime();
        long duration = (endTime - startTime);
        searchTimeList.add(duration);

        if (purchase == null) {
            System.out.println("Customer not found");
        } else {
            System.out.printf(" %d transaction found for %s %n", purchase.getListOfProdcuts().size(), purchase.getName());
            purchase.getListOfProdcuts().print();
        }
    }

}