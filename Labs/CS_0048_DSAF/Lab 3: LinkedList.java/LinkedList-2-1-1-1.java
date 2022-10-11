import java.util.Arrays;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class LinkedList<T> implements List<T> {
    private int size;
    private Node head;
    private Node tail;

    @Override
    public boolean add(int index, T obj) {
      if (index >= size) {
            return add(obj);
      } else if (index == 0) {
            Node n1 = new Node(obj);
            n1.setNext(head);
            head.setPrevious(n1);
            head = n1;
            size++;
            return true;
      } else {
          Node n = getNodeAt(index);
          Node nP = n.getPrevious();
          Node nN = new Node(obj);

          nP.next = nN;
          nN.previous = nP;
          nN.next = n;
          n.previous = nN;
          size++;
          return true;
      }
    }

    @Override
    public boolean add(T obj) {
        Node n = new Node(obj);
        if (size == 0) {
            head = n;
            tail = n;
        } else {
            tail.next = n;
            n.previous = tail;
            tail = n;
        }
        size++;
        return true;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index > size) throw new IndexOutOfBoundsException();

       return getNodeAt(index).getValue();
    }

    @Override
    public int indexOf(T obj) {
        Node cur = head;
        int i = 0 ;
        while (cur != null) {

            if (cur.getValue().equals(obj)) {
                return i;
            }
            i++;
            cur = cur.getNext();
        }
        return -1;
    }

    @Override
    public int lastIndexOf(T obj) {
        Node cur = head;
        int index = -1;
        int i = 0 ;

        while (cur != null) {

            if (cur.getValue().equals(obj)) {
                index = i;
            }
            i++;
            cur = cur.getNext();
        }
        return index;
    }

    @Override
    public ListIterator<T> listIterator() {
       return new ListIterator<T>() {
        static final int PREVIOUS = 1;
        static final int NEXT = 2;
        int lastCalled = 0;
        //LastCalled indicates if next or previous have been called.
        int nextIndex = 0;


            @Override
           public boolean hasNext() {
               return nextIndex < size;
           }

           @Override
           public T next() {
               if (!hasNext()) {
                   throw new NoSuchElementException();
               }
               lastCalled = NEXT;
               return getNodeAt(nextIndex++).getValue();
           }

           @Override
           public boolean hasPrevious() {
               return nextIndex > 0;
           }

           @Override
           public T previous() {
               if (!hasPrevious()) {
                   throw new NoSuchElementException();
               }
               lastCalled = PREVIOUS;
               return getNodeAt(--nextIndex).getValue();
           }

           @Override
           public int nextIndex() {
               return nextIndex;
           }

           @Override
           public int previousIndex() {
               return nextIndex - 1;
           }

           @Override
           public void remove() {
            if(lastCalled == PREVIOUS) { //Remove current postiion
                LinkedList.this.remove(nextIndex);
            } else if (lastCalled == NEXT) { //Remove last position
                LinkedList.this.remove(--nextIndex);
            } else {
                throw new IllegalStateException("removed called without next or previous");
            }
            lastCalled = 0; //Reset last called to prevent repeated calls.

           }

           @Override
           public void set(T e) {
            if (lastCalled == PREVIOUS) {
                getNodeAt(nextIndex).setValue(e);
            } else if (lastCalled == NEXT) {
                getNodeAt(nextIndex-1).setValue(e);
            } else {
                throw new IllegalStateException("set called without next or previous");
            }
           }

           @Override
           public void add(T e) {
                LinkedList.this.add(nextIndex++, e);
           }

       };
    }

    @Override
    public T remove(int index) {
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException();
        T val = null;

        if (isEmpty()) {
            return val;
        }


        if (index == 0) {
            /**
             * No prev
             */
            val = head.getValue();
            head = head.getNext();
            head.setPrevious(null);
            size--;
            return val;
        } else if (index == size-1) {
            /**
             * You're @ tail
             */
            val = tail.getValue();
            tail = tail.previous;
            size--;
            return val;
        } else {
            /**
             * Somewhere in the middle
             */
            Node n = getNodeAt(index);
            Node nP = n.previous;
            val = n.value;
            Node nN = n.next;

            nP.next = nN;
            nN.previous = nP;
            size--;
            return val;
        }
    }

    @Override
    public T set(int index, T obj) {
        if (index < 0 && index > size) throw new IndexOutOfBoundsException();

        Node node = getNodeAt(index);
        T val = getNodeAt(index).getValue();

        node.setValue(obj);

        return val;
    }

    @Override
    public boolean contains(T obj) {
        return indexOf(obj) >= 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }

    @Override
    public boolean isEmpty() {
        return head == null;
    }

    @Override
    public Object[] toArray() {
        Object[] a = new Object[size];
        Node cur = head;
        int i=0;
        while (cur != null) {
            a[i++] = cur.getValue();
            cur = cur.getNext();
        }
        return a;
    }

    @SuppressWarnings("unchecked")
    @Override
    public T[] toArray(T[] a) {
        return (T[])Arrays.copyOf(toArray(), size, a.getClass());
    }

    private Node getNodeAt (int index) {
        if (index == 0) return head;
        if (index == size-1) return tail;
        double midpoint = Math.ceil(size / 2);
        if (index > midpoint) {
            Node cur = tail;
            for (int i = size-1; i > index; i--) {
                cur = cur.previous;
            }
            return cur;
        } else {
            Node cur = head;
            for (int i = 0; i < index; i++) {
                cur = cur.next;
            }
            return cur;
        }
    }


    private class Node {
        private T value;
        private Node previous;
        private Node next;

        public Node(T value) {
            this.value = value;
        }

        public T getValue() {
            return value;
        }

        public void setValue(T value) {
            this.value = value;
        }

        public Node getPrevious() {
            return previous;
        }

        public Node getNext() {
            return next;
        }

        public void setPrevious(Node previous) {
            this.previous = previous;
        }

        public void setNext(Node next) {
            this.next = next;
        }
    }
}
