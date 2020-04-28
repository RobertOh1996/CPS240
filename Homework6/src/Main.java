
public class Main {

	public static void main(String[] args) {
		IntCell intCell = new IntCell(0);
		
		Thread t = new Incrementer(intCell);
		Thread t2 = new Decrementer(intCell);
		
		t.start();
		t2.start();
		
		try {
			t.join();
			t2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println(intCell.getVal());		
		System.out.println("Work out");
		
	}

}
