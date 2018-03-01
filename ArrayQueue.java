import java.util.NoSuchElementException;

/**
 * Your implementation of an array-backed queue.
 *
 * @author Amani Konduru
 * @version 1.0
 */
public class ArrayQueue<T> implements QueueInterface<T> {

    // Do not add new instance variables.
    private T[] backingArray;
    private int front;
    private int size;

    /**
     * Constructs a new ArrayQueue.
     */
    public ArrayQueue() {
        backingArray = (T[]) new Object[INITIAL_CAPACITY];
    }

    /**
     * Dequeue from the front of the queue.
     *
     * Do not shrink the backing array.
     * If the queue becomes empty as a result of this call, you should
     * explicitly reset front to 0.
     *
     * You should replace any spots that you dequeue from with null. Failure to
     * do so can result in a loss of points.
     *
     * See the homework pdf for more information on implementation details.
     *
     * @see QueueInterface#dequeue()
     */
    @Override
    public T dequeue() {
        //remove
        if (size == 0) {
            throw new NoSuchElementException("the Queue is empty, "
                    + " there are no elements");
        } else {
            T data = backingArray[front];
            backingArray[front] = null;
            front++;
            front = front % backingArray.length;
            size--;
            if (size == 0) {
                front = 0;
            }
            return data;
        }
    }

    /**
     * Add the given data to the queue.
     *
     * If sufficient space is not available in the backing array, you should
     * regrow it to double the current length. If a regrow is necessary,
     * you should copy elements to the front of the new array and reset
     * front to 0.
     *
     * @see QueueInterface#enqueue(T)
     */
    @Override
    public void enqueue(T data) {
        //add
        if (data == null) {
            throw new IllegalArgumentException("Data is null and "
                    + "we don't want a null element");
        } else if (size == backingArray.length) {
            T[] tempArray = (T[]) new Object[backingArray.length * 2];
            for (int i = 0; i < size; i++) {
                tempArray[i] = backingArray[(front + i) % backingArray.length];
            }
            front = 0;
            backingArray = tempArray;
            //backingArray[(front + size) % backingArray.length] = data;
            //size++;
        }
        backingArray[(front + size) % backingArray.length] = data;
        size++;
    }

    @Override
    public T peek() {
        return backingArray[front];
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
     * Returns the backing array of this queue.
     * Normally, you would not do this, but we need it for grading your work.
     *
     * DO NOT USE THIS METHOD IN YOUR CODE.
     *
     * @return the backing array
     */
    public Object[] getBackingArray() {
        // DO NOT MODIFY THIS METHOD!
        return backingArray;
    }
}
