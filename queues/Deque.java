import java.lang.Integer;
import java.lang.NullPointerException;
import java.lang.Iterable;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    private int size;

    private class Node
    {
        Item item;
        Node next;
    }

    private Node first = null;

    public Deque()                           // construct an empty deque
    {
        size = 0;
    }

    public boolean isEmpty()                 // is the deque empty?
    {
        return false;
    }

    public int size()                        // return the number of items on the deque
    {
        return size;
    }

    public void addFirst(Item item)          // add the item to the front
    {
        if (item == null)
            throw new NullPointerException();

        Node oldfirst = first;
        first = new Node();
        first.item = item;
        first.next = oldfirst;

        size++;
    }

    public void addLast(Item item)           // add the item to the end
    {
        if (item == null)
            throw new NullPointerException();

        if (size() == 0) {
            addFirst(item);
            return;
        }

        Node current = first;
        while (current.next != null) {
            current = current.next;
        }

        Node last = new Node();
        last.item = item;
        current.next = last;

        size++;
    }

    public Item removeFirst()                // remove and return the item from the front
    {
        if (size() == 0)
            throw new NoSuchElementException();

        Item item = first.item;
        first = first.next;
        size--;
        return item;
    }

    public Item removeLast()                 // remove and return the item from the end
    {
        if (size() == 0)
            throw new NoSuchElementException();

        if (size() == 1)
            return removeFirst();

        Node current = first;
        while (current.next.next != null) {
            current = current.next;
        }

        Item item = current.next.item;
        current.next = null;
        size--;
        return item;
    }

    private class ListIterator implements Iterator<Item> {
        private Node current = first;

        public boolean hasNext() {
            return current != null;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            Item item = current.item;
            current = current.next;
            return item;
        }
    }

    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    public static void main(String[] args)   // unit testing
    {
        StdOut.println("Start Test");
        Deque<Integer> d = new Deque<Integer>();

        d.addFirst(0);
        d.addFirst(1);
        d.addFirst(2);

        StdOut.println(d.removeFirst());
        StdOut.println(d.removeFirst());
        StdOut.println(d.removeFirst());

        d.addFirst(0);
        d.addFirst(1);
        d.addFirst(2);

        StdOut.println(d.removeLast());
        StdOut.println(d.removeLast());
        StdOut.println(d.removeLast());

        d.addLast(0);
        d.addLast(1);
        d.addLast(2);

        StdOut.println(d.removeLast());
        StdOut.println(d.removeLast());
        StdOut.println(d.removeLast());

        d.addLast(0);
        d.addLast(1);
        d.addLast(2);

        Iterator<Integer> i = d.iterator();

        while (i.hasNext())
            StdOut.println(i.next());

    }

}