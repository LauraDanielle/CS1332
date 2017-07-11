import java.util.ArrayList;
import java.util.List;
/**
 *
 * Implementation of BSTInterface
 * @author Laura Cox
 * @version 1.0
 */
public class BST<T extends Comparable<T>> implements BSTInterface<T> {
    private int size;
    private ArrayList<T> traversalArray;
    private Node<T> root;

    @Override
    public void add(T data) {
        Node<T> previous = null;
        Node<T> current = null;
        Node<T> dummyNode = new Node<T>(data);

        if (root == null) {
            root = dummyNode;
        } else {
            current = root;
            while (current != null) {
                previous = current;
                if (data.compareTo(current.getData()) < 0) {
                    current = current.getLeft();
                } else if (data.compareTo(current.getData()) > 0) {
                    current = current.getRight();
                }
            }
            if (data.compareTo(previous.getData()) < 0) {
                previous.setLeft(dummyNode);
            } else if (data.compareTo(previous.getData()) > 0) {
                previous.setRight(dummyNode);
            }
        }
        size++;
    }


    @Override
    public T remove(T data) {
        Node<T> current = null;
        Node<T> previous = null;
        Node<T> dummyCurrent = null;

        if (root == null) {
            return null;
        } else {
            size--;
            current = root;
            while (current != null) {
                previous = current;
                dummyCurrent = current;
                if (data.compareTo(current.getData()) < 0) {
                    current = current.getLeft();
                } else if (data.compareTo(current.getData()) > 0) {
                    current = current.getRight();
                }

                if (previous.getLeft() == null) {
                    previous.setData(current.getRight().getData());
                    return dummyCurrent.getData();
                } else if ((current.getLeft() != null)
                    && (current.getRight() != null)) {
                    current = current.getLeft();
                    current.setLeft(null);
                } else {
                    if (current.getLeft() != null) {
                        current = current.getLeft();
                    } else {
                        current = current.getRight();
                    }
                }
            }

            return null;
        }
    }

    @Override
    public T get(T data) {
        boolean isHere = false;
        Node<T> current = root;
        T foundData = null;

        if (current == null) {
            return foundData;
        } else {
            while (isHere) {
                if (current != null) {
                    if (data.equals(current.getData())) {
                        isHere = true;
                        foundData  = current.getData();
                    } else {
                        if (data.compareTo(current.getData()) < 0) {
                            current = current.getLeft();
                        } else if (data.compareTo(current.getData()) > 0) {
                            current = current.getRight();
                        }
                    }
                }
            }
            return foundData;
        }
    }

    @Override
    public boolean contains(T data) {
        if (root == null) {
            return false;
        } else {
            return contains(data, root);
        }
    }
    /**
    *
    * helper function that recursively moves through the tree
    * depending on contents of data
    * @param [data] data given from public contains that you search for
    * @param [curr] current node you are testing
    * @return boolean stating whether data is in tree or not
    *
    */
    private boolean contains(T data, Node<T> curr) {
        if (curr == null) {
            return false;
        }
        if (data.compareTo(curr.getData()) < 0) {
            return contains(data, curr.getLeft());
        } else if (data.compareTo(curr.getData()) > 0) {
            return contains(data, curr.getRight());
        } else {
            return true;
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public List<T> preorder() {
        traversalArray = new ArrayList<T>(size());
        if (root != null) {
            preorder(root);
        }
        return traversalArray;
    }
    /**
     *
     * helper function that adds numbers to the array depending on preorder
     * algorithim
     * @param [curr] current node you are testing for the preorder traversal
     */
    private void preorder(Node<T> curr) {
        if (curr != null) {
            traversalArray.add(curr.getData());
            preorder(curr.getLeft());
            preorder(curr.getRight());
        }
    }

    @Override
    public List<T> postorder() {
        traversalArray = new ArrayList<T>(size());
        postorder(root);
        return traversalArray;
    }
    /**
     *  helper function that adds numbers to the array depending on postorder
     * algorithim
     * @param [curr] current node you are testing for the postorder traversal
     */
    private void postorder(Node<T> curr) {
        if (curr != null) {
            postorder(curr.getLeft());
            postorder(curr.getRight());
            traversalArray.add(curr.getData());
        }
    }
    @Override
    public List<T> inorder() {
        traversalArray = new ArrayList<T>(size());
        inorder(root);
        return traversalArray;
    }
    /**
     *  helper function that adds numbers to the array depending on inorder
     * algorithim
     * @param [curr] current node you are testing for the inorder traversal
     */
    private void inorder(Node<T> curr) {
        if (curr != null) {
            inorder(curr.getLeft());
            traversalArray.add(curr.getData());
            inorder(curr.getRight());
        }
    }

    @Override
    public List<T> levelorder() {
        traversalArray = new ArrayList<T>(size());
        levelorder(root);
        return traversalArray;
    }

    private void levelorder(Node<T> curr) {
        int i = 0;
        if (curr != null) {
            traversalArray.add(curr.getData());
            while (i < size()) {
                if (curr.getLeft() != null) {
                    traversalArray.add(curr.getLeft().getData());
                }
                if (curr.getRight() != null) {
                    traversalArray.add(curr.getRight().getData());
                }
                i++;
            }
        }
    }

    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    @Override
    public int height() {
        return height(root);
    }
    /**
     * Recursively finds height of the tree by going to deepest node in tree
     * and testing the max of left vs. right node
     *
     * @param [curr] current node you are looking at
     * @return height of tree as an int
     */
    private int height(Node<T> curr) {
        if (curr == null) {
            return -1;
        } else {
            return 1 + Math.max(height(curr.getLeft()),
                height(curr.getRight()));
        }

    }
    @Override
    public Node<T> getRoot() {
        return root;
    }
}