import java.util.NoSuchElementException;

/**
 * Your implementation of a linked queue. It should NOT be circular.
 *
 * @author Amani Konduru
 * @userid akonduru3
 * @GTID 903163197
 * @version 1.0
 */
public class LinkedQueue<T> implements QueueInterface<T> {

    // Do not add new instance variables.
    private LinkedNode<T> head;
    private LinkedNode<T> tail;
    private int size;

    @Override
    public T dequeue() {
        //delete or remove
        if (size == 0) {
            throw new NoSuchElementException("there are no elements "
                    + "in the stack, the stack is empty");
        }
        if (size == 1) {
            T deletedElement = head.getData();
            head = null;
            tail = null;
            size--;
            return deletedElement;
        } else {
            T deletedElement = head.getData();
            head = head.getNext();
            size--;
            return deletedElement;
        }
    }

    @Override
    public void enqueue(T data) {
        //add
        if (data == null) {
            throw new IllegalArgumentException("Data is null and "
                    + "we don't want a null element");
        }

        if (size == 0) {
            head = new LinkedNode<T>(data);
            tail = head;
            size++;

        } else {
            LinkedNode<T> newNode = new LinkedNode<>(
                    data, null);
            tail.setNext(newNode);
            tail = newNode;
            size++;
        }
    }

    @Override
    public T peek() {
        //first element = head
        if (size == 0) {
            return null;
        } else {
            return head.getData();
        }
    }

    @Override
    public boolean isEmpty() {
        // DO NOT MODIFY THIS METHOD!
        return size == 0;
    }

    @Override
    public int size() {
        // DO NOT MODIFY THIS METHOD!
        return size;
    }

    /**
     * Returns the head of this queue.
     * Normally, you would not do this, but we need it for grading your work.
     *
     * DO NOT USE THIS METHOD IN YOUR CODE.
     *
     * @return the head node
     */
    public LinkedNode<T> getHead() {
        // DO NOT MODIFY THIS METHOD!
        return head;
    }

    /**
     * Returns the tail of this queue.
     * Normally, you would not do this, but we need it for grading your work.
     *
     * DO NOT USE THIS METHOD IN YOUR CODE.
     *
     * @return the tail node
     */
    public LinkedNode<T> getTail() {
        // DO NOT MODIFY THIS METHOD!
        return tail;
    }
}