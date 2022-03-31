package game;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageFilter;
import java.awt.image.ImageProducer;
import java.awt.image.RGBImageFilter;

import engine.FileHandler;
import engine.InputHandler;
import misc.Misc;

public class MainCharacter extends Entity {
	
	private static MainCharacter instance;
	
	private final int PLAYER_SPEED = 500;
	
	Image sprite;
	Image darkSprite;
	Image blackSprite;
	BufferedImage bSprite;
	FileHandler fileHandler;
	InputHandler input;
	
	private MainCharacter() {
		
		fileHandler = FileHandler.getInstance();
		input = InputHandler.getInstance();
		position = new Point2D.Double();
		
		defaultPlayerStatus();
		getPlayerImage();
		
		bSprite = toBufferedImage(sprite);
		darkSprite = setColor(bSprite, Color.cyan);
		blackSprite = setColor(bSprite, Color.black);
	}
	
	public static BufferedImage toBufferedImage(Image img)
	{
	    if (img instanceof BufferedImage)
	    {
	        return (BufferedImage) img;
	    }

	    // Create a buffered image with transparency
	    BufferedImage bimage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);

	    // Draw the image on to the buffered image
	    Graphics2D bGr = bimage.createGraphics();
	    bGr.drawImage(img, 0, 0, null);
	    bGr.dispose();

	    // Return the buffered image
	    return bimage;
	}
	
	public static MainCharacter getInstance() {
		if(instance == null) {
			instance = new MainCharacter();
		}
		
		return instance;
	}
	
	public void defaultPlayerStatus() {
		
		position.setLocation(1300, 300);
		
	}
	
	public void getPlayerImage() {
		
		sprite = fileHandler.openImage("sprites/player/HQ.png");
		
	}
	
	public void update(double delta) {
		
		move(delta);
		
	}
	
	public void draw(Graphics2D g2) {
		
		
		
		for (int i = 0; i < 1; i++) {
			double a = Math.sin(System.nanoTime()/1000000000.0 + i)/2.0 + 0.5;
			AffineTransform at = new AffineTransform();
		
			at.translate(400, 500);
			at.rotate(System.nanoTime()/1000000000.0 + i);
			//at.shear(-Math.sin(System.nanoTime()/1000000000.0 + i), Math.sin(System.nanoTime()/1000000000.0 + i));
			//at.scale(Math.cos(System.nanoTime()/1000000000.0 + i), Math.cos(System.nanoTime()/1000000000.0 + i));
			at.scale(Misc.Background.TILE_WIDTH/1500.0 * 2.0, Misc.Background.TILE_HEIGHT/1301.0 * 2.0);
			at.translate(-1500.0/2.0, -1301/3.0 * 2.0);
		
		if (a >= 0.5) {
			g2.drawImage(darkSprite, at, null);
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float) (a - 0.5) * 2f));
			g2.drawImage(sprite, at, null);
			
			}
		else {
			g2.drawImage(blackSprite,at, null);
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float) ((a * 2.0))));
			g2.drawImage(darkSprite, at, null);
			
		}
		}
		
		
		
		
		
	}
	
	public void move(double delta) {
		
		if(input.getInput(InputHandler.KEY_UP) == 1) {
			position.setLocation(position.getX(), position.getY() - (int) (PLAYER_SPEED * delta));
		}
		
		if(input.getInput(InputHandler.KEY_DOWN) == 1) {
			position.setLocation(position.getX(), position.getY() + (int) (PLAYER_SPEED * delta));
		}
		
		if(input.getInput(InputHandler.KEY_LEFT) == 1) {
			position.setLocation(position.getX() - (int) (PLAYER_SPEED * delta), position.getY());
		}
		
		if(input.getInput(InputHandler.KEY_RIGHT) == 1) {
			position.setLocation(position.getX() + (int) (PLAYER_SPEED * delta), position.getY());
		}	
	}

	public void update() {}

	public void destroy() {}

	@Override
	public Entity newInstance() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public static Image setColor(Image im, final Color color) {
	    ImageFilter filter = new RGBImageFilter() {

	        public final int filterRGB(int x, int y, int rgb) {
	            return rgb & color.getRGB();
	        }
	    };

	    ImageProducer ip = new FilteredImageSource(im.getSource(), filter);
	    return Toolkit.getDefaultToolkit().createImage(ip);
	}
	
}