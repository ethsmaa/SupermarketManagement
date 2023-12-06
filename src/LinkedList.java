public class LinkedList {
    Node head;

    public void add(Object dataToAdd) {
        Node newNode = new Node(dataToAdd);

        if (head == null) {
            head = newNode;
        } else {
            Node temp = head;
            while (temp.getLink() != null) {
                temp = temp.getLink();
            }
            temp.setLink(newNode);

        }
    }

    public void remove(int index) {
        if (head == null || index < 0) {
            return;
        }

        if (index == 0) {
            head = head.getLink();
        } else {
            Node temp = head;
            int count = 0;

            while (temp != null && count < index - 1) {
                count++;
                temp = temp.getLink();
            }

            if (temp != null && temp.getLink() != null) {
                temp.setLink(temp.getLink().getLink());
            }
        }
    }

    public int size() {
        if (head == null) return 0;

        else {
            Node temp = head;
            int count = 0;

            while (temp != null) {
                count++;
                temp = temp.getLink();
            }
            return count;
        }

    }

    public boolean searching(Object item) {

        boolean flag = false;
        if (head == null)
            System.out.println("linked list is empty");
        else {
            Node temp = head;
            while (temp != null) {
                if (item == temp.getData())
                    flag = true;
                temp = temp.getLink();
            }
        }
        return flag;
    }

    public Object get(int index) {
        if (head == null || index < 0) {
            return null;
        } else {
            Node temp = head;
            int count = 0;

            while (temp != null && count < index) {
                count++;
                temp = temp.getLink();
            }

            if (temp == null) {
                return null;
            } else {
                return temp.getData();
            }
        }
    }

    public void set(int index, Object newValue) {
        if (head == null || index < 0) {
            return;
        } else {
            Node temp = head;
            int count = 0;

            while (temp != null && count < index) {
                count++;
                temp = temp.getLink();
            }

            if (temp != null) {
                temp.setData(newValue);
            }
        }
    }

    // gelen purchase'in date ve name
    public void print() {
        Node temp = head;
        while (temp != null) {
            Product product = (Product) temp.getData();
            product.date.print();
            System.out.println(product.productName);
            temp = temp.getLink();
        }
    }


    public void sort() {
        for (int i = 0; i < size(); i++) {
            // for each element for listOfProducts
            for (int j = 0; j < size(); j++) {

                Product product1 = (Product) get(i);
                Product product2 = (Product) get(j);

                // if year is same
                if (product1.date.year == product2.date.year) {
                    // if month is same
                    if (product1.date.month == product2.date.month) {
                        // if day is same
                        if (product1.date.day == product2.date.day) {
                            // do nothing
                        } else if (product1.date.day > product2.date.day) {
                            // swap
                            set(i, product2);
                            set(j, product1);
                        }
                    } else if (product1.date.month > product2.date.month) {
                        // swap
                        set(i, product2);
                        set(j, product1);
                    }
                } else if (product1.date.year > product2.date.year) {
                    // swap
                    set(i, product2);
                    set(j, product1);
                }
            }
        }

    }


}
