package dacGame;


import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import javax.swing.JProgressBar;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import java.awt.SystemColor;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JScrollBar;


public class Game extends JFrame {
	public Game() {
	}
	
	static final int MY_MINIMUM = 0;

	static final int MY_MAXIMUM = 100;
	

	
	
	ArrayList<PlayerThread> TeamA = new ArrayList<PlayerThread>();
	ArrayList<PlayerThread> TeamB = new ArrayList<PlayerThread>();
	PlayerThread p1a = new PlayerThread(TeamA,'A',0,"holding", true, 207, 199, 117, 128);
	PlayerThread p2a = new PlayerThread(TeamA,'A',1,"expecting", false, 355, 200, 117, 128);
	PlayerThread p3a = new PlayerThread(TeamA,'A',2,"standing" ,false, 480, 200, 117, 128);
	PlayerThread p4a = new PlayerThread(TeamA,'A',3,"standing", false, 605, 200, 117, 128);
	
	PlayerThread p1b = new PlayerThread(TeamB,'B',0,"holding", true, 300, 370, 117, 128);
	PlayerThread p2b = new PlayerThread(TeamB,'B',1,"expecting", false, 425, 370, 117, 128);
	PlayerThread p3b = new PlayerThread(TeamB,'B',2,"standing" ,false, 550, 370, 117, 128);
	PlayerThread p4b = new PlayerThread(TeamB,'B',3,"standing", false, 674, 370, 117, 128);
	
	PlayerThread HolderB = p1b;
	
	PlayerThread Holder = p1a;

	JProgressBar progressBar_1;
	JProgressBar progressBar_1_1;
	
	JScrollBar scrollBar;
	JScrollBar scrollBar_1;
	
	int randomA;
	int randomB;
	
	int scoreA = 0, scoreB = 0;
	int trackerA = 0, trackerB = 0;
	
	int timesClicked = 0;
	int timesClickedB = 0;
	JLabel num0a;
	
	
	JLabel PlayerThread2A = new JLabel("");
	
	JLabel PlayerThread1A = new JLabel("");
	
	pBar p, p1;
	
	public boolean hitA = false;
	public boolean hitB = false;
	public boolean firsttimeA = true;
	public boolean firsttimeB = true;

	
	String indicationA = "forth";
	String indicationB = "forth";
	
	
	static public boolean started = false;
	
	
	
	private JPanel contentPane;
	private JLabel num1a;
	private JLabel lblNewLabel_2;
	private JLabel num1b;
	private JLabel lblNewLabel_4;
	private JLabel num0b;
	private JLabel pause;
	private JLabel restart;
	private JLabel logo;
	logo l ;
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		
		
		
		Sound.playSound("C:\\Users\\david alaba\\Videos\\4K Video Downloader\\music\\background.wav", true);
		Sound.playSound("C:\\Users\\david alaba\\Videos\\4K Video Downloader\\music\\r.wav", true);
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Game frame = new Game();
					frame.setVisible(true);
					frame.startScreen();
					Timer timer = new Timer(30, new ActionListener() {
			            @Override
			            public void actionPerformed(ActionEvent e) {
			            
			            	if(started)
			            	{
			            		
			            		frame.init();
			            }}
			        });
					timer.start();
					frame.addKeyListener(new KeyAdapter() {
						@Override
						public void keyPressed(KeyEvent e) {
							if(e.getKeyCode() == 32)
							{
								
								frame.PlayA();
								
							}}
					});
					frame.addKeyListener(new KeyAdapter() {
						@Override
						public void keyPressed(KeyEvent e) {
							if(e.getKeyCode() == 10)
							{
							
								frame.PlayB();
						}}
					});
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	}
	
	 public void PlayA()
	{
		{
			if(randomA  - progressBar_1.getValue()*3 + 50 >= -18 &&
					randomA  - progressBar_1.getValue()*3 + 50 <= 18)
				hitA = true;
			
			if(hitA) {
				trackerA++;
					Sound.playSound("C:\\Users\\david alaba\\Videos\\4K Video Downloader\\music\\score.wav");
					if(timesClicked < 1)
					{
					p1a.throwBall();
					
					p1a.stand();
					
					synchronized(p2a)
					{
						p2a.notify();
					}
					timesClicked++;
					}
					else {
						synchronized(PlayerThread.holderA)
						{
							PlayerThread.holderA.notify();
						}
					}
					System.out.println(trackerA);
					if(trackerA == 6)
					{
						scoreA++;
						if(scoreA ==10)
						{
							num0a.setIcon(new ImageIcon(Game.class.getResource("/numbers/1.png")));
							num1a.setIcon(new ImageIcon(Game.class.getResource("/numbers/0.png")));
						}
						else {
						String location = "/numbers/"+scoreA +".png";
						num1a.setIcon(new ImageIcon(Game.class.getResource(location)));
						}
						trackerA =0;
					}
					
					
				
			//restarts the  progress bar
			
			p.stop();
			p = new pBar(progressBar_1);
			p.start();
			
			//sets a new x value for the scroll bar
			randomA = (int)(Math.random()*250);
			scrollBar.setBounds(randomA + 330, 110, 29, 50);

			
			hitA = false;
		}}
	}
	 public void PlayB()
		{
		 {
				if(randomB  - progressBar_1_1.getValue()*3 + 50 >= -18 &&
						randomB  - progressBar_1_1.getValue()*3 + 50 <= 18)
					hitB = true;
				
				if(hitB) {
					trackerB++;
						Sound.playSound("C:\\Users\\david alaba\\Videos\\4K Video Downloader\\music\\score.wav");
						if(timesClickedB < 1)
						{
						p1b.throwBall();
						
						p1b.stand();
						
						synchronized(p2b)
						{
							p2b.notify();
						}
						timesClickedB++;
						}
						else {
							synchronized(PlayerThread.holderB)
							{
								PlayerThread.holderB.notify();
							}
						}
						System.out.println(trackerB);
						if(trackerB == 6)
						{
							scoreB++;
							if(scoreB ==10)
							{
								num0b.setIcon(new ImageIcon(Game.class.getResource("/numbers/1.png")));
								num1b.setIcon(new ImageIcon(Game.class.getResource("/numbers/0.png")));
							}
							else {
							String location = "/numbers/"+scoreB +".png";
							num1b.setIcon(new ImageIcon(Game.class.getResource(location)));
							}
							trackerB =0;
						}
						
						
					
				//restarts the  progress bar
				
				p1.stop();
				p1 = new pBar(progressBar_1_1);
				p1.start();
				
				//sets a new x value for the scroll bar
				randomB = (int)(Math.random()*250);
				scrollBar_1.setBounds(330+randomB, 315, 29, 50);

				
				hitB = false;
			}}
		}
	 
	
	public void startScreen()
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 834, 530);
		contentPane = new JPanel();
		
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel start = new JLabel("");
		start.setIcon(new ImageIcon(Game.class.getResource("/numbers/start.png")));
		start.setBounds(350, 330, 150, 40);
		start.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				start.setIcon(null);
				logo.setIcon(null);
				started = true;
			
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				start.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				start.setBounds(350, 325, 150, 40);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				start.setBounds(350, 330, 150, 40);
			}
		});
		contentPane.add(start);
		
		logo = new JLabel("");
		logo.setIcon(new ImageIcon(Game.class.getResource("/numbers/logo.png")));
		logo.setBounds(150, 106, 627, 263);
		contentPane.add(logo);
		l = new logo(logo);
		
		
		JLabel background = new JLabel("");
		background.setIcon(new ImageIcon(Game.class.getResource("/TeamB/tumblr_p0veshDKMG1wp00e8o1_1280.png")));
		background.setBounds(-17, -89, 882, 644);
		contentPane.add(background);
		
	
		
		l.start();
		
	}
	 
	 

	/**
	 * Create the frame.
	 */
	public void init() {
		
		
		
		TeamA.add(p1a);
		TeamA.add(p2a);
		TeamA.add(p3a);
		TeamA.add(p4a);
		
		p1a.start();
		p2a.start();
		p3a.start();
		p4a.start();
		PlayerThread.setHolderA(p1a);
		
		TeamB.add(p1b);
		TeamB.add(p2b);
		TeamB.add(p3b);
		TeamB.add(p4b);
		
		p1b.start();
		p2b.start();
		p3b.start();
		p4b.start();
		PlayerThread.setHolderB(p1a);
		
		
       
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 834, 525);
		contentPane = new JPanel();
		
		
		
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		
		contentPane.add(p2a.getJstate());
		
		
		contentPane.add(p3a.getJstate());
		
		
		contentPane.add(p4a.getJstate());
		
		
		
		contentPane.add(p1a.getJstate());
		
		contentPane.add(p2b.getJstate());
		
		
		contentPane.add(p3b.getJstate());
		
		
		contentPane.add(p4b.getJstate());
		
		
		
		contentPane.add(p1b.getJstate());
		
		JLabel basketA = new JLabel("");
		basketA.setIcon(new ImageIcon(Game.class.getResource("/TeamA/basket.png")));
		basketA.setBounds(39, 77, 161, 238);
		contentPane.add(basketA);
		
		JLabel basketB = new JLabel("");
		basketB.setIcon(new ImageIcon(Game.class.getResource("/TeamA/basket.png")));
		basketB.setBounds(75, 240, 161, 238);
		contentPane.add(basketB);
		
		
		 randomA = (int)(Math.random()*250);
		 
		 
		 scrollBar = new JScrollBar();
		 scrollBar.setBackground(Color.RED);
		 scrollBar.setBounds(300 + randomA, 106, 29, 50);
		 contentPane.add(scrollBar);
		
		 progressBar_1 = new JProgressBar();
		 progressBar_1.setForeground(new Color(204, 51, 51));
	
		 p = new pBar(progressBar_1);
		 
		progressBar_1.setBackground(new Color(153, 255, 204));
		progressBar_1.setBounds(300, 120, 300, 25);
		progressBar_1.setMinimum(MY_MINIMUM);
		progressBar_1.setMaximum(MY_MAXIMUM);
		
		contentPane.add(progressBar_1);
		
		p.start();
		
		JLabel background = new JLabel("");
		
		
		
		 progressBar_1_1 = new JProgressBar();
		 p1 = new pBar(progressBar_1_1);
		 
		progressBar_1_1.setMinimum(0);
		progressBar_1_1.setMaximum(100);
		progressBar_1_1.setForeground(new Color(153, 255, 204));
		progressBar_1_1.setBackground(new Color(204, 51, 51));
		progressBar_1_1.setBounds(300, 327, 300, 25);
		contentPane.add(progressBar_1_1);
		p1.start();
		
		randomB = (int)(Math.random()*250);
		
		scrollBar_1 = new JScrollBar();
		scrollBar_1.setBackground(Color.RED);
		scrollBar_1.setBounds(300+randomB, 315, 29, 50);
		contentPane.add(scrollBar_1);
		
		num1a = new JLabel("New label");
		num1a.setIcon(new ImageIcon(Game.class.getResource("/numbers/0.png")));
		num1a.setBounds(85, 22, 46, 77);
		contentPane.add(num1a);
		
		num0a = new JLabel("New label");
		num0a.setIcon(new ImageIcon(Game.class.getResource("/numbers/0.png")));
		num0a.setBounds(39, 22, 46, 77);
		contentPane.add(num0a);
		
		num1b = new JLabel("New label");
		num1b.setIcon(new ImageIcon(Game.class.getResource("/numbers/0.png")));
		num1b.setBounds(723, 22, 46, 77);
		contentPane.add(num1b);
		
		num0b = new JLabel("New label");
		num0b.setIcon(new ImageIcon(Game.class.getResource("/numbers/0.png")));
		num0b.setBounds(677, 22, 46, 77);
		contentPane.add(num0b);
		
		pause = new JLabel("");
		pause.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				pause.setBounds(415, 13, 82, 77);
				pause.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				
			}
			@Override
			public void mouseExited(MouseEvent e) {
				pause.setBounds(415, 18, 82, 77);
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				p.suspend();
				p1.suspend();
				
					
				
			
			}
		});
		
		
		
		pause.setIcon(new ImageIcon(Game.class.getResource("/numbers/pause.png")));
		pause.setBounds(415, 18, 82, 77);
		contentPane.add(pause);
		
		restart = new JLabel("");
		restart.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				
				restart.setBounds(313, 13, 57, 77);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				restart.setBounds(313, 18, 57, 77);
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				
				p1.resume();
			
			}
		});
		restart.setIcon(new ImageIcon(Game.class.getResource("/numbers/restart.png")));
		restart.setBounds(313, 18, 57, 77);
		contentPane.add(restart);
		
		
		background.setIcon(new ImageIcon(Game.class.getResource("/TeamB/tumblr_p0veshDKMG1wp00e8o1_1280.png")));
		background.setBounds(-17, -89, 882, 644);
		contentPane.add(background);
		
		lblNewLabel_4 = new JLabel("New label");
		lblNewLabel_4.setBounds(0, 0, 46, 77);
		contentPane.add(lblNewLabel_4);
		
		lblNewLabel_2 = new JLabel("New label");
		lblNewLabel_2.setBounds(659, 22, 46, 77);
		contentPane.add(lblNewLabel_2);
		System.out.println("done");
	}
}
