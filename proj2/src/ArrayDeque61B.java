import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

public class ArrayDeque61B<T> implements Deque61B<T> {
    T[] items;
    int size;
    int nextFirst;
    int nextLast;

    public ArrayDeque61B() {
        items = (T[]) new Object[8];
        size = 0;
        nextFirst = 0;
        nextLast = 1;
    }

    @Override
    public void addFirst(T x) {
        if (size == items.length) {
            resize(items.length * 2);
        }
        items[nextFirst] = x;
        nextFirst = (nextFirst - 1 + items.length) % items.length;
        size++;
    }

    @Override
    public void addLast(T x) {
        if (size == items.length) {
            resize(items.length * 2);
        }
        items[nextLast] = x;
        nextLast = (nextLast + 1) % items.length;
        size++;
    }

    @Override
    public List<T> toList() {
        List<T> returnList = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            returnList.add(get(i));
        }
        return returnList;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public T getFirst() {
        if (size == 0) {
            return null;
        }

        return items[(nextFirst + 1)%items.length];
    }

    @Override
    public T getLast() {
        if (size == 0) {
            return null;
        }
        return items[(nextLast - 1 + items.length)%items.length];
    }

    @Override
    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        int index = (nextFirst + 1) % items.length;
        T removed = items[index];
        items[index] = null;
        nextFirst = index;
        size--;
        resizeDown();
        return removed;
    }

    @Override
    public T removeLast() {
        if (size == 0) {
            return null;
        }
        int index = (nextLast - 1 + items.length) % items.length;
        T removed = items[index];
        items[index] = null;
        nextLast = index;
        size--;
        resizeDown();
        return removed;
    }

    @Override
    public T get(int index) {
        if (index >= size || index < 0) {
            return null;
        }
        int actual = (nextFirst + index + 1) % items.length;
        return items[actual];
    }

    @Override
    public T getRecursive(int index) {
        throw new UnsupportedOperationException("No need to implement getRecursive for ArrayDeque61B.");
    }

    public void resize(int extension) {
        T[] newItems = (T[]) new Object[extension];
        int start = (nextFirst + 1) % items.length;
        for (int i = 0; i < size; i++) {
            newItems[i] = items[(start + i) % items.length];
        }
        items = newItems;
        nextFirst = items.length - 1;
        nextLast = size;
    }

    public void resizeDown() {
        if (items.length >= 16 && size <= items.length / 4) {
            resize(items.length / 2);
        }
    }

    @Override
    public boolean equals(Object udda) {
        if (this == udda) {
            return true;
        }
        if (!(udda instanceof Deque61B<?>)) {
            return false;
        }
        Deque61B<?> other = (Deque61B<?>) udda;

        if (this.size() != other.size()) {
            return false;
        }

        for (int i = 0; i < size; i++) {
            T x = this.get(i);
            Object y = other.get(i);
            if (x == null) {
                if (y != null) {
                    return false;
                }
            } else {
                if (!x.equals(y)) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public String toString() {
        String s = "[";
        for (int i = 0; i < size; i++) {
            int index = (nextFirst + 1 + i)%items.length;
            s += items[index];
            if (i < size-1) {
                s += ", ";
            }
        }
        return s + "]";
    }
    @Override
    public Iterator<T> iterator() {
        return new ArrayDequeIterator();
    }

    private class ArrayDequeIterator implements Iterator<T> {
        int pos = 0;

        @Override
        public boolean hasNext() {
            return pos < size;
        }

        @Override
        public T next() {
            int index = (nextFirst + 1 + pos) % items.length;
            T item = items[index];
            pos++;
            return item;
        }
    }
}