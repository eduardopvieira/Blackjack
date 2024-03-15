package ImpLL;

public interface InterfaceLinkedList<T>{
    void addFirst(T value);
    void addLast(T value);
    boolean addAfter(T dado, T crit);
    
    T peekFirst();
    T peekLast();
    
    T search(T crit);
    
    T removeFirst();
    T removeLast();
    T remove(T crit); 

}