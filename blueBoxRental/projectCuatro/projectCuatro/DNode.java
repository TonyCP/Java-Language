package projectCuatro;
import java.io.Serializable;

public class DNode<E> implements Serializable {
    private E data;
    private DNode<E> next;
    private DNode<E> prev;

    public DNode(E data, DNode<E> next, DNode<E> prev) {
        super();
        this.data = data;
        this.next = next;
        this.prev = prev;
    }

    public DNode() {
    }

    public E getData() {
        return data;
    }

    public void setData(E data) {
        this.data = data;
    }

    public DNode<E> getNext() {
        return next;
    }

    public void setNext(DNode<E> next) {
        this.next = next;
    }

    public DNode<E> getPrev() {
        return prev;
    }

    public void setPrev(DNode<E> prev) {
        this.prev = prev;
    }
}

