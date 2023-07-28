package dacGame;

import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class  PlayerThread extends Thread{
	//direction : true = left to right
	//			 false = right to left
	
	private boolean direction;
	
	private char team;
	private ArrayList<PlayerThread> teamMembers;
	public int index;
	
	private JLabel Jstate = new JLabel("");
	
	public static PlayerThread holderA;
	public static PlayerThread holderB;
	
	public static boolean clicked = true;
	Semaphore s = new Semaphore(3, "game semaphore");
	public synchronized void run()
	{
		if(team == 'A') {
		while(true) {

			try {
				wait();
			} catch (InterruptedException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
		if(index == 1 || index == 2) {
		setDirectionA(!direction);
		}
		
		hold();
		setHolderA(this);
		
			try {
				wait();
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		throwBall();
		try {
			sleep(400);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(direction == true && index <=3)
		{
			setHolderA(teamMembers.get(index+1));
			synchronized(holderA)
				{
					holderA.notify();
					
					holderA.hold();
					
				}
		}
		if(direction == false && index >0)
		{
			setHolderA(teamMembers.get(index-1));
			synchronized(holderA)
				{
					holderA.notify();
				
					holderA.hold();
				
				}
		}
		stand();
	}}
		if(team == 'B')
		{
			while(true) {

				try {
					wait();
				} catch (InterruptedException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
			if(index == 1 || index == 2) {
			setDirectionA(!direction);
			}
			
			hold();
			setHolderB(this);
			
				try {
					wait();
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			throwBall();
			try {
				sleep(400);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(direction == true && index <=3)
			{
				setHolderB(teamMembers.get(index+1));
				synchronized(holderB)
					{
						holderB.notify();
						
						holderB.hold();
					}
			}
			if(direction == false && index >0)
			{
				setHolderB(teamMembers.get(index-1));
				synchronized(holderB)
					{
						holderB.notify();
					
						holderB.hold();
					}
			}
			stand();
		}
		}
	}
	
	
	
	public PlayerThread(ArrayList<PlayerThread> te,char t, int i, String s, boolean dir, int x, int y, int w, int h)
	{
		index = i;
		direction = dir;
		team = t;
		teamMembers = te;
		switch(s)
		{
		
		case "holding":{
			if(t == 'A')
			Jstate.setIcon(new ImageIcon(PlayerThread.class.getResource("/TeamA/PlayerHolding.gif")));
			if(t == 'B')
			Jstate.setIcon(new ImageIcon(PlayerThread.class.getResource("/TeamB/PlayerHoldingB.gif")));
			break;}
		case "expecting":{
			if(t == 'A')
			Jstate.setIcon(new ImageIcon(PlayerThread.class.getResource("/TeamA/PlayerExpectingInverted.gif")));
			if(t == 'B')
				Jstate.setIcon(new ImageIcon(PlayerThread.class.getResource("/TeamB/PlayerExpectingInvertedB.gif")));
			break;
			}
		case "standing":
		{	if(t == 'A')
			Jstate.setIcon(new ImageIcon(PlayerThread.class.getResource("/TeamA/PlayerInverted.gif")));
			if(t == 'B')
			Jstate.setIcon(new ImageIcon(PlayerThread.class.getResource("/TeamB/PlayerInvertedB.gif")));
			break;
		}
		}
		
		Jstate.setBounds(x, y, w, h);
	}
	
	public void throwBall()
	{
		if(direction)
		{
			if(team == 'A')
			Jstate.setIcon(new ImageIcon(PlayerThread.class.getResource("/TeamA/PlayerThrowing.gif")));
			if(team == 'B')
				Jstate.setIcon(new ImageIcon(PlayerThread.class.getResource("/TeamB/PlayerThrowingB.gif")));
			
		}
		else
		{
			if(team == 'A')
			Jstate.setIcon(new ImageIcon(PlayerThread.class.getResource("/TeamA/PlayerThrowingInverted.gif")));
			if(team == 'B')
				Jstate.setIcon(new ImageIcon(PlayerThread.class.getResource("/TeamB/PlayerThrowingInvertedB.gif")));
			
		}
		
	}
	
	
			
	
	public void hold()
	{
		if(direction)
		{   if(team == 'A')
			Jstate.setIcon(new ImageIcon(PlayerThread.class.getResource("/TeamA/PlayerHolding.gif")));
			if(team == 'B')
				Jstate.setIcon(new ImageIcon(PlayerThread.class.getResource("/TeamB/PlayerHoldingB.gif")));
			
		}
		else
		{	if(team == 'A')
			Jstate.setIcon(new ImageIcon(PlayerThread.class.getResource("/TeamA/PlayerHoldingInverted.gif")));
		if(team == 'B')
			Jstate.setIcon(new ImageIcon(PlayerThread.class.getResource("/TeamB/PlayerHoldingInvertedB.gif")));
		
		}
		}
	
	public void expect()
	{
		if(direction)
		{	if(team == 'A')
			Jstate.setIcon(new ImageIcon(PlayerThread.class.getResource("/TeamA/PlayerExpecting.gif")));
			if(team == 'B')
				Jstate.setIcon(new ImageIcon(PlayerThread.class.getResource("/TeamB/PlayerExpectinB.gif")));
			
		}
		else
		{	if(team == 'A')
			Jstate.setIcon(new ImageIcon(PlayerThread.class.getResource("/TeamA/PlayerExpectingInverted.gif")));
			if(team == 'B')
				Jstate.setIcon(new ImageIcon(PlayerThread.class.getResource("/TeamB/PlayerExpectingInvertedB.gif")));
			
		}
		}
	
	public void stand()
	{
		if(direction)
		{	if(team == 'A')
			Jstate.setIcon(new ImageIcon(PlayerThread.class.getResource("/TeamA/Player.gif")));
			if(team == 'B')
				Jstate.setIcon(new ImageIcon(PlayerThread.class.getResource("/TeamB/PlayerB.gif")));
			
		}
		else
		{	if(team == 'A')
			Jstate.setIcon(new ImageIcon(PlayerThread.class.getResource("/TeamA/PlayerInverted.gif")));
			if(team == 'B')
				Jstate.setIcon(new ImageIcon(PlayerThread.class.getResource("/TeamB/PlayerInvertedB.gif")));
			
		}
		}
	
	public void setDirectionA(boolean dir)
	{
		direction = dir;
	}
	
	public boolean getDirection()
	{
		return direction;
	}
	public JLabel getJstate()
	{
		return Jstate;
	}
	
	public static void setHolderA(PlayerThread p)
	{
		holderA = p;
	}
	public static void setHolderB(PlayerThread p)
	{
		holderB = p;
	}
	
}
