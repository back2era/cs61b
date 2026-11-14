package deque;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ArrayDeque61B<T> implements Deque61B<T> {

    private T[] items;
    private int size;
    private int first;
    private int last;
    public ArrayDeque61B(){
        items =(T[]) new Object[8];
        size = 0;
        first = 3;
        last = 4;
    }
    @Override
    public void addLast(T x) {
        if(size == items.length){
            resize();
        }
        size ++;
        items[last] = x;
        last = Math.floorMod(last+1, items.length);
    }

    @Override
    public void addFirst(T x) {
        if(size == items.length){
            resize();
        }
        size ++;
        items[first] = x;
        first = Math.floorMod(first-1, items.length);
    }

    public void resize(){
        int newlength = 2 * items.length;
        int realLast = last;
        int end;
        T[] newlist = (T[]) new Object[newlength];
        if (last == 0){
            realLast = items.length;
        }
        if (realLast - first > 1){
            end = realLast;
        }else{
            end = items.length;
        }
        for (int i = first+1; i < end; i++) {
            newlist[i + items.length] = get(i);
        }
        if (last <= first | last - first == 1){
            for (int j = 0; j < realLast; j++) {
                newlist[j] = get(j);
            }
        }

        first = first + items.length;
        last = realLast;
        items = newlist;
    }

    @Override
    public List<T> toList() {
        int end;
        List<T> returnList = new ArrayList<>();
        if (last >= first){
            end = last;
        }else{
            end = items.length;
        }

        for (int i = Math.floorMod(first+1, items.length); i < end; i++) {

            returnList.add(items[i]);
        }
        if (last < first){
            for (int j = 0; j < last; j++) {
                returnList.add(items[j]);
            }
        }

        return returnList;
    }

    @Override
    public boolean isEmpty() {
        if(size == 0){
            return true;
        }
        return false;
    }

    @Override
    public int size() {
        return size;
    }

    public int capacity(){return items.length;}
    @Override
    public T removeFirst() {
        first = Math.floorMod(first+1, items.length);
        size -= 1;
        if (size <= items.length *0.25){
            unresize();
        }
        return null;
    }

    @Override
    public T removeLast() {
        last = Math.floorMod(last-1, items.length);
        size -= 1;
        if (size <= items.length *0.25){
            unresize();
        }
        return null;
    }

    public void unresize(){
        int newlength = items.length/2;
        int realLast = last;
        int end;
        int n = 0;
        T[] newlist = (T[]) new Object[newlength];
//        if (last == 0){
//            realLast = items.length;
//        }
        if (realLast - first > 1){
            end = realLast;
        }else{
            end = items.length;
        }
        for (int i = first+1; i < end; i++) {
            newlist[n] = get(i);
            n++;
        }
        if (last <= first | last - first == 1){
            for (int j = 0; j < realLast; j++) {
                newlist[n] = get(j);
            }
        }

        first = items.length-1;
        last = n;
        items = newlist;
    }

    @Override
    public T get(int index) {
        if (index < 0 | index >= size){
            return null;
        }
        return items[Math.floorMod(index, items.length)];
    }

    @Override
    public T getRecursive(int index) {

        return null;
    }

    @Override
    public Iterator<T> iterator() {
        return null;
    }

    public T gt(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        int actualIndex = Math.floorMod(first + 1 + index, items.length);
        return items[actualIndex];
    }
    @Override
    public boolean equals(Object other){
        if (this == other){return true;}
        if(other instanceof ArrayDeque61B<?> otherSet){
            if (this.size != otherSet.size){
                return false;
            }
            for (int i = 0;i < size;i++){
                if (!(this.gt(i) == otherSet.gt(i))){
                    return false;
                }
            }
            return true;
        }
        return false;
    }
}
