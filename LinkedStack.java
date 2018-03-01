import java.util.NoSuchElementException;

/**
 * Your implementation of a linked stack. It should NOT be circular.
 *
 * @author Amani Konduru
 * @version 1.0
 */
public class LinkedStack<T> implements StackInterface<T> {

    // Do not add new instance variables.
    private LinkedNode<T> head;
    private int size;

    @Override
    public T pop() {
        //remove
        if (size == 0) {
            throw new NoSuchElementException("there are no elements "
                    + "in the stack, the stack is empty");
        } else {
            T deletedElement = head.getData();
            //head.setData(head.getNext().getData());
            //head.setNext(head.getNext().getNext());
            head = head.getNext();
            size--;
            return deletedElement;
        }

    }

    @Override
    public void push(T data) {
        //add
        if (data == null) {
            throw new IllegalArgumentException("Data is null and "
                    + "we don't want a null element");
        }

        if (size == 0) {
            //head = new LinkedNode<>(data,head);
            head = new LinkedNode<T>(data);
            //head.setNext(head);
            size++;
            //return;

        } else {
            LinkedNode<T> newNode = new LinkedNode<>(
                    data, head);
            head = newNode;
            size++;
        }
    }

    @Override
    public T peek() {
        //look
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
     * Returns the head of this stack.
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
}
