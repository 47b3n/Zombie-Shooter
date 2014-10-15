import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Frame extends javax.swing.JFrame {

	public static void setFrame(){
		Frame f = new Frame();
		
		
		
		
		f.setResizable(false);
		f.setDefaultCloseOperation(Frame.EXIT_ON_CLOSE);
		f.setSize(800, 600);
		f.setTitle("Damto Technologies                                                              Zombie Shooter Beta v0.3");
		f.setLocationRelativeTo(null);
		f.setVisible(true);
		
		f.add(new Game());
		
		
		}
}
