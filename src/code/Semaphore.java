package dacGame;

public class Semaphore {
	int n, max;
	String name;
	
	
	public Semaphore(int max, String s)
	{
		this.max = max;
		name = s;
	}
	
	public synchronized void P() throws InterruptedException
	{
		System.out.println("a thread is running");
		n--;
		 wait();
		System.out.println("a thread has been notified");
	}
	
	public synchronized void V() throws InterruptedException
	{
		n++;
		notify();
	}
}
