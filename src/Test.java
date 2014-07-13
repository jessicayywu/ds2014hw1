
public class Test {
	public static void main(String args[]) {
		Polynomial poly1 = new Polynomial("A");
		poly1.insert(1, 2);
		poly1.print();		
		poly1.insert(2, 0);
		poly1.print();
		poly1.insert(-7, 4);
		poly1.print();
		poly1.insert(0, 5);
		poly1.print();
		poly1.insert(-4, 3);
		poly1.print();
		poly1.insert(8, 2);
		poly1.print();
		poly1.insert(7, 4);
		poly1.print();
		
		System.out.println();
		
		Polynomial poly2 = new Polynomial("B");
		poly2.insert(7, 2);
		poly2.print();
		poly2.insert(4, 3);
		poly2.print();
		poly2.insert(-9, 6);
		poly2.print();
		poly2.insert(-8, 5);
		poly2.print();
		
		System.out.println();
		
		
		Polynomial result = poly1.add(poly2);
		result.print();
		
		System.out.println();
		
		result = poly1.subtract(poly2);
		result.print();
	}
}
