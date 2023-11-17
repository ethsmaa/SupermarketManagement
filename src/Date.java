public class Date {
    int day;
    int month;
    int year;

    public Date(int day, int month, int year) {
        this.day = day;
        this.month = month;
        this.year = year;
    }

    public void print() {
        System.out.print(day + "-" + month + "-" + year + " ");
    }
}
