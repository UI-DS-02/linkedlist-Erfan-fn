import java.util.LinkedList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        DoublyLinkedList<Integer> linkedList = new DoublyLinkedList<Integer>();
        CompareLinkedLists compareLinkedLists=new CompareLinkedLists();

        System.out.println(compareLinkedLists.compareAddFirst());
        System.out.println(compareLinkedLists.compareGetFirst());
        System.out.println(compareLinkedLists.compareRemoveFirst());
        System.out.println(compareLinkedLists.compareAddLast());
        System.out.println(compareLinkedLists.compareGetLast());
        System.out.println(compareLinkedLists.compareSort());
        System.out.println(compareLinkedLists.compareGet());
        System.out.println(compareLinkedLists.compareRemoveLast());
        System.out.println(compareLinkedLists.compareIsEmpty());
        System.out.println(compareLinkedLists.compareInsertMid());
        System.out.println(compareLinkedLists.compareSize());
        System.out.println(compareLinkedLists.compareRemoveI());

    }

    public static String commandPerformer(String command) {
        String result = "";
        LinkedList<Integer>test=new LinkedList<>();
        String[] array = command.split(" ");

        if (array[0].equals("addFirst")) {
            test.addFirst(Integer.parseInt(array[1]));
        }
        if (array[0].equals("size")) {
            result = String.valueOf(test.size());
        }
        if (array[0].equals("isEmpty")) {
            result = String.valueOf(test.isEmpty());
        }
        if (array[0].equals("addLast")) {
            test.addLast(Integer.parseInt(array[1]));
        }
        if (array[0].equals("remove")) {
            result = String.valueOf(test.remove(Integer.parseInt(array[1])));
        }
        if (array[0].equals("get")) {
            try {
                result = String.valueOf(test.get(Integer.parseInt(array[1])));
            } catch (NullPointerException e) {
                result="";
            }
        }
        if (array[0].equals("add")) {
            test.add(Integer.parseInt(array[1]), Integer.parseInt(array[2]));
        }
        if (array[0].equals("sort")) {
            test.sort(Integer::compareTo);
        }
        if (array[0].equals("removeFirst")) {
            result = String.valueOf(test.removeFirst());
        }
        if (array[0].equals("removeLast")) {
            result = String.valueOf(test.removeLast());
        }
        if (array[0].equals("last")) {
            result = String.valueOf(test.getLast());
        }
        if (array[0].equals("first")) {
            result = String.valueOf(test.getFirst());
        }
        return result;
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
        if (isEmpty().equals("Yes")){
            addFirst(element);
        }
        else
        {
            newNode.previous = this.tail;
            this.tail.next = newNode;
            tail = newNode;
            size++;
        }

    }

    public E removeFirst() {
        Node<E> temp;
        if (this.size>1)
        {
            temp = head;
            head = this.head.next;
            head.previous = null;
            size--;
        }
        else
        {
            temp=this.head;
            this.head.next=null;
            this.head=null;
            size--;
        }

        return temp.element;
    }

    public E removeLast() {

        Node<E> temp ;
        tail = this.tail.previous;
        temp = tail.next;
        tail.next = null;
        size--;
        return  temp.element;
    }

    public void sort(DoublyLinkedList<Integer> n) {

        Node<Integer> traverser = n.head;
        int temp ;
        if (this.size>1)
        {
            while (traverser.next != null) {
                Node<Integer> current = n.head;
                while (current.next != null) {

                    if (current.next.element < current.element) {
                        //swap elements
                        temp = current.element;
                        current.element = current.next.element;
                        current.next.element = temp;
                    }
                    current = current.next;
                }
                traverser = traverser.next;
            }
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
            addLast(element);
        }
    }

    //------------------------------------------------------------------

    public E remove(int index) {
        int counter = -1;
        Node<E> current = this.head;
        Node<E> previous = this.head;
        Node<E> result = new Node<>(get(index));

        while (current != null) {
            counter++;

            if (index == 0) {
                removeFirst();
                break;
            }else if (index ==size)
            {
                removeLast();
                break;
            }
            else if (index == counter) {

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

        if (index==0){
            return first();
        }
        if (index==this.size)
        {
            return last();
        }

        while (current.next != null) {
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

//comparator class that compare the functions between Java LinkedList and my Implemented LinkedList
 class CompareLinkedLists{
    private DoublyLinkedList<Integer>implementedLinkedList;
    private LinkedList<Integer>javaLinkedList;

     public CompareLinkedLists() {
         this.implementedLinkedList = new DoublyLinkedList<Integer>();
         this.javaLinkedList = new LinkedList<>();
     }

     //a function that compare the AddLast() of my implemented LinkedList to java LinkedList
     public String compareAddLast()
     {
         String result;
         //addLast function compare between my linkedList and javaLinkedList

         //initial memory usage and time execution for addLast
         long myCodeInitialMemory=Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory();
         long myCodeStart=System.currentTimeMillis();
         for (int i = 1; i <10000; i++) {
             this.implementedLinkedList.addLast(10000-i);
         }
         // final memory usage and time execution for addLast
         long myCodeEnd=System.currentTimeMillis();
         long myCodeFinalMemory=Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory();

         // estimate memory usage and time of execution
         long executionTime=myCodeEnd-myCodeStart;
         long memoryUsage=myCodeFinalMemory-myCodeInitialMemory;

         result="My Code addLast function execution time: "+executionTime+'\n'+"My Code addLast function memoryUsage: "+memoryUsage+'\n';

         //initial memory usage and time execution for addLast
         long javaCodeInitialMemory=Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory();
         long javaCodeStart=System.currentTimeMillis();
         for (int i = 1; i <10000; i++) {
             this.javaLinkedList.addLast(10000-i);
         }

         // final memory usage and time execution for addLast
         long javaCodeEnd=System.currentTimeMillis();
         long javaCodeFinalMemory=Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory();

         // estimate memory usage and time of execution
         long javaExecutionTime=javaCodeEnd-javaCodeStart;
         long javaMemoryUsage=javaCodeFinalMemory-javaCodeInitialMemory;

         result+="Java Code addLast function execution time: "+javaExecutionTime+'\n'+"Java Code addLast function memoryUsage: "+javaMemoryUsage;
         result+='\n'+"----------------------------------------------------------------";
         return result;
     }

     //a function that compare the AddFirst() of my implemented LinkedList to java LinkedList
     public String compareAddFirst()
     {
         String result;
         //addFirst function compare between my linkedList and javaLinkedList

         //initial memory usage and time execution for addFirst
         long myCodeInitialMemory=Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory();
         long myCodeStart=System.currentTimeMillis();
         for (int i = 1; i <10000; i++) {
             this.implementedLinkedList.addFirst(i);
         }
         // final memory usage and time execution for addFirst
         long myCodeEnd=System.currentTimeMillis();
         long myCodeFinalMemory=Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory();

         // estimate memory usage and time of execution
         long executionTime=myCodeEnd-myCodeStart;
         long memoryUsage=myCodeFinalMemory-myCodeInitialMemory;

         result="My Code addFirst function execution time: "+executionTime+'\n'+"My Code addFirst function memoryUsage: "+memoryUsage+'\n';

         //initial memory usage and time execution for addFirst
         long javaCodeInitialMemory=Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory();
         long javaCodeStart=System.currentTimeMillis();
         for (int i = 1; i <10000; i++) {
             this.javaLinkedList.addFirst(i);
         }

         // final memory usage and time execution for addFirst
         long javaCodeEnd=System.currentTimeMillis();
         long javaCodeFinalMemory=Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory();

         // estimate memory usage and time of execution
         long javaExecutionTime=javaCodeEnd-javaCodeStart;
         long javaMemoryUsage=javaCodeFinalMemory-javaCodeInitialMemory;

         result+="Java Code addFirst function execution time: "+javaExecutionTime+'\n'+"Java Code addFirst function memoryUsage: "+javaMemoryUsage;
         result+='\n'+"----------------------------------------------------------------";
         return result;
     }

     //a function that compare the RemoveFirst() of my implemented LinkedList to java LinkedList
     public String compareRemoveFirst()
     {
         String result;
         //a function compare RemoveFirst() between my linkedList and javaLinkedList

         //initial memory usage and time execution for RemoveFirst()
         long myCodeInitialMemory=Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory();
         long myCodeStart=System.currentTimeMillis();
         for (int i = 1; i <10000; i++) {
             this.implementedLinkedList.removeFirst();
         }
         // final memory usage and time execution for RemoveFirst()
         long myCodeEnd=System.currentTimeMillis();
         long myCodeFinalMemory=Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory();

         // estimate memory usage and time of execution
         long executionTime=myCodeEnd-myCodeStart;
         long memoryUsage=myCodeFinalMemory-myCodeInitialMemory;

         result="My Code RemoveFirst function execution time: "+executionTime+'\n'+"My Code RemoveFirst function memoryUsage: "+memoryUsage+'\n';

         //initial memory usage and time execution for RemoveFirst()
         long javaCodeInitialMemory=Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory();
         long javaCodeStart=System.currentTimeMillis();
         for (int i = 1; i <10000; i++) {
             this.javaLinkedList.removeFirst();
         }

         // final memory usage and time execution for RemoveFirst()
         long javaCodeEnd=System.currentTimeMillis();
         long javaCodeFinalMemory=Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory();

         // estimate memory usage and time of execution
         long javaExecutionTime=javaCodeEnd-javaCodeStart;
         long javaMemoryUsage=javaCodeFinalMemory-javaCodeInitialMemory;

         result+="Java Code RemoveFirst function execution time: "+javaExecutionTime+'\n'+"Java Code RemoveFirst function memoryUsage: "+javaMemoryUsage;
         result+='\n'+"----------------------------------------------------------------";
         return result;
     }

     //a function that compare the RemoveLast() of my implemented LinkedList to java LinkedList
     public String compareRemoveLast()
     {
         String result;
         //a function compare RemoveLast() between my linkedList and javaLinkedList

         //initial memory usage and time execution for RemoveLast()
         long myCodeInitialMemory=Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory();
         long myCodeStart=System.currentTimeMillis();
         for (int i = 1; i <5000; i++) {
             this.implementedLinkedList.removeLast();
         }
         // final memory usage and time execution for RemoveLast()
         long myCodeEnd=System.currentTimeMillis();
         long myCodeFinalMemory=Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory();

         // estimate memory usage and time of execution
         long executionTime=myCodeEnd-myCodeStart;
         long memoryUsage=myCodeFinalMemory-myCodeInitialMemory;

         result="My Code RemoveLast function execution time: "+executionTime+'\n'+"My Code RemoveLast function memoryUsage: "+memoryUsage+'\n';

         //initial memory usage and time execution for RemoveLast()
         long javaCodeInitialMemory=Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory();
         long javaCodeStart=System.currentTimeMillis();
         for (int i = 1; i <5000; i++) {
             this.javaLinkedList.removeLast();
         }

         // final memory usage and time execution for RemoveLast()
         long javaCodeEnd=System.currentTimeMillis();
         long javaCodeFinalMemory=Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory();

         // estimate memory usage and time of execution
         long javaExecutionTime=javaCodeEnd-javaCodeStart;
         long javaMemoryUsage=javaCodeFinalMemory-javaCodeInitialMemory;

         result+="Java Code RemoveLast function execution time: "+javaExecutionTime+'\n'+"Java Code RemoveLast function memoryUsage: "+javaMemoryUsage;
         result+='\n'+"----------------------------------------------------------------";
         return result;
     }

     //a function that compare the get(i) of my implemented LinkedList to java LinkedList
     public String compareGet()
     {
         String result;
         //a function compare get(i) between my linkedList and javaLinkedList

         //initial memory usage and time execution for get(i)
         long myCodeInitialMemory=Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory();
         long myCodeStart=System.currentTimeMillis();
         for (int i = 1; i <5000; i++) {
             this.implementedLinkedList.get(i);
         }
         // final memory usage and time execution for get(i)
         long myCodeEnd=System.currentTimeMillis();
         long myCodeFinalMemory=Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory();

         // estimate memory usage and time of execution
         long executionTime=myCodeEnd-myCodeStart;
         long memoryUsage=myCodeFinalMemory-myCodeInitialMemory;

         result="My Code get(i) function execution time: "+executionTime+'\n'+"My Code get(i) function memoryUsage: "+memoryUsage+'\n';

         //initial memory usage and time execution for get(i)
         long javaCodeInitialMemory=Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory();
         long javaCodeStart=System.currentTimeMillis();
         for (int i = 1; i <5000; i++) {
             this.javaLinkedList.get(i);
         }

         // final memory usage and time execution for get(i)
         long javaCodeEnd=System.currentTimeMillis();
         long javaCodeFinalMemory=Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory();

         // estimate memory usage and time of execution
         long javaExecutionTime=javaCodeEnd-javaCodeStart;
         long javaMemoryUsage=javaCodeFinalMemory-javaCodeInitialMemory;

         result+="Java Code get(i) function execution time: "+javaExecutionTime+'\n'+"Java Code get(i) function memoryUsage: "+javaMemoryUsage;
         result+='\n'+"----------------------------------------------------------------";
         return result;
     }

     //a function that compare the Last() of my implemented LinkedList to java LinkedList
     public String compareGetLast()
     {
         String result;
         //a function compare Last() between my linkedList and javaLinkedList

         //initial memory usage and time execution for Last()
         long myCodeInitialMemory=Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory();
         long myCodeStart=System.currentTimeMillis();
         for (int i = 1; i <5000; i++) {
             this.implementedLinkedList.last();
         }
         // final memory usage and time execution for Last()
         long myCodeEnd=System.currentTimeMillis();
         long myCodeFinalMemory=Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory();

         // estimate memory usage and time of execution
         long executionTime=myCodeEnd-myCodeStart;
         long memoryUsage=myCodeFinalMemory-myCodeInitialMemory;

         result="My Code Last() function execution time: "+executionTime+'\n'+"My Code Last() function memoryUsage: "+memoryUsage+'\n';

         //initial memory usage and time execution for Last()
         long javaCodeInitialMemory=Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory();
         long javaCodeStart=System.currentTimeMillis();
         for (int i = 1; i <5000; i++) {
             this.javaLinkedList.getLast();
         }

         // final memory usage and time execution for Last()
         long javaCodeEnd=System.currentTimeMillis();
         long javaCodeFinalMemory=Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory();

         // estimate memory usage and time of execution
         long javaExecutionTime=javaCodeEnd-javaCodeStart;
         long javaMemoryUsage=javaCodeFinalMemory-javaCodeInitialMemory;

         result+="Java Code Last() function execution time: "+javaExecutionTime+'\n'+"Java Code Last() function memoryUsage: "+javaMemoryUsage;
         result+='\n'+"----------------------------------------------------------------";
         return result;
     }

    //a function that compare the First() of my implemented LinkedList to java LinkedList
    public String compareGetFirst()
    {
        String result;
        //a function compare First() between my linkedList and javaLinkedList

        //initial memory usage and time execution for First()
        long myCodeInitialMemory=Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory();
        long myCodeStart=System.currentTimeMillis();
        for (int i = 1; i <10000; i++) {
            this.implementedLinkedList.first();
        }
        // final memory usage and time execution for First()
        long myCodeEnd=System.currentTimeMillis();
        long myCodeFinalMemory=Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory();

        // estimate memory usage and time of execution
        long executionTime=myCodeEnd-myCodeStart;
        long memoryUsage=myCodeFinalMemory-myCodeInitialMemory;

        result="My Code First() function execution time: "+executionTime+'\n'+"My Code First() function memoryUsage: "+memoryUsage+'\n';

        //initial memory usage and time execution for First()
        long javaCodeInitialMemory=Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory();
        long javaCodeStart=System.currentTimeMillis();
        for (int i = 1; i <10000; i++) {
            this.javaLinkedList.getFirst();
        }

        // final memory usage and time execution for First()
        long javaCodeEnd=System.currentTimeMillis();
        long javaCodeFinalMemory=Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory();

        // estimate memory usage and time of execution
        long javaExecutionTime=javaCodeEnd-javaCodeStart;
        long javaMemoryUsage=javaCodeFinalMemory-javaCodeInitialMemory;

        result+="Java Code First() function execution time: "+javaExecutionTime+'\n'+"Java Code First() function memoryUsage: "+javaMemoryUsage;
        result+='\n'+"----------------------------------------------------------------";
        return result;
    }

     //a function that compare the Size() of my implemented LinkedList to java LinkedList
     public String compareSize()
     {
         String result;
         //a function compare Size() between my linkedList and javaLinkedList

         //initial memory usage and time execution for Size()
         long myCodeInitialMemory=Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory();
         long myCodeStart=System.currentTimeMillis();
         for (int i = 1; i <10000; i++) {
             this.implementedLinkedList.Size();
         }
         // final memory usage and time execution for Size()
         long myCodeEnd=System.currentTimeMillis();
         long myCodeFinalMemory=Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory();

         // estimate memory usage and time of execution
         long executionTime=myCodeEnd-myCodeStart;
         long memoryUsage=myCodeFinalMemory-myCodeInitialMemory;

         result="My Code Size() function execution time: "+executionTime+'\n'+"My Code Size() function memoryUsage: "+memoryUsage+'\n';

         //initial memory usage and time execution for Size()
         long javaCodeInitialMemory=Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory();
         long javaCodeStart=System.currentTimeMillis();
         for (int i = 1; i <10000; i++) {
             this.javaLinkedList.size();
         }

         // final memory usage and time execution for Size()
         long javaCodeEnd=System.currentTimeMillis();
         long javaCodeFinalMemory=Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory();

         // estimate memory usage and time of execution
         long javaExecutionTime=javaCodeEnd-javaCodeStart;
         long javaMemoryUsage=javaCodeFinalMemory-javaCodeInitialMemory;

         result+="Java Code Size() function execution time: "+javaExecutionTime+'\n'+"Java Code Size() function memoryUsage: "+javaMemoryUsage;
         result+='\n'+"----------------------------------------------------------------";
         return result;
     }

     //a function that compare the IsEmpty() of my implemented LinkedList to java LinkedList
     public String compareIsEmpty()
     {
         String result;
         //a function compare IsEmpty() between my linkedList and javaLinkedList

         //initial memory usage and time execution for IsEmpty()
         long myCodeInitialMemory=Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory();
         long myCodeStart=System.currentTimeMillis();
         for (int i = 1; i <10000; i++) {
             this.implementedLinkedList.isEmpty();
         }
         // final memory usage and time execution for IsEmpty()
         long myCodeEnd=System.currentTimeMillis();
         long myCodeFinalMemory=Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory();

         // estimate memory usage and time of execution
         long executionTime=myCodeEnd-myCodeStart;
         long memoryUsage=myCodeFinalMemory-myCodeInitialMemory;

         result="My Code IsEmpty() function execution time: "+executionTime+'\n'+"My Code IsEmpty() function memoryUsage: "+memoryUsage+'\n';

         //initial memory usage and time execution for IsEmpty()
         long javaCodeInitialMemory=Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory();
         long javaCodeStart=System.currentTimeMillis();
         for (int i = 1; i <10000; i++) {
             this.javaLinkedList.isEmpty();
         }

         // final memory usage and time execution for IsEmpty()
         long javaCodeEnd=System.currentTimeMillis();
         long javaCodeFinalMemory=Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory();

         // estimate memory usage and time of execution
         long javaExecutionTime=javaCodeEnd-javaCodeStart;
         long javaMemoryUsage=javaCodeFinalMemory-javaCodeInitialMemory;

         result+="Java Code IsEmpty() function execution time: "+javaExecutionTime+'\n'+"Java Code IsEmpty() function memoryUsage: "+javaMemoryUsage;
         result+='\n'+"----------------------------------------------------------------";
         return result;
     }

     //a function that compare the sort() of my implemented LinkedList to java LinkedList
     public String compareSort()
     {
         String result;
         //a function compare sort() between my linkedList and javaLinkedList

         //initial memory usage and time execution for sort()
         long myCodeInitialMemory=Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory();
         long myCodeStart=System.currentTimeMillis();
         for (int i = 1; i <2; i++) {
             this.implementedLinkedList.sort(implementedLinkedList);
         }
         // final memory usage and time execution for sort()
         long myCodeEnd=System.currentTimeMillis();
         long myCodeFinalMemory=Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory();

         // estimate memory usage and time of execution
         long executionTime=myCodeEnd-myCodeStart;
         long memoryUsage=myCodeFinalMemory-myCodeInitialMemory;

         result="My Code sort() function execution time: "+executionTime+'\n'+"My Code sort() function memoryUsage: "+memoryUsage+'\n';

         //initial memory usage and time execution for sort()
         long javaCodeInitialMemory=Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory();
         long javaCodeStart=System.currentTimeMillis();
         for (int i = 1; i <2; i++) {
             this.javaLinkedList.sort(Integer::compareTo);
         }

         // final memory usage and time execution for sort()
         long javaCodeEnd=System.currentTimeMillis();
         long javaCodeFinalMemory=Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory();

         // estimate memory usage and time of execution
         long javaExecutionTime=javaCodeEnd-javaCodeStart;
         long javaMemoryUsage=javaCodeFinalMemory-javaCodeInitialMemory;

         result+="Java Code sort() function execution time: "+javaExecutionTime+'\n'+"Java Code sort() function memoryUsage: "+javaMemoryUsage;
         result+='\n'+"----------------------------------------------------------------";
         return result;
     }

     //a function that compare the add(i,e) of my implemented LinkedList to java LinkedList
     public String compareInsertMid()
     {
         String result;
         //a function compare add(i,e) between my linkedList and javaLinkedList

         //initial memory usage and time execution for add(i,e)
         long myCodeInitialMemory=Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory();
         long myCodeStart=System.currentTimeMillis();
         for (int i = 1; i <5000; i++) {
             this.implementedLinkedList.add(i,10000-i);
         }
         // final memory usage and time execution for add(i,e)
         long myCodeEnd=System.currentTimeMillis();
         long myCodeFinalMemory=Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory();

         // estimate memory usage and time of execution
         long executionTime=myCodeEnd-myCodeStart;
         long memoryUsage=myCodeFinalMemory-myCodeInitialMemory;

         result="My Code add(i,e) function execution time: "+executionTime+'\n'+"My Code add(i,e) function memoryUsage: "+memoryUsage+'\n';

         //initial memory usage and time execution for add(i,e)
         long javaCodeInitialMemory=Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory();
         long javaCodeStart=System.currentTimeMillis();
         for (int i = 1; i <5000; i++) {
             this.javaLinkedList.add(i,10000-i);
         }

         // final memory usage and time execution for add(i,e)
         long javaCodeEnd=System.currentTimeMillis();
         long javaCodeFinalMemory=Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory();

         // estimate memory usage and time of execution
         long javaExecutionTime=javaCodeEnd-javaCodeStart;
         long javaMemoryUsage=javaCodeFinalMemory-javaCodeInitialMemory;

         result+="Java Code add(i,e) function execution time: "+javaExecutionTime+'\n'+"Java Code add(i,e) function memoryUsage: "+javaMemoryUsage;
         result+='\n'+"----------------------------------------------------------------";
         return result;
     }

    //a function that compare the remove(i) of my implemented LinkedList to java LinkedList
    public String compareRemoveI()
    {
        String result;
        //a function compare remove(i) between my linkedList and javaLinkedList

        //initial memory usage and time execution for remove(i)
        long myCodeInitialMemory=Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory();
        long myCodeStart=System.currentTimeMillis();
        for (int i = 5000; i >=1; i--) {
            this.implementedLinkedList.remove(i);
        }
        // final memory usage and time execution for remove(i)
        long myCodeEnd=System.currentTimeMillis();
        long myCodeFinalMemory=Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory();

        // estimate memory usage and time of execution
        long executionTime=myCodeEnd-myCodeStart;
        long memoryUsage=myCodeFinalMemory-myCodeInitialMemory;

        result="My Code remove(i) function execution time: "+executionTime+'\n'+"My Code remove(i) function memoryUsage: "+memoryUsage+'\n';

        //initial memory usage and time execution for remove(i)
        long javaCodeInitialMemory=Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory();
        long javaCodeStart=System.currentTimeMillis();
        for (int i = 5000; i >=1; i--) {
            this.javaLinkedList.remove(i);
        }

        // final memory usage and time execution for remove(i)
        long javaCodeEnd=System.currentTimeMillis();
        long javaCodeFinalMemory=Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory();

        // estimate memory usage and time of execution
        long javaExecutionTime=javaCodeEnd-javaCodeStart;
        long javaMemoryUsage=javaCodeFinalMemory-javaCodeInitialMemory;

        result+="Java Code remove(i) function execution time: "+javaExecutionTime+'\n'+"Java Code remove(i) function memoryUsage: "+javaMemoryUsage;
        result+='\n'+"----------------------------------------------------------------";
        return result;
    }

 }
