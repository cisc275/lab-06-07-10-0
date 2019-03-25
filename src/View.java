// Steven Soranno

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
	
	final int frameCount = 10;
    int picNum = 0;
    // Added BufferImage arrays for each direction of the orc
    BufferedImage[] southEastPics;
    BufferedImage[] southWestPics;
    BufferedImage[] northEastPics;
    BufferedImage[] northWestPics;
    BufferedImage[] northPics;
    BufferedImage[] southPics;
    BufferedImage[] westPics;
    BufferedImage[] eastPics;
    final static int frameWidth = 500;
    final static int frameHeight = 300;
    final static int imgWidth = 165;
    final static int imgHeight = 165;
    Direction d;
    int xloc;
    int yloc;
    JFrame frame;
    JButton stop;
    
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
    // This method updates the views direction and location
    // It also repaints the frame
    public void update(int x, int y, Direction d, boolean s) {
    	this.d = d;
    	this.xloc = x;
    	this.yloc = y;
    	System.out.println(s);
    	if(!s) {
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
    	
    	this.add(stop);
    	stop.setBackground(Color.RED);
    	stop.setOpaque(true);
    	frame = new JFrame();
    	frame.getContentPane().add(this);
    	//frame.add(stop);
    	//HandlerClass handler = new HandlerClass();
    	//j.addActionListener(handler);
    	frame.setBackground(Color.gray);
    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	frame.setSize(frameWidth, frameHeight);
    	frame.setVisible(true);
    	/*frame.repaint();
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}*/
    	
    	// TODO: Change this constructor so that at least eight orc animation pngs are loaded
    }
	
	void addStopListener(ActionListener mal) {
		stop.addActionListener(mal);
	}
	
	
}

