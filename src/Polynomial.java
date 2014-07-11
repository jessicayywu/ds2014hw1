public class Polynomial {
	private Node firstNode, lastNode;
	private String name;

	public Polynomial() { 
		this("polynomial"); 
	}

	public Polynomial(String listName) {
		name = listName;
		firstNode = lastNode = null;
	}
	
	public void insert(int newCoef, int newExp) {
		if (isEmpty()) {
			firstNode = lastNode = new Node(newCoef, newExp);
		}
		else if (firstNode.exp > newExp) {
			firstNode = new Node(newCoef, newExp, firstNode);
		}
		else if(lastNode.exp < newExp)
			lastNode = lastNode.nextNode = new Node(newCoef, newExp);
		else {
			Node current = firstNode;

			while (current != null) {
				if(current.exp == newExp) {
					current.coef += newCoef;
					break;
				}
				if(current.exp < newExp && current.nextNode.exp > newExp) {
					Node temp = current.nextNode;
				    current.nextNode = new Node(newCoef, newExp);
				    (current.nextNode).nextNode = temp;
				    break;
				}
				current = current.nextNode;
			}
		}
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
		Node current = firstNode;

		while (current != null) {
			if(current.coef != 0)
				System.out.printf("%+d x^%d ", current.coef, current.exp);
			current = current.nextNode;
		}
		System.out.println();
	}
}