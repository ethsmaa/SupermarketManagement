import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files

public class FileReader {

    String fileName;

    public FileReader(String fileName) {
        this.fileName = fileName;
    }

    HashTable readSupermarket() {
        HashTable<Purchase> map = new PurchaseHashTable();
        try {
            File myObj = new File(fileName);
            Scanner myReader = new Scanner(myObj);
            myReader.nextLine(); // ilk satırı atla cunku header

            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] arrOfStr = data.split(",");

                String userId = arrOfStr[0];
                String[] arrOfId = userId.split("-");
                try{
                    if(arrOfId[0].length() != 8 || arrOfId[1].length() != 4 ||arrOfId[2].length() != 4 ||
                    arrOfId[3].length() != 4 || arrOfId[4].length() != 12 ) {
                        throw new Exception("Id format is wrong");
                    }
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }


                String name = arrOfStr[1];

                try{
                    if(userId.length() != 36) {
                        throw new Exception("User id must be 8 characters");
                    }
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }

               try { // arrrofStr size must be 4
                    if(arrOfStr.length != 4) {
                        throw new Exception("File line must have 4 elements");
                    }
                } catch (Exception e) {
                    throw new RuntimeException(e);

               }


                String[] arrOfDates = arrOfStr[2].split("-");
                int year= Integer.parseInt(arrOfDates[0]);
                int month = Integer.parseInt(arrOfDates[1]);
                int day = Integer.parseInt(arrOfDates[2]);

                try{
                    if(year < 0 || month < 0 || day < 0) {
                        throw new Exception("Date cannot be negative");
                    }
                } catch (Exception e) {
                    System.out.println(e);
                }




                String productName = arrOfStr[3];

                Date date = new Date(year, month, day);

                Product product = new Product(date, productName);

                // eğer map'in keyleri arasında id key'i yoksa devamına ekle
                    // eğer keyi bulduysa value'nun içine gir. linkedf liste ürünü ekle
                    if(map.get(userId) != null) {
                        map.get(userId).addToLinkedList(product);
                    } else {
                        Purchase purchase = new Purchase(userId, name);
                        // Add first product to linked list
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


    public void customerReadandParse(HashTable hashMap) {
        try {
            File myObj = new File("customer_1K.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String id = myReader.nextLine();
                searchAndPrint(id, hashMap);
            }


            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public void searchAndPrint(String id, HashTable hashMap) {
        Purchase purchase = (Purchase) hashMap.get(id);

        if(purchase == null) {
            System.out.println("Customer not found");
        } else {
            System.out.printf(" %d transaction found for %s %n", purchase.getListOfProdcuts().size(), purchase.getName());
            purchase.getListOfProdcuts().print();
        }
    }




}


