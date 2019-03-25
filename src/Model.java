// Steven Soranno

/**
 * Model: Contains all the state and logic
 * Does not contain anything about images or graphics, must ask view for that
 *
 * has methods to
 *  detect collision with boundaries
 * decide next direction
 * provide direction
 * provide location
 **/
public class Model{
	int frameWidth;
    int frameHeight;
    int imgWidth;
    int imgHeight;
    int x =0;
    int y =0;
    Direction d;
    int xIncr = 8;
    int yIncr = 2;
 // Added Boolean flags to identify direction.
    boolean north;
    boolean west;
    boolean east = true;
    boolean south;
    boolean stop = false;
	
    // Model constructor
	public Model(int width, int height, int imgWidth, int imgHeight) {
		frameWidth = width;
		frameHeight = height;
		this.imgWidth = imgWidth;
		this.imgHeight = imgHeight;
	}
	
	public void stop() {
		stop = true;
	}
	
	// Update the location and direction of the orc for the view class
	public void updateLocationAndDirection() {
		// Determine if orc should be traveling east or west
    	if(x >= frameWidth-imgWidth) {
    		east = false;
    		west = true;
    	} else if(x <= 0) {
    		east = true;
    		west = false;
    	}
    	
    	// Determine if orc should be traveling north or south
    	if(y >= frameHeight-imgHeight) {
    		south = false;
    		north = true;
    	} else if(y <= 0) {
    		south = true;
    		north = false;
    	}
    	
    	// If the orc is traveling East increment the xIncr
    	if(west) {
    		x-=xIncr;
    	} else {
    		x+=xIncr;
    	}
    	
    	// If the orc is traveling South increment the yIncr
    	if(north) {
    		y-=yIncr;
    	} else {
    		y+=yIncr;
    	}
    	
    	// Determine the direction of the orc based on the north, south, east and west flags
    	if(south && east) {
    		d = Direction.SOUTHEAST;
    	} else if(south && west) {
    		d = Direction.SOUTHWEST;
    	} else if(north && east) {
    		d = Direction.NORTHEAST;
    	} else if(north && west) {
    		d = Direction.NORTHWEST;
    	} else if(north) {
    		d = Direction.NORTH;
    	} else if(south) {
    		d = Direction.SOUTH;
    	} else if(east) {
    		d = Direction.EAST;
    	} else if(west) {
    		d = Direction.WEST;
    	}

	}
	
	// Getters to update the view
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public Direction getDirect() {
		return d;
	}
	
	public boolean getStop() {
		return stop;
	}
	
	public void setStop() {
		stop = !stop;
	}
}