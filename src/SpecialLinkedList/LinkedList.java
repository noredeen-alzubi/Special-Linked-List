package SpecialLinkedList;


public class LinkedList<E> {
	
	//we mark a sentinel node by declaring its value null
	public Node<E> sentinel = new Node<E>(null, null, null);
	public int length = 0; 
	
	
	//add to the bottom of the deck -> (end) back of the linked list
	public void add(E element) {
		
		//This line checks if the Linked List is empty. If it is, then the sentinel will 
		//be treated as the only node. If not, whatever is before the sentinel is chosen for
		//the swap
		Node<E> temp = sentinel.prev == null? sentinel : sentinel.prev;
		
		//the new node to be added
		Node<E> new_node = new Node<E>(element, temp, sentinel);
		
		//Add new node into the chain and correct pointers
		new_node.next = sentinel;
		new_node.prev = temp;
		sentinel.prev = new_node;
		temp.next = new_node;
		

		length++;
	}
	
	//draw from the top of the deck -> (beginning) front of linked list
	//return null if deck is empty
	public Node<E> remove() {
		Node<E> temp = sentinel.next;
		
		//if there is something in the linked list (sentinel not alone), then set
		//the sentinel to skip the first node and point to what is after it.
		if (temp != null && temp.value != null) {
			
			//both to and from arrows corrected
			sentinel.next = sentinel.next.next;
			sentinel.next.prev = sentinel;
			length--;
			return temp; 
		}
		return null;
	}
	
	
	//changes positions of the nodes. Uses a perfect shuffle with randomness
	public void shuffle() {
		
		int shuffle_pos;
		
		Node<E> card_to_switch;
		
		//where to split in the linked list
		shuffle_pos = (int)(Math.random()*(length) - 1);
		
		//if left is bigger than right
		if(shuffle_pos <= (length - 1) / 2) {
			
			
			//save the current node and what is after it because the order of the
			//original nodes will be lost during shuffling.
			Node<E> current = sentinel.next;
			Node<E> next_current;
			
			//looping through left side
			for (int i = 0; i <= shuffle_pos; i++, current = next_current) {
				
				card_to_switch = current;
	
				//This current is like j to i in nested for loops. This current
				//loops through the right side
				Node<E> inner_current = current;
				
				//reaching the target position (where the original node will be moved)
				for(int j = i; j < shuffle_pos + (i*2) + 1; j++, inner_current = inner_current.next); 
				
				
				//Rearrange connections in the original position (left side)
				card_to_switch.prev.next = card_to_switch.next;
				card_to_switch.next.prev = card_to_switch.prev;
				
				//Keeping track of the surrounding nodes to the target position
				Node<E> target_temp_front = inner_current.next;
				Node<E> target_temp_back = inner_current;
				
				//add the card to place it in its new position in the chain
				//inner_current is the target position. The original node will be placed
				//after it
				inner_current.next.prev = card_to_switch;				
				inner_current.next = card_to_switch;
				
				//rearranging the pointers around target and original position
				next_current = card_to_switch.next;
				card_to_switch.next = target_temp_front;
				card_to_switch.prev = target_temp_back;
				
			}
			
			
			
		//right side is bigger than left side	
		}else if(shuffle_pos > (length - 1) / 2) {
			
			
			//Exact same steps but just reversed. Going the other direction of the list
			Node<E> current = sentinel.prev;
			
			Node<E> next_current;
			
			
			//end to start
			for (int i = length - 1; i >= shuffle_pos; i--, current = next_current) {
				
				card_to_switch = current;
	
				Node<E> inner_current = current;
				
				//condition is placed because the addition of new nodes makes the
				//actual target positions unreachable
				for(int j = i; j > shuffle_pos - (length - i) - (length-i-1); j--, inner_current = inner_current.prev); 
				
				
				//remove the card to switch from its original position in the chain
				card_to_switch.prev.next = card_to_switch.next;
				card_to_switch.next.prev = card_to_switch.prev;
				
				Node<E> target_temp_front = inner_current.prev;
				Node<E> target_temp_back = inner_current;
				
				//add the card to switch in its new position in the chain
				inner_current.prev.next = card_to_switch;				
				inner_current.prev = card_to_switch;
				next_current = card_to_switch.prev;
				card_to_switch.prev = target_temp_front;
				card_to_switch.next = target_temp_back;
			
			}
		}
	}
	
	public int size() {
		int count = 0;
		Node<E> current = sentinel.prev;
		
		//we know we reached the end if we get to the sentinel.
		while(current.next.value != null) {
			current = current.next;
			count++;
		}
		return count;
	}
	
	//splits the list equally to two sub lists ll1 and ll2.
	public void split(LinkedList<E> ll1, LinkedList<E> ll2) {
		
		Node<E> current = sentinel.next;
		
		//first half --> add to ll1, second half --> add to ll2
		for (int i = 0; i < length && current.value != null; i++, current = current.next) {
			if(i < length/2) {
				ll1.add(current.value);
				
			}else {
				if(current.value == null)
					System.out.println("sentinel added");
				ll2.add(current.value);
			}
			
			current.prev.next = current.next;
			current.next.prev = current.prev;
			
			
			
		}
	}
	
	
	public String toString() {
		
		String output = "sentinel\t<>\t";
		int i = 0;
		for (Node<E> current = sentinel.next; length > 0 && current.value != null; current = current.next, i++) {
			output += i + " " + current.value + "\t<>\t";
		}
		return output + "sentinel";
	}
	
}

