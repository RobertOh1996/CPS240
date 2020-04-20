/**
 * 
 * @author Jaemin Oh
 * If CustomThread class extends Thread, because JAVA doesn't support multi-extenstion, it cannot extends another class.
 * But it can extends another class by implementing Runnable class.
 * 
 */
public class Main {
	public static void main(String[] args) {
		Runnable r = new CustomThread();		
		System.out.println(Thread.activeCount());
		for(int i = 0; i < 10; i++) {
			new Thread(r).start();
		}
	}
}
