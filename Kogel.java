public class Kogel {

	public int x;
	public int y;
	
	public boolean dead = false;
	
	public int dir;
	
	public Kogel(int x, int y, int dir) {
		this.x = x;
		this.y = y;
		this.dir =dir;
	}
	
	public void update() {
		if (dir == 1) {
			y-=2;
		}
		if (dir == 2) {
			x+=2;
		}
		if (dir == 3) {
			y+=2;
		}
		if (dir == 4) {
			x-=2;
		}
	}
	
}
