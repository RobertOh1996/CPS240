
public class Decrementer extends Thread {
	
	IntCell intCell;
	
	public Decrementer(IntCell intCell) {
		this.intCell = intCell;
	}

	@Override
	public void run() {
		
		for(int i = 0; i < 10000; i++) {
			intCell.decrement(intCell.getVal());
		}
	}
	
}
