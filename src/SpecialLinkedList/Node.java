package SpecialLinkedList;

public class Node<E> {

	public E value;
	public Node<E> next; 
	public Node<E> prev;
	
	public Node(E value, Node<E> prev, Node<E> next){
		this.value = value;
	    this.next = next;
	}
	
}