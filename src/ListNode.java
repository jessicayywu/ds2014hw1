
class ListNode<E> {
	E data;
	ListNode<E> nextNode;

	ListNode(E object) { 
		this( object, null ); 
	}

	ListNode(E object, ListNode<E> node) {
		data = object;    
		nextNode = node;  
	}

	E getData() { 
		return data;
	}

	ListNode<E> getNext() { 
	      return nextNode;
	}
}
