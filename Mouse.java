package Java_Game;

import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class Mouse implements MouseMotionListener, MouseListener{

	
	public int mouseX;
	public int mouseY;

	public void mouseMoved(MouseEvent e) {
		mouseX = e.getX();
		mouseY = e.getY();
	}	
	
	public void mouseClicked(MouseEvent e) {
		Rectangle mr = new Rectangle(mouseX, mouseY, 10, 10);
		if (Game.state == "begin_menu") {
		Rectangle r = new Rectangle(350, 13, 107, 20);
		if(mr.intersects(r)){
			Game.state = "ingame";
		}
	}
		
		if (Game.state == "pause_menu"){
			Rectangle r = new Rectangle(360, 12, 130, 20);
			
			if (mr.intersects(r)){
				Game.state = "ingame";
			}
		}
}
	public void mouseEntered(MouseEvent arg0) {}
	public void mouseExited(MouseEvent arg0) {}
	public void mousePressed(MouseEvent arg0) {}
	public void mouseReleased(MouseEvent arg0) {}
	public void mouseDragged(MouseEvent e) {}
	
	

}
