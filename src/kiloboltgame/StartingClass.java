package kiloboltgame;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import kiloboltgame.framework.Animation;
import java.awt.Rectangle;
public class StartingClass extends Applet implements Runnable, KeyListener {

	enum GameState {
		Running, Dead, Paused
	}

	GameState state = GameState.Paused;
	private Rectangle robotBox;
	static Robot robot;
	ArrayList <Pipe> pipes;
	public static int score = 0;
	private Font font = new Font(null, Font.BOLD, 30);

	private Image image, bird, background, background2, upPipe, downPipe;


	private Graphics second;
	private URL base;
	private static Background bg1, bg2;
	private Animation anim, hanim;

	@Override
	public void init() {
		pipes = new ArrayList<Pipe>();
		setSize(480, 800);
		setBackground(Color.BLACK);
		setFocusable(true);
		addKeyListener(this);
		Frame frame = (Frame) this.getParent().getParent();
		frame.setTitle("Flappy Bird");
		try {
			base = getDocumentBase();
		} catch (Exception e) {
			// TODO: handle exception
		}

		// Image Setups
		bird = getImage(base, "data/flappybird.png");
		bird=bird.getScaledInstance(51, 36, Image.SCALE_SMOOTH);
		background = getImage(base, "data/background.jpg");
		background = background.getScaledInstance(480,800,Image.SCALE_SMOOTH);
		background2 = getImage(base, "data/background2.jpg");
		background2 = background2.getScaledInstance(480,800,Image.SCALE_SMOOTH);
		upPipe= getImage(base, "data/upPipe.png");
		downPipe= getImage(base,"data/downPipe.png");
		anim = new Animation();
		anim.addFrame(bird, 1250);

	}

	@Override
	public void start() {
		bg1 = new Background(0, 0);
		bg2 = new Background(2160, 0);
		robot = new Robot();
		int y1=(int)(Math.random()*(-450))-300;
		pipes.add(new Pipe('d',y1,500));
		pipes.add(new Pipe('u',y1,500));
		y1=(int)(Math.random()*(-450))-300;
		pipes.add(new Pipe('d',y1,900));
		pipes.add(new Pipe('u',y1,900));
		y1=(int)(Math.random()*(-450))-300;
		pipes.add(new Pipe('d',y1,1300));
		pipes.add(new Pipe('u',y1,1300));
		Thread thread = new Thread(this);
		thread.start();
	}

	@Override
	public void stop() {
		// TODO Auto-generated method stub
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
	}

	@Override
	public void run() {
		if (state == GameState.Running||state==GameState.Paused) {

			while (true) {
				
				bg1.update();
				if (state == GameState.Running){
					robot.update();
					int y1=0;
					for (int pcount=0;pcount<pipes.size();pcount++){
						Pipe p=pipes.get(pcount);
						p.update();
						if (p.getX()<=-300){
							if (p.getOrientation()=='d'){
								y1=(int)(Math.random()*(-450))-300;
								pipes.set(pcount, new Pipe('d',y1,900));
							}else{
								pipes.set(pcount, new Pipe ('u',y1,900));
							}
						}
						if (p.getX()==50&&p.getOrientation()=='d'){
							if (p.getOrientation()=='d'){
								score++;
							}
						}
							
					}
						
					//update each pipe's location
					robotBox= robot.getBoundingBox();
					for (int pcount=0;pcount<pipes.size();pcount++){
						
						if (robotBox.intersects(pipes.get(pcount).getBoundingBox())){
							state=GameState.Dead;
						}
					}
				}
				animate();
				repaint();
				try {
					Thread.sleep(17);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
			}
		}
	}

	public void animate() {
		anim.update(10);
	}

	@Override
	public void update(Graphics g) {
		if (image == null) {
			image = createImage(this.getWidth(), this.getHeight());
			second = image.getGraphics();
		}

		second.setColor(getBackground());
		second.fillRect(0, 0, getWidth(), getHeight());
		second.setColor(getForeground());
		paint(second);

		g.drawImage(image, 0, 0, this);

	}

	@Override
	public void paint(Graphics g) {

		if (state == GameState.Running||state==GameState.Paused) {

			g.drawImage(background, bg1.getBgX(), bg1.getBgY(), this);
			


			g.drawImage(bird, robot.getCenterX() - 61,
					robot.getCenterY() - 63, this);
			for (int pcount=0;pcount<pipes.size();pcount++){
				Pipe p = pipes.get(pcount);
				char o = p.getOrientation();
				if (o=='d'){
					g.drawImage(downPipe,p.getX(),p.getY(),this);
				}else{
					g.drawImage(upPipe,(int)p.getBoundingBox().getX(),(int)p.getBoundingBox().getY(),this);
				}
				
			}
			if (robot.getCenterY()>720){
				state=GameState.Dead;
			}
			g.drawImage(background2, bg2.getBgX(),bg2.getBgY(),this);
			g.setFont(font);
			g.setColor(Color.WHITE);
			g.drawString(Integer.toString(score), 400, 30);
			g.setColor(new Color(0,255,0,130));
			if (state==GameState.Running)
			//g.fillRect((int)robotBox.getX(),(int) robotBox.getY(), robotBox.width, robotBox.height);
			g.setColor(new Color(255,0,0,130));
			//for (int pcount=0;pcount<pipes.size();pcount++)
				//g.fillRect((int)pipes.get(pcount).getBoundingBox().getX(), (int)pipes.get(pcount).getBoundingBox().getY(),
						//pipes.get(pcount).getBoundingBox().width, pipes.get(pcount).getBoundingBox().height);
		} else if (state == GameState.Dead) {
			g.setColor(Color.BLACK);
			g.fillRect(0, 0, 800, 480);
			g.setColor(Color.WHITE);
			g.drawString("Dead", 360, 240);
			g.drawString(Integer.toString(score), 400, 30);
		}
	}


	@Override
	public void keyPressed(KeyEvent e) {

		switch (e.getKeyCode()) {
			
		case KeyEvent.VK_SPACE:
			if (state == GameState.Running) {
				robot.jump();
				break;
			}else if (state == GameState.Paused){
				state=GameState.Running;
				robot.jump();
				break;
			}else if (state == GameState.Dead){
				state=GameState.Paused;
				reset();
			}
			

		}

	}
	public void reset(){
		pipes=new ArrayList<Pipe>();
		score=0;
		robot = new Robot();
		int y1=(int)(Math.random()*(-450))-300;
		pipes.add(new Pipe('d',y1,500));
		pipes.add(new Pipe('u',y1,500));
		y1=(int)(Math.random()*(-450))-300;
		pipes.add(new Pipe('d',y1,900));
		pipes.add(new Pipe('u',y1,900));
		y1=(int)(Math.random()*(-450))-300;
		pipes.add(new Pipe('d',y1,1300));
		pipes.add(new Pipe('u',y1,1300));
		
	}
	@Override
	public void keyReleased(KeyEvent e) {
		switch (e.getKeyCode()) {


		case KeyEvent.VK_SPACE:
			break;


		}

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	public static Background getBg1() {
		return bg1;
	}

	public static Background getBg2() {
		return bg2;
	}

	public static Robot getRobot() {
		return robot;
	}

}