package engine;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;
import java.awt.image.BufferedImage;

public final class ImageBufferHandler {
	private static BufferedImage buffer[];
	private static int bufferSize;
	private static ImageBufferHandler instance = null;
	private static FileHandler file;
	
	private static final int TYPE_C = 10;
	private static final int QUALITY_C = 5;
	
	private static final int ARROW_C = 10;
	private static final int BEAM_C = 10;
	private static final int DONUT_C = 10;
	private static final int HALF_C = 10;
	private static final int HOLLOW_C = 10;
	private static final int LASER_C = 10;
	private static final int QUARTER_C = 10;
	private static final int SHINE_C = 10;
	private static final int TARGET_C = 10;
	private static final int TRIANGLE_C = 10;
	
	private static final int TYPE_V[] = {
			ARROW_C, 
			BEAM_C, 
			DONUT_C, 
			HALF_C, 
			HOLLOW_C, 
			LASER_C, 
			QUARTER_C, 
			SHINE_C,
			TARGET_C, 
			TRIANGLE_C
		};
	
	private static final String PATH = "sprites/";
	private static final String SUFFIX = ".png";
	private static final String TYPE[] = {"Arrow", "Beam", "Donut", "Half", "Hollow", "Laser", "Quarter", "Shine", "Target", "Triangle"};
	private static final String B_COLOR[] = {"red", "orange", "yellow", "green", "cyan", "blue", "purple", "magenta", "white", "black"};
	private static final String T_COLOR[] = {"black", "blue1", "blue2", "orange1", "orange2", "gray1", "gray2", "light1", "light2", "white"};
	private static final String QUALITY[] = {"UHQ", "HQ", "MQ", "LQ", "ULQ"};
	
	public static final int ARROW = 0;
	public static final int BEAM = 1;
	public static final int DONUT = 2;
	public static final int HALF = 3;
	public static final int HOLLOW = 4;
	public static final int LASER = 5;
	public static final int QUARTER = 6;
	public static final int SHINE = 7;
	public static final int TARGET = 8;
	public static final int TRIANGLE = 9;
	
	public static final int B_RED = 0;
	public static final int B_ORANGE = 1;
	public static final int B_YELLOW = 2;
	public static final int B_GREEN = 3;
	public static final int B_CYAN = 4;
	public static final int B_BLUE = 5;
	public static final int B_PURPLE = 6;
	public static final int B_MAGENTA = 7;
	public static final int B_WHITE = 8;
	public static final int B_BLACK = 9;
	
	public static final int T_BLACK = 0;
	public static final int T_BLUE1 = 1;
	public static final int T_BLUE2 = 2;
	public static final int T_ORANGE1 = 3;
	public static final int T_ORANGE2 = 4;
	public static final int T_GRAY1 = 5;
	public static final int T_GRAY2 = 6;
	public static final int T_LIGHT1 = 7;
	public static final int T_LIGHT2 = 8;
	public static final int T_WHITE = 9;
	
	public static final int UHQ = 0;
	public static final int HQ = 1;
	public static final int MQ = 2;
	public static final int LQ = 3;
	public static final int ULQ = 4;
	
	public static final Point SIZE_UHQ_BULLET = 	new Point(1000, 1000);
	public static final Point SIZE_UHQ_TRIANGLE = 	new Point(1500, 1301);
	
	public static final Point SIZE_HQ_BULLET = 	 	new Point(500, 500);
	public static final Point SIZE_HQ_TRIANGLE = 	new Point(1000, 867);
	
	public static final Point SIZE_MQ_BULLET =	 	new Point(250, 250);
	public static final Point SIZE_MQ_TRIANGLE = 	new Point(500, 434);
	
	public static final Point SIZE_LQ_BULLET =	 	new Point(100, 100);
	public static final Point SIZE_LQ_TRIANGLE = 	new Point(250, 217);
	
	public static final Point SIZE_ULQ_BULLET =	 	new Point(50, 50);
	public static final Point SIZE_ULQ_TRIANGLE = 	new Point(125, 109);
	
	public static final Point2D INV_SIZE_UHQ_BULLET = 	new Point2D.Double(1.0 / 1000.0, 1.0 / 1000.0);
	public static final Point2D INV_SIZE_UHQ_TRIANGLE = new Point2D.Double(1.0 / 1500.0, 1.0 / 1301.0);
	
	public static final Point2D INV_SIZE_HQ_BULLET = 	new Point2D.Double(1.0 / 500.0, 1.0 / 500.0);
	public static final Point2D INV_SIZE_HQ_TRIANGLE = 	new Point2D.Double(1.0 / 1000.0, 1.0 / 867.0);
	
	public static final Point2D INV_SIZE_MQ_BULLET =	new Point2D.Double(1.0 / 250.0, 1.0 / 250.0);
	public static final Point2D INV_SIZE_MQ_TRIANGLE = 	new Point2D.Double(1.0 / 500.0, 1.0 / 434.0);
	
	public static final Point2D INV_SIZE_LQ_BULLET =	new Point2D.Double(1.0 / 100.0, 1.0 / 100.0);
	public static final Point2D INV_SIZE_LQ_TRIANGLE = 	new Point2D.Double(1.0 / 250.0, 1.0 / 217.0);
	
	public static final Point2D INV_SIZE_ULQ_BULLET =	new Point2D.Double(1.0 / 50.0, 1.0 / 50.0);
	public static final Point2D INV_SIZE_ULQ_TRIANGLE = new Point2D.Double(1.0 / 125.0, 1.0 / 109.0);
	
	public static final Point2D BULLET_CENTER_OFFSET = 	new Point2D.Double(-1.0 / 2.0, -1.0 / 2.0);
	public static final Point2D TRIANGLE_CENTER_OFFSET =new Point2D.Double(-1.0 / 2.0, -2.0 / 3.0);
	
	private ImageBufferHandler() {
		int c = 0;
		for (int i: TYPE_V) c += i;
	
		c *= QUALITY_C;
		buffer = new BufferedImage[c];
		bufferSize = c;
		//System.out.println(c);
		file = FileHandler.getInstance();
	}
	
	public static void getImageData(ImageData ret, int bullet, double scale) {
		ret.type = bullet;
		switch (bullet) {
			case TRIANGLE:
				if (scale <= 125) {
					ret.size = SIZE_ULQ_TRIANGLE;
					ret.invScaleRates = INV_SIZE_ULQ_TRIANGLE;
					ret.quality = 4;
				}
				else if (scale <= 250) {
					ret.size = SIZE_LQ_TRIANGLE;
					ret.invScaleRates = INV_SIZE_LQ_TRIANGLE;
					ret.quality = 3;
				}
				else if (scale <= 500) {
					ret.size = SIZE_MQ_TRIANGLE;
					ret.invScaleRates = INV_SIZE_MQ_TRIANGLE;
					ret.quality = 2;
				}
				else if (scale <= 1000) {
					ret.size = SIZE_HQ_TRIANGLE;
					ret.invScaleRates = INV_SIZE_HQ_TRIANGLE;
					ret.quality = 1;
				}
				else if (scale <= 1500) {
					ret.size = SIZE_UHQ_TRIANGLE;
					ret.invScaleRates = INV_SIZE_UHQ_TRIANGLE;
					ret.quality = 0;
				}
				ret.offset = TRIANGLE_CENTER_OFFSET;
			break;
			default:
				if (scale <= 50) {
					ret.size = SIZE_ULQ_BULLET;
					ret.invScaleRates = INV_SIZE_ULQ_BULLET;
					ret.quality = 4;
				}
				if (scale <= 100) {
					ret.size = SIZE_LQ_BULLET;
					ret.invScaleRates = INV_SIZE_LQ_BULLET;
					ret.quality = 3;
				}
				if (scale <= 250) {
					ret.size = SIZE_MQ_BULLET;
					ret.invScaleRates = INV_SIZE_MQ_BULLET;
					ret.quality = 2;
				}
				if (scale <= 500) {
					ret.size = SIZE_HQ_BULLET;
					ret.invScaleRates = INV_SIZE_HQ_BULLET;
					ret.quality = 1;
				}
				if (scale <= 1000) {
					ret.size = SIZE_UHQ_BULLET;
					ret.invScaleRates = INV_SIZE_UHQ_BULLET;
					ret.quality = 0;
				}
				ret.offset = BULLET_CENTER_OFFSET;
			break;
		}
	}
	
	public static ImageBufferHandler getInstance() {
		if (instance == null) {
			instance = new ImageBufferHandler();
		}
		return instance;
	}
	
	public static BufferedImage getImage(int type, int color, int quality) {
		getInstance();
		int pos = 0, typeIndex = 0, colorIndex = 0;
		Image im;
		if (type >= TYPE_C || type < 0) {
			System.out.println("Invalid Type");
			return null;
		}
		if (color >= TYPE_V[type] || color < 0) {
			System.out.println("Invalid Color");
			return null;
		}
		if (quality >= QUALITY_C || quality < 0) {
			System.out.println("Invalid Quality");
			return null;
		}
		//System.out.println(PATH + TYPE[type] + "/" + QUALITY[quality] + "_" + B_COLOR[color] + SUFFIX);
		
		for (int i = 0; i < type; i++) typeIndex += TYPE_V[i] * QUALITY_C;
		colorIndex = color * QUALITY_C;
		
		pos = typeIndex + colorIndex + quality;
		
		if (pos >= bufferSize || pos < 0) {
			System.out.println("Invalid Index: " + pos + " // " + bufferSize);
			return null;
		}
		
		
		if (buffer[pos] == null) {
			if (type != TRIANGLE) {
				im = file.openImage(PATH + TYPE[type] + "/" + QUALITY[quality] + "_" + B_COLOR[color] + SUFFIX);
				if (im.getWidth(null) == -1) {
					{
						System.out.println("Invalid Path:" + PATH + TYPE[type] + "/" + QUALITY[quality] + "_" + B_COLOR[color] + SUFFIX);
						return null;
					}		
				}
			} 
			else {
				im = file.openImage(PATH + TYPE[type] + "/" + QUALITY[quality] + "_" + T_COLOR[color] + SUFFIX);
				if (im.getWidth(null) == -1) {
					System.out.println("Invalid Path:" + PATH + TYPE[type] + "/" + QUALITY[quality] + "_" + T_COLOR[color] + SUFFIX);
					return null;
				}
			}
			
			buffer[pos] = toBuffer(im);
		}
		
		return buffer[pos];
	}
	
	private static BufferedImage toBuffer(Image image) {
		if (image instanceof BufferedImage)
	    {
	        return (BufferedImage) image;
	    }

	    // Create a buffered image with transparency
	    BufferedImage bimage = new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_INT_ARGB);

	    // Draw the image on to the buffered image
	    Graphics2D bGr = bimage.createGraphics();
	    bGr.drawImage(image, 0, 0, null);
	    bGr.dispose();

	    // Return the buffered image
	    return bimage;
	}
}
