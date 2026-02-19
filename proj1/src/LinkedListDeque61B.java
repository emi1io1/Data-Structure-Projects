import org.w3c.dom.Node;

import java.util.List;
import java.util.ArrayList; // import the ArrayList class



public class LinkedListDeque61B<T> implements Deque61B<T> {
    int size;
    Node sentinel;

    public class Node {
        T item;
        Node next;
        Node prev;


        public Node(Node prev, Node next, T item){
            this.prev = prev;
            this.next = next;
            this.item = item;
        }
    }

    public static void main() {
        Deque61B<Integer> lld = new LinkedListDeque61B<>();
        lld.addLast(0);   // [0]
        lld.addLast(1);   // [0, 1]
        lld.addFirst(-1); // [-1, 0, 1]

    }


    public LinkedListDeque61B(){
        this.sentinel = new Node(null, null, null);
        this.sentinel.next = sentinel;
        this.sentinel.prev = sentinel;
        this.size = 0;
    }
    /**
     * Add {@code x} to the front of the deque. Assumes {@code x} is never null.
     *
     * @param x item to add
     */
    @Override
    public void addFirst(T x) {
        Node n = new Node(sentinel, sentinel.next, x);
        sentinel.next.prev = n;
        sentinel.next = n;
        size++;
    }

    /**
     * Add {@code x} to the back of the deque. Assumes {@code x} is never null.
     *
     * @param x item to add
     */
    @Override
    public void addLast(T x) {
        Node n = new Node(sentinel.prev, sentinel, x);
        sentinel.prev.next = n;
        sentinel.prev = n;
        size++;

    }

    /**
     * Returns a List copy of the deque. Does not alter the deque.
     *
     * @return a new list copy of the deque.
     */
    @Override
    public List<T> toList() {
        List<T> returnList = new ArrayList<>();
        Node bruh = sentinel.next;
        while (bruh != sentinel){
            returnList.add(bruh.item);
            bruh = bruh.next;
        }
        return returnList;
    }

    /**
     * Returns if the deque is empty. Does not alter the deque.
     *
     * @return {@code true} if the deque has no elements, {@code false} otherwise.
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Returns the size of the deque. Does not alter the deque.
     *
     * @return the number of items in the deque.
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Return the element at the front of the deque, if it exists.
     *
     * @return element, otherwise {@code null}.
     */
    @Override
    public T getFirst() {
        if (size == 0){
            return null;
        }
        return sentinel.next.item;
    }

    /**
     * Return the element at the back of the deque, if it exists.
     *
     * @return element, otherwise {@code null}.
     */
    @Override
    public T getLast() {
        if (size == 0){
            return null;
        }
        return sentinel.prev.item;
    }

    /**
     * Remove and return the element at the front of the deque, if it exists.
     *
     * @return removed element, otherwise {@code null}.
     */
    @Override
    public T removeFirst() {
        if (size == 0){
            return null;
        }

        Node first = sentinel.next;
        T removed = first.item;
        sentinel.next = first.next;
        first.next.prev = sentinel;

        first.next = null;
        first.prev = null;
        first.item = null;

        size-=1;
        return removed;


    }

    /**
     * Remove and return the element at the back of the deque, if it exists.
     *
     * @return removed element, otherwise {@code null}.
     */
    @Override
    public T removeLast() {
        if (size == 0) {
            return null;
        }
        Node last = sentinel.prev;
        T removed = last.item;
        sentinel.prev = last.prev;
        last.prev.next = sentinel;

        last.next = null;
        last.prev = null;
        last.item = null;
        size-=1;
        return removed;
    }

    /**
     * The Deque61B abstract data type does not typically have a get method,
     * but we've included this extra operation to provide you with some
     * extra programming practice. Gets the element, iteratively. Returns
     * null if index is out of bounds. Does not alter the deque.
     *
     * @param index index to get
     * @return element at {@code index} in the deque
     */
    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        Node x = sentinel.next;
        for (int i = 0; i < index; i++){
            x = x.next;
        }
        return x.item;

    }

    /**
     * This method technically shouldn't be in the interface, but it's here
     * to make testing nice. Gets an element, recursively. Returns null if
     * index is out of bounds. Does not alter the deque.
     *
     * @param index index to get
     * @return element at {@code index} in the deque
     */
    @Override
    public T getRecursive(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        return getRecursive(sentinel.next, index);

    }
    private T getRecursive(Node i, int index){
        if (index == 0){
            return i.item;
        }
        return getRecursive(i.next, index -1);
    }
}
