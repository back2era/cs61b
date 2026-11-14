package deque;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class LinkedListDeque61B<T> implements Deque61B<T> {

    private Node sentinal;
    private int size = 0;
    public  LinkedListDeque61B(){
        sentinal = new Node(null);
        sentinal.next = sentinal;
        sentinal.prev = sentinal;
    }

    @Override
    public Iterator<T> iterator() {
        return null;
    }

    public class Node {
        public T item;
        public Node next;
        public Node prev;

        Node(T item){
            this.item = item;
        }
    }


    @Override
    public void addFirst(T x) {
        Node F = new Node(x);
        if (isEmpty()){
            sentinal.prev = F;
        }
        F.prev = sentinal;
        F.next = sentinal.next;
        sentinal.next = F;
        size += 1;
    }

    @Override
    public void addLast(T x) {
        Node L = new Node(x);
        if (isEmpty()){
            sentinal.next = L;
        }
        sentinal.prev.next = L;
        L.prev = sentinal.prev;
        L.next = sentinal;
        sentinal.prev = L;
        size += 1;
    }

    @Override
    public List<T> toList() {
        List<T> returnList = new ArrayList<>();
        Node i = sentinal.next;
        while(i != sentinal){
            returnList.add(i.item);
            i = i.next;
        }
        return returnList;
    }

    @Override
    public boolean isEmpty() {
        return sentinal.next.equals(sentinal);
    }

    @Override
    public int size() {

        return this.size;
    }

    @Override
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        if (size == 1){
            sentinal.prev = sentinal;
        }
        T removedItem = sentinal.next.item;
        sentinal.next.next.prev = sentinal;
        sentinal.next = sentinal.next.next;
        return removedItem;
    }

    @Override
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        if (size == 1){
            sentinal.next = sentinal;
        }
        T removedItem = sentinal.prev.item;
        sentinal.prev.prev.next = sentinal;
        sentinal.prev = sentinal.prev.prev;
        return removedItem;
    }

    @Override
    public T get(int index) {
        if (index > size | index < 0){
            return null;
        }

        List<T> L = toList();
        return L.get(index);
    }

    @Override
    public T getRecursive(int index) {
        if (index > size | index < 0){
            return null;
        }
        Node L = sentinal.next;
        int n = index;
        while(n > 0){
            L = L.next;
            n = n-1;
        }
        return L.item;
    }

    @Override
    public boolean equals(Object other){
        if (this == other){return true;}
        if(other instanceof LinkedListDeque61B<?> otherSet){
            if (this.size != otherSet.size){
                return false;
            }
            for (int i = 0;i < size;i++){
                if (!(this.get(i) == otherSet.get(i))){
                    return false;
                }
            }
            return true;
        }
        return false;
    }
}
