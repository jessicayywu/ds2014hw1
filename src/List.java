
public class List<E> {
	private ListNode<E> firstNode, lastNode;
	private String name;

	public List() { 
		this("list"); 
	}

	public List(String listName) {
		name = listName;
		firstNode = lastNode = null;
	}

	public void insertAtFront(E insertItem) {
		if ( isEmpty() )
			firstNode = lastNode = new ListNode<E>(insertItem);
		else
			firstNode = new ListNode<E>(insertItem, firstNode);
	}

	public void insertAtBack(E insertItem) {
		if (isEmpty())
			firstNode = lastNode = new ListNode<E>(insertItem);
		else
		   lastNode = lastNode.nextNode = new ListNode<E>(insertItem);
	}

	public E deleteFromFront() throws EmptyListException {
		if (isEmpty())
			throw new EmptyListException(name);

		E deletedItem = firstNode.data;

		if (firstNode == lastNode)
			firstNode = lastNode = null;
		else
			firstNode = firstNode.nextNode;

		return deletedItem;
	}

	public E deleteFromBack() throws EmptyListException {
		if (isEmpty())
			throw new EmptyListException(name);

		E deletedItem = lastNode.data;

		if (firstNode == lastNode)
			firstNode = lastNode = null;
		else { 
			ListNode<E> current = firstNode;

			while (current.nextNode != lastNode)
				current = current.nextNode;
   
			lastNode = current;
			current.nextNode = null;
		}

		return deletedItem;
	}

	public boolean isEmpty() { 
		return firstNode == null;
	}

	public void print() {
		if (isEmpty()) {
			System.out.printf("Empty %s\n", name);
			return;
		}

		System.out.printf("The %s is: ", name);
		ListNode< E > current = firstNode;

		while (current != null) {
			System.out.printf("%s ", current.data);
			current = current.nextNode;
		}
	}
	
	public int count() {
		int countData = 0;
		if (isEmpty()) {
			return 0;
		}

		ListNode<E> current = firstNode;

		while (current != null) {
			countData++;
			current = current.nextNode;
		}
			
		return countData;
	}
}