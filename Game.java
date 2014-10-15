import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.*;

public class Game extends JPanel{
	
	public boolean isRunning = true;
	
	public int x = 100;
	public int y = 200;
	
	public int kogels_over = 50;
	
	public int xOff;
	public int yOff;
	
	public ArrayList<Kogel> kogels = new ArrayList<Kogel>();
	public ArrayList<Enemy> enemys = new ArrayList<Enemy>();
	
	public FPS fps = new FPS();
	public Mouse m = new Mouse();
	public Sound sound = new Sound();
	
	public static String state = "begin_menu";
	
	public static void main(String[] args){
		
		String in = JOptionPane.showInputDialog(null, "Username: ", "Username", 3);
		JOptionPane.showMessageDialog(null, in +" ", "Username" ,3);
		
		
		
		
			Frame.setFrame();
		
	}
	
	public Game() {
		start();
	}	
	public void paint(Graphics g) {
		
	if (state == "ingame" || state == "pause_menu"){
		g.fillRect(0, 0, 800, 600);
		g.drawImage(new ImageIcon("head.jpg").getImage(), x, y, null);
		
		for (int a = 0; a < kogels.size(); a++) {
			Kogel k = kogels.get(a);
			if (k.dead) continue;
			g.drawImage(new ImageIcon("bullet.jpg").getImage(), k.x, k.y, null);	
			
		}
	
		
		for (int a = 0; a < enemys.size(); a++) {
			Enemy e = enemys.get(a);
			if (e.dead) continue;
			g.setColor(Color.orange);
			g.drawImage(new ImageIcon("enemy.jpg").getImage(), e.x, e.y, null);
			
			
		}
		
		g.setFont(new Font("Verdana", 10, 15));
		g.setColor(Color.RED);
		g.drawString("Zombie Shooter Beta v0.3                              Ammo: " + kogels_over, 300, 20);
        g.drawString("FPS: " + fps.getFPS(),650, 37);
        
    	if (state == "pause_menu"){
    		g.setColor(Color.blue);
    		g.fillRect(300, 0, 250, 600);
    		g.setColor(Color.red);
    		g.setFont(new Font("Verdana", 10, 20));
    		g.drawString("Speel verder", 360, 30);
    		g.drawString("Developer:", 370, 80);
    		g.drawString("Ruben de Groot", 345, 99);
    		g.drawRect(360, 12, 130, 20);
    	}
	} 
	else
		if (state == "begin_menu"){
			g.fillRect(0, 0, 800, 600);
			g.setFont(new Font("Verdana", 10, 20));	
			g.setColor(Color.green);
			g.drawString( "Begin spel", 350, 30);
			g.drawRect(350, 13, 107, 20);
			g.drawRect(m.mouseX, m.mouseY, 10, 10);
			
		}

}
	
	public void gameLoop() {
		while(isRunning) {
			
		if (state == "ingame"){
		for (int a = 0; a < enemys.size(); a++) {
			Enemy e = enemys.get(a);
			Rectangle r = new Rectangle(e.x, e.y, e.w, e.h);
			
			for (int b = 0; b < kogels.size(); b++)	{
				Kogel k = kogels.get(b);
				if (k.dead) continue;
				k.update();
				if (e.dead) continue;
				Rectangle kr = new Rectangle(k.x, k.y, 10, 10);
				if (r.intersects(kr)) {
					e.health-=50;
					k.dead = true;
					if (e.health <=0) e.dead = true;
				}
				
				
			}
			
			
		}
		
			fps.update();
			move();
		}	
		repaint();
		requestFocusInWindow();
		try {Thread.sleep(10);} catch (InterruptedException e) {e.printStackTrace();}	
		}
	}
	
	public void move() {
		x+=xOff;
		y+=yOff;
	}
	
	public void shoot(int x, int y, int dir) {
		if (state != "ingame") return;
		if (kogels_over <= 0) return;
		kogels_over--;
		Kogel k = new Kogel(x, y, dir);
		kogels.add(k);
		sound.playSound("wep");
		System.out.println("Afgeschoten kogels: " + kogels.size());
		System.out.println("Kogels: " + kogels_over);
	}
	
	public void start() {
		Thread loop = new Thread () {
			public void run() {
				Enemy e = new Enemy(50, 50, 50, 100, 100);
				enemys.add(e);
				
				addKeyListener(new keyInput());
				addMouseListener(m);
				addMouseMotionListener(m);
				gameLoop();
			}
		};
		loop.start();
	}
	
	public class keyInput extends KeyAdapter {
		
		public void keyPressed(KeyEvent e) {
			int key = e.getKeyCode();
			
			if (key == KeyEvent.VK_W) {
				yOff =-1;	
			}
			
			if (key == KeyEvent.VK_S) {
				yOff = 1;
			}
			
			if (key == KeyEvent.VK_A) {
				xOff =-1;
			}
			
			if (key == KeyEvent.VK_D) {
				xOff = 1;
			}
			
			if (key == KeyEvent.VK_UP) {
				shoot(x, y, 1);
			}
			if (key == KeyEvent.VK_RIGHT) {
				shoot(x, y, 2);
			}
			if (key == KeyEvent.VK_DOWN) {
				shoot(x, y, 3);
			}
			if (key == KeyEvent.VK_LEFT) {
				shoot(x, y, 4);
			}
			
			if (key == KeyEvent.VK_ESCAPE) {
				if (state == "ingame") {state = "pause_menu"; return;}
				if (state == "pause_menu") {state = "ingame"; return;}
			 
			

			}
			
		}
		
		public void keyReleased(KeyEvent e) {
			int key = e.getKeyCode();
			
			
			if (key == KeyEvent.VK_W) {
				yOff =-0;
				
			}
			
			if (key == KeyEvent.VK_S) {
				yOff = 0;
			}
			
			if (key == KeyEvent.VK_A) {
				xOff =-0;
			}
			
			if (key == KeyEvent.VK_D) {
				xOff = 0;
			}
			
		}
		
	}
	

}
