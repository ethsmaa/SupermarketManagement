import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files

public class FileReader {

    String fileName;

    public FileReader(String fileName) {
        this.fileName = fileName;
    }

    HashTable readAndParseFile() {
        HashTable<Purchase> map = new PurchaseHashTable();
        try {
            File myObj = new File(fileName);
            Scanner myReader = new Scanner(myObj);
            myReader.nextLine(); // ilk satırı atla cunku header

            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] arrOfStr = data.split(",");

                String userId = arrOfStr[0];
                String name = arrOfStr[1];

                String[] arrOfDates = arrOfStr[2].split("-");
                int year= Integer.parseInt(arrOfDates[0]);
                int month = Integer.parseInt(arrOfDates[1]);
                int day = Integer.parseInt(arrOfDates[2]);

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

}


