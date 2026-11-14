package deque;
import java.util.Comparator;
public class MaxArrayDeque61B<T> extends ArrayDeque61B<T> {
    private Comparator<T> comp;
    public MaxArrayDeque61B(Comparator<T> c){
        comp = c;
    }

    public T max(){
        return max(comp);
    }

    public T max(Comparator<T> c){
        if (isEmpty()){return null;}

        T maxItem = gt(0);

        for (int i = 1; i < size(); i++) {
            T current = gt(i);

            if (c.compare(current,maxItem) > 0){
                maxItem = current;
            }
        }

        return maxItem;


    }


}
