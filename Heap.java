/**
 *
 * Class that creates a minimum heap
 *
 * @author Laura Cox
 *
 * @version 1.0
 */

public class Heap<T extends Comparable<? super T>> implements HeapInterface<T>,
       Gradable<T> {
    @SuppressWarnings("unchecked")
    private T[] heapArray = (T[]) (new Comparable[10]);
    private int size = 0;

    @Override
    public void add(T item) {
        heapArray[0] = null;
        if (item == null) {
            throw new IllegalArgumentException();
        }
        if ((size() + 1) == heapArray.length) {
            heapArray = reSize();
        }
        size++;
        heapArray[size()] = item;
        heapify();

    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public T peek() {
        return heapArray[1];
    }

    @Override
    public T remove() {
        T data = null;
        if (size() > 0) {
            data = heapArray[1];
            heapArray[1] = heapArray[size()];
            heapArray[size()] = null;
            size--;
        }
        heapify();

        return data;

    }

    @Override
    public int size() {
        return size;
    }
/**
 * private method used to change the array size when
 * the number of elements exceeds original array's capacity
 *
 * @return T[] array that is the original array copied into one double the size
 */
    private T[] reSize() {
        @SuppressWarnings("unchecked")
        T[] duplicate = (T[]) (new Comparable[(heapArray.length) * 2]);
        if (heapArray.length == (size() + 1)) {
            for (int i = 1; i <= size(); i++) {
                duplicate[i] = heapArray[i];
            }
        }

        return duplicate;
    }
/**
 *
 * private method used to "heapify" the tree using the notion
 * that the tree's root node must be the minimum number
 * and subsquent nodes follow suit
 */
    private void heapify() {
        if (size() > 2) {
            for (int i = 1; i < size(); i++) {
                int left = 2 * i;
                int right = (2 * i) + 1;
                int parent = i;
                int small = 0;

                if (left <= (size() - 1)) {
                    if (heapArray[left].compareTo(heapArray[right]) < 0) {
                        small = left;
                    } else {
                        small = right;
                    }

                    if (heapArray[small].compareTo(heapArray[parent]) < 0) {
                        T dummySmall = heapArray[small];
                        heapArray[small] = heapArray[parent];
                        heapArray[parent] = dummySmall;
                    }
                }
            }

        } else if (size() == 2) {
            if (heapArray[2].compareTo(heapArray[1]) < 0) {
                T dummy2 = heapArray[2];
                heapArray[2] = heapArray[1];
                heapArray[1] = dummy2;
            }
        }
    }
    @Override
    public T[] toArray() {
        return heapArray;
    }

}
