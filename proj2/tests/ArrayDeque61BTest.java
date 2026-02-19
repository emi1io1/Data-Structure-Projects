import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static com.google.common.truth.Truth.assertThat;


public class ArrayDeque61BTest {
    @Test
    public void addFirstTest(){
        Deque61B<Integer> x = new ArrayDeque61B<>();

        x.addFirst(1);
        assertThat(x.size()).isEqualTo(1);
        assertThat(x.toList()).containsExactly(1);

        x.addFirst(2);
        assertThat(x.size()).isEqualTo(2);
        assertThat(x.toList()).containsExactly(2,1).inOrder();
    }

    @Test
    public void addLastTest() {
        Deque61B<Integer> x = new ArrayDeque61B<>();

        x.addLast(1);
        assertThat(x.size()).isEqualTo(1);
        assertThat(x.toList()).containsExactly(1);
        x.addLast(2);
        assertThat(x.size()).isEqualTo(2);
        assertThat(x.toList()).containsExactly(1,2).inOrder();
    }

    @Test
    public void getFirstTest() {
        Deque61B<Integer> x = new ArrayDeque61B<>();
        assertThat(x.getFirst()).isNull();
        x.addLast(1);
        assertThat(x.getLast()).isEqualTo(1);
        x.addFirst(0);
        x.addLast(2);
        assertThat(x.getFirst()).isEqualTo(0);
    }

    @Test
    public void getLastTest() {
        Deque61B<Integer> x = new ArrayDeque61B<>();
        assertThat(x.getFirst()).isNull();

        x.addFirst(1);
        assertThat(x.getLast()).isEqualTo(1);

        x.addLast(2);
        x.addFirst(0);
        assertThat(x.getLast()).isEqualTo(2);
    }

    @Test
    public void getTest() {
        Deque61B<Integer> x = new ArrayDeque61B<>();
        assertThat(x.get(0)).isNull();

        x.addFirst(0);
        x.addLast(1);
        x.addLast(2);

        assertThat(x.get(0)).isEqualTo(0);
        assertThat(x.get(1)).isEqualTo(1);
        assertThat(x.get(2)).isEqualTo(2);

        assertThat(x.get(-1)).isNull();
        assertThat(x.get(3)).isNull();
    }

    @Test
    public void sizeAndIsEmptyTest() {
        Deque61B<Integer> x = new ArrayDeque61B<>();
        assertThat(x.isEmpty()).isTrue();
        assertThat(x.size()).isEqualTo(0);

        x.addFirst(1);
        assertThat(x.isEmpty()).isFalse();
        assertThat(x.size()).isEqualTo(1);

        x.addLast(2);
        assertThat(x.size()).isEqualTo(2);

        x.removeFirst();
        x.removeLast();

        assertThat(x.isEmpty()).isTrue();
        assertThat(x.size()).isEqualTo(0);
    }

    @Test
    public void toListTest() {
        Deque61B<Integer> x = new ArrayDeque61B<>();
        x.addLast(1);
        x.addFirst(0);
        x.addLast(2);
        x.addLast(3);

        x.removeFirst();
        x.removeFirst();

        assertThat(x.toList()).containsExactly(2, 3).inOrder();
    }

    @Test
    public void removeFirstTest(){
        Deque61B<Integer> x = new ArrayDeque61B<>();
        assertThat(x.removeFirst()).isNull();

        for (int i = 0; i < 9; i++){
            x.addFirst(i);
        }

        assertThat(x.size()).isEqualTo(9);

        for (int i = 0; i < 8; i++){
            x.removeFirst();
        }

        assertThat(x.size()).isEqualTo(1);
        assertThat(x.removeFirst()).isNotNull();
        assertThat(x.size()).isEqualTo(0);
        assertThat(x.toList()).isEmpty();
    }

    @Test
    public void removeLastTest() {
        Deque61B<Integer> x = new ArrayDeque61B<>();
        assertThat(x.removeLast()).isNull();

        for (int i = 0; i < 9; i++){
            x.addLast(i);
        }

        assertThat(x.size()).isEqualTo(9);

        for (int i = 0; i < 8; i++){
            x.removeLast();
        }

        assertThat(x.size()).isEqualTo(1);
        assertThat(x.removeLast()).isNotNull();
        assertThat(x.size()).isEqualTo(0);
        assertThat(x.toList()).isEmpty();
    }

    @Test
    public void resizeTest() {
        Deque61B<Integer> x = new ArrayDeque61B<>();

        for (int i = 0; i < 9; i++){
            x.addLast(i);
        }

        assertThat(x.size()).isEqualTo(9);
        assertThat(x.toList()).containsExactly(0,1,2,3,4,5,6,7,8).inOrder();

        for (int i = 0; i < 7; i++){
            assertThat(x.removeFirst()).isEqualTo(i);
        }

        assertThat(x.toList()).containsExactly(7,8).inOrder();

        for (int i = 9; i < 30; i++) {
            x.addLast(i);
        }

        assertThat(x.size()).isEqualTo(23);
        assertThat(x.toList()).containsExactly(
                7,8,9,10,11,12,13,14,15,16,17,
                18,19,20,21,22,23,24,25,26,27,28,29
        ).inOrder();
    }
    @Test
    public void resizeDownTest() {
        Deque61B<Integer> x = new ArrayDeque61B<>();

        for (int i = 0; i < 20 ; i++){
            x.addLast(i);
        }

        for (int i = 0; i <= 18; i++){
            assertThat(x.removeFirst()).isEqualTo(i);
        }

        assertThat(x.size()).isEqualTo(1);
        assertThat(x.toList()).containsExactly(19).inOrder();

        x.addLast(20);
        x.addLast(21);
        assertThat(x.toList()).containsExactly( 19, 20, 21).inOrder();
    }

    @Test
    public void iteratorBasicTest() {
        Deque61B<String> x = new ArrayDeque61B<>();
        x.addLast("front");
        x.addLast("middle");
        x.addLast("back");

        String result = "";
        for (String s : x) {
            result += s;
        }

        assertThat(result).isEqualTo("frontmiddleback");
    }

    @Test
    public void equalsBasicTest() {
        Deque61B<String> x = new ArrayDeque61B<>();
        Deque61B<String> y = new ArrayDeque61B<>();

        x.addLast("front");
        x.addLast("middle");
        x.addLast("back");

        y.addLast("front");
        y.addLast("middle");
        y.addLast("back");

        assertThat(x).isEqualTo(y);
    }

    @Test
    public void addAfterRemoveTest() {
        Deque61B<Integer> x = new ArrayDeque61B<>();
        x.addLast(1);
        x.addLast(2);
        assertThat(x.removeFirst()).isEqualTo(1);
        assertThat(x.removeFirst()).isEqualTo(2);
        assertThat(x.isEmpty()).isTrue();
        x.addFirst(10);
        assertThat(x.toList()).containsExactly(10);
        x.removeLast();
        x.addLast(20);
        assertThat(x.toList()).containsExactly(20);


    }
}
