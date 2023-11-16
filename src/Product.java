public class Product {
    Date date;
    String productName;

    public Product(Date date, String productName) {
        this.date = date;
        this.productName = productName;
    }

    public void print() {
        System.out.println(date + "\t" + productName);
    }
}
