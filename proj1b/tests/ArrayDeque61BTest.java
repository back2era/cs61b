import jh61b.utils.Reflection;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.ArrayDeque;
import java.util.List;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;

public class ArrayDeque61BTest {

     @Test
     @DisplayName("ArrayDeque61B has no fields besides backing array and primitives")
     void noNonTrivialFields() {
         List<Field> badFields = Reflection.getFields(ArrayDeque61B.class)
                 .filter(f -> !(f.getType().isPrimitive() || f.getType().equals(Object[].class) || f.isSynthetic()))
                 .toList();

         assertWithMessage("Found fields that are not array or primitives").that(badFields).isEmpty();
     }

     @Test
    void toListTest(){
         Deque61B<Integer> L = new ArrayDeque61B<>();
        L.addFirst(1);

         L.addLast(8);
         assertThat(L.toList()).containsExactly(1,8).inOrder();
     }
     @Test
     void resizeTest(){
         Deque61B<Integer> L = new ArrayDeque61B<>();
         L.addFirst(1);
         L.addFirst(2);
         L.addLast(6);
         L.addFirst(3);
         L.addLast(7);
         L.addFirst(4);
         L.addLast(8);
         L.addFirst(10);
         L.addLast(12);
         L.addLast(5);
         L.addFirst(2);
         L.addLast(6);
         L.addFirst(2);
         L.addLast(6);
         L.addFirst(2);
         L.addLast(6);
         L.addFirst(2);
         L.addLast(6);
         assertThat(L.toList()).containsExactly(2,2,2,2,10,4,3,2,1,6,7,8,12,5,6,6,6,6).inOrder();
     }
    @Test
    void getTest(){
        Deque61B<Integer> L = new ArrayDeque61B<>();
        L.addFirst(1);
        L.addLast(5);
        L.addFirst(2);
        L.addLast(6);
        L.addFirst(3);
        L.addLast(7);
        L.addFirst(4);
        L.addLast(8);
        assertThat(L.get(9)).isEqualTo(null);
        //assertThat(L.toList()).containsExactly(4,3,2,1,5,6,7,8).inOrder();
    }

    @Test
    void addFirstLastTest(){
        Deque61B<Integer> L = new ArrayDeque61B<>();
        L.addFirst(1);
        L.addFirst(2);
        L.addLast(6);
        L.addFirst(3);
        L.addLast(7);
        L.addFirst(4);
        L.addLast(8);

        assertThat(L.toList()).containsExactly(4,3,2,1,6,7,8).inOrder();


    }

    @Test
    void removeTest(){
        Deque61B<Integer> L = new ArrayDeque61B<>();
        L.addFirst(1);
        L.addFirst(2);
        L.addLast(6);
        L.addFirst(3);
        L.addLast(7);
        L.addFirst(4);
        L.addLast(8);
        L.removeFirst();
        L.removeLast();
        assertThat(L.toList()).containsExactly(3,2,1,6,7).inOrder();


    }

    @Test
    void longremoveTest(){
        Deque61B<Integer> L = new ArrayDeque61B<>();
        L.addFirst(1);
        L.addFirst(2);
        L.addLast(6);
        L.addFirst(3);
        L.addLast(7);
        L.addFirst(4);
        L.addLast(8);
        L.addFirst(10);
        L.addLast(12);
        L.addLast(5);
        L.addFirst(2);
        L.addLast(6);
        L.addFirst(2);
        L.addLast(6);
        L.addFirst(2);
        L.addLast(6);
        L.addFirst(2);
        L.addLast(6);
        L.removeFirst();
        L.removeLast();
        assertThat(L.toList()).containsExactly(2,2,2,10,4,3,2,1,6,7,8,12,5,6,6,6).inOrder();
    }

//    @Test
//    void unresizeTest(){
//        Deque61B<Integer> L = new ArrayDeque61B<>();
//        L.addFirst(1);
//        L.addFirst(2);
//        L.removeLast();
//        assertThat(L.length()).isEqualTo(4);
//    }

    @Test
    void unresizeTest() {
        ArrayDeque61B<Integer> l = new ArrayDeque61B<>();

        // 添加9个元素，容量从8扩容到16
        for (int i = 0; i < 9; i++) {
            l.addLast(i);
        }

        assertThat(l.capacity()).isEqualTo(16);

        // 删除5个，剩余4个
        for (int i = 0; i < 5; i++) {
            l.removeFirst();
        }

        // 4/16 = 25%，此时应该缩容
        assertThat(l.capacity()).isEqualTo(8);

        assertThat(l.size()).isEqualTo(4);
    }
    @Test
    void resizeAt25PercentTest() {
        ArrayDeque61B<Integer> l = new ArrayDeque61B<>();

        for (int i = 0; i < 9; i++) {
            l.addLast(i);
        }

        assertThat(l.capacity()).isEqualTo(16);

        for (int i = 0; i < 5; i++) {
            l.removeFirst();
        }

        // 4 / 16 = 25%
        assertThat(l.capacity()).isEqualTo(8);
        assertThat(l.size()).isEqualTo(4);
    }
}
