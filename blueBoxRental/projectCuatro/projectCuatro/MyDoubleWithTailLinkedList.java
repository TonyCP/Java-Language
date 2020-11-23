package projectCuatro;
import java.io.Serializable;

/*********************************************************************
 * Project 4 - "RedBox-Like Program"
 * - Double Linked List with Tail
 * @author TonaePatterson
 ********************************************************************/
public class MyDoubleWithTailLinkedList<E> implements Serializable
{
    private DNode<E> top;
    private DNode<E> tail;
    private int size;

    /*********************************************************************
     * Class Constructor
     ********************************************************************/
    public MyDoubleWithTailLinkedList() {
        top = tail = null;
        size = 0;
    }

    /*********************************************************************
     * Method to get the size of linked list.
     * @return size - size of list.
     ********************************************************************/
    public int size() {
        size = 0;
        DNode<E> temp = top;
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
     * Method to clear the list.
     ********************************************************************/
    public void clear () {
        top = tail = null;
        size = 0;
    }

    /*********************************************************************
     * Method to add to the list (adds to end then sorts).
     * @param s - to be added.
     * FIX!!! NOT IN ORDER READ DIRECTIONS
     * MAKE A SORT
     ********************************************************************/
    public void add(E s) {
        // No List: Case 0
        if(top == null){
            top = tail = new DNode<E>(s,null, null);
            return;
        }
        // List Exists: Case 1
        DNode<E> temp = new DNode<E>(s, null, tail);
        tail.setNext(temp);
        tail = temp;

        //Comparator here
    }

    /********************************************************************
     * Method to add to the list (adds to the top).
     * @param s - to be added.
     * MAYBE ADD SORT LIKE OTHER ADD???
     ********************************************************************/
    public void addTop(E s){
        // No List: Case 0
        if(top == null){
            top = tail = new DNode<E>(s, null, null);
            return;
        }
        // List Exits: Case 1
        DNode<E> temp = top;
        top = new DNode<E>(s, temp, null);
        temp.setPrev(top);

        // Maybe add a sort here
    }

    /*********************************************************************
     * Method to get insert after specified index.
     * @param s - to be added.
     * @param index - index of insertion within list.
     ********************************************************************/
    public void insertAfter(E s, int index){
        // No list: Case 0
        // Cannot insert after if list does not exist.
        if(index < 0 || index > size() - 1){
            throw new IndexOutOfBoundsException("No list or index out of bounds.");
        }
//        // No list: Case 1a
//        // Automatically adds it to first position if no list
//        if(top == null && index == 0){
//            top = tail = new DNode<E>(s, null, null);
//        }
        // One item: Case 1
        if(top == tail && index == 0){
            tail = new DNode<E>(s, null, top);
            top.setNext(tail);
            return;
        }
        // List Exists: Case 2a (insert after top)
        if(top != tail && index == 0){
            DNode<E> temp = new DNode<E>(s, top.getNext(), top);
            top.getNext().setPrev(temp);
            top.setNext(temp);
            return;
        }
        // List Exists: Case 2b (insert after tail)
        if(top != tail && index == size() - 1){
            DNode<E> temp = new DNode<E>(s, null, tail);
            tail.setNext(temp);
            tail = temp;
            return;
        }
        // List Exists: Case 2c (insert after middle)
        DNode<E> temp = top;
        for(int i = 0; i < index; i++){
            temp = temp.getNext();
        }
        DNode<E> temp2= new DNode<E>(s, temp.getNext(), temp);
        temp.getNext().setPrev(temp2);
        temp.setNext(temp2);

    }

    /*********************************************************************
     * Method to remove specified index of list.
     * @throws IllegalArgumentException if no list available.
     * @param index - index to be removed.
     * @return - object removed.
     ********************************************************************/
    public E remove(int index) {
        // No list: Case 0
        if(index < 0 || index > size() -1 || size() == 0){
            throw new IllegalArgumentException("No list or index out of bounds.");
        }
        // One item: Case 1
        if(top == tail && index  == 0){
           E temp = top.getData();
            top = tail = null;
            return temp;
        }
        // Multi-item: 1a(remove top)
        if(top != tail && index == 0){
            E temp = top.getData();
            top = top.getNext();
            top.setPrev(null);
            return temp;
        }
        // Multi-item: 1b(remove tail)
        if(top != tail && index == size() - 1){
            E temp = tail.getData();
            tail = tail.getPrev();
            tail.setNext(null);
            return temp;
        }
        //Multi-item: 1c(remove middle)
        DNode<E> temp = top;
        for(int i = 0; i < index; i++){
            temp = temp.getNext();
        }
        E temp2 = temp.getData();
        temp.getPrev().setNext(temp.getNext());
        temp.getNext().setPrev(temp.getPrev());
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
        if(index < 0 || index > size() -1 || size() == 0){
            throw new IllegalArgumentException("No list or index out of bounds.");
        }

        // Multi-item: Case 2
        DNode<E> temp = top;
        for(int i =0; i < index; i++){
            temp = temp.getNext();
        }
        return temp.getData();
    }

    /*********************************************************************
     * Method to return a string of the list.
     * @return list as a list of strings.
     ********************************************************************/
    public String toString() {
        String objToString = "";
        DNode temp = top;
        for(int i = 0; i < size(); i++){
            while(temp != null) {
                objToString += temp.getData().toString() + "\n";
                temp = temp.getNext();
            }
        }
        return objToString;
    }

    public static void main (String [] args){
//        MyDoubleWithTailLinkedList<String> list = new MyDoubleWithTailLinkedList<String>();
//        list.add("A");
//        list.add("B");
//        list.add("C");
//        list.add("D");
//        list.addTop("A");
//        list.addTop("B");
//        list.addTop("C");
//        list.clear();
//
//        System.out.print(list);
    }



}
