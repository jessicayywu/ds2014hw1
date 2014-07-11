
public class ListTest {
	public static void main( String[] args ) {
		List< Integer > list = new List< Integer >();

		System.out.println("insertAtFront: ");
		System.out.println("===============");
		list.insertAtFront( 1 );
		list.print();
		list.insertAtFront( 2 );
		list.print();
		list.insertAtFront( 3 );
		list.print();
		System.out.println("\ninsertAtBack: ");
		System.out.println("===============");
		list.insertAtBack( 4 );
		list.print();
		list.insertAtBack( 5 );
		list.print();
		
		System.out.println("=============================");

		try {
			System.out.println("\ndeleteFromFront: ");
			System.out.println("===============");
			
			int deletedItem = list.deleteFromFront();
			System.out.printf( "%d removed\n", deletedItem );
			list.print();

			deletedItem = list.deleteFromFront();
			System.out.printf( "\n%d removed\n", deletedItem );
			list.print();
			
			System.out.println("\ndeleteFromBack: ");
			System.out.println("===============");
			
			deletedItem = list.deleteFromBack();
			System.out.printf( "%d removed\n", deletedItem );
			list.print();

			deletedItem = list.deleteFromBack();
			System.out.printf( "\n%d removed\n", deletedItem );
			list.print();
		}
		catch ( EmptyListException emptyListException ) {
			emptyListException.printStackTrace();
		}
	}
}
