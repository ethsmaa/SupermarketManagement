public class Purchase {

    private String id;
    private String name;
    public LinkedList listOfProdcuts = new LinkedList();

    public Purchase(String id, String name) {
        this.id = id;
        this.name = name;
    }


    // tarihler linked listte sıralanarak eklesin  veya ayrı bi fonksiyonda linked listi sırala
    public void addToLinkedList(Object product) {
        listOfProdcuts.add(product);

        listOfProdcuts.sort();
    }

    public void sortLinkedList(LinkedList listOfProducts) {
        for(int i = 0; i < listOfProdcuts.size(); i++) {
            // for each element for listOfProducts
            for(int j = 0; j < listOfProdcuts.size(); j++) {
                // if year is same

                Product product1 = (Product) listOfProdcuts.get(i);
                Product product2 = (Product) listOfProdcuts.get(j);

                if(product1.date.year == product2.date.year) {
                    // if month is same
                    if(product1.date.month == product2.date.month) {
                        // if day is same
                        if(product1.date.day == product2.date.day) {
                            // do nothing
                        } else if(product1.date.day < product2.date.day) {
                            // swap
                            listOfProdcuts.set(i, product2);
                            listOfProdcuts.set(j, product1);
                        }
                    } else if(product1.date.month < product2.date.month) {
                        // swap
                        listOfProdcuts.set(i, product2);
                        listOfProdcuts.set(j, product1);
                    }
                } else if(product1.date.year < product2.date.year) {
                    // swap
                    listOfProdcuts.set(i, product2);
                    listOfProdcuts.set(j, product1);
                }
            }
        }
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
