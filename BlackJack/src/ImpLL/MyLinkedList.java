package ImpLL;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyLinkedList<T> implements InterfaceLinkedList<T>, Iterable<T> {

    private Node head;
    private Node tail;
    private int size;
    
    class Node 
    {
        private Node next;
        private Node prev;
        private T data;
    
        public Node (T data) {
            this.next = null;
            this.prev = null;
            this.data = data;
        }

    }

    public MyLinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    // public T get(int indice){
    //     Node temp = head;

    //     if(isEmpty() != true){
    //         T element = temp.data;

    //         for (int i = 0; i < indice + 1; i++) {
    //             temp = temp.next;
    //             element = temp.data;
    //         }
    //     } else {
    //         T element = temp.data;
    //         System.out.println("Linked List vazia.");
    //         return null;
    //     }
        
    //     return element;
    // }


    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Índice inválido: " + index);
        }

        Node current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current.data;
    }
    
    
    @Override
    public void addFirst(T value) {
        Node p = new Node(value);
        if (head == null) {
            head = p;
            tail = p;
        } else {
            head.prev = p;
            p.next = head;
            head = p;
        }
        size++;
    
    }

    @Override
    public void addLast(T value) {
        Node p = new Node(value);
        if (head == null) {
            head = p;
            tail = p;
        } else {
            tail.next = p;
            p.prev = tail;
            tail = p;
        }
        size++;
    }

    @Override
    public boolean addAfter(T dado, T crit) {
        throw new UnsupportedOperationException("Unimplemented method 'addAfter'");
    }

    @Override
    public T peekFirst() {
        return head.data;    
    }

    @Override
    public T peekLast() {
        return tail.data;
    }

    @Override
    public T search(T crit) {
        Node p = head;
        while (p.data != crit) {
            if (p.next == null) {
                System.out.println("Criterio nao encontrado na lista.");
                return null;
            }
            p = p.next;
        }
        return p.data;
    }

    @Override
    public T removeFirst() {
        if (isEmpty()) {
            System.out.println("Linked List vazia.");
            return null;
        } else if (head == tail) {
            Node p = head;
            head = null;
            tail = null;
            size--;
            return p.data;
        }
        
        Node p = head.next;
        p.prev = null;
        p = head;
        head = p.next;
        p.next = null;
        size--;
        return p.data;
    }

    @Override
    public T removeLast() {
        if (isEmpty()) {
            System.out.println("Linked List vazia.");
            return null;
        } else if (head == tail) {
            Node p = head;
            head = null;
            tail = null;
            size--;
            return p.data;
        }
        
        Node p = tail.prev;
        p.next = null;
        p = tail;
        tail = p.prev;
        p.prev = null;
        size--;
        return p.data;
    }

    @Override
    public T remove(T crit) {
        if (search(crit) == null) {
            System.out.println("Nao existe esse elemento na lista");
            return null;
        }
        
        if (head.data == crit) {
            removeFirst();    
        }

        Node p = head;
        while (p.data != crit) {
            p = p.next;
        }

        if (p.next == null) {
            removeLast();
        }
        

        Node pivo = p.prev;
        pivo.next = p.next;
        pivo = pivo.next;
        pivo.prev = p.prev;
        size--;
        return p.data;        
    }
    
    public int getSize() {
        return this.size;
    }

    public boolean isEmpty() {
        if (head == null) {
            return true;
        }
        return false;
    }

    
    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private Node current = head;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                T data = current.data;
                current = current.next;
                return data;
            }
        };
    }

}
