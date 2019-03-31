import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Do not modify this file without permission from your TA.
 **/
public class Controller {

	private Model model;
	private View view;
	
	// Controller constructor
	public Controller(){
		view = new View();
		model = new Model(view.getWidth(), view.getHeight(), view.getImageWidth(), view.getImageHeight());
		view.addStopListener(new StopListener());
		view.addFireListenerView(new CustomKeyListener());
	}
	
	// stop listener class for stop button
	class StopListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			System.out.println("ActionListener");
			model.setStop();
		}
	}
	
	// key listener class for f and j keys
	class CustomKeyListener implements KeyListener{
	      public void keyTyped(KeyEvent e) {
	      }
	      public void keyPressed(KeyEvent e) {
	         if(e.getKeyCode() == KeyEvent.VK_F){
	            view.fire();
	            model.fire();
	         }
	         if(e.getKeyCode() == KeyEvent.VK_J) {
	        	 view.jump();
	        	 //model.jump();
	         }
	      }
	      public void keyReleased(KeyEvent e) {
	      }   
	   }
	
        //run the simulation
	public void start(){
		while(true) {
			System.out.println();
			//increment the x and y coordinates, alter direction if necessary
			if(!model.getStop() && !model.getFire()) {
				model.updateLocationAndDirection();
			}
			//update the view
			view.update(model.getX(), model.getY(), model.getDirect(), model.getStop());
		}
	}
}
