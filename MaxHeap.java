import java.util.ArrayList;
import java.util.NoSuchElementException;
/**
 * Your implementation of a max heap.
 *
 * @author Amani Konduru
 * @version 1.0
 */
public class MaxHeap<T extends Comparable<? super T>>
    implements HeapInterface<T> {

    private T[] backingArray;
    private int size;
    // Do not add any more instance variables. Do not change the declaration
    // of the instance variables above.

    /**
     * Creates a Heap with an initial capacity of {@code INITIAL_CAPACITY}
     * for the backing array.
     *
     * Use the constant field in the interface. Do not use magic numbers!
     */
    public MaxHeap() {
        backingArray = (T[]) new Comparable[INITIAL_CAPACITY];
    }

    /**
     * Creates a properly ordered heap from a set of initial values.
     *
     * You must use the Build Heap algorithm that was taught in lecture! Simply
     * adding the data one by one using the add method will not get any credit.
     *
     * The initial array before the Build Heap algorithm takes place should
     * contain the data in the same order as it appears in the ArrayList.
     *
     * The {@code backingArray} should have capacity 2n + 1 where n is the
     * number of data in the passed in ArrayList (not INITIAL_CAPACITY from
     * the interface). Index 0 should remain empty, indices 1 to n should
     * contain the data in proper order, and the rest of the indices should
     * be empty.
     *
     * @param data a list of data to initialize the heap with
     * @throws IllegalArgumentException if data or any element in data is null
     */
    //@SuppressWarnings("unchecked")
    public MaxHeap(ArrayList<T> data) {
        if (data == null) {
            throw new IllegalArgumentException("Data is null");
        } else {
            backingArray = (T[]) new Comparable[(2 * data.size()) + 1];
            for (int i = 0; i < data.size(); i++) {
                if (data.get(i) == null) {
                    throw new IllegalArgumentException("Data is null");
                }
                backingArray[i + 1] = data.get(i);
                size++;
            }
            for (int i = (size / 2); i >= 1; i--) {
                downHeap(i);
            }
        }

    }

    @Override
    public void add(T item) {
        if (item == null) {
            throw new IllegalArgumentException("Item is null");
        }
        if (size == backingArray.length - 1) {
            T[] tempArray = (T[]) new Comparable[backingArray.length * 2];
            for (int i = 0; i < backingArray.length; i++) {
                tempArray[i] = backingArray[i];
            }
            backingArray = tempArray;
        }
        backingArray[size + 1] = item;
        size++;
        upHeap();
    }

    /**
     * This method is heapifyUp/Upheap helps us add to the heap
     */
    private void upHeap() {
        int curr = size;
        boolean swapped = true;
        while (curr > 1 && swapped) {
            if (backingArray[curr].compareTo(backingArray[curr / 2]) > 0) {
                T temp = backingArray[curr];
                backingArray[curr] = backingArray[curr / 2];
                backingArray[curr / 2] = temp;
                curr = curr / 2;
            } else {
                swapped = false;
            }
        }
    }
    @Override
    public T remove() {
        if (size == 0) {
            throw new NoSuchElementException("there are no elements "
                    + "in the stack, the stack is empty");
        }
        T temp = backingArray[1];
        backingArray[1] = backingArray[size];
        backingArray[size] = null;
        size--;
        downHeap(1);
        return temp;
    }

    /**
     * This method is heapifyDown/Downheap helps us remove from the heap
     * @param index passed in an index so we can keep track
     *              of where we are moving in the heap
     */
    private void downHeap(int index) {
        // backingarray at 1 is the root
        int curr = index;
        //int left = 2 * curr;
        boolean swapped = true;
        // left child
        //checks if it has children

        while ((2 * curr) <= size
                && backingArray[2 * curr] != null && swapped) {
            //there is a right child so 2 children
            if (((2 * curr) + 1) <= size) {
                // if the left child > right child
                if (backingArray[curr * 2].compareTo(
                        backingArray[(curr * 2) + 1]) > 0) {
                    //and left child is greater then parent
                    if (backingArray[curr * 2].compareTo(
                        backingArray[curr]) > 0) {
                        //swap left with the parent
                        T temp = backingArray[curr];
                        backingArray[curr] = backingArray[curr * 2];
                        backingArray[curr * 2] = temp;
                        curr = curr * 2;
                    } else {
                        swapped = false;
                    }
                } else {
                    //right > left
                    if (backingArray[(curr * 2) + 1].compareTo(
                            backingArray[curr * 2]) > 0) {
                        //and right child is greater then parent
                        if (backingArray[(curr * 2) + 1].compareTo(
                                backingArray[curr]) > 0) {
                            //swap right with the parent
                            T temp = backingArray[curr];
                            backingArray[curr] = backingArray[(curr * 2) + 1];
                            backingArray[(curr * 2) + 1] = temp;
                            curr = (curr * 2) + 1;
                        } else {
                            swapped = false;
                        }
                    }
                }
            } else {
                if (backingArray[curr * 2].compareTo(backingArray[curr]) > 0) {
                    //swap left with the parent
                    T temp = backingArray[curr];
                    backingArray[curr] = backingArray[curr * 2];
                    backingArray[curr * 2] = temp;
                    curr = curr * 2;
                } else {
                    swapped = false;
                }
            }
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

    @Override
    public void clear() {
        backingArray = (T[]) new Comparable[INITIAL_CAPACITY];
        size = 0;
    }

    @Override
    public Comparable[] getBackingArray() {
        // DO NOT MODIFY THIS METHOD!
        return backingArray;
    }

}
