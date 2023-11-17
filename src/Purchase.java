public class Purchase {

    private String id;
    private String name;
    private LinkedList listOfProdcuts = new LinkedList();

    public Purchase(String id, String name) {
        this.id = id;
        this.name = name;
    }


    // tarihler linked listte sıralanarak eklesin  veya ayrı bi fonksiyonda linked listi sırala
    public void addToLinkedList(Object product) {
        listOfProdcuts.add(product);
    }

    public void sortLinkedList(LinkedList listOfProdcuts) {
        // yıllar aynıysa
        // aylar aynıysa
        // günlere bak


    }

    public Object getName() {
        return name;
    }


    public String getId() {
        return id;
    }


    public LinkedList getListOfProdcuts() {
        return listOfProdcuts;
    }


    //


}
