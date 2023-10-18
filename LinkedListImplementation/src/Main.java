import java.util.Objects;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        scanner.nextLine();
        DoublyLinkedList<Integer> linkedList = new DoublyLinkedList<Integer>();

        for (int i = 1; i <n; i++) {

            String order = scanner.nextLine();
            String result = linkedList.commandPerformer(linkedList, order);
            if (!result.equals("")){System.out.println(result);}

        }
    }
}


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

    public void addFirst(E element) {
        Node<E> newNode = new Node<>(element);

        if (this.size == 0) {
            head = newNode;
            this.head.previous = null;
            newNode.next = null;
            tail = newNode;
        } else {
            newNode.next = head;
            head.previous = newNode;
            head = newNode;
        }

        size++;
    }

    public void addLast(E element) {
        Node<E> newNode = new Node<>(element);
        this.tail.next = newNode;
        newNode.previous = this.tail;
        tail = newNode;
        size++;
    }

    public E removeFirst() {
        Node<E> temp;
        head = this.head.next;
        temp = head.previous;
        head.previous = null;
        size--;
        return (E) temp;
    }

    public E removeLast() {
        Node<E> temp = new Node<E>(null);
        tail = this.tail.previous;
        temp = tail.next;
        tail.next = null;
        size--;
        return (E) temp;
    }

    public void sort(DoublyLinkedList<Integer> n) {
        Node<Integer> traverser = n.head.next;

        int temp ;
        while (traverser != null) {
            Node<Integer> current = n.head.next;
            while (current != null) {

                if (current.element < current.previous.element) {
                    //swap elements
                    temp = current.previous.element;
                    current.previous.element = current.element;
                    current.element = temp;
                }
                current = current.next;
            }
            traverser = traverser.next;
        }
    }
    public void add(int index, E element) {
        int counter = -1;
        Node<E> current = this.head;
        Node<E> previous=this.head;

        while (current != null) {
            counter++;

            if (index == 0) {
                Node<E> newNode = new Node<>(element);

                newNode.next = head;
                newNode.previous=null;
                head.previous = newNode;
                head = newNode;
                size++;
                break;

            } else if (index == counter) {
                Node<E> newNode = new Node<>(element);
                previous.next = newNode;
                newNode.previous=previous;
                newNode.next = current;
                current.previous=newNode;
                size++;
                break;
            }

            previous = current;
            current = current.next;
        }

        if (index==size)
        {
            Node<E> newNode = new Node<>(element);
            tail.next=newNode;
            newNode.previous=tail;
            newNode.next=null;
            tail=newNode;
            size++;
        }
    }

    //------------------------------------------------------------------

    public E remove(int index) {
        int counter = -1;
        Node<E> current = this.head;
        Node<E> previous = this.head;
        Node<E> result = null;

        while (current != null) {
            counter++;

            if (index == 0) {
                result = head;
                this.head.next = this.head;
                head.previous = null;
                size--;
                break;
            } else if (index == counter) {

                result = current;
                previous.next = current.next;
                current = current.next;
                current.previous = previous;
                size--;
                break;
            }
            previous = current;
            current = current.next;
        }
        return result.element;
    }
    //--------------get the i-th element of the list

    public E get(int index) {
        int counter = -1;
        Node<E> current = this.head;
        Node<E> result = null;

        while (current != null) {
            counter++;
            if (index == counter) {

                result = current;
                break;
            }
            current = current.next;
        }

        return result.element;
    }

    //--------------
    public String commandPerformer(DoublyLinkedList<Integer> linkedList, String command) {
        String result = "";
        String[] array = command.split(" ");
        if (array[0].equals("size")) {
            result = String.valueOf(linkedList.Size());
        }
        if (array[0].equals("isEmpty")) {
            result = String.valueOf(linkedList.isEmpty());
        }
        if (array[0].equals("first")) {
            result = String.valueOf(linkedList.first());
        }
        if (array[0].equals("addFirst")) {
            linkedList.addFirst(Integer.parseInt(array[1]));
        }
        if (array[0].equals("addLast")) {
            linkedList.addLast(Integer.parseInt(array[1]));
        }
        if (array[0].equals("remove")) {
            result = String.valueOf(linkedList.remove(Integer.parseInt(array[1])));
        }
        if (array[0].equals("get")) {
            try {
                result = String.valueOf(linkedList.get(Integer.parseInt(array[1])));
            } catch (NullPointerException e) {
                result="";
            }
        }
        if (array[0].equals("add")) {
            linkedList.add(Integer.parseInt(array[1]), Integer.parseInt(array[2]));
        }
        if (array[0].equals("sort")) {
            linkedList.sort(linkedList);
        }
        if (array[0].equals("removeFirst")) {
            result = String.valueOf(linkedList.removeFirst());
        }
        if (array[0].equals("removeLast")) {
            result = String.valueOf(linkedList.removeLast());
        }
        if (array[0].equals("last")) {
            result = String.valueOf(linkedList.last());
        }
        if (array[0].equals("first")) {
            result = String.valueOf(linkedList.first());
        }


        return result;
    }

}
