/**
 * CircularLinkedList implementation
 * @author Laura Cox
 * @version 1.0
 */
public class CircularLinkedList<T> implements LinkedListInterface<T> {
    private Node<T> head;
    private Node<T> tail;
    private int sizeList = 0;
 
    
    @Override
    public void addAtIndex(int index, T data) {
    	
    	   if ((index < 0) || (index > size())) {
        	   throw new IndexOutOfBoundsException();
            } 
    	   Node<T> node = new Node<T>(data);
   	       sizeList++;
        	  if (head == null) {  
        	    
        	    if (index == 0) {
        		    head = node;
        	    } else if (head != null) {
        	    	if (index == size()) {
        	   
        		    tail.setNext(node);
        		    tail = node;
        		    node.setNext(head.getNext());
        	    } else if (size() >= 1) {
        		   Node<T> prev = iterateThrough(index - 1);
        		   Node<T> curr = iterateThrough(index);
        		   
        		   prev.setNext(node);
        		   node.setNext(curr);
        		   }
        	    }
        	    
            }
    }
        	  
        	  
            
        
    

    @Override
    public T get(int index) {
    	T dataNode = null;
    	if ((index < 0) || (index >= size())) {
    		throw new IndexOutOfBoundsException();
    	} 
    	   	
    	if (index == 0) {
    		dataNode = head.getNext().getData();
    		
    	} else if (index == (size() - 1)) {
    		dataNode = tail.getData();
    		
    	} else {
    	    Node<T> node = iterateThrough(index);
    	    dataNode = node.getData();
    		}
    	return dataNode;
    	}
		
        		
    

    @Override
    public T removeAtIndex(int index) {
    	T t = null;
    	if ((index < 0) || (index > size())) {
    		throw new IndexOutOfBoundsException();
    	} 
    	if (head != null) {
    		sizeList--;
    	    if (index == 0) {
    		    removeFromFront();
    		    t = head.getNext().getData();
    	    } else if (size() >= 1) {
    		Node<T> curr = iterateThrough(index);
    		Node<T> prev = iterateThrough(index - 1);
    		prev.setNext(curr.getNext());
    		t =  curr.getData();
    		}
    	} 
    	
    	return t;
        

    }

    @Override
    public void addToFront(T t) {
    	head = new Node<T>(t, head);
    	if (tail == null) {
    	    tail = head;
    	} 
    	tail.setNext(head);
        sizeList++;
    }

    @Override
    public void addToBack(T t) {
       Node<T> node = new Node<T>(t, head);
       if (head == null) {
    	   head = node;
    	   node.setNext(head);
       }
       tail = node;
       sizeList++;
    }

    @Override
    public T removeFromFront() {
    	T t = null;
    
    if (sizeList > 0) {
    if (head == tail) {
    	    head = null;
    	    tail.setNext(null);
    	    sizeList--;
    	    t = head.getNext().getData();
        } else {
            head = head.getNext().getNext();
            tail.setNext(head.getNext().getNext());
            sizeList--;
        	t = head.getNext().getData();
        }
    }
    return t;
    }
    	

    @Override
    public T removeFromBack() {
    	T t = null;
    	
    	if (head != null) {
    		t = tail.getData();
    		if (size() >= 1) {
        	Node<T> curr = iterateThrough(size() - 1);
        	tail = curr;
        	head = curr;
        	
        	} else if (size() == 0) {
        		head = null;
        		tail.setNext(null);
        	}
    	sizeList--;
       	
        } 
    	 return t;
    }
    
    

    @SuppressWarnings("unchecked")
	@Override
    public T[] toList() {
    	T[] list = (T[]) (new Object[size()]);
    	
    	if (head == null) {
    		return null;
    	} else {
    		
    		for (int i = 0; i < size(); i++) {
    			list[i] = iterateThrough(i).getData();
    		}
    	}
        return list;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public int size() {
        return sizeList;
    }
    /**
    *Simple method to traverse through the linked list
    *
    *
    * @param index [uses any given index from other public methods]
    * @return node [returns the current node depending on position or index]
    *
    */

    private Node<T> iterateThrough(int index) {
 
    	if (head != null) {
    	Node<T> node = head.getNext();
    	for (int i = 0; i < size(); i++) {
    		if (i == index) {
    			return node;
    		} else {
    		node = node.getNext();
    		}
    	}
        }
    	return null;
    }
    @Override
    public void clear() {
        sizeList = 0;
        head = null;
        tail = null;
    }

    /**
     * Reference to the head node of the linked list.
     * Normally, you would not do this, but we need it
     * for grading your work.
     *
     * @return Node representing the head of the linked list
     */
    public Node<T> getHead() {
        return head;
    }

    /**
     * Reference to the tail node of the linked list.
     * Normally, you would not do this, but we need it
     * for grading your work.
     *
     * @return Node representing the tail of the linked list
     */
    public Node<T> getTail() {
        return tail;
    }

    /**
     * This method is for your testing purposes.
     * You may choose to implement it if you wish.
     */
    @Override
    public String toString() {
        return "";
    }
    
    public static void main(String[] args) {
    	
    }
}

