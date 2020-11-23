package projectCuatro;

public class Queue<E> {
    private MySingleWithTailLinkedList<E> list = new MySingleWithTailLinkedList<>();

    public Queue(){ }

    public int size(){
        return list.size();
    }

    public boolean isEmpty(){
        return list.isEmpty();
    }

    public void push(E element, String e){
        list.addTop(element);
    }

    public E pop(){
        return list.remove(0);
    }

    public E top(){
        return list.get(0);
    }


}
