
public class CustomThread implements Runnable {
//Sub-Thread doesn't extend Thread, instead, it implements Runnable.
	@Override
	public void run() {
		System.out.println("Running...");
		try {
			Thread.sleep((int) (Math.random() * ((10000 - 1000) + 1)) + 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Thread " + Thread.currentThread().getId() + " done.");	
	}
	//Method is same with before
}
