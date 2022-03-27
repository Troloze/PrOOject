package misc;

import java.awt.Point;
import java.awt.geom.Point2D;

public class Misc {
	
	public static class Other {
		
		public static final double nanoSecond = 1000000000.0;
		
		public static double getAngleBetween(Point2D a, Point2D b) {
			return Math.toDegrees(Math.atan2(b.getY() - a.getY(), b.getX() - a.getX()));
		}
		
		public static Point2D rotateAround(Point2D src, double dist, double angle) {
			double x = Math.cos(angle) * dist + src.getX();
			double y = Math.sin(angle) * dist + src.getY();
			
			return new Point2D.Double(x, y);
		}
	
	}
	
	public static class Background {
		
		public static final double TILE_WIDTH = 100.0;
		public static final double TILE_HEIGHT = TILE_WIDTH/2.0 * 1.73205080757;
		public static final double TILE_HEIGHT_O3 = TILE_HEIGHT/3.0;
		public static final double TILE_HEIGHT_2O3 = TILE_HEIGHT_O3 * 2;
		public static final double TILE_WIDTH_O2 = TILE_WIDTH/2.0;
		
		public static Point world2Back(Point2D offset, Point2D worldPos) {
			double retX, retY;
			double comp;
			
			Point2D newPos = new Point2D.Double(2 * (worldPos.getX() - offset.getX())/TILE_WIDTH, (worldPos.getY() - offset.getY())/TILE_HEIGHT + 1.0/3.0);
			Point2D newDecPos = new Point2D.Double(newPos.getX() - (int) Math.floor(newPos.getX()), newPos.getY() - (int) Math.floor(newPos.getY()));
			
			
			retX = (int) Math.floor(newPos.getX());
			retY = (int) Math.floor(newPos.getY());
			
			comp = ((int) Math.floor(newPos.getX())%2 == 0) ? 1 - newDecPos.getY() : newDecPos.getY();
			
			if (newDecPos.getX() >= comp)
				retX += 1;
			
			return new Point((int) Math.floor(retX + 0.5), (int) Math.floor(retY + 0.5));
		}
		
		public static Point2D back2World(Point2D offset, Point backPos) {
			double retX = backPos.getX() * TILE_WIDTH_O2;
			double retY;
			
			if ((backPos.getX()%2 == 0) ^ (backPos.getY()%2 == 0)) 
				retY = backPos.getX() * TILE_HEIGHT;
			else
				retY = backPos.getY() * TILE_HEIGHT + TILE_HEIGHT_O3;
			
			return new Point2D.Double(retX + offset.getX(), retY + offset.getY());
		}

	}
	
	public static class Transform {
		public static final double INV_INT_SSTART2 = 3.0;
		public static final double INV_INT_SSTART3 = 4.0;
		public static final double INV_INT_SSTART4 = 5.0;
		public static final double INV_INT_SSTART5 = 6.0;
		public static final double INV_INT_SSTOP2 = 3.0/2.0;
		public static final double INV_INT_SSTOP3 = 4.0/3.0;
		public static final double INV_INT_SSTOP4 = 5.0/4.0;
		public static final double INV_INT_SSTOP5 = 6.0/5.0;
		public static final double INV_INT_SSTEP2 = 2.0;
		public static final double INV_INT_SSTEP3 = 2.0;
		public static final double INV_INT_SSTEP4 = 2.0;
		public static final double INV_INT_SSTEP5 = 2.0;
		public static final double INV_INT_ARCH2 = 1.5;
		public static final double INV_INT_ARCH3_START = 1.0/0.5625;
		public static final double INV_INT_ARCH3_STOP = 1.0/0.5625;
		public static final double INV_INT_ARCH4 = 1.875;
		public static final double INV_INT_ARCH4_START = 2.109375;
		public static final double INV_INT_ARCH4_STOP = 2.109375;
		public static final double INV_INT_ARCH5_START = 2.4576;
		public static final double INV_INT_ARCH5_STOP = 2.4576;
		public static final double INV_INT_BELL_CURVE = 2.1875;
		
		private static final double ARCH2_CONSTANT = 4;
		private static final double ARCH3_CONSTANT = 6.75;
		private static final double ARCH4_CONSTANT = 16.0;
		private static final double ARCH4_ALT_CONSTANT = 256.0/27;
		private static final double BELL_CONSTANT = 64.0;
		private static final double ARCH5_CONSTANT = 3125.0/4.0;
				
		public static double smoothStart2(double t) {
			return t * t;
		}
		
		public static double smoothStart3(double t) {
			return t * t * t;
		}
		
		public static double smoothStart4(double t) {
			return t * t * t * t;
		}
		
		public static double smoothStart5(double t) {
			return t * t * t * t * t;
		}
		
		public static double smoothStop2(double t) {
			return 1 - (1 - t) * (1 - t);
		}
		
		public static double smoothStop3(double t) {
			return 1 - (1 - t) * (1 - t) * (1 - t);
		}
		
		public static double smoothStop4(double t) {
			return 1 - (1 - t) * (1 - t) * (1 - t) * (1 - t);
		}
		
		public static double smoothStop5(double t) {
			return 1 - (1 - t) * (1 - t) * (1 - t) * (1 - t) * (1 - t);
		}
		
		public static double smoothStep2(double t) {
			return (-2 * t + 3) * t * t;
		}
		
		public static double smoothStep3(double t) {
			return ((6 * t - 15) * t + 10) * t * t * t;
		}
		
		public static double smoothStep4(double t) {
			return (((-20 * t + 70) * t - 84) * t + 35) * t * t * t * t;
		}
		
		public static double smoothStep5(double t) {
			return ((((70 * t - 315) * t + 540) * t - 420) * t + 126) * t * t * t * t * t;
		}
		
		public static double arch2(double t) {
			return t * (1 - t) * ARCH2_CONSTANT;
		}
		
		public static double archStart3(double t) {
			return t * t * (1 - t) * ARCH3_CONSTANT;
		}
		
		public static double archStop3(double t) {
			return t * (1 - t) * (1 - t) * ARCH3_CONSTANT;
		}

		public static double arch4(double t) {
			return t * t * (1 - t) * (1 - t) * ARCH4_CONSTANT;
		}
		
		public static double archStart4(double t) {
			return t * t * t * (1 - t) * ARCH4_ALT_CONSTANT;
		}
		
		public static double archStop4(double t) {
			return t * (1 - t) * (1 - t) * (1 - t) * ARCH4_ALT_CONSTANT;
		}
		
		public static double archStart5(double t) {
			return t * t * t * t * (1 - t) * ARCH5_CONSTANT;
		}
		
		public static double archStop5(double t) {
			return t * (1 - t) * (1 - t) * (1 - t) * (1 - t) * ARCH5_CONSTANT;
		}
	
		public static double bellCurve(double t) {
			return t * t * t * (1 - t) * (1 - t) * (1 - t) * BELL_CONSTANT;
		}
	}
}
