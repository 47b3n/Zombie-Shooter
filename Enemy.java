public class Enemy {
	
	public int x;
	public int y;
	public int w;
	public int h;
	public int health;
	
	public boolean dead = false; 
	
	public Enemy(int x, int y, int w, int h, int health) {
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		this.health = health;
	}

}
