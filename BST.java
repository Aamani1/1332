import java.util.Collection;
import java.util.ArrayList;
import java.util.Queue;
import java.util.List;
import java.util.LinkedList;
import java.util.NoSuchElementException;
/**
 * Your implementation of a binary search tree.
 *
 * @author Amani Konduru
 * @version 1.0
 */
public class BST<T extends Comparable<? super T>> implements BSTInterface<T> {
    // DO NOT ADD OR MODIFY INSTANCE VARIABLES.
    private BSTNode<T> root;
    private int size;

    /**
     * A no-argument concurr.getData()uctor that should initialize an empty BST.
     * DO NOT IMPLEMENT THIS CONcurr.getData()UCTOR!
     */
    public BST() {
        root = null;
        size = 0;
    }

    /**
     * Initializes the BST with the data in the Collection. The data
     * should be added in the same order it is in the Collection.
     *
     * @param data the data to add to the tree
     * @throws IllegalArgumentException if data or any element in data is null
     */
    public BST(Collection<T> data) {
        if (data == null) {
            throw new IllegalArgumentException("Data is null and "
                    + "we don't want a null element");
        }
        for (T i : data) {
            if (i == null) {
                throw new IllegalArgumentException("Data is null and "
                        + "we don't want a null element");
            } else {
                add(i);
            }
        }
    }

    @Override
    public void add(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data is null and "
                    + "we don't want a null element");
        }
        root = addhelp(root, data);
    }

    /**
     * uses recursive calls that checks to help determines
     * where we need to add the data that is passed
     * @param curr is the node that is traversing through the BST
     * @param data is the element that we are trying to add to the tree
     * @return curr is the node where we need to add
     */
    private BSTNode<T> addhelp(BSTNode<T> curr, T data) {
        if (curr == null) {
            size++;
            return new BSTNode<>(data);
        }
        // setting the crr's left to reccursive call
        if (data.compareTo(curr.getData()) < 0) {
            // where is curr curr's data
            curr.setLeft(addhelp(curr.getLeft(), data));
        } else if (data.compareTo(curr.getData()) > 0) {
            // where is curr curr's data
            curr.setRight(addhelp(curr.getRight(), data));
        }
        return curr;
    }


    @Override
    public T remove(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data is null and "
                    + "we don't want a null element");
        }
        BSTNode<T> dummy = new BSTNode<>(null);
        root = removehelp(root, data, dummy);
        size--;
        return dummy.getData();
    }

    /**
     * uses recursive calls that checks to help determines
     * how to remove the data that is passed
     * NoSuchElementException: handles if the current node is null
     * @param curr is the node that is traversing through the BST
     * @param data is the element that we are trying to remove from the tree
     * @param dummy is just a temp node that is used to store a node
     * @return curr is the node where we need to remove
     */
    private BSTNode<T> removehelp(BSTNode<T> curr, T data, BSTNode<T> dummy) {
        if (curr == null) {
            throw new NoSuchElementException("there are no elements "
                    + "in the stack, the stack is empty");
        }
        if (curr.getData().equals(data)) {
            dummy.setData(curr.getData());
            //0 children
            if (curr.getRight() == null && curr.getLeft() == null) {
                return null;
            }
            //right child
            if (curr.getRight() != null && curr.getLeft() == null) {
                return curr.getRight();
            }
            //left child
            if (curr.getLeft() != null && curr.getRight() == null) {
                return curr.getLeft();
            } else {
                //2 children
                BSTNode<T> prddum = new BSTNode<>(null);
                curr.setLeft(predRem(curr.getLeft(), prddum));
                curr.setData(prddum.getData());
                return curr;
            }
        }
        if (curr.getData().compareTo(data) < 0) {
            curr.setRight(removehelp(curr.getRight(), data, dummy));
        } else {
            curr.setLeft(removehelp(curr.getLeft(), data, dummy));
        }
        return curr;
    }

    /**
     * predRem handles the predecessor case and returns the predecessor
     * @param curr is the node that is traversing through the BST
     * @param prddum is just a temp node that is used to store a node
     * @return returns the predecessor
     */
    private BSTNode<T> predRem(BSTNode<T> curr, BSTNode<T> prddum) {
        //
        if (curr.getRight() != null) {
            curr.setRight(predRem(curr.getRight(), prddum));
            return curr;
            //else found the pred
        } else {
            prddum.setData(curr.getData());
            return curr.getLeft();
        }
    }

    @Override
    public T get(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data is null and "
                    + "we don't want a null element");
        } else {
            return gethelp(root, data);
        }
    }

    /**
     * uses recursive calls that helps us get the data
     * NoSuchElementException: handles if the current node is null
     * @param curr is the node that is traversing through the BST
     * @param data is the element that we are trying to get from the tree
     * @return the data we need to get from the tree
     */
    private T gethelp(BSTNode<T> curr, T data) {
        if (curr == null) {
            throw new NoSuchElementException("there are no elements "
                    + "in the stack, the stack is empty");
        }
        if (curr.getData().equals(data)) {
            return curr.getData();
        }
        if (data.compareTo(curr.getData()) < 0) { // where is curr curr's data
            return gethelp(curr.getLeft(), data);
        } else {
            // where is curr curr's data
            return gethelp(curr.getRight(), data);
        }
    }

    @Override
    public boolean contains(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data is null and "
                    + "we don't want a null element");
        }
        return contHelper(root, data);
    }

    /**
     * uses recursive calls that checks to see the element
     * that is passed in is in the tree or not
     * how to remove the data that is passed
     * @param curr is the node that is traversing through the BST
     * @param data is the element that we are trying to remove from the tree
     * @return true if it's in the tree else it returns false
     */
    private boolean contHelper(BSTNode<T> curr, T data) {
        if (curr == null) {
            return false;
        }
        if (curr.getData().equals(data)) {
            return true;
        }
        if (data.compareTo(curr.getData()) < 0) { // where is curr curr's data
            return contHelper(curr.getLeft(), data);
        } else {
            // where is curr curr's data
            return contHelper(curr.getRight(), data);
        }
    }

    @Override
    public List<T> preorder() {
        List<T> pre = new ArrayList<>();
        preorderhelp(root, pre);
        return pre;
    }

    /**
     * uses recursive calls that checks to help determines
     * the pre order of the list
     * @param curr is the node that is traversing through the BST
     * @param pre is an empty list that is being passed it
     */
    private void preorderhelp(BSTNode<T> curr, List<T> pre) {
        if (curr != null) {
            pre.add(curr.getData());
            preorderhelp(curr.getLeft(), pre);
            preorderhelp(curr.getRight(), pre);
        }
    }

    @Override
    public List<T> postorder() {
        List<T> post = new ArrayList<>();
        postorderhelp(root, post);
        return post;
    }

    /**
     * uses recursive calls that checks to help determines
     * the post order of the list
     * @param curr is the node that is traversing through the BST
     * @param post is an empty list that is being passed it
     */
    private void postorderhelp(BSTNode<T> curr, List<T> post) {
        if (curr != null) {
            postorderhelp(curr.getLeft(), post);
            postorderhelp(curr.getRight(), post);
            post.add(curr.getData());
        }
    }

    @Override
    public List<T> inorder() {
        List<T> in = new ArrayList<>();
        inorderhelp(root, in);
        return in;
    }

    /**
     * uses recursive calls that checks to help determines
     * the in-order of the list
     * @param curr is the node that is traversing through the BST
     * @param in is an empty list that is being passed it
     */
    private void inorderhelp(BSTNode<T> curr, List<T> in) {
        if (curr != null) {
            inorderhelp(curr.getLeft(), in);
            in.add(curr.getData());
            inorderhelp(curr.getRight(), in);
        }
    }

    @Override
    public List<T> levelorder() {
        List<T> levelList = new ArrayList<>();
        Queue<BSTNode<T>> nodeQueue = new LinkedList();
        if (root == null) {
            return levelList;
        }
        nodeQueue.add(root);
        BSTNode<T> str;
        while (!nodeQueue.isEmpty()) {
            str = nodeQueue.remove();
            levelList.add(str.getData());
            if (str.getLeft() != null) {
                nodeQueue.add(str.getLeft());
            }
            if (str.getRight() != null) {
                nodeQueue.add(str.getRight());
            }
        }
        return levelList;
    }

    @Override
    public int distanceBetween(T data1, T data2) {
        if (data1 == null || data2 == null) {
            throw new IllegalArgumentException("Data is null and "
                    + "we don't want a null element");
        }
        return dca(root, data1, data2);

    }

    /**
     * uses recursive calls that helps us find the deepest common ancestor
     * @param curr is the node that is traversing through the BST
     * @param data1 is the element that is being
     *        passed in to find the distance to
     * @param data2 is the element that is being
     *        passed in to find the distance to
     * @return the over all distance
     */
    private int dca(BSTNode<T> curr, T data1, T data2) {
        //T curr.getData() = curr.getData();
        // to right
        if (curr == null) {
            throw new NoSuchElementException("there are no elements "
                    + "in the stack, the stack is empty");
        }
        if (curr.getData().equals(data1) && curr.getData().equals(data2)) {
            return 0;
        }
        if (curr.getData().compareTo(data1) < 0
                && curr.getData().compareTo(data2) < 0) {
            return dca(curr.getRight(), data1, data2);
        }
        // to left
        if (curr.getData().compareTo(data1) > 0
                && curr.getData().compareTo(data2) > 0) {
            return dca(curr.getLeft(), data1, data2);
        } else {
            return dishelp(curr, data1) + dishelp(curr, data2);
        }

    }

    /**
     * uses recursive calls that helps us get the distance
     * NoSuchElementException: handles if the current node is null
     * @param curr is the node that is traversing through the BST
     * @param data1 is the element that is being
     *        passed in to find the distance to
     * @return the data we need to get from the tree
     */
    private int dishelp(BSTNode<T> curr, T data1) {
        if (curr == null) {
            throw new NoSuchElementException("there are no elements "
                    + "in the stack, the stack is empty");
        }
        if (data1.compareTo(curr.getData()) < 0) {
            return dishelp(curr.getLeft(), data1) + 1;
        } else if (data1.compareTo(curr.getData()) > 0) {
            return dishelp(curr.getRight(), data1) + 1;
        }
        return 0;
    }

    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    @Override
    public int height() {
        if (root == null) {
            return -1;
        }
        return helpHeight(root);
    }

    /**
     * uses recursive calls that checks to help determine the height
     * @param curr is the node that is traversing through the BST
     * @return returns the height of the tree
     */
    private int helpHeight(BSTNode<T> curr) {
        if (curr == null) {
            return -1;
        }
        return Math.max(helpHeight(curr.getLeft()),
                helpHeight(curr.getRight())) + 1;
    }

    @Override
    public int size() {
        // DO NOT MODIFY THIS METHOD
        return size;
    }

    @Override
    public BSTNode<T> getRoot() {
        // DO NOT MODIFY THIS METHOD!
        return root;
    }
}
