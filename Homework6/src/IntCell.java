
public class IntCell {
	
	private int val = 0;
	
	public IntCell(int val) {
		this.val = val;
	}
	
	public synchronized void increment(int val) {
		this.val = val + 1;
	}
	
	public synchronized void decrement(int val) {
		this.val = val - 1;
	}

	public int getVal() {
		return val;
	}
}
