public class Product {
    public Date date;
    public String productName;

    public Product(Date date, String productName) {
        this.date = date;
        this.productName = productName;
    }

    //get date
    public Date getDate() {
        return date;
    }
}
