import org.w3c.dom.Node;

import java.util.Iterator;
import java.util.Set;

public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {
    
    private Node root = null;
    private int size = 0;
    private class Node{
        K key;
        V value;
        Node left;
        Node right;

        Node(K key,V value){
            this.key = key;
            this.value = value;
        }
    }

    private Node find(Node T,K key){
        if(T == null){
            return null;
        }
        if (T.key.compareTo(key) > 0) {
            return find(T.left,key);
        } else if (T.key.compareTo(key) < 0) {
            return find(T.right,key);
        }else{
            return T;
        }
    }

    private Node putHelper(Node T,K key,V value){
        if(T == null){
            size ++;
            return new Node(key,value);
        }
        int cmp = T.key.compareTo(key);
        if (cmp < 0) {
            T.right = putHelper(T.right,key,value);
        } else if (cmp > 0) {
            T.left = putHelper(T.left,key,value);
        }else{
            T.value = value;

        }
        return T;
    }

    private void printInOrder(Node T){
        if (T == null){
            return;
        }
        printInOrder(T.left);
        System.out.println(T.value);
        printInOrder(T.right);
    }
    @Override
    public void put(K key, V value) {
        root = putHelper(root,key,value);
    }

    @Override
    public V get(K key) {
        Node T = find(root,key);
        if(T == null){
            return null;
        }
        return T.value;
    }

    @Override
    public boolean containsKey(K key) {
        if(find(root,key) == null){
            return false;
        } else if (find(root,key).key.compareTo(key) == 0) {
            return true;
        }
        return false;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    @Override
    public Set<K> keySet() {
        throw new UnsupportedOperationException();
    }

    @Override
    public V remove(K key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<K> iterator() {

        throw new UnsupportedOperationException();
    }
}
