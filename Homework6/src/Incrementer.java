
public class Incrementer extends Thread {
	
	IntCell intCell;
	
	public Incrementer(IntCell intCell) {
		this.intCell = intCell;
	}
	
	@Override
	public void run() {
		
		for(int i = 0; i < 10000; i++) {
			intCell.increment(intCell.getVal());
		}		
	}

}