/**
 * Your implementation of a circular singly linked list.
 *
 * @author Amani Konduru
 * @version 1.0
 */
public class SinglyLinkedList<T> implements LinkedListInterface<T> {
    // Do not add new instance variables or modify existing ones.
    private LinkedListNode<T> head;
    private int size;

    @Override
    public void addAtIndex(T data, int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("index is negative or"
                    + " greater than size");
        }
        if (data == null) {
            throw new IllegalArgumentException("Data is null and "
                    + "we don't want a null element");
        }
        if (index == 0) {
            addToFront(data);
            return;
        }

        if (size == 0) {
            head = new LinkedListNode<>(data, head);
            size++;
            return;
        }

        LinkedListNode<T> curr = head;
        for (int i = 0; i < index - 1; i++) {
            curr = curr.getNext();
        }
        curr.setNext(new LinkedListNode<>(data, curr.getNext()));
        size++;
    }

    @Override
    public void addToFront(T data) {

        if (data == null) {
            throw new IllegalArgumentException("Data is null and "
                    + "we don't want a null element");
        }

        if (size == 0) {
            //head = new LinkedListNode<>(data,head);
            head = new LinkedListNode<>(data);
            head.setNext(head);
            size++;
            //return;


        } else {
            LinkedListNode<T> newNode = new LinkedListNode<>(
                    head.getData(), head.getNext());
            head.setNext(newNode);
            head.setData(data);
            size++;
        }
    }

    @Override
    public void addToBack(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data is null and "
                    + "we don't want a null element");
        }

        if (size == 0) {
            //head = new LinkedListNode<>(data,head);
            head = new LinkedListNode<>(data);
            head.setNext(head);
            size++;
            //return;
        } else {
            LinkedListNode<T> newNode = new LinkedListNode<>(
                    head.getData(), head.getNext());
            head.setNext(newNode);
            head.setData(data);
            head = head.getNext();
            size++;
        }
    }

    @Override
    public T removeAtIndex(int index) {

        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("index is negative or"
                    + " greater than size");
        }
        if (index == 0) {
            return removeFromFront();
        } else {
            LinkedListNode<T> curr = head;
            for (int i = 0; i < index - 1; i++) {
                curr = curr.getNext();
            }
            T value = curr.getNext().getData();
            curr.setNext(curr.getNext().getNext());
            size--;
            return value;
        }
    }

    @Override
    public T removeFromFront() {
        if (size == 0) {
            return null;
        }
        if (size == 1) {
            T deletedElement = head.getData();
            head = null;
            size--;
            return deletedElement;
        } else {

            T deletedElement = head.getData();
            head.setData(head.getNext().getData());
            head.setNext(head.getNext().getNext());
            size--;
            return deletedElement;
        }
    }

    @Override
    public T removeFromBack() {
        if (size == 0) {
            return null;
        }
        return removeAtIndex(size - 1);
    }

    @Override
    public T removeLastOccurrence(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data is null "
                    + "and we don't want a null element");
        }
        LinkedListNode<T> curr = head;
        LinkedListNode<T> pre = null;
        if (size == 0) {
            return null;
        }

        if (size == 1) {
            if (head.getData().equals(data)) {
                return removeFromFront();
            } else {
                return null;
            }
        } else {
            for (int i = 1; i < size; i++) {
                if (curr.getNext().getData().equals(data)) {
                    pre = curr;
                }
                curr = curr.getNext();
            }
            if (pre != null) {
                T removeddata = pre.getNext().getData();
                pre.setNext(pre.getNext().getNext());
                size--;
                return removeddata;
            } else {
                if (head.getData().equals(data)) {
                    return removeFromFront();
                } else {
                    return null;
                }
            }
        }
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("index is negative or"
                    + " greater than size");
        }
        LinkedListNode<T> curr = head;
        for (int i = 0; i < index; i++) {
            curr = curr.getNext();
        }
        return curr.getData();
    }

    @Override
    public Object[] toArray() {
        //can use get here
        Object[] toArr = new Object[size];
        LinkedListNode<T> curr = head;
        for (int i = 0; i < size; i++) {
            toArr[i] = curr.getData();
            curr = curr.getNext();
        }
        return toArr;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        head = null;
        size = 0;
    }

    @Override
    public int size() {
        // DO NOT MODIFY!
        return size;
    }

    @Override
    public LinkedListNode<T> getHead() {
        // DO NOT MODIFY!
        return head;
    }
}
