public class Polynomial {
	Node firstNode;
	Node lastNode;
	String name;
	String tempResult = "";

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
		
		Node current = firstNode;
		
		while (current != null) {
			result.insert(current.coef, current.exp);
			result.print();
			current = current.nextNode;
		}
		
		current = poly.firstNode;
		
		while (current != null) {
			result.insert(current.coef, current.exp);
			result.print();
			current = current.nextNode;
		}
		return result;
	}
	
	public Polynomial subtract(Polynomial poly) {
		Polynomial result = new Polynomial(name + "-" + poly.name);
		
		Node current = firstNode;
	
		while (current != null) {
			result.insert(current.coef, current.exp);
			result.print();
			current = current.nextNode;
		}
		
		current = poly.firstNode;
		
		while (current != null) {
			result.insert(-current.coef, current.exp);
			result.print();
			current = current.nextNode;
		}
		return result;
	}

	public String print() {
		String string = "";
		
		if (isEmpty()) {
			return String.format("Empty %s\n", name);
		}

		string += String.format("%s = ", name);
		Node current = firstNode;

		while (current != null) {
			string += String.format("%+dx^(%d) ", current.coef, current.exp);
			current = current.nextNode;
		}
		tempResult += (string + "\n");
		return string;
	}
	
	public String getTempResult() {
		return tempResult;
	}
}