import java.util.NoSuchElementException;

/**
 * Your implementation of a max priority queue.
 * 
 * @author Amani Konduru
 * @userid akonduru3
 * @GTID 903163197
 * @version 1.0
 */
public class MaxPriorityQueue<T extends Comparable<? super T>>
    implements PriorityQueueInterface<T> {

    private HeapInterface<T> backingHeap;
    // Do not add any more instance variables. Do not change the declaration
    // of the instance variables above.

    /**
     * Creates a priority queue.
     */
    public MaxPriorityQueue() {
        backingHeap = new MaxHeap<>();
    }

    @Override
    public void enqueue(T item) {
        if (item == null) {
            throw new IllegalArgumentException("Data is null and it should not be");
        }
        backingHeap.add(item);
    }

    @Override
    public T dequeue() {
        if (backingHeap.isEmpty()) {
            throw new NoSuchElementException("Priority Queue is empty");
        }

        return backingHeap.remove();
    }

    @Override
    public boolean isEmpty() {
        // DO NOT MODIFY THIS METHOD!
        return backingHeap.isEmpty();
    }

    @Override
    public int size() {
        // DO NOT MODIFY THIS METHOD!
        return backingHeap.size();
    }

    @Override
    public void clear() {
        backingHeap.clear();
    }

    @Override
    public HeapInterface<T> getBackingHeap() {
        // DO NOT MODIFY THIS METHOD!
        return backingHeap;
    }

}