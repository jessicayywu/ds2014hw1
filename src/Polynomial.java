public class Polynomial {
	Node firstNode;
	Node lastNode;
	String name;

	public Polynomial() { 
		this("polynomial"); 
	}

	public Polynomial(String listName) {
		name = listName;
		firstNode = lastNode = null;
	}
	
	public void insert(int newCoef, int newExp) {
		if (newCoef == 0)
			return;
		
		if (isEmpty()) {
			firstNode = lastNode = new Node(newCoef, newExp);
		}
		else if (firstNode.exp > newExp) {
			firstNode = new Node(newCoef, newExp, firstNode);
		}
		else if (lastNode.exp < newExp) 
			lastNode = lastNode.nextNode = new Node(newCoef, newExp);
		else {
			Node current = firstNode;
			Node previous = null;

			while (current != null) {
				if (current.exp == newExp) {
					if (current.coef == -newCoef) {
						if (current == lastNode) 
							lastNode = previous;

						current = current.nextNode;
						previous.nextNode = current;
					}
					else 
						current.coef += newCoef;
					
					break;					
				}
				if (current.exp < newExp && current.nextNode.exp > newExp) {
					Node temp = current.nextNode;
				    current.nextNode = new Node(newCoef, newExp);
				    (current.nextNode).nextNode = temp;
				    break;
				}
				previous = current;
				current = current.nextNode;
			}
		}
	}

	public boolean isEmpty() { 
		return firstNode == null;
	}
	
	public Polynomial add(Polynomial poly) {
		Polynomial result = new Polynomial(name + "+" + poly.name);
		
		Node current1 = firstNode;
		Node current2 = poly.firstNode;
		
		while (current1 != null) {
			result.insert(current1.coef, current1.exp);
			result.print();
			current1 = current1.nextNode;
		}
		

		
		while (current2 != null) {
			result.insert(current2.coef, current2.exp);
			result.print();
			current2 = current2.nextNode;
		}
		
		return result;
	}
	
	public Polynomial subtract(Polynomial poly) {
		Polynomial result = new Polynomial(name + "-" + poly.name);
		
		Node current1 = firstNode;
		Node current2 = poly.firstNode;
		
		while (current1 != null) {
			result.insert(current1.coef, current1.exp);
			result.print();
			current1 = current1.nextNode;
		}
		

		
		while (current2 != null) {
			result.insert(-current2.coef, current2.exp);
			result.print();
			current2 = current2.nextNode;
		}
		
		return result;
	}

	public void print() {
		if (isEmpty()) {
			System.out.printf("Empty %s\n", name);
			return;
		}

		System.out.printf("The %s is: ", name);
		Node current = firstNode;

		while (current != null) {
			System.out.printf("%+d x^%d ", current.coef, current.exp);
			current = current.nextNode;
		}
		System.out.println();
	}
}