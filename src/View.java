// Group 0
// Steven Soranno

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.swing.JButton;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * View: Contains everything about graphics and images
 * Know size of world, which images to load etc
 *
 * has methods to
 * provide boundaries
 * use proper images for direction
 * load images for all direction (an image should only be loaded once!!! why?)
 **/
public class View extends JPanel{
	// frame counts picture numbers for different images
	final int frameCount = 10;
	final int frameCountfire = 4;
	final int frameCountjump = 8;
    int picNum = 0;
    int picNumFire = 0;
    int picNumJump = 0;
    // Added BufferImage arrays for each direction of the orc
    BufferedImage[] southEastPics;
    BufferedImage[] southWestPics;
    BufferedImage[] northEastPics;
    BufferedImage[] northWestPics;
    BufferedImage[] northPics;
    BufferedImage[] southPics;
    BufferedImage[] westPics;
    BufferedImage[] eastPics;
    // BufferImage arrays for the orc firing, and jumping
    BufferedImage[] fireEast;
    BufferedImage[] fireWest;
    BufferedImage[] fireNorth;
    BufferedImage[] fireNortheast;
    BufferedImage[] fireNorthwest;
    BufferedImage[] fireSouth;
    BufferedImage[] fireSoutheast;
    BufferedImage[] fireSouthwest;
    BufferedImage[] jumpEast;
    BufferedImage[] jumpWest;
    BufferedImage[] jumpNorth;
    BufferedImage[] jumpNortheast;
    BufferedImage[] jumpNorthwest;
    BufferedImage[] jumpSouth;
    BufferedImage[] jumpSoutheast;
    BufferedImage[] jumpSouthwest;
    final static int frameWidth = 500;
    final static int frameHeight = 300;
    final static int imgWidth = 165;
    final static int imgHeight = 165;
    Direction d;
    int xloc;
    int yloc;
    JFrame frame;
    JButton stop;
    boolean fire = false;
    boolean jump = false;
    
    // Getters for model class
    public int getHeight() {
    	return frameHeight;
    }
    
    public int getWidth() {
    	return frameWidth;
    }
    
    public int getImageWidth() {
    	return imgWidth;
    }
    
    public int getImageHeight() {
    	return imgHeight;
    }
    
    // update fire flag
    public void fire() {
    	this.fire = !this.fire;
    }
    
    // Update jump flag to begin jump
    public void jump() {
    	jump = true;
    }
    // This method updates the views direction and location
    // It also repaints the frame
    public void update(int x, int y, Direction d, boolean s) {
    	this.d = d;
    	this.xloc = x;
    	this.yloc = y;
    	if(!s) {
    		// end orc jump if there are no jump images left
    		if(picNumJump == frameCountjump-1) {
        		jump = false;
        		picNumJump = 0;
        	}
	    	frame.repaint();
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
    	}
    }
    
    //Override this JPanel's paint method to cycle through picture array and draw images
    public void paintComponent(Graphics g) {
    	// paint fire images
    	if(fire) {
    		
    		picNumFire = (picNumFire + 1) % frameCountfire;
    		
    		// Draw the orc image based on which direction it is traveling
        	switch (d){		
    		case SOUTHEAST:
    			g.drawImage(fireSoutheast[picNumFire], xloc, yloc, Color.gray, this);
    			break;
    		case SOUTHWEST:
    			g.drawImage(fireSouthwest[picNumFire], xloc, yloc, Color.gray, this);
    			break;
    		case NORTHEAST:
    			g.drawImage(fireNortheast[picNumFire], xloc, yloc, Color.gray, this);
    			break;
    		case NORTHWEST:
    			g.drawImage(fireNorthwest[picNumFire], xloc, yloc, Color.gray, this);
    			break;
    		case NORTH:
    			g.drawImage(fireNorth[picNumFire], xloc, yloc, Color.gray, this);
    			break;
    		case SOUTH:
    			g.drawImage(fireSouth[picNumFire], xloc, yloc, Color.gray, this);
    			break;
    		case EAST:
    			g.drawImage(fireEast[picNumFire], xloc, yloc, Color.gray, this);
    			break;
    		case WEST:
    			g.drawImage(fireWest[picNumFire], xloc, yloc, Color.gray, this);
    			break;
    		}
    		// paint jump images
    	} else if(jump) {
    		picNumJump = (picNumJump + 1) % frameCountjump;
    		switch (d){		
    		case SOUTHEAST:
    			g.drawImage(jumpSoutheast[picNumJump], xloc, yloc, Color.gray, this);
    			break;
    		case SOUTHWEST:
    			g.drawImage(jumpSouthwest[picNumJump], xloc, yloc, Color.gray, this);
    			break;
    		case NORTHEAST:
    			g.drawImage(jumpNortheast[picNumJump], xloc, yloc, Color.gray, this);
    			break;
    		case NORTHWEST:
    			g.drawImage(jumpNorthwest[picNumJump], xloc, yloc, Color.gray, this);
    			break;
    		case NORTH:
    			g.drawImage(jumpNorth[picNumJump], xloc, yloc, Color.gray, this);
    			break;
    		case SOUTH:
    			g.drawImage(jumpSouth[picNumJump], xloc, yloc, Color.gray, this);
    			break;
    		case EAST:
    			g.drawImage(jumpEast[picNumJump], xloc, yloc, Color.gray, this);
    			break;
    		case WEST:
    			g.drawImage(jumpWest[picNumJump], xloc, yloc, Color.gray, this);
    			break;
    		}
    		// paint orc walking images
    	} else {
    	
	    	picNum = (picNum + 1) % frameCount;
	    	// Draw the orc image based on which direction it is traveling
	    	switch (d){		
			case SOUTHEAST:
				g.drawImage(southEastPics[picNum], xloc, yloc, Color.gray, this);
				break;
			case SOUTHWEST:
				g.drawImage(southWestPics[picNum], xloc, yloc, Color.gray, this);
				break;
			case NORTHEAST:
				g.drawImage(northEastPics[picNum], xloc, yloc, Color.gray, this);
				break;
			case NORTHWEST:
				g.drawImage(northWestPics[picNum], xloc, yloc, Color.gray, this);
				break;
			case NORTH:
				g.drawImage(northPics[picNum], xloc, yloc, Color.gray, this);
				break;
			case SOUTH:
				g.drawImage(southPics[picNum], xloc, yloc, Color.gray, this);
				break;
			case EAST:
				g.drawImage(eastPics[picNum], xloc, yloc, Color.gray, this);
				break;
			case WEST:
				g.drawImage(westPics[picNum], xloc, yloc, Color.gray, this);
				break;
			}
    	}
    	
    }
    
    //Read image from file and return
	private BufferedImage createImage(String filename){
    	BufferedImage bufferedImage;
    	try {
    		bufferedImage = ImageIO.read(new File(filename));
    		return bufferedImage;
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
    	return null;
    	
    	// TODO: Change this method so you can load other orc animation bitmaps
    }
	
	// Constructor
	public View(){
		
		stop = new JButton("Stop");
    	// Get southeast direction image
    	BufferedImage img = createImage("src/orc_animation/orc_forward_southeast.png");
    	southEastPics = new BufferedImage[10];
    	for(int i = 0; i < frameCount; i++)
    		southEastPics[i] = img.getSubimage(imgWidth*i, 0, imgWidth, imgHeight);
    	// Get southwest direction image
    	BufferedImage img2 = createImage("src/orc_animation/orc_forward_southwest.png");
    	southWestPics = new BufferedImage[10];
    	for(int i = 0; i < frameCount; i++)
    		southWestPics[i] = img2.getSubimage(imgWidth*i, 0, imgWidth, imgHeight);
    	// Get northeast direction image
    	BufferedImage img3 = createImage("src/orc_animation/orc_forward_northeast.png");
    	northEastPics = new BufferedImage[10];
    	for(int i = 0; i < frameCount; i++)
    		northEastPics[i] = img3.getSubimage(imgWidth*i, 0, imgWidth, imgHeight);
    	// Get northwest direction image
    	BufferedImage img4 = createImage("src/orc_animation/orc_forward_northwest.png");
    	northWestPics = new BufferedImage[10];
    	for(int i = 0; i < frameCount; i++)
    		northWestPics[i] = img4.getSubimage(imgWidth*i, 0, imgWidth, imgHeight);
    	// Get north direction image
    	BufferedImage img5 = createImage("src/orc_animation/orc_forward_north.png");
    	northPics = new BufferedImage[10];
    	for(int i = 0; i < frameCount; i++)
    		northPics[i] = img5.getSubimage(imgWidth*i, 0, imgWidth, imgHeight);
    	// Get south direction image
    	BufferedImage img6 = createImage("src/orc_animation/orc_forward_south.png");
    	southPics = new BufferedImage[10];
    	for(int i = 0; i < frameCount; i++)
    		southPics[i] = img6.getSubimage(imgWidth*i, 0, imgWidth, imgHeight);
    	// Get east direction image
    	BufferedImage img7 = createImage("src/orc_animation/orc_forward_east.png");
    	eastPics = new BufferedImage[10];
    	for(int i = 0; i < frameCount; i++)
    		eastPics[i] = img7.getSubimage(imgWidth*i, 0, imgWidth, imgHeight);
    	// Get west direction image
    	BufferedImage img8 = createImage("src/orc_animation/orc_forward_west.png");
    	westPics = new BufferedImage[10];
    	for(int i = 0; i < frameCount; i++)
    		westPics[i] = img8.getSubimage(imgWidth*i, 0, imgWidth, imgHeight);
    	
    	// Get fire images and store them in buffer image arrays
    	BufferedImage fire1 = createImage("src/orc_animation/orc_fire_east.png");
    	fireEast = new BufferedImage[4];
    	for(int i = 0; i < frameCountfire; i++)
    		fireEast[i] = fire1.getSubimage(imgWidth*i, 0, imgWidth, imgHeight);
    	
    	BufferedImage fire2 = createImage("src/orc_animation/orc_fire_west.png");
    	fireWest = new BufferedImage[4];
    	for(int i = 0; i < frameCountfire; i++)
    		fireWest[i] = fire2.getSubimage(imgWidth*i, 0, imgWidth, imgHeight);
    	
    	BufferedImage fire3 = createImage("src/orc_animation/orc_fire_north.png");
    	fireNorth = new BufferedImage[4];
    	for(int i = 0; i < frameCountfire; i++)
    		fireNorth[i] = fire3.getSubimage(imgWidth*i, 0, imgWidth, imgHeight);
    	
    	BufferedImage fire4 = createImage("src/orc_animation/orc_fire_northeast.png");
    	fireNortheast = new BufferedImage[4];
    	for(int i = 0; i < frameCountfire; i++)
    		fireNortheast[i] = fire4.getSubimage(imgWidth*i, 0, imgWidth, imgHeight);
    	
    	BufferedImage fire5 = createImage("src/orc_animation/orc_fire_northwest.png");
    	fireNorthwest = new BufferedImage[4];
    	for(int i = 0; i < frameCountfire; i++)
    		fireNorthwest[i] = fire5.getSubimage(imgWidth*i, 0, imgWidth, imgHeight);
    	
    	BufferedImage fire6 = createImage("src/orc_animation/orc_fire_south.png");
    	fireSouth = new BufferedImage[4];
    	for(int i = 0; i < frameCountfire; i++)
    		fireSouth[i] = fire6.getSubimage(imgWidth*i, 0, imgWidth, imgHeight);
    	
    	BufferedImage fire7 = createImage("src/orc_animation/orc_fire_southeast.png");
    	fireSoutheast = new BufferedImage[4];
    	for(int i = 0; i < frameCountfire; i++)
    		fireSoutheast[i] = fire7.getSubimage(imgWidth*i, 0, imgWidth, imgHeight);
    	
    	BufferedImage fire8 = createImage("src/orc_animation/orc_fire_southwest.png");
    	fireSouthwest = new BufferedImage[4];
    	for(int i = 0; i < frameCountfire; i++)
    		fireSouthwest[i] = fire8.getSubimage(imgWidth*i, 0, imgWidth, imgHeight);
    	
    	// Get jump images and store them in buffer image arrays
    	BufferedImage jump1 = createImage("src/orc_animation/orc_jump_east.png");
    	jumpEast = new BufferedImage[10];
    	for(int i = 0; i < frameCountjump; i++)
    		jumpEast[i] = jump1.getSubimage(imgWidth*i, 0, imgWidth, imgHeight);
    	
    	BufferedImage jump2 = createImage("src/orc_animation/orc_jump_north.png");
    	jumpNorth = new BufferedImage[10];
    	for(int i = 0; i < frameCountjump; i++)
    		jumpNorth[i] = jump2.getSubimage(imgWidth*i, 0, imgWidth, imgHeight);
    	
    	BufferedImage jump3 = createImage("src/orc_animation/orc_jump_northeast.png");
    	jumpNortheast = new BufferedImage[10];
    	for(int i = 0; i < frameCountjump; i++)
    		jumpNortheast[i] = jump3.getSubimage(imgWidth*i, 0, imgWidth, imgHeight);
    	
    	BufferedImage jump4 = createImage("src/orc_animation/orc_jump_northwest.png");
    	jumpNorthwest = new BufferedImage[10];
    	for(int i = 0; i < frameCountjump; i++)
    		jumpNorthwest[i] = jump4.getSubimage(imgWidth*i, 0, imgWidth, imgHeight);
    	
    	BufferedImage jump5 = createImage("src/orc_animation/orc_jump_south.png");
    	jumpSouth = new BufferedImage[10];
    	for(int i = 0; i < frameCountjump; i++)
    		jumpSouth[i] = jump5.getSubimage(imgWidth*i, 0, imgWidth, imgHeight);
    	
    	BufferedImage jump6 = createImage("src/orc_animation/orc_jump_southeast.png");
    	jumpSoutheast = new BufferedImage[10];
    	for(int i = 0; i < frameCountjump; i++)
    		jumpSoutheast[i] = jump6.getSubimage(imgWidth*i, 0, imgWidth, imgHeight);
    	
    	BufferedImage jump7 = createImage("src/orc_animation/orc_jump_southwest.png");
    	jumpSouthwest = new BufferedImage[10];
    	for(int i = 0; i < frameCountjump; i++)
    		jumpSouthwest[i] = jump7.getSubimage(imgWidth*i, 0, imgWidth, imgHeight);
    	
    	BufferedImage jump8 = createImage("src/orc_animation/orc_jump_west.png");
    	jumpWest = new BufferedImage[10];
    	for(int i = 0; i < frameCountjump; i++)
    		jumpWest[i] = jump8.getSubimage(imgWidth*i, 0, imgWidth, imgHeight);
    	
    	// create Jframe and button and add them to the view
    	this.add(stop);
    	stop.setBackground(Color.RED);
    	stop.setOpaque(true);
    	frame = new JFrame();
    	frame.getContentPane().add(this);
    	frame.setBackground(Color.gray);
    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	frame.setSize(frameWidth, frameHeight);
    	frame.setVisible(true);
    	
    	// TODO: Change this constructor so that at least eight orc animation pngs are loaded
    }
	
	// Add stop listener to the view
	void addStopListener(ActionListener mal) {
		stop.addActionListener(mal);
	}
	
	// Add fire and jump listener to the view
	void addFireListenerView(KeyListener l) {
		stop.addKeyListener(l);
	}
	
	
}

