package dacGame;

import javax.swing.JLabel;

public class logo extends Thread {
	
			private JLabel j;
			public logo(JLabel label)
			{
				j = label;
			}
			
			public  synchronized void run()
			{
				while(true) {
				for(int i = 0; i < 20; i+=1)
				{
					j.setBounds(j.getBounds().x , j.getBounds().y- 1,
							j.getBounds().width, j.getBounds().height);
					try {
						sleep(30);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				for(int i = 0; i < 20; i+=1)
				{
					j.setBounds(j.getBounds().x , j.getBounds().y+ 1,
							j.getBounds().width, j.getBounds().height);
					try {
						sleep(30);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				}
				
			}
}
