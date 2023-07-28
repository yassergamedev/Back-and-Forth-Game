package dacGame;

import javax.swing.JProgressBar;

public class pBar extends Thread {
	JProgressBar prog;
	public boolean isHit = false;
	
	public pBar(JProgressBar p)
	{
		prog = p;
	}
	
	public synchronized void  run()
	{
		while(!isHit)
		{
		for(int i = 0; i < 100; i++)
		{
			prog.setValue(i);
			try {
				sleep(15);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		}}
		
}
