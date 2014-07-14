public class Polynomial {
	Node firstNode;
	Node lastNode;
	String name; // string used in printing
	String tempResult = ""; // string used in showing steps

	/** Create empty Polynomial with "polynomial" as the name */
	public Polynomial() { 
		this("polynomial"); 
	}
	
	/** Create empty Polynomial with a specified name */
	public Polynomial(String listName) {
		name = listName;
		firstNode = lastNode = null;
	}
	
	/** Insert a coefficient and an exponent */
	public void insert(int newCoef, int newExp) {
		if (newCoef == 0) // Do nothing if the coefficient is zero
			return;
		
		if (isEmpty()) { // If the polynomial is empty
			firstNode = lastNode = new Node(newCoef, newExp);
		}
		else if (firstNode.exp > newExp) { // Insert at front if the exponent is smaller than the first one
			firstNode = new Node(newCoef, newExp, firstNode);
		}
		else if (lastNode.exp < newExp) // Insert at Back if the exponent is bigger than the last one
			lastNode = lastNode.nextNode = new Node(newCoef, newExp);
		else { // Insert at center
			Node current = firstNode;
			Node previous = null;

			while (current != null) {
				if (current.exp == newExp) { // If the exponent exists
					if (current.coef == -newCoef) { // Remove the current node if the new coefficient equals the old one
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
			} // End of the while loop
		}
	} // End of the insert method

	/** Check if the Polynomial is Empty */
	public boolean isEmpty() { 
		return firstNode == null;
	}
	
	/** Add a polynomial to another */
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
	} // End of the add method
	
	/** Subtract a polynomial from another */
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
	} // End of the subtract method

	/** Return the String of the Polynomial */
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
		tempResult += (string + "\n"); // Update the tempResult
		return string;
	} // End of the print method
	
	/** Return the tempResult*/
	public String getTempResult() {
		return tempResult;
	}
}