import java.util.ArrayList;
import java.util.Arrays;

public class UnionFind {
    // TODO: Instance variables
    private int[] arr;
    /* Creates a UnionFind data structure holding N items. Initially, all
       items are in disjoint sets. */
    public UnionFind(int N) {
        // TODO: YOUR CODE HERE
        arr = new int[N];
        Arrays.fill(arr,-1);
    }

    /* Returns the size of the set V belongs to. */
    public int sizeOf(int v) {
        // TODO: YOUR CODE HERE
        int x = arr[v];
        while(x >= 0){
            x = arr[x];
        }
        return Math.abs(x);
    }

    /* Returns the parent of V. If V is the root of a tree, returns the
       negative size of the tree for which V is the root. */
    public int parent(int v) {
        // TODO: YOUR CODE HERE
        return arr[v];
    }

    /* Returns true if nodes/vertices V1 and V2 are connected. */
    public boolean connected(int v1, int v2) {
        // TODO: YOUR CODE HERE
        return find(v1) == find(v2);
    }

    /* Returns the root of the set V belongs to. Path-compression is employed
       allowing for fast search-time. If invalid items are passed into this
       function, throw an IllegalArgumentException. */
    public int find(int v) {
        // TODO: YOUR CODE HERE
        if (v < 0 | v >= arr.length){  //当v不正常的时候
            throw new IllegalArgumentException("Some comment to describe the reason for throwing.");
        }
        int x = parent(v);
        int y = parent(v);
        if (x < 0){ //第一次父节点就是负的
            return v;
        }
        while(arr[x] >= 0){  //正常情况递归
            x = parent(x);
        }
        while(arr[y] >= 0){
            arr[y] = x;
            y = parent(y);
        }
        return x; //返回根节点
    }

    /* Connects two items V1 and V2 together by connecting their respective
       sets. V1 and V2 can be any element, and a union-by-size heuristic is
       used. If the sizes of the sets are equal, tie break by connecting V1's
       root to V2's root. Union-ing an item with itself or items that are
       already connected should not change the structure. */
    public void union(int v1, int v2) {
        // TODO: YOUR CODE HERE
        if (v1 == v2 | find(v1) == find(v2)){
            return;
        }
        int size1 = sizeOf(v1);
        int size2 = sizeOf(v2);
        if (size1 <= size2){
            arr[find(v1)] = find(v2);
            arr[find(v2)]=arr[find(v2)]-size1;
        }else{
            arr[find(v2)] = find(v1);
            arr[find(v1)]=arr[find(v1)]-size2;
        }


    }

}
