import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Do not modify this file without permission from your TA.
 **/
public class Controller {

	private Model model;
	private View view;
	
	public Controller(){
		view = new View();
		model = new Model(view.getWidth(), view.getHeight(), view.getImageWidth(), view.getImageHeight());
		view.addStopListener(new StopListener());
	}
	
	class StopListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			System.out.println("ActionListener");
			model.setStop();
		}
	}
	
        //run the simulation
	public void start(){
		while(true) {
			//increment the x and y coordinates, alter direction if necessary
			if(!model.getStop()) {
				model.updateLocationAndDirection();
			}
			//update the view
			view.update(model.getX(), model.getY(), model.getDirect(), model.getStop());
		}
	}
}
