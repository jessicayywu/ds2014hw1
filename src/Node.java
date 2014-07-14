class Node {
	int coef, exp; // coefficient, exponent
	Node nextNode; // reference to the next node
	
	/** Create a Node*/
	Node(int coef, int exp) { 
		this(coef, exp, null);
	}

	/** Create a Node the next Node */
	Node(int coef, int exp, Node node) { 
		this.coef = coef;
		this.exp = exp;
		nextNode = node;
	}
}
