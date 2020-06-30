//Kyle Kumar
//KillBob.java
import javax.swing.*; import java.awt.*; import java.awt.event.*;
import java.util.Timer; import java.util.TimerTask;

public class KillBob extends JFrame implements ActionListener
{
	boolean immortal;//set this to true to make the player immortal (for testing purposes)
	JButton done,done2,prefs,done3,toStart,select;
	int  enemySpeed, bobSpeed,charHealth;
	int[] buttonx,buttony,sdeltax,sdeltay;
	LayoutPanel lpan;
	InstructPanel ipan; 
	GamePanel gpan;
	StartPanel span;
	PrefPanel ppan;
	JTextArea ilab;
	SelectPanel epan;
	Timer timer;
	TimerTask task;
	int playerProjSpeed,bulletRainSpeed,playerSpeed,pCount2;
	String selection;
	double[] projx,projy,enemyx,enemyy,xAdd,yAdd,enemyxAdd,enemyyAdd,enemyProjx,enemyProjy,enemyProjxAdd,enemyProjyAdd;
	double[] enemyShotDelay,stoe,stoP,bulletRainx, bulletRainy,fshotx,fshoty;
	double[][] bonusShotx,bonusShoty;
	boolean[][]bonusAir;
	double deltax,deltay,charx,chary;
	int killCount, enemyProjSpeed, playerHealth, bobHealth,lotlFrame;
	int pCount, playerFrame,aCount,guardFrame,projFrame,mFrame,win,guardHealth,bossHealth,fwait;
	double time,stox,stoy;
	int[] bulletCounter, enemyHealth,eCount;
	char lastKey;
	boolean[] inAir,fireAtWill, alive, rainBullets,fired,bonusShot,sto,aboutTo,fshotAir;
	boolean wkey,akey,skey,dkey, cAlive,fightingBob, bossPhase2,pattack,lookUp,testing;
	boolean paused;
	Image wizard1,wizard2,wizardB1,wizardB2,wizardB3,wizardL1,wizardL2,wizardR1,wizardR2;
	Image wizardBA, wizardFA,wizardRA,wizardLA,guard1,guard2,guardA,vproj1,vproj2,sproj;
	Image lotl1,lotl2,lotl3,lotla,lotlm1,lotlm2,lotlm3,lotlma,grass,ice,grave,glands;
	Image dgreen,gtrees,desert,dark,bforest,empty,beach,yforest,arrow;
	Image wiz,hunt,sorc,warr,knight,pally,priest,rogue,sin,trickster,necro,archer,mystic;
	Image merchant,iceShot,whiteShot,bproj1,bproj2,shopb;
	Image[] fshot;
	public static void main(String [] args)
	{
		KillBob kb = new KillBob();//runs the program by calling the constructor
	}
	public KillBob()//makes the JFrame and sets up variables
	{
		super("Game Project");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setSize(800,740);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		setResizable(false);
		immortal = false;
		lookUp=true;
		selection = "Wizard";
		setStuffUp();
		guard1 = new ImageIcon("images/Guard1.png").getImage();
		guard2 = new ImageIcon("images/Guard2.png").getImage();
		guardA = new ImageIcon("images/GuardA.png").getImage();
		vproj1 = new ImageIcon("images/VProj1.png").getImage();
		vproj2 = new ImageIcon("images/VProj2.png").getImage();
		sproj = new ImageIcon("images/SProj.png").getImage();
		lotl1 = new ImageIcon("images/LOTL1.png").getImage();
		lotl2 = new ImageIcon("images/LOTL2.png").getImage();
		lotl3 = new ImageIcon("images/LOTL3.png").getImage();
		lotla = new ImageIcon("images/LOTLA.png").getImage();
		lotlm1= new ImageIcon("images/LotlMinion1.png").getImage();
		lotlm2= new ImageIcon("images/LotlMinion2.png").getImage();
		lotlm3= new ImageIcon("images/LotlMinion3.png").getImage();
		lotlma= new ImageIcon("images/LotlMinionAttack.png").getImage();
		grass= new ImageIcon("images/GrassyB.png").getImage();
		ice= new ImageIcon("images/Ice.png").getImage();
		grave= new ImageIcon("images/Graveyard.png").getImage();
		glands= new ImageIcon("images/Glands.png").getImage();
		dgreen= new ImageIcon("images/DarkGreen.png").getImage();
		dark= new ImageIcon("images/Dark.png").getImage();
		desert= new ImageIcon("images/Desert.png").getImage();
		beach= new ImageIcon("images/Beach.png").getImage();
		gtrees= new ImageIcon("images/GreenTrees.png").getImage();
		bforest= new ImageIcon("images/BetterForest.png").getImage();
		empty= new ImageIcon("images/Empty.png").getImage();
		yforest= new ImageIcon("images/YellowForest.png").getImage();
		wiz= new ImageIcon("images/Wizard.png").getImage();
		warr= new ImageIcon("images/Warrior.png").getImage();
		sorc= new ImageIcon("images/Sorc.png").getImage();
		knight= new ImageIcon("images/Knight.png").getImage();
		pally= new ImageIcon("images/Pally.png").getImage();
		necro= new ImageIcon("images/Necro.png").getImage();
		sin= new ImageIcon("images/Assassin.png").getImage();
		rogue= new ImageIcon("images/Rogue.png").getImage();
		mystic= new ImageIcon("images/Mystic.png").getImage();
		archer= new ImageIcon("images/Archer.png").getImage();
		hunt= new ImageIcon("images/Huntress.png").getImage();
		priest= new ImageIcon("images/Priest.png").getImage();
		trickster= new ImageIcon("images/Trickster.png").getImage();
		arrow= new ImageIcon("images/Arrow.png").getImage();
		merchant= new ImageIcon("images/Merchant.png").getImage();
		whiteShot= new ImageIcon("images/WhiteShot.png").getImage();
		iceShot= new ImageIcon("images/IceShot.png").getImage();
		bproj1= new ImageIcon("images/BProj1.png").getImage();
		bproj2= new ImageIcon("images/BProj2.png").getImage();
		fshot = new Image[4];
		fshot[0]=new ImageIcon("images/4Shot0.png").getImage();
		fshot[1]=new ImageIcon("images/4Shot1.png").getImage();
		fshot[2]=new ImageIcon("images/4Shot2.png").getImage();
		fshot[3]=new ImageIcon("images/4Shot3.png").getImage();
		shopb = new ImageIcon("images/ShopButton.png").getImage();
		fshotAir = new boolean[4];
		fshotx = new double[4];
		fshoty = new double[4];
		charx = 400;
		chary = 620;//sets the initial position of the char
		projx = new double[5];
		projy = new double[5];//sets up an array to limit the character's bullets on screen to 5
		bonusShotx=new double[5][2];
		bonusShoty=new double[5][2];
		bonusAir = new boolean[5][2];
		bonusShot = new boolean[2];
		inAir = new boolean[5];
		xAdd = new double[5];
		yAdd = new double[5];//arrays for the movement of character bullets
		enemyx = new double[4];
		enemyy = new double[4];
		enemyxAdd=new double[4];
		enemyyAdd = new double[4];
		fireAtWill = new boolean[4];
		enemyProjx = new double[4];
		enemyProjy=new double[4];
		enemyProjyAdd = new double[4];
		enemyProjxAdd = new double[4];
		enemyShotDelay = new double[4];
		sto=new boolean[2];
		aboutTo = new boolean[10];
		fired = new boolean[4];
		stoe = new double[4];
		stoP = new double[4];
		eCount = new int[4];
		alive = new boolean[4];
		enemyHealth = new int[4];//sets up every array that relates to the enemy characters
		cAlive = true;//makes the character alive
		for (int i = 0; i<=3; i++) alive[i]=true;//makes every enemy alive
		for(int i = 0; i<=3; i++)
		{
			enemyx[i]=700*Math.random()+20;
			enemyy[i]=300*Math.random()+20;//sets the random coordinates of the enemies for practice mode
		}
		bulletRainx = new double[10];
		bulletRainy = new double[10];
		rainBullets = new boolean[10];
		bulletCounter= new int[10];//creates arrays for the rain of bullets in the boss room
		for(int i = 0; i<=9;i++)
		{
			bulletRainx[i]=55+73*i;
			bulletRainy[i]=70;//spaces the bullets apart and places them on the top of the screen
		}
		done = new JButton("done");
		done.setActionCommand("done");
		done.addActionListener(this);
		done.setBackground(Color.RED);
		done.setOpaque(true);
		done.setBorderPainted(false);
		done2 = new JButton("done");
		done2.setActionCommand("done");
		done2.addActionListener(this);
		done3 = new JButton("Back To Instructions");
		done3.setActionCommand("inst");
		done3.addActionListener(this);
		done3.setBackground(Color.RED);
		done3.setOpaque(true);
		done3.setBorderPainted(false);
		toStart = new JButton("Back To Start");
		toStart.addActionListener(this);
		toStart.setActionCommand("done");
		toStart.setBackground(Color.RED);
		toStart.setOpaque(true);
		toStart.setBorderPainted(false);
		done2.setPreferredSize(new Dimension(800,10));
		prefs = new JButton("Shop");
		prefs.setActionCommand("prefs");
		prefs.addActionListener(this);
		prefs.setBackground(Color.RED);
		prefs.setOpaque(true);
		prefs.setBorderPainted(false);
		select = new JButton("Character Select Screen");
		select.setActionCommand("select");
		select.addActionListener(this);
		select.setBackground(Color.RED);
		select.setOpaque(true);
		select.setBorderPainted(false);
		ilab = new InstructLabel();
		charHealth = 3;
		bossHealth = 100;
		guardHealth = 25;
		playerProjSpeed = 5;
		enemyProjSpeed = 9;
		bulletRainSpeed = 9;
		playerSpeed = 8;
		enemySpeed = 50;
		bobSpeed = 35;
		span = new StartPanel();
		gpan = new GamePanel();
		ipan = new InstructPanel();
		ppan = new PrefPanel();
		epan = new SelectPanel();
		lpan = new LayoutPanel();//sets field instances of the panels so that they can be referenced
		setContentPane(lpan);
		setVisible(true);//makes the main panel visible
		span.requestFocus();
	}
	public void setStuffUp()//defines all of the sprites for the selected character
	{
		wizard1 = new ImageIcon("images/"+selection+"1.png").getImage();
		wizard2 = new ImageIcon("images/"+selection+"2.png").getImage();
		wizardB1 = new ImageIcon("images/"+selection+"B1.png").getImage();
		wizardB2 = new ImageIcon("images/"+selection+"B2.png").getImage();
		wizardB3 = new ImageIcon("images/"+selection+"B3.png").getImage();
		wizardR1 = new ImageIcon("images/"+selection+"R1.png").getImage();
		wizardR2 = new ImageIcon("images/"+selection+"R2.png").getImage();
		wizardL1 = new ImageIcon("images/"+selection+"L1.png").getImage();
		wizardL2 = new ImageIcon("images/"+selection+"L2.png").getImage();
		wizardRA = new ImageIcon("images/"+selection+"RA.png").getImage();
		wizardBA = new ImageIcon("images/"+selection+"BA.png").getImage();
		wizardLA = new ImageIcon("images/"+selection+"LA.png").getImage();
		wizardFA = new ImageIcon("images/"+selection+"FA.png").getImage();
	}
	public void actionPerformed(ActionEvent e)//handles the buttons so that they do something
	{

		switch (e.getActionCommand()) {
			case "prefs":
				((CardLayout) (lpan.getLayout())).show(lpan, "prefs");//shows the panel with preferences
				break;
			case "select":
				((CardLayout) (lpan.getLayout())).show(lpan, "selection");
				epan.requestFocus();
				break;
			case "done":
				win = 0;
				((CardLayout) (lpan.getLayout())).show(lpan, "start");
				span.requestFocus();
				break;
			case "inst":
				((CardLayout) (lpan.getLayout())).show(lpan, "instruct");
				break;
		}
	}
	class StartPanel extends JPanel implements MouseListener,KeyListener//the start panel
	{
		public StartPanel()//sets up the layout of the start panel
		{
			addMouseListener(this);
			addKeyListener(this);
			buttonx = new int[3];
			buttony = new int[3];
			sdeltax= new int[3];
			sdeltay=new int[3];
			for(int i=0; i<=2; i++)
			{
				buttonx[i]=800/3;
				buttony[i]=(i*2+1)*740/9;
			}
		}
		public void paintComponent(Graphics g)//draws the buttons and the character on the start screen
		{
			super.paintComponent(g);
			g.setFont(new Font("serif",Font.BOLD,30));
			g.drawImage(grave,0,0,800,740,null);
			g.setColor(Color.WHITE);
			if(win==2)g.drawString("You Won!", 345, 50);
			if(win==1)g.drawString("You Died!", 335, 50);
			g.setColor(new Color(39,47,63));
			for(int i = 0; i<=2; i++) g.fillRect(buttonx[i],buttony[i],800/3,740/9);
			g.setColor(new Color(118,118,118));
			g.drawString("How To Play", buttonx[0]+50, buttony[0]+50);
			g.drawString("Practice",buttonx[1]+75,buttony[1]+50);
			g.drawString("Kill Bob", buttonx[2]+75, buttony[2]+50);
			if(playerFrame==0&&lastKey=='s'&&skey)g.drawImage(wizard1,(int)charx-25, (int)chary-25, 50, 50,null);
			else if(playerFrame==1&&lastKey=='s'&&skey)g.drawImage(wizard2,(int)charx-25, (int)chary-25, 50, 50,null);
			else if(playerFrame==0&&lastKey=='w'&&wkey)g.drawImage(wizardB2,(int)charx-25, (int)chary-25, 50, 50,null);
			else if(playerFrame==1&&lastKey=='w'&&wkey)g.drawImage(wizardB3,(int)charx-25, (int)chary-25, 50, 50,null);
			else if(playerFrame==0&&lastKey=='a'&&akey)g.drawImage(wizardL1,(int)charx-25, (int)chary-25, 50, 50,null);
			else if(playerFrame==1&&lastKey=='a'&&akey)g.drawImage(wizardL2,(int)charx-25, (int)chary-25, 50, 50,null);
			else if(playerFrame==0&&lastKey=='d'&&dkey)g.drawImage(wizardR1,(int)charx-25, (int)chary-25, 50, 50,null);
			else if(playerFrame==1&&lastKey=='d'&&dkey)g.drawImage(wizardR2,(int)charx-25, (int)chary-25, 50, 50,null);
			else if(playerFrame==0&&skey)g.drawImage(wizard1,(int)charx-25,(int) chary-25, 50, 50,null);
			else if(playerFrame==1&&skey)g.drawImage(wizard2,(int)charx-25, (int)chary-25, 50, 50,null);
			else if(playerFrame==0&&wkey)g.drawImage(wizardB2,(int)charx-25,(int) chary-25, 50, 50,null);
			else if(playerFrame==1&&wkey)g.drawImage(wizardB3,(int)charx-25,(int) chary-25, 50, 50,null);
			else if(playerFrame==0&&akey)g.drawImage(wizardL1,(int)charx-25,(int) chary-25, 50, 50,null);
			else if(playerFrame==1&&akey)g.drawImage(wizardL2,(int)charx-25,(int) chary-25, 50, 50,null);
			else if(playerFrame==0&&dkey)g.drawImage(wizardR1,(int)charx-25,(int) chary-25, 50, 50,null);
			else if(playerFrame==1&&dkey)g.drawImage(wizardR2,(int)charx-25,(int) chary-25, 50, 50,null);
			else if(lastKey=='s')g.drawImage(wizard1,(int)charx-25,(int) chary-25, 50, 50,null);
			else if(lastKey=='w')g.drawImage(wizardB1,(int)charx-25,(int) chary-25, 50, 50,null);
			else if(lastKey=='d')g.drawImage(wizardR1,(int)charx-25,(int) chary-25, 50, 50,null);
			else if(lastKey=='a')g.drawImage(wizardL1,(int)charx-25,(int) chary-25, 50, 50,null);
			else if(!lookUp) g.drawImage(wizard1,(int)charx-25, (int)chary-25, 50, 50,null);
			else g.drawImage(wizardB1,(int)charx-25,(int) chary-25, 50, 50,null);
		}
		public void mousePressed(MouseEvent e)//manages the moving buttons
		{
			int x = e.getX();
			int y = e.getY();
			if(x>=buttonx[0]&&x<=buttonx[0]+800/3&&y>=buttony[0]&&y<=buttony[0]+740/9)
			{
				((CardLayout)(lpan.getLayout())).show(lpan,"instruct");
				for(int i=0; i<=2; i++)
				{
					buttonx[i]=800/3;
					buttony[i]=(i*2+1)*740/9;
				}
				charx = 400;
				chary = 620;
			}
			else if(x>=buttonx[1]&&x<=buttonx[1]+800/3&&y>=buttony[1]&&y<=buttony[1]+740/9)
			{
				win=0;
				fightingBob=false;
				bobHealth=bossHealth;
				playerHealth=charHealth;
				paused=false;
				charx = 400;
				chary = 620;
				pattack=false;
				fwait=0;
				bossPhase2=false;
				for(int i=0; i<=2; i++)
				{
					buttonx[i]=800/3;
					buttony[i]=(i*2+1)*740/9;
				}
				for(int i = 0; i<=3; i++)
				{
					enemyy[i]=50;
					enemyx[i]=400.0/3*i+200;//sets the positions of the guards as constants
					enemyHealth[i]=guardHealth;
					fireAtWill[i]=false;
					fshotAir[i]=false;
					alive[i]=true;
				}
				((CardLayout)(lpan.getLayout())).show(lpan,"start");
				deltax=0;
				deltay=0;
				wkey=false;
				akey=false;
				skey=false;
				dkey=false;
				for(int i = 0; i<=3; i++)
					{
					inAir[i]=false;
					enemyShotDelay[i]=0;
					enemyProjy[i]=50;
					enemyProjx[i]=400.0/3*i+200;
					for(int j = 0; j<=1; j++)bonusAir[i][j]=false;
					}
				for(int i = 0; i<=9; i++) 
					{
					rainBullets[i]=false;
					bulletCounter[i]=0;
					bulletRainx[i]=55+73*i;
					bulletRainy[i]=70;
					aboutTo[i]=false;
				}//resets all relevant variables
				((CardLayout)(lpan.getLayout())).show(lpan,"game");//shows the panel displaying the game
				gpan.requestFocus();//makes the keyListener work
			}
			else if(x>=buttonx[2]&&x<=buttonx[2]+800/3&&y>=buttony[2]&&y<=buttony[2]+740/9)
			{
				if(!paused)
				{
				win=0;
				fightingBob=true;
				bobHealth=bossHealth;
				playerHealth=charHealth;
				charx = 400;
				chary = 620;
				for(int i=0; i<=2; i++)
				{
					buttonx[i]=800/3;
					buttony[i]=(i*2+1)*740/9;
				}
				pattack=false;
				for(int i = 0; i<=3; i++)
				{
					enemyy[i]=50;
					enemyx[i]=400.0/3*i+200;//sets the positions of the guards as constants
					enemyHealth[i]=guardHealth;
					alive[i]=true;//revives the guards in case this isn't the first fight
				}
				}
				if(paused)
				{
					charx=stox;
					chary=stoy;
					paused=false;
				}
				((CardLayout)(lpan.getLayout())).show(lpan,"game");//shows the panel displaying the game
				gpan.requestFocus();//makes the keyListener work
			}
		}
		public void mouseClicked(MouseEvent e) {}//has to be here
		public void mouseReleased(MouseEvent e) {}//has to be here
		public void mouseEntered(MouseEvent e) {}//has to be here
		public void mouseExited(MouseEvent e) {}//has to be here
		public void keyTyped(KeyEvent e)//finds out when a key is pressed
		{
			if(e.getKeyChar()== 'w')
			{
				wkey = true;
				lastKey='w';
			}
			else if (e.getKeyChar()=='a')
			{
				akey = true;
				lastKey='a';
			}
			else if(e.getKeyChar()=='s')
			{
				skey = true;
				lastKey='s';
			}
			else if(e.getKeyChar()=='d')
			{
				dkey = true;
				lastKey='d';
			}//checks to see what relevant keys are being held down
		}
		public void keyPressed(KeyEvent e) {}//has to exist
		public void keyReleased(KeyEvent e) //finds out when key is released
		{
			if(e.getKeyChar()=='w')wkey = false;
			else if (e.getKeyChar()=='a')akey = false;
			else if(e.getKeyChar()=='s')skey = false;
			else if(e.getKeyChar()=='d')dkey = false;//checks to see if a key is no longer being held down
		}
	}
	class LayoutPanel extends JPanel//the panel that manages the CardLayout
	{
		public LayoutPanel()//sets up the CardLayout
		{
			CardLayout cl = new CardLayout();
			setLayout(cl);
			add(span, "start");
			add(ipan,"instruct");
			add(gpan, "game");
			add(ppan,"prefs");
			add(epan,"selection");
			cl.show(this,"start");//adding the panels to the CardLayout and showing the start panel
		}
	}
	class InstructPanel extends JPanel//instruction panel
	{
		public InstructPanel()//sets up the instruction panel
		{
			setLayout(new BorderLayout());
			add(ilab,BorderLayout.CENTER);
			add(prefs,BorderLayout.NORTH);//soon to be customizable with JSliders so that player decides settings
			add(done,BorderLayout.SOUTH);//positions the buttons on the top or bottom
		}
		public void paintComponent(Graphics g)//writes the background
		{
			super.paintComponent(g);
			g.drawImage(empty,0,0,800,740,null);
		}
	}
	class InstructLabel extends JTextArea//writes the insctuctions for the player
	{
		public InstructLabel()//contains the instructions and sets their font
		{
			setFont(new Font("sans-serif",Font.BOLD,18));
			setOpaque(false);
			setText("General Instructions:\n\nUse WASD to move and aim with the mouse\nA shot is fired when the mouse is pressed"
			+ "\nYou may not leave the play area(it is impossible)"
			+ "\nNo projectiles can leave the play area(also impossible)\n\nPractice Mode:\nFour enemies shoot and move at the player\n"
			+ "Enemies die after being hit once\nWhen all four enemies are killed they will repsawn\nYou get one point for killing an enemy"
			+ "\nIf the player is hit, they will return to the spawn location and lose one point\n\n\n\n\nKill Bob Mode:\nFour guards protect the boss\nEach guard has"
			+ " 25 health and shoots at the player\nWhen all four guards die the boss will spawn, it has 100 health"
			+ "\nPress 'p' to pause the game. Entering practice mode will delete any paused game\n\nShop:\nYou can buy upgrades to your weapon or debuffs for enemies"
			+ "\nExtra lives only work in \"Kill Bob\" mode");
		}//write the instructions
	}
	class PrefPanel extends JPanel implements MouseListener//creates the shop
	{
		String life,shot,slowSpeed,slowProj;
		public PrefPanel()//sets up the preferences panel
		{
			add(done3);
			add(toStart);
			add(select);
			addMouseListener(this);
			life = "+1 Life - 20 points";
			shot = "+1 Bullet - 50 points";
			slowSpeed = "Decrease Enemy Move Speed - 10 points";
			slowProj = "Decrease Enemy Shot Speed - 15 points";
		}
		public void paintComponent(Graphics g)//draws the shop
		{
			super.paintComponent(g);
			g.drawImage(bforest,0,0,800,740,null);
			g.drawImage(merchant,40,300,125,125,null);
			g.setColor(Color.CYAN);
			g.fillRect(100,600,300,100);
			g.setColor(new Color(209,223,72));
			g.fillRect(230,270,550,40);
			g.fillRect(230,330,550,40);
			g.fillRect(230,390,550,40);
			g.fillRect(230, 450, 550, 40);
			g.setFont(new Font("serif", Font.BOLD, 30));
			g.setColor(Color.BLACK);
			g.drawString(life,235,300);
			g.drawString(shot, 235, 360);
			g.drawString(slowSpeed, 235, 420);
			g.drawString(slowProj, 235, 480);
			g.setFont(new Font("serif", Font.BOLD, 50));
			g.drawString("Points: " + killCount, 120, 665);
			g.drawRect(230,270,550,40);
			g.drawRect(230,330,550,40);
			g.drawRect(230,390,550,40);
			g.drawRect(230, 450, 550, 40);
			g.drawImage(shopb,170,270,50,50,null);
			g.drawImage(shopb,170,330,50,50,null);
			g.drawImage(shopb,170,390,50,50,null);
			g.drawImage(shopb,170,450,50,50,null);
			
		}
		public void mousePressed(MouseEvent e)//makes the shop work
		{
			int x = e.getX();
			int y = e.getY();
			if(x>=170&&x<=170+50&&y>=270&&y<=270+50&&killCount>=20&&!life.equals("Maxed Life"))
			{
				charHealth++;
				killCount-=20;
				if(charHealth>=10)life = "Maxed Life";
			}
			else if(x>=170&&x<=170+50&&y>=330&&y<=330+50&&killCount>=50&&!shot.equals("Maxed Bullets"))
			{
				killCount-=50;
				if(!bonusShot[0])
					{
					bonusShot[0]=true;
					sto[0]=true;
					}
				else if(!bonusShot[1])
					{
					bonusShot[1]=true;
					sto[1]=true;
					shot="Maxed Bullets";
					}
			}
			else if(x>=170&&x<=170+50&&y>=390&&y<=390+50&&killCount>=10&&!slowSpeed.equals("Minimum Enemy Move Speed"))
			{
				enemySpeed-=3;
				killCount-=10;
				if(enemySpeed<=20)slowSpeed="Minimum Enemy Move Speed";
			}
			else if(x>=170&&x<=170+50&&y>=450&&y<=450+50&&killCount>=15&&!slowProj.equals("Minimum Enemy Shot Speed"))
			{
				enemyProjSpeed-=1;
				killCount-=15;
				if(enemyProjSpeed<=4)slowProj="Minimum Enemy Shot Speed";
			}
			repaint();
		}
		public void mouseClicked(MouseEvent e) {}//has to be here
		public void mouseReleased(MouseEvent e) {}//has to be here
		public void mouseEntered(MouseEvent e) {}//has to be here
		public void mouseExited(MouseEvent e) {}//has to be here
	}
	class SelectPanel extends JPanel implements MouseListener//displays character selection
	{
		public SelectPanel()//adds the listener
		{
			addMouseListener(this);
		}
		public void paintComponent(Graphics g)//draws the characters
		{
			super.paintComponent(g);
			g.setColor(Color.BLUE);
			g.drawImage(ice, 0,0,800,740,null);
			if(selection.equals("Hunt"))g.fillRect(0,0,200,185);
			g.drawImage(hunt, 10,20,180,165,null);
			if(selection.equals("Necro"))g.fillRect(200,0,200,185);
			g.drawImage(necro, 210,20,180,165,null);
			if(selection.equals("Mys"))g.fillRect(400,0,200,185);
			g.drawImage(mystic, 410,20,180,165,null);
			if(selection.equals("Pally"))g.fillRect(600,0,200,185);
			g.drawImage(pally, 610,20,180,165,null);
			if(selection.equals("Knight"))g.fillRect(0,185,200,185);
			g.drawImage(knight, 10,195,180,165,null);
			if(selection.equals("Warr"))g.fillRect(200,185,200,185);
			g.drawImage(warr, 210,195,180,165,null);
			if(selection.equals("Trick"))g.fillRect(400,185,200,185);
			g.drawImage(trickster, 410,195,180,165,null);
			if(selection.equals("Sin"))g.fillRect(600,185,200,185);
			g.drawImage(sin, 610,195,180,165,null);
			if(selection.equals("Rogue"))g.fillRect(0,370,200,175);
			g.drawImage(rogue, 10,380,180,165,null);
			if(selection.equals("Priest"))g.fillRect(200,370,200,175);
			g.drawImage(priest, 210,380,180,165,null);
			if(selection.equals("Sorc"))g.fillRect(400,370,200,175);
			g.drawImage(sorc, 410,380,180,165,null);
			if(selection.equals("Archer"))g.fillRect(600,370,200,175);
			g.drawImage(archer, 610,380,180,165,null);
			if(selection.equals("Wizard"))g.fillRect(0,545,800,185);
			g.drawImage(wiz, 310,555,180,165,null);
			g.setColor(Color.BLACK);
			g.fillRect(0,0,800,15);
			g.setColor(Color.CYAN);
			g.setFont(new Font("serif", Font.BOLD, 18));
			g.drawString("Done", 380, 15);
		}
		public void mousePressed(MouseEvent e) //makes the char selection work
		{
			int x=e.getX();
			int y = e.getY();
			if(y<15)
			{
			((CardLayout)(lpan.getLayout())).show(lpan,"prefs");
			setStuffUp();
			}
			else if(x>=0&&x<=200&&y<=185) selection = "Hunt";
			else if(x>=200&&x<=400&&y<=185) selection = "Necro";
			else if(x>=400&&x<=600&&y<=185) selection = "Mys";
			else if(x>=600&&y<=185) selection = "Pally";
			else if(x>=0&&x<=200&&y<=370) selection = "Knight";
			else if(x>=200&&x<=400&&y<=370) selection = "Warr";
			else if(x>=400&&x<=600&&y<=370) selection = "Trick";
			else if(x>=600&&x<=800&&y<=370) selection = "Sin";
			else if(x>=0&&x<=200&&y<=565) selection = "Rogue";
			else if(x>=200&&x<=400&&y<=565) selection = "Priest";
			else if(x>=400&&x<=600&&y<=565) selection = "Sorc";
			else if(x>=600&&x<=800&&y<=565) selection = "Archer";
			else if(y>=566) selection = "Wizard";
			repaint();
		}
		public void mouseReleased(MouseEvent e) {}//has to be here
		public void mouseEntered(MouseEvent e) {}//has to be here
		public void mouseExited(MouseEvent e) {}//has to be here
		public void mouseClicked(MouseEvent e) {}//has to be here
	}
	class GamePanel extends JPanel implements MouseListener, KeyListener//the panel that displays the game
	{
		public GamePanel()//sets up the game
		{
			timer = new Timer();
			task = new TimerTask() {//creates a fixed tick rate for the game
				public void run() {//has to be included
					if(gpan.hasFocus())//makes it so that the game panel does not run when the program starts
					{
						if(fightingBob&&bossPhase2)
						{
						fwait++;
						if(fwait>=500*9/enemyProjSpeed)
						{
							for(int i = 0; i<=3; i++)fshotAir[i]=true;
							fshotx[1]=enemyx[0];
							fshotx[3]=enemyx[0];
							fshoty[2]=enemyy[0];
							fshoty[0]=enemyy[0];
							fshotx[0]=enemyx[0]+100-enemyProjSpeed*.1;
							fshotx[2]=enemyx[0]+enemyProjSpeed*.1-39;
							fshoty[1]=enemyy[0]+100-enemyProjSpeed*.1;
							fshoty[3]=enemyy[0]+enemyProjSpeed*.1-39;
							fwait=0;
						}
							if(fshotx[0]<=10||fshoty[0]<=10||fshotx[0]+39>=790||fshoty[0]+99>=790)fshotAir[0]=false;
							if(fshotx[1]<=10||fshoty[1]<=10||fshotx[1]+99>=790||fshoty[1]+39>=790)fshotAir[1]=false;
							if(fshotx[2]<=10||fshoty[2]<=10||fshotx[2]+39>=790||fshoty[2]+99>=790)fshotAir[2]=false;
							if(fshotx[3]<=10||fshoty[3]<=10||fshotx[3]+99>=790||fshoty[3]+39>=790)fshotAir[3]=false;
							if(fshotx[0]<=charx+25&&fshotx[0]+39>=charx-25&&fshoty[0]<=chary+25&&fshoty[0]+99>=chary-25&&fshotAir[0])
							{
								fshotAir[0]=false;
								if(!immortal)playerHealth--;
							}
							if(fshotx[1]<=charx+25&&fshotx[1]+99>=charx-25&&fshoty[1]<=chary+25&&fshoty[1]+39>=chary-25&&fshotAir[1])
							{
								fshotAir[1]=false;
								if(!immortal)playerHealth--;
							}
							if(fshotx[2]<=charx+25&&fshotx[2]+39>=charx-25&&fshoty[2]<=chary+25&&fshoty[2]+99>=chary-25&&fshotAir[2])
							{
								fshotAir[2]=false;
								if(!immortal)playerHealth--;
							}
							if(fshotx[3]<=charx+25&&fshotx[3]+99>=charx-25&&fshoty[3]<=chary+25&&fshoty[3]+39>=chary-25&&fshotAir[3])
							{
								fshotAir[3]=false;
								if(!immortal)playerHealth--;
							}
							if(fshotAir[0])fshotx[0]+=enemyProjSpeed*.15;
							if(fshotAir[1])fshoty[1]+=enemyProjSpeed*.15;
							if(fshotAir[2])fshotx[2]-=enemyProjSpeed*.15;
							if(fshotAir[3])fshoty[3]-=enemyProjSpeed*.15;
						}
						pCount++;
						if(pCount>=50)
						{
							pCount=0;
							playerFrame++;
							guardFrame++;
							projFrame++;
							lotlFrame++;
							mFrame++;
							if(lotlFrame==2)lotlFrame=0;
							if(projFrame==2) projFrame=0;
							if(guardFrame==2)guardFrame=0;
							if(playerFrame==2)playerFrame=0;
							if(mFrame==3)mFrame=0;
						}
						if(pattack)
						{
							aCount++;
							if(aCount==20)
								{
								pattack=false;
								aCount=0;
								}
						}
						for(int i = 0; i<=3; i++)
						{
							if(fired[i])
							{
							eCount[i]++;
							if(eCount[i]==40)
							{
								fired[i]=false;
								eCount[i]=0;
							}
							}
						}
						if(fightingBob)
						{
							for(int i = 0; i<=9;i++)
							{
								if(!rainBullets[i]) bulletCounter[i]++;//developer comments below
								if(bulletCounter[i]==100*(i+1)-95) aboutTo[i]=true;
								if(bulletCounter[i]==/*this number is 10*n */100*(i+1))
									{
									rainBullets[i]=true;
									aboutTo[i]=false;
									}
								if(bulletCounter[i]==/*this number is 100*n +1*/1001) bulletCounter[i]=0;
								if(rainBullets[i]) bulletRainy[i]+=bulletRainSpeed/40.0+bulletRainy[i]/130;//change this to change bullet rain speed
								if(bulletRainy[i]>=790)
									{
									rainBullets[i]=false;
									bulletRainy[i]=70;//stops the projectiles from leaving the screen
									}
							}
							
						}
					for(int i = 0; i<=3;i++)
					{
						if(!fireAtWill[i]) enemyShotDelay[i]++;//counts the delay only if the bullets is not in the air
						if(enemyShotDelay[i] == 100) 
							{
								fireAtWill[i] = true;//sets the bullet in the air
								fired[i]=true;
								enemyProjx[i]=enemyx[i];
								enemyProjy[i]=enemyy[i];
								stoe[i]=enemyx[i];
								stoP[i] = charx;
								enemyProjxAdd[i]=.5*Math.cos(Math.atan((chary-enemyy[i])/(enemyx[i]-charx)));
								enemyProjyAdd[i]= .5*-1*Math.sin(Math.atan((chary-enemyy[i])/(enemyx[i]-charx)));
								enemyShotDelay[i] = 0;//calculates how the enemy shots should be aimed to go towards the player
							}
					}
					
					deltay=0;
					deltax=0;
					if(wkey) deltay-=1.65;
					if(akey) deltax-=1.65;
					if(skey) deltay+=1.65;
					if(dkey) deltax+=1.65;
					charx+=deltax;
					chary+=deltay;//moves the player according to keys pressed
					if(fightingBob&&bossPhase2)//boss phase 2 means that kim is being fought
					{
						for(int i = 0; i<=3; i++)
						{//enemySpeed.getValue()/100.0
						enemyxAdd[i]= bobSpeed/100.0*Math.cos(Math.atan((chary-enemyy[0]-50)/(enemyx[0]+50-charx)));
						enemyyAdd[i]= bobSpeed/100.0*-1*Math.sin(Math.atan((chary-enemyy[0]-50)/(enemyx[0]+50-charx)));
						if(charx>enemyx[0]+50)//calculates how the Kim should move in a bossfight
						{
						enemyx[i]+=enemyxAdd[i];
						enemyy[i]+=enemyyAdd[i];
						}
						else
						{
							enemyx[i]-=enemyxAdd[i];
							enemyy[i]-=enemyyAdd[i];
						}//this if exists to negate the sign change of cos and sin based on quadrant
						}
					}
						for(int i = 0; i<=3; i++)
						{
							if(!fightingBob)//means if in practice mode
							{
								enemyxAdd[i]= enemySpeed/100.0*Math.cos(Math.atan((chary-enemyy[i])/(enemyx[i]-charx)));
								enemyyAdd[i]= enemySpeed/100.0*-1*Math.sin(Math.atan((chary-enemyy[i])/(enemyx[i]-charx)));
								if(charx>enemyx[i])//calculates how the enemies in practice move
								{
								enemyx[i]+=enemyxAdd[i];
								enemyy[i]+=enemyyAdd[i];
								}
								else
								{
									enemyx[i]-=enemyxAdd[i];
									enemyy[i]-=enemyyAdd[i];
								}//same reason as the other if statements for adding
							}
						if(enemyProjx[i]>=790||enemyProjx[i]<=10||enemyProjy[i]>=790||enemyProjy[i]<=10)fireAtWill[i]=false;
						//stops any projectile that leaves the bounds of the screen
						if(!fireAtWill[i])
						{
							enemyProjx[i]=-1000;
							enemyProjy[i]=-1000;//takes the projectiles off the screen if not being fired-
						}//they will not be drawn anyway but their hitbox may be considered so they need to-
						else//be off the screen
						{
							if(stoP[i]>stoe[i])//two storage values so that the bullets don't glitch
							{
								enemyProjx[i]+=enemyProjSpeed*enemyProjxAdd[i];
								enemyProjy[i]+=enemyProjSpeed*enemyProjyAdd[i];
							}
							else
							{
								enemyProjx[i]-=enemyProjSpeed*enemyProjxAdd[i];
								enemyProjy[i]-=enemyProjSpeed*enemyProjyAdd[i];
							}//same reason as other if statements on bullet adding
						}
					}
						time+=Math.PI/50;
					for(int i = 0; i<=4; i++) {
					projx[i]+=playerProjSpeed*xAdd[i];
					projy[i]+=playerProjSpeed*yAdd[i];//sets the adding of the character's projectiles
					}
					for(int i = 0; i<=4; i++)
					{
					if(time>=2*Math.PI)time=0;
							if(bonusAir[i][0])
							{
								bonusShotx[i][0]=projx[i]+40*Math.sin(time);
								bonusShoty[i][0]=projy[i]+40*Math.cos(time);
							}
							if(bonusAir[i][1])
							{
								bonusShotx[i][1]=projx[i]+40*Math.cos(time);
								bonusShoty[i][1]=projy[i]+40*Math.sin(time);
							}//move this to be after the calculation of regular proj
					if(projx[i]>=790||projx[i]<=10||projy[i]>=790||projy[i]<=10)inAir[i] = false;//takes projectiles off the screen if they are outside the boundary
					for(int j = 0; j<=1; j++)if(bonusShotx[i][j]>=790||bonusShotx[i][j]<=10||bonusShoty[i][j]>=790||bonusShoty[i][j]<=10)bonusAir[i][j]=false;
					}
					if(fightingBob)//only for boss fight mode
					{
						for(int i = 0; i<=4; i++)
						{
							for(int j = 0; j<=3; j++)
							{
							if(projx[i]+15>=enemyx[j]-25&&projx[i]<=enemyx[j]+50&&projy[i]+15>=enemyy[j]&&projy[i]<=enemyy[j]+75&&inAir[i]&&alive[j]&&!bossPhase2)
							{//restricted to phase 1(guards)
								inAir[i]=false;
								enemyHealth[j]--;//takes the bullet off the screen and and ticks down enemy health if they're hit
							}
							if(projx[i]>=enemyx[0]&&projx[i]<=enemyx[0]+100&&projy[i]>=enemyy[0]&&projy[i]<=enemyy[0]+100&&bossPhase2&&inAir[i])
							{//restricted to bob fight
								bobHealth--;
								inAir[i]=false;//takes the bullet off the screen and ticks down bob's health if he's hit
							}
							for(int k = 0; k<=1; k++)
							{
								if(bonusShotx[i][k]+15>=enemyx[j]-25&&bonusShotx[i][k]<=enemyx[j]+50&&bonusShoty[i][k]+15>=enemyy[j]&&bonusShoty[i][k]<=enemyy[j]+75&&bonusAir[i][k]&&alive[j]&&!bossPhase2&&bonusShot[k])
								{
									bonusAir[i][k]=false;
									enemyHealth[j]--;
								}
								if(bonusShotx[i][k]>=enemyx[0]&&bonusShotx[i][k]<=enemyx[0]+100&&bonusShoty[i][k]>=enemyy[0]&&bonusShoty[i][k]<=enemyy[0]+100&&bossPhase2&&bonusAir[i][k]&&bonusShot[k])
								{
									bobHealth--;
									bonusAir[i][k]=false;
								}
							}}}
						}
					if(!fightingBob)//only for practice mode
					{
						for(int i = 0; i<=3; i++)
						{
							for(int j = 0; j<=4; j++)
							{
								if(projx[j]+15>=enemyx[i]&&projx[j]<=enemyx[i]+60&&projy[j]+15>=enemyy[i]&&projy[j]<=enemyy[i]+40&&alive[i]&&inAir[j])
									{
										alive[i]=false;
										inAir[j]=false;
										killCount++;//takes the bullet off the screen and kills the enemy if hit
									}
								for(int k = 0; k<=1;k++)
								{
									if(bonusShotx[j][k]+15>=enemyx[i]&&bonusShotx[j][k]<=enemyx[i]+60&&bonusShoty[j][k]+15>=enemyy[i]&&bonusShoty[j][k]<=enemyy[i]+40&&alive[i]&&bonusAir[j][k]&&bonusShot[k])
									{
										alive[i]=false;
										bonusAir[j][k]=false;
										killCount++;//takes the bullet off the screen and kills the enemy if hit
									}
								}
							}
						}
						if(!alive[0]&&!alive[1]&&!alive[2]&&!alive[3]&&!fightingBob)
						{
							for(int i = 0; i<=3; i++)
							{
								enemyx[i]=700*Math.random()+20;
								enemyy[i]=300*Math.random()+20;
								alive[i]=true;//sets the spawn location of the next round of practice enemies if one dies
								fireAtWill[i]=false;
								enemyShotDelay[i]=-5;
							}
						}
					}
					for(int i = 0; i<=3; i++)
					{
						if(enemyHealth[i]<=0&&fightingBob&&!bossPhase2&&alive[i]) 
							{
							alive[i]=false;//kills the guard if it has no health
							killCount+=3;
							}
						
						if(enemyProjx[i]>=charx-35&&enemyProjx[i]<=charx+25&&enemyProjy[i]>=chary-35&&enemyProjy[i]<=chary+25&&fireAtWill[i]&&alive[i])
							{
							fireAtWill[i]=false;//stops the bullet if it hits an enemy
							if(!fightingBob&&!immortal)
							{
							killCount--;
							charx=400;
							chary=620;//if in practice resets the position of the character
							}
							else if(!immortal)playerHealth--;//if in the bossfight it ticks down the player health
							}
					}
					for(int i = 0; i<=9; i++)
					{
						if(bulletRainx[i]>=charx-35&&bulletRainx[i]<=charx+25&&bulletRainy[i]>=chary-35&&bulletRainy[i]<=chary+25&&rainBullets[i])
						{
							if(!immortal)playerHealth--;
							rainBullets[i]=false;//if a bulletRain projectile hits the player, it is no longer drawn and the player-
						}//-takes damage
					}
					if(playerHealth<=0||(bobHealth<=0&&bossPhase2))//this mean if bob is dead during the second phase of the fight
						{
						((CardLayout)(lpan.getLayout())).show(lpan,"start");
						deltax=0;
						deltay=0;
						charx=400;
						chary=620;
						wkey=false;
						akey=false;
						skey=false;
						dkey=false;
						bossPhase2=false;
						fwait=0;
						for(int i = 0; i<=3; i++)fshotAir[i]=false;
						if(playerHealth<=0)win=1;
						if(bobHealth<=0)
							{
							win=2;
							killCount+=100;
							}
						for(int i = 0; i<=3; i++)
							{
							inAir[i]=false;
							enemyShotDelay[i]=0;
							enemyProjy[i]=50;
							enemyProjx[i]=400.0/3*i+200;
							fireAtWill[i]=false;
							for(int j = 0; j<=1; j++)bonusAir[i][j]=false;
							}
						for(int i = 0; i<=9; i++) 
							{
							rainBullets[i]=false;
							bulletCounter[i]=0;
							bulletRainx[i]=55+73*i;
							bulletRainy[i]=70;
							aboutTo[i]=false;
						}//resets all relevant variables
						span.requestFocus();//makes gpan.hasFocus() become false
						}
					if(!alive[0] && !alive[1] && !alive[2] && !alive[3] && !bossPhase2)
					{
						bossPhase2 = true;
						bobHealth=bossHealth;
						enemyx[0]=350;
						enemyy[0]=350;
						enemyx[2]=450;
						enemyy[2]=450;
						enemyx[1]=350;
						enemyy[1]=450;
						enemyx[3]=450;
						enemyy[3]=350;
						for(int i = 0; i<=3; i++)
						{
							alive[i]=true;
							enemyShotDelay[i]=-300;
							fireAtWill[i]=false;
						}
						for(int i = 0; i<=9; i++) 
							{
							bulletCounter[i]=0;
							rainBullets[i]=false;
							bulletRainx[i]=55+73*i;
							bulletRainy[i]=70;
							aboutTo[i]=false;
							}
						
					}//removes the guards when they die and sets up the final boss
					if(charx-25<10)charx=10+25;
					if(charx+25>790)charx=790-25;
					if(chary-25<10)chary=10+25;
					if(chary+25>710)chary=710-25;//stops the player from leaving the play area
					if(fightingBob&&bossPhase2)
					{
							if(enemyx[0]<10) 
							{
								enemyx[0]=10;
								enemyx[1]=10;
								enemyx[2]=110;
								enemyx[3]=110;
							}
							if(enemyx[0]>690)
							{
								enemyx[0]=690;
								enemyx[1]=690;
								enemyx[2]=790;
								enemyx[3]=790;
							}
							if(enemyy[0]>610)
							{
								enemyy[0]=610;
								enemyy[1]=710;
								enemyy[2]=710;
								enemyy[3]=610;
							}
							if(enemyy[0]<10)
							{
								enemyy[0]=10;
								enemyy[1]=110;
								enemyy[2]=110;
								enemyy[3]=10;
							}//stops the final boss from leaving the play area
					}
					if(!fightingBob)
					{
						for(int i = 0; i<=3; i++)
						{
							if(enemyx[i]<40) enemyx[i]=40;
							if(enemyx[i]>760) enemyx[i]=760;
							if(enemyy[i]<40) enemyy[i]=40;
							if(enemyy[i]>680) enemyy[i]=680;
						}//stops the practice enemies from leaving the play area
					}
					repaint();
			}
			else if(span.hasFocus())
			{
				
				deltay=0;
				deltax=0;
				if(wkey) deltay-=1.65;
				if(akey) deltax-=1.65;
				if(skey) deltay+=1.65;
				if(dkey) deltax+=1.65;
				charx+=deltax;
				chary+=deltay;
				pCount2++;
				if(pCount2>=60)
				{
					pCount2=0;
					playerFrame++;
					if(playerFrame==2)playerFrame=0;
				}
				if(charx-25<10)charx=10+25;
				if(charx+25>790)charx=790-25;
				if(chary-25<10)chary=10+25;
				if(chary+25>710)chary=710-25;
				for(int i = 0; i<=2; i++)
				{
					sdeltax[i]=0;
					sdeltay[i]=0;
				}
				for(int i = 0; i<=2; i++)
				{
					if(charx+25>=buttonx[i]&&charx-25<=buttonx[i]+800.0/3&&chary-25<=buttony[i]+740.0/9&&chary+25>=buttony[i])
					{
						if(charx-deltax+25<=buttonx[i])
						{
							sdeltax[i]++;
							charx--;
							charx=buttonx[i]-25;
						}
						if(charx-deltax-25>=buttonx[i]+800.0/3)
						{
							sdeltax[i]--;
							charx++;
							charx = buttonx[i]+25+800.0/3;
						}
						if(chary-deltay+25<=buttony[i])
						{
							sdeltay[i]++;
							chary--;
							chary=buttony[i]-25;
						}
						if(chary-deltay-25>=buttony[i]+740.0/9)
						{
							sdeltay[i]--;
							chary++;
							chary=buttony[i]+25+740.0/9;
						}
					}
				}
				for(int i=0; i<=2; i++)
				{
					buttonx[i]+=sdeltax[i];
					buttony[i]+=sdeltay[i];
				}
				for(int i=0; i<=2;i++)
				{
					if(buttonx[i]<10)buttonx[i]=10;
					if(buttonx[i]+800/3>780)buttonx[i]=780-800/3;
					if(buttony[i]<10)buttony[i]=10;
					if(buttony[i]+740/9>=710)buttony[i]=710-740/9;
				}
				if(sdeltax[1]!=0||sdeltay[1]!=0)
				{
					if(buttonx[1]+800/3>=buttonx[0]&&buttonx[1]<=buttonx[0]+800/3&&buttony[1]+740/9>=buttony[0]&&buttony[1]<=buttony[0]+740/9)
					{
						if(buttonx[1]-sdeltax[1]>=buttonx[0]+800/3)sdeltax[0]=-1;
						if(buttonx[1]-sdeltax[1]+800/3<=buttonx[0])sdeltax[0]=1;
						if(buttony[1]-sdeltay[1]>=buttony[0]+740/9)sdeltay[0]=-1;
						if(buttony[1]-sdeltay[1]+740/9<=buttony[0])sdeltay[0]=1;
					}
					if(buttonx[1]+800/3>=buttonx[2]&&buttonx[1]<=buttonx[2]+800/3&&buttony[1]+740/9>=buttony[2]&&buttony[1]<=buttony[2]+740/9)
					{
						if(buttonx[1]-sdeltax[1]>=buttonx[2]+800/3)sdeltax[2]=-1;
						if(buttonx[1]-sdeltax[1]+800/3<=buttonx[2])sdeltax[2]=1;
						if(buttony[1]-sdeltay[1]>=buttony[2]+740/9)sdeltay[2]=-1;
						if(buttony[1]-sdeltay[1]+740/9<=buttony[2])sdeltay[2]=1;
					}
				}
				if(sdeltax[2]!=0||sdeltay[2]!=0)
				{
					if(buttonx[2]+800/3>=buttonx[1]&&buttonx[2]<=buttonx[1]+800/3&&buttony[2]+740/9>=buttony[1]&&buttony[2]<=buttony[1]+740/9)
					{
						if(buttonx[2]-sdeltax[2]>=buttonx[1]+800/3)sdeltax[1]=-1;
						if(buttonx[2]-sdeltax[2]+800/3<=buttonx[1])sdeltax[1]=1;
						if(buttony[2]-sdeltay[2]>=buttony[1]+740/9)sdeltay[1]=-1;
						if(buttony[2]-sdeltay[2]+740/9<=buttony[1])sdeltay[1]=1;
					}
					if(buttonx[2]+800/3>=buttonx[0]&&buttonx[2]<=buttonx[0]+800/3&&buttony[2]+740/9>=buttony[0]&&buttony[2]<=buttony[0]+740/9)
					{
						if(buttonx[2]-sdeltax[2]>=buttonx[0]+800/3)sdeltax[0]=-1;
						if(buttonx[2]-sdeltax[2]+800/3<=buttonx[0])sdeltax[0]=1;
						if(buttony[2]-sdeltay[2]>=buttony[0]+740/9)sdeltay[0]=-1;
						if(buttony[2]-sdeltay[2]+740/9<=buttony[0])sdeltay[0]=1;
					}
				}
				if(sdeltax[0]!=0||sdeltay[0]!=0)
				{
					if(buttonx[0]+800/3>=buttonx[1]&&buttonx[0]<=buttonx[1]+800/3&&buttony[0]+740/9>=buttony[1]&&buttony[0]<=buttony[1]+740/9)
					{
						if(buttonx[0]-sdeltax[0]>=buttonx[1]+800/3)
						{
							buttonx[1]--;
							sdeltax[1]=-1;
							
						}
						if(buttonx[0]-sdeltax[0]+800/3<=buttonx[1])
						{
							buttonx[1]++;
							sdeltax[1]=1;
						}
						if(buttony[0]-sdeltay[0]>=buttony[1]+740/9)
						{
							buttony[1]--;
							sdeltay[1]=-1;
						}
						if(buttony[0]-sdeltay[0]+740/9<=buttony[1])
						{
							buttony[1]++;
							sdeltay[1]=1;
						}
					}
					if(buttonx[0]+800/3>=buttonx[2]&&buttonx[0]<=buttonx[2]+800/3&&buttony[0]+740/9>=buttony[2]&&buttony[0]<=buttony[2]+740/9)
					{
						if(buttonx[0]-sdeltax[0]>=buttonx[2]+800/3)
						{
							buttonx[2]--;
							sdeltax[2]=-1;
						}
						if(buttonx[0]-sdeltax[0]+800/3<=buttonx[2])
						{
							buttonx[2]++;
							sdeltax[2]=1;
						}
						if(buttony[0]-sdeltay[0]>=buttony[2]+740/9)
						{
							buttony[2]--;
							sdeltay[2]=-1;
						}
						if(buttony[0]-sdeltay[0]+740/9<=buttony[2])
						{
							buttony[2]++;
							sdeltay[2]=1;
						}
					}
				}
				if(sdeltax[1]!=0||sdeltay[1]!=0)
				{
					if(buttonx[1]+800/3>=buttonx[0]&&buttonx[1]<=buttonx[0]+800/3&&buttony[1]+740/9>=buttony[0]&&buttony[1]<=buttony[0]+740/9)
					{
						if(buttonx[1]-sdeltax[1]>=buttonx[0]+800/3)buttonx[0]--;
						if(buttonx[1]-sdeltax[1]+800/3<=buttonx[0])buttonx[0]++;
						if(buttony[1]-sdeltay[1]>=buttony[0]+740/9)buttony[0]--;
						if(buttony[1]-sdeltay[1]+740/9<=buttony[0])buttony[0]++;
					}
					if(buttonx[1]+800/3>=buttonx[2]&&buttonx[1]<=buttonx[2]+800/3&&buttony[1]+740/9>=buttony[2]&&buttony[1]<=buttony[2]+740/9)
					{
						if(buttonx[1]-sdeltax[1]>=buttonx[2]+800/3)buttonx[2]--;
						if(buttonx[1]-sdeltax[1]+800/3<=buttonx[2])buttonx[2]++;
						if(buttony[1]-sdeltay[1]>=buttony[2]+740/9)buttony[2]--;
						if(buttony[1]-sdeltay[1]+740/9<=buttony[2])buttony[2]++;
					}
				}
				if(sdeltax[2]!=0||sdeltay[2]!=0)
				{
					if(buttonx[2]+800/3>=buttonx[1]&&buttonx[2]<=buttonx[1]+800/3&&buttony[2]+740/9>=buttony[1]&&buttony[2]<=buttony[1]+740/9)
					{
						if(buttonx[2]-sdeltax[2]>=buttonx[1]+800/3)buttonx[1]--;
						if(buttonx[2]-sdeltax[2]+800/3<=buttonx[1])buttonx[1]++;
						if(buttony[2]-sdeltay[2]>=buttony[1]+740/9)buttony[1]--;
						if(buttony[2]-sdeltay[2]+740/9<=buttony[1])buttony[1]++;
					}
					if(buttonx[2]+800/3>=buttonx[0]&&buttonx[2]<=buttonx[0]+800/3&&buttony[2]+740/9>=buttony[0]&&buttony[2]<=buttony[0]+740/9)
					{
						if(buttonx[2]-sdeltax[2]>=buttonx[0]+800/3)buttonx[0]--;
						if(buttonx[2]-sdeltax[2]+800/3<=buttonx[0])buttonx[0]++;
						if(buttony[2]-sdeltay[2]>=buttony[0]+740/9)buttony[0]--;
						if(buttony[2]-sdeltay[2]+740/9<=buttony[0])buttony[0]++;
					}
				}
				
				span.repaint();
			}
		}};
			timer.scheduleAtFixedRate(task, 0,5);//sets the tick rate of the game
			addMouseListener(this);
			addKeyListener(this);
		}
		public void paintComponent(Graphics g)//draws the game in the game panel
		{
		super.paintComponent(g);
		g.setColor(Color.BLACK);
		g.fillRect(-10,-10,1000,1000);
		g.setFont(new Font("serif", Font.PLAIN, 30));
		if(!fightingBob)g.drawImage(beach,10,10,780,700,null);
		else g.drawImage(glands,10,10,780,700,null);
		if(!fightingBob)
		{
			g.setColor(Color.RED);
			g.fillRect(10,10,220,40);
			g.setColor(Color.BLACK);
			if(!testing)g.drawString("Try TRIPLE Shot",10,40);
			else g.drawString("Back to Normal", 10, 40);
		}
		if(fightingBob)
		{
			for(int i = 0; i<=9; i++)
			{
				if(rainBullets[i])g.drawImage(arrow,(int)bulletRainx[i], (int)bulletRainy[i]-25, 15, 50,null);
				if(aboutTo[i])g.drawImage(arrow,(int)bulletRainx[i],-5,15,50,null);
			}//draws the bullet rain
				if(fshotAir[0]&&bossPhase2)
				{
					g.drawImage(fshot[0],(int)fshotx[0],(int)fshoty[0],39,99,null);
				}
				if(fshotAir[1]&&bossPhase2)
				{
					g.drawImage(fshot[1],(int)fshotx[1],(int)fshoty[1],99,39,null);
				}
				if(fshotAir[2]&&bossPhase2)
				{
					g.drawImage(fshot[2],(int)fshotx[2],(int)fshoty[2],39,99,null);
				}
				if(fshotAir[3]&&bossPhase2)
				{
					g.drawImage(fshot[3],(int)fshotx[3],(int)fshoty[3],99,39,null);
				}
		}
		if(pattack)
		{
			if(wkey)g.drawImage(wizardBA,(int)charx-25,(int) chary-25, 50, 50,null);
			else if(dkey&&(selection.equals("Wizard")||selection.equals("Priest")||selection.equals("Sorc")||selection.equals("Mys")||selection.equals("Trick")||selection.equals("Rogue")))g.drawImage(wizardRA,(int)charx-25,(int) chary-25, 70, 50,null);//8by11
			else if(dkey&&(selection.equals("Hunt")||selection.equals("Archer")))g.drawImage(wizardRA,(int)charx-25, (int)chary-25, 50, 50,null);
			else if(dkey&&(selection.equals("Warr")||selection.equals("Pally")||selection.equals("Knight")||selection.equals("Necro")))g.drawImage(wizardRA,(int)charx-25,(int)chary-25,74,50,null);
			else if(dkey&&selection.equals("Sin"))g.drawImage(wizardRA,(int)charx-25,(int)chary-25,62,50,null);
			else if(akey&&(selection.equals("Wizard")||selection.equals("Priest")||selection.equals("Sorc")||selection.equals("Mys")||selection.equals("Trick")||selection.equals("Rogue")))g.drawImage(wizardLA,(int)charx-45, (int)chary-25, 70, 50,null);
			else if(akey&&(selection.equals("Hunt")||selection.equals("Archer")))g.drawImage(wizardLA,(int)charx-25,(int) chary-25, 50, 50,null);
			else if(akey&&selection.equals("Sin"))g.drawImage(wizardLA,(int)charx-35,(int)chary-25,62,50,null);
			else if(akey&&(selection.equals("Warr")||selection.equals("Pally")||selection.equals("Knight")||selection.equals("Necro")))g.drawImage(wizardLA,(int)charx-51,(int)chary-25,74,50,null);
			else if(skey)g.drawImage(wizardFA,(int)charx-25, (int)chary-25, 50, 50,null);
			else if(lastKey=='d'&&(selection.equals("Wizard")||selection.equals("Priest")||selection.equals("Sorc")||selection.equals("Mys")||selection.equals("Trick")||selection.equals("Rogue")))g.drawImage(wizardRA,(int)charx-25,(int) chary-25, 70, 50,null);//8by11
			else if(lastKey=='d'&&(selection.equals("Hunt")||selection.equals("Archer")))g.drawImage(wizardRA,(int)charx-25, (int)chary-25, 50, 50,null);
			else if(lastKey=='d'&&(selection.equals("Warr")||selection.equals("Pally")||selection.equals("Knight")||selection.equals("Necro")))g.drawImage(wizardRA,(int)charx-25,(int)chary-25,74,50,null);
			else if(lastKey=='d'&&selection.equals("Sin"))g.drawImage(wizardRA,(int)charx-25,(int)chary-25,62,50,null);
			else if(lastKey=='a'&&(selection.equals("Wizard")||selection.equals("Priest")||selection.equals("Sorc")||selection.equals("Mys")||selection.equals("Trick")||selection.equals("Rogue")))g.drawImage(wizardLA,(int)charx-45, (int)chary-25, 70, 50,null);
			else if(lastKey=='a'&&(selection.equals("Hunt")||selection.equals("Archer")))g.drawImage(wizardLA,(int)charx-25,(int) chary-25, 50, 50,null);
			else if(lastKey=='a'&&selection.equals("Sin"))g.drawImage(wizardLA,(int)charx-35,(int)chary-25,62,50,null);
			else if(lastKey=='a'&&(selection.equals("Warr")||selection.equals("Pally")||selection.equals("Knight")||selection.equals("Necro")))g.drawImage(wizardLA,(int)charx-51,(int)chary-25,74,50,null);
			else if(lastKey=='s')g.drawImage(wizardFA,(int)charx-25, (int)chary-25, 50, 50,null);
			else if(lookUp) g.drawImage(wizardBA,(int)charx-25,(int) chary-25, 50, 50,null);
			else g.drawImage(wizardFA,(int)charx-25,(int) chary-25, 50, 50,null);
		}
		if(!pattack)
		{
			if(playerFrame==0&&lastKey=='s'&&skey)g.drawImage(wizard1,(int)charx-25,(int) chary-25, 50, 50,null);
			else if(playerFrame==1&&lastKey=='s'&&skey)g.drawImage(wizard2,(int)charx-25, (int)chary-25, 50, 50,null);
			else if(playerFrame==0&&lastKey=='w'&&wkey)g.drawImage(wizardB2,(int)charx-25,(int) chary-25, 50, 50,null);
			else if(playerFrame==1&&lastKey=='w'&&wkey)g.drawImage(wizardB3,(int)charx-25, (int)chary-25, 50, 50,null);
			else if(playerFrame==0&&lastKey=='a'&&akey)g.drawImage(wizardL1,(int)charx-25,(int) chary-25, 50, 50,null);
			else if(playerFrame==1&&lastKey=='a'&&akey)g.drawImage(wizardL2,(int)charx-25,(int) chary-25, 50, 50,null);
			else if(playerFrame==0&&lastKey=='d'&&dkey)g.drawImage(wizardR1,(int)charx-25,(int) chary-25, 50, 50,null);
			else if(playerFrame==1&&lastKey=='d'&&dkey)g.drawImage(wizardR2,(int)charx-25, (int)chary-25, 50, 50,null);
			else if(playerFrame==0&&skey)g.drawImage(wizard1,(int)charx-25,(int) chary-25, 50, 50,null);
			else if(playerFrame==1&&skey)g.drawImage(wizard2,(int)charx-25, (int)chary-25, 50, 50,null);
			else if(playerFrame==0&&wkey)g.drawImage(wizardB2,(int)charx-25,(int) chary-25, 50, 50,null);
			else if(playerFrame==1&&wkey)g.drawImage(wizardB3,(int)charx-25,(int) chary-25, 50, 50,null);
			else if(playerFrame==0&&akey)g.drawImage(wizardL1,(int)charx-25,(int) chary-25, 50, 50,null);
			else if(playerFrame==1&&akey)g.drawImage(wizardL2,(int)charx-25,(int) chary-25, 50, 50,null);
			else if(playerFrame==0&&dkey)g.drawImage(wizardR1,(int)charx-25,(int) chary-25, 50, 50,null);
			else if(playerFrame==1&&dkey)g.drawImage(wizardR2,(int)charx-25,(int) chary-25, 50, 50,null);
			else if(lastKey=='s')g.drawImage(wizard1,(int)charx-25, (int)chary-25, 50, 50,null);
			else if(lastKey=='w')g.drawImage(wizardB1,(int)charx-25,(int) chary-25, 50, 50,null);
			else if(lastKey=='d')g.drawImage(wizardR1,(int)charx-25,(int) chary-25, 50, 50,null);
			else if(lastKey=='a')g.drawImage(wizardL1,(int)charx-25,(int) chary-25, 50, 50,null);
			else if(!lookUp) g.drawImage(wizard1,(int)charx-25, (int)chary-25, 50, 50,null);
			else g.drawImage(wizardB1,(int)charx-25,(int) chary-25, 50, 50,null);
		}
		for(int i = 0; i<=3; i++)
		{
			if(alive[i]&&!bossPhase2&&fightingBob)
			{
			if(fired[i]) g.drawImage(guardA,(int)enemyx[i]-25, (int)enemyy[i], 75, 75,null);
			else if(guardFrame==1) g.drawImage(guard2,(int)enemyx[i]-25, (int)enemyy[i], 75, 75,null);
			else g.drawImage(guard1,(int)enemyx[i]-25, (int)enemyy[i], 75, 75,null);
			g.setColor(Color.RED);
			g.fillRect((int)(enemyx[i])-25,(int)enemyy[i]+78,75*enemyHealth[i]/guardHealth,7);
			g.setColor(Color.BLACK);
			}
			if(alive[i]&&!fightingBob)
			{
				if(fired[i]) g.drawImage(lotlma,(int)enemyx[i], (int)enemyy[i], 60, 60,null);
				else if(mFrame==1) g.drawImage(lotlm2,(int)enemyx[i], (int)enemyy[i], 60, 60,null);
				else if(mFrame==2) g.drawImage(lotlm3,(int)enemyx[i], (int)enemyy[i], 60, 60,null);
				else g.drawImage(lotlm1,(int)enemyx[i], (int)enemyy[i], 60, 60,null);
			}
		}
		if(!fightingBob) g.drawString("Points: " + killCount, 50, 680);
		if(fightingBob&&bossPhase2)
		{
			if(fired[0]||fired[1]||fired[2]||fired[3])g.drawImage(lotla,(int)enemyx[0], (int)enemyy[0], 100, 100,null);
			else if(lotlFrame==0)g.drawImage(lotl1,(int)enemyx[0], (int)enemyy[0], 100, 100,null);
			else g.drawImage(lotl2,(int)enemyx[0], (int)enemyy[0], 100, 100,null);
			g.setColor(Color.RED);
			g.fillRect((int)enemyx[0],(int)enemyy[0]+103,100*bobHealth/bossHealth,6);
			g.setColor(Color.BLACK);
		}
		for(int i = 0; i<=3;i++)
		{
			if(fireAtWill[i]&&alive[i]&&!fightingBob) g.drawImage(sproj,(int)(enemyProjx[i]), (int)(enemyProjy[i]), 15, 15,null);
			else if(fireAtWill[i]&&alive[i]&&fightingBob&&!bossPhase2)g.drawImage(whiteShot,(int)(enemyProjx[i]), (int)(enemyProjy[i]), 15, 15,null);
			else if(fireAtWill[i]&&alive[i]&&fightingBob&&bossPhase2)g.drawImage(iceShot,(int)(enemyProjx[i]), (int)(enemyProjy[i]), 20, 20,null);
			else if(fireAtWill[i]&&alive[i])g.fillOval((int)(enemyProjx[i]), (int)(enemyProjy[i]), 10, 10);
		}
		for(int i = 0; i<=4; i++) 
		{
			if(inAir[i])
			{
				if(projFrame==0)g.drawImage(vproj1,(int)projx[i], (int)projy[i], 20, 20,null);
				else g.drawImage(vproj2,(int)projx[i], (int)projy[i], 20, 20,null);
			}
			for(int j = 0; j<=1; j++)
			{
				if(bonusAir[i][j])
				{
					if(projFrame==0)g.drawImage(bproj1,(int)bonusShotx[i][j], (int)bonusShoty[i][j], 20, 20,null);
					else g.drawImage(bproj2,(int)bonusShotx[i][j], (int)bonusShoty[i][j], 20, 20,null);
				}
			}}
		if(fightingBob)//draws projectiles
		{
		g.setColor(Color.GREEN);
		g.fillRect((int)charx-25,(int)chary+25, 50*playerHealth/charHealth, 5);//draws player health bar
		}
		if(!fightingBob)
		{
		g.setFont(new Font("serif",Font.PLAIN,10));
		g.setColor(Color.BLACK);
		g.fillRect(0,0,900,14);
		g.setColor(Color.CYAN);
		g.drawString("back to start",375,9);
		}}
		public void mousePressed(MouseEvent e)//manages the projectiles and calculates slopes
		{//is not a loop because it needs to check only one at a time, so if else is useful
			lookUp= e.getY() < chary;
				if(!inAir[0])
				{
					pattack=true;
					projx[0]=charx;
					projy[0]=chary;
					if(e.getX()>=charx)
					{
						inAir[0] = true;
						int x = e.getX()-(int)charx;
						int y = (int)chary-e.getY();
						xAdd[0]= Math.cos(Math.atan((y+0.0)/x));
						yAdd[0] = -1*Math.sin(Math.atan((y+0.0)/x));
					}
					else
					{
						inAir[0] = true;
						int x = e.getX()-(int)charx;
						int y = (int)chary-e.getY();
						xAdd[0]= -1*Math.cos(Math.atan((y+0.0)/x));
						yAdd[0] = Math.sin(Math.atan((y+0.0)/x));
					}
					if(bonusShot[0])
					{
						bonusAir[0][0]=true;
						bonusShotx[0][0]=charx;
						bonusShoty[0][0]=chary;
					}
					if(bonusShot[1])
					{
						bonusAir[0][1]=true;
						bonusShotx[0][1]=charx;
						bonusShoty[0][1]=chary;
					}
					}
				else if(!inAir[1])
				{
					pattack=true;
					projx[1]=charx;
					projy[1]=chary;
					if(e.getX()>=charx)
					{
						inAir[1] = true;
						int x = e.getX()-(int)charx;
						int y = (int)chary-e.getY();
						xAdd[1]= Math.cos(Math.atan((y+0.0)/x));
						yAdd[1] = -1*Math.sin(Math.atan((y+0.0)/x));
					}
					else
					{
						inAir[1] = true;
						int x = e.getX()-(int)charx;
						int y = (int)chary-e.getY();
						xAdd[1]= -1*Math.cos(Math.atan((y+0.0)/x));
						yAdd[1] = Math.sin(Math.atan((y+0.0)/x));
					}
					if(bonusShot[0])
					{
						bonusAir[1][0]=true;
						bonusShotx[1][0]=charx;
						bonusShoty[1][0]=chary;
					}
					if(bonusShot[1])
					{
						bonusAir[1][1]=true;
						bonusShotx[1][1]=charx;
						bonusShoty[1][1]=chary;
					}
				}
				else if(!inAir[2])
				{
					pattack=true;
					projx[2]=charx;
					projy[2]=chary;
					if(e.getX()>=charx)
					{
						inAir[2] = true;
						int x = e.getX()-(int)charx;
						int y = (int)chary-e.getY();
						xAdd[2]= Math.cos(Math.atan((y+0.0)/x));
						yAdd[2] = -1*Math.sin(Math.atan((y+0.0)/x));
					}
					else
					{
						inAir[2] = true;
						int x = e.getX()-(int)charx;
						int y = (int)chary-e.getY();
						xAdd[2]= -1*Math.cos(Math.atan((y+0.0)/x));
						yAdd[2] = Math.sin(Math.atan((y+0.0)/x));
					}
					if(bonusShot[0])
					{
						bonusAir[2][0]=true;
						bonusShotx[2][0]=charx;
						bonusShoty[2][0]=chary;
					}
					if(bonusShot[1])
					{
						bonusAir[2][1]=true;
						bonusShotx[2][1]=charx;
						bonusShoty[2][1]=chary;
					}
				}
				else if(!inAir[3])
				{
					pattack=true;
					projx[3]=charx;
					projy[3]=chary;
					if(e.getX()>=charx)
					{
						inAir[3] = true;
						int x = e.getX()-(int)charx;
						int y = (int)chary-e.getY();
						xAdd[3]= Math.cos(Math.atan((y+0.0)/x));
						yAdd[3] = -1*Math.sin(Math.atan((y+0.0)/x));
					}
					else
					{
						inAir[3] = true;
						int x = e.getX()-(int)charx;
						int y = (int)chary-e.getY();
						xAdd[3]= -1*Math.cos(Math.atan((y+0.0)/x));
						yAdd[3] = Math.sin(Math.atan((y+0.0)/x));
					}
					if(bonusShot[0])
					{
						bonusAir[3][0]=true;
						bonusShotx[3][0]=charx;
						bonusShoty[3][0]=chary;
					}
					if(bonusShot[1])
					{
						bonusAir[3][1]=true;
						bonusShotx[3][1]=charx;
						bonusShoty[3][1]=chary;
					}
				}
				else if(!inAir[4])
				{
					pattack=true;
					projx[4]=charx;
					projy[4]=chary;
					if(e.getX()>=charx)
					{
						inAir[4] = true;
						int x = e.getX()-(int)charx;
						int y = (int)chary-e.getY();
						xAdd[4]= Math.cos(Math.atan((y+0.0)/x));
						yAdd[4] = -1*Math.sin(Math.atan((y+0.0)/x));
					}
					else
					{
						inAir[4] = true;
						int x = e.getX()-(int)charx;
						int y = (int)chary-e.getY();
						xAdd[4]= -1*Math.cos(Math.atan((y+0.0)/x));
						yAdd[4] = Math.sin(Math.atan((y+0.0)/x));
					}
					if(bonusShot[0])
					{
						bonusAir[4][0]=true;
						bonusShotx[4][0]=charx;
						bonusShoty[4][0]=chary;
					}
					if(bonusShot[1])
					{
						bonusAir[4][1]=true;
						bonusShotx[4][1]=charx;
						bonusShoty[4][1]=chary;
					}
				}
				if(!fightingBob&&gpan.hasFocus())
				{
					int y = e.getY();
					if(y<12)
						{
						((CardLayout)(lpan.getLayout())).show(lpan,"start");
						deltax=0;
						deltay=0;
						charx=400;
						chary=620;
						wkey=false;
						akey=false;
						skey=false;
						dkey=false;
						bonusShot[0]=sto[0];
						bonusShot[1]=sto[1];
						testing=false;
						for(int i = 0; i<=3; i++)
							{
							inAir[i]=false;
							enemyShotDelay[i]=0;
							enemyProjy[i]=50;
							enemyProjx[i]=400.0/3*i+200;
							fireAtWill[i]=false;
							for(int j = 0; j<=1; j++)bonusAir[i][j]=false;
							}
						for(int i = 0; i<=9; i++) 
							{
							rainBullets[i]=false;
							bulletCounter[i]=0;
							bulletRainx[i]=55+73*i;
							bulletRainy[i]=60;
							}//resets all relevant variables
						span.requestFocus();
						}
				if(e.getX()>=10&&e.getX()<=230&&e.getY()>=10&&e.getY()<=50&&!testing)
				{
					bonusShot[0]=true;
					bonusShot[1]=true;
					testing=true;
				}
				else if(e.getX()>=10&&e.getX()<=230&&e.getY()>=10&&e.getY()<=50&&testing)
				{
					bonusShot[0]=sto[0];
					bonusShot[1]=sto[1];
					testing=false;
				}}
		}
		public void keyTyped(KeyEvent e)//finds out when a key is pressed
		{
			if(e.getKeyChar()== 'w')
			{
				wkey = true;
				lastKey='w';
			}
			else if (e.getKeyChar()=='a')
			{
				akey = true;
				lastKey='a';
			}
			else if(e.getKeyChar()=='s')
			{
				skey = true;
				lastKey='s';
			}
			else if(e.getKeyChar()=='d')
			{
				dkey = true;
				lastKey='d';
			}//checks to see what relevant keys are being held down
			if(e.getKeyChar()=='p')
			{
				paused=true;
				stox=charx;
				stoy=chary;
				((CardLayout)(lpan.getLayout())).show(lpan,"start");
				span.requestFocus();
				
			}
		}
		public void mouseClicked(MouseEvent e) {}//has to exist
		public void mouseReleased(MouseEvent e) {}//has to exist
		public void mouseEntered(MouseEvent e) {}//has to exist
		public void mouseExited(MouseEvent e) {}//has to exist
		public void keyPressed(KeyEvent e) {}//has to exist
		public void keyReleased(KeyEvent e) //finds out when key is released
		{
			if(e.getKeyChar()=='w')wkey = false;
			else if (e.getKeyChar()=='a')akey = false;
			else if(e.getKeyChar()=='s')skey = false;
			else if(e.getKeyChar()=='d')dkey = false;
			repaint();//checks to see if a key is no longer being held down
		}
}}
