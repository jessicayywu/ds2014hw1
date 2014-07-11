class Node {
	int coef, exp;
	Node nextNode;
	
	Node(int coef, int exp) { 
		this(coef, exp, null);
	}

	Node(int coef, int exp, Node node) { 
		this.coef = coef;
		this.exp = exp;
		nextNode = node;
	}
}
