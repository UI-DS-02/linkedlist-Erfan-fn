//--------------DoublyLinkedList Implementation-----------------------------------

class DoublyLinkedList<E> {
    private Node<E> head;
    private Node<E> tail;
    private int size = 0;

    static class Node<E> {

        private E element;
        private Node<E> next;
        private Node<E> previous;

        public Node(E element) {
            this.element = element;
            this.next = null;
        }

        public E getElement() {
            return element;
        }

        public void setElement(E element) {
            this.element = element;
        }

        public Node<E> getNext() {
            return next;
        }

        public void setNext(Node<E> next) {
            this.next = next;
        }

        public Node<E> getPrevious() {
            return previous;
        }

        public void setPrevious(Node<E> previous) {
            this.previous = previous;
        }
    }

    public int Size() {
        return size;
    }

    public String isEmpty() {
        if (this.size==0){return "Yes";}
        else
            return "No";
    }

    public E first() {
        return this.head.element;
    }

    public E last() {
        return this.tail.element;
    }
