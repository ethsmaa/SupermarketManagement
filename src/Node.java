public class Node {
    private Object data;
    Node link;

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Node getLink() {
        return link;
    }

    public void setLink(Node link) {
        this.link = link;
    }

    public Node(Object data) {
        this.data = data;
        link = null;
    }
}
