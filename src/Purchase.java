public class Purchase {

    private String id;
    private String name;
    public LinkedList listOfProdcuts = new LinkedList();

    public Purchase(String id, String name) {
        this.id = id;
        this.name = name;
    }


    // add dates to linked list and sort them
    public void addToLinkedList(Object product) {
        listOfProdcuts.add(product);

        listOfProdcuts.sort();
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
