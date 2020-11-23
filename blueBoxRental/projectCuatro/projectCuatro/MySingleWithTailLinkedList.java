package projectCuatro;
import java.io.Serializable;
import java.util.Arrays;

/*********************************************************************
 * Project 4 - "RedBox-Like Program"
 * - Single Linked List with Tail
 * @author TonaePatterson
 ********************************************************************/
public class MySingleWithTailLinkedList<E> implements Serializable {
    private Node<E> top;
    private Node<E> tail;
    private int size;

    /*********************************************************************
     * Class Constructor
     ********************************************************************/
    public MySingleWithTailLinkedList() {
        top = tail = null;
        size = 0;
    }

    /*********************************************************************
     * Method to get the size of linked list.
     * @return size - size of list.
     ********************************************************************/
    public int size() {
        size = 0;
        Node<E> temp = top;
        while(temp != null){
            temp = temp.getNext();
            size++;
        }
        return size;

    }

    /*********************************************************************
     * Method to get boolean if list is empty.
     * @return true/false.
     ********************************************************************/
    public boolean isEmpty(){
        if(size() == 0){
            return true;
        }
        return false;
    }

    /*********************************************************************
     * Method to clear the list of all objects.
     ********************************************************************/
    public void clear () {
        top = tail = null;
        size = 0;
    }

    /*********************************************************************
     * Method to add object to the list (adds to end then sorts).
     * @param s - object to be added.
     * FIX!!! NOT IN ORDER READ DIRECTIONS
     * MAKE A SORT
     ********************************************************************/
    public void add(E s) {
        // No List: Case 0
        if(top == null) {
            top = tail = new Node<E>(s, null);
            return;
        }
        // List Exists: Case 1
        Node<E> temp = new Node<E>(s,null);
        tail.setNext(temp);
        tail = temp;

//
    }

    /********************************************************************
     * Method to add to the list of objects(adds to the top ).
     * @param s - to be added.
     * MAYBE ADD SORT LIKE OTHER ADD???
     ********************************************************************/
    public void addTop(E s){
        // No List: Case 0
        if(top == null){
            top = tail = new Node<E>(s, null);
            return;
        }
        // List Exits: Case 1
        top = new Node<E>(s, top);

    }

    /*********************************************************************
     * Method to insert after specified index.
     * @param s - to be added.
     * @param index - index of insertion within list.
     ********************************************************************/
    public void insertAfter(E s, int index){
        // No list: Case 0
        // Cannot insert after if list does not exist.
        if(index < 0  || index > size() - 1){
            throw new IndexOutOfBoundsException("No list or index out of bounds.");
        }
//        // One item: Case 1
//        if(top == tail && index == 0){
//            tail = new Node<E>(s, null);
//            top.setNext(tail);
//            return;
//        }
        // List Exists: Case 2a (insert after top)
        if(top != tail && index == 0){
            Node<E> temp = new Node<E>(s, top.getNext());
            top.setNext(temp);
            return;
        }
        // list Exists: case 2b (insert after tail)
        if(top != tail && index == size() - 1){
            Node<E> temp = new Node<E>(s, null);
            tail.setNext(temp);
            tail = temp;
            return;
        }
        // List Exists: Case 2c (insert after middle)
        Node<E> temp = top;
        for(int i = 0; i < index; i++){
            temp = temp.getNext();
        }
        Node<E> temp2 = new Node<E>(s, temp.getNext());
        temp.setNext(temp2);

    }

    /*********************************************************************
     * Method to remove specified index of list.
     * @throws IllegalArgumentException if no list available.
     * @param index - index to be removed.
     * @return - object to be removed.
     ********************************************************************/
    public E remove(int index) {
        // No list: Case 0
        if(index < 0 || index > size() - 1 || size() == 0){
            throw new IllegalArgumentException("No list or index out of bounds.");
        }
        // One item: Case 1
        if(top == tail && index == 0){
            E temp = top.getData();
            top = tail = null;
            return temp;
        }
        // Multi-item: Case 2a(remove top)
        if(top != tail && index == 0){
            E temp = top.getData();
            top = top.getNext();
            return temp;
        }
        //  Multi-item: Case 2b(remove tail)
        if(top != tail && index == size() -1){
            Node<E> temp = top;
            while(temp.getNext().getNext() != null){
                temp = temp.getNext();
            }
            E temp2 = temp.getNext().getData();
            tail = temp;
            tail.setNext(null);
            return temp2;
        }
        // Multi-item: Case 2c(remove middle)
        Node<E> temp = top;
        for(int i = 0; i < index -1; i++){
            temp = temp.getNext();
        }
        E temp2 = temp.getNext().getData();
        temp.setNext(temp.getNext().getNext());
        return temp2;
    }

    /*********************************************************************
     * Method to get the specified index of list.
     * @throws IllegalArgumentException if no list available.
     * @param index - index to be returned.
     * @return object.
     ********************************************************************/
    public E get(int index) {
        // No list: Case 0
        if(index < 0 || index > size() - 1){
            throw new IllegalArgumentException("No list or index out of bounds.");
        }

        // List Exists: Case 1
        Node<E> temp = top;
        for(int i = 0; i < index; i++){
            temp = temp.getNext();
        }
        E temp2 = temp.getData();
        return temp2;
    }

    /*********************************************************************
     * Method to return a string of the list.
     * @return list as a list of strings.
     ********************************************************************/
    public String toString() {
        String objToString = "";
        Node<E> temp = top;
        for(int i = 0; i < size(); i++){
            while(temp != null) {
                objToString += temp.getData().toString() + "\n";
                temp = temp.getNext();
            }
        }
        return objToString;
    }

    public static void main(String[] args){
//        MySingleWithTailLinkedList<String> list = new MySingleWithTailLinkedList<String>();
//        list.addTop("A");
//        list.addTop("B");
//        list.addTop("C");
//        list.addTop("D");
//        list.addTop("E");
//        list.addTop("F");
//        list.clear();
//        System.out.print(list);

    }

}
