package misc;

import java.awt.geom.Point2D;

public final class Miscelaneous {


	public static final class Background {
		public static final double TRIANGLE_LENGTH = 1.0;
		public static final double TRIANGLE_LENGTH_2 = TRIANGLE_LENGTH/2.0;
		public static final double TRIANGLE_HEIGHT = 1.0;
		public static final double TRIANGLE_HEIGHT_3 = TRIANGLE_HEIGHT/3.0;
		public static final double TRIANGLE_HEIGHT_2_3 = TRIANGLE_HEIGHT_3 * 2.0;
		
		public static Point2D MeshToWorld(Point2D offset, Point2D meshPosition) {
			double returnX = meshPosition.getX() * TRIANGLE_LENGTH_2;
			double returnY;
			
			if ((((int) meshPosition.getY())%2 == 0) ^ (((int) meshPosition.getX())%2 == 0)) 
				returnY = meshPosition.getY() * TRIANGLE_HEIGHT;
			else 
				returnY = meshPosition.getY() * TRIANGLE_HEIGHT + TRIANGLE_HEIGHT_3;
			
			return new Point2D.Double(returnX, returnY);
		}
		
		public static Point2D WorldToMesh(Point2D offset, Point2D worldPosition) {
			Point2D newCoords = new Point2D.Double((2*worldPosition.getX()/TRIANGLE_LENGTH), (((worldPosition.getY())/TRIANGLE_HEIGHT) + (1.0/3.0))); // Conversões assustadoras
			Point2D newCoordsDecimal = new Point2D.Double(newCoords.getX() - ((int) newCoords.getX()), newCoords.getY() - ((int) newCoords.getY()));
			
			double returnX = offset.getX() + (int) newCoords.getX();	// Fazer a conversão e adicionar o offset
			double returnY = offset.getY() + (int) newCoords.getY();	
			
			boolean auxConditional = ((int) newCoords.getX())%2 == 0;	// Se X par
			auxConditional = (auxConditional)? 
					newCoordsDecimal.getX() >= 1 - newCoordsDecimal.getY(): // Verificar se os valores decimais de X são maiores que 1 menos os valores decimais de Y
					newCoordsDecimal.getX() >= newCoordsDecimal.getY();	// Senão Verificar se os valores decimais de X são maiores que os valores decimais de Y
			
			if (auxConditional) // Se a verificação for bem sucedida, aumentar 1 em returnX
				returnX += 1;
			
			return new Point2D.Double(returnX, returnY);
		}
	}
	
	public static final class Transformation {
		public static double BELLCURVE_INTEGRAL_INVERSE = 2.1875;
		public static double SSTART_ARC_3_INTEGRAL_INVERSE = 1.0/0.5625;
		public static double SSTOP_ARC_3_INTEGRAL_INVERSE = SSTART_ARC_3_INTEGRAL_INVERSE;
		public static double ARCH_INTEGRAL_INVERSE = 1.5;
		public static double SSTART_2_INTEGRAL_INVERSE = 3.0;
		public static double SSTART_3_INTEGRAL_INVERSE = 4.0;
		public static double SSTART_4_INTEGRAL_INVERSE = 5.0;
		public static double SSTART_5_INTEGRAL_INVERSE = 6.0;
		public static double SSTOP_2_INTEGRAL_INVERSE = 3.0/2.0;
		public static double SSTOP_3_INTEGRAL_INVERSE = 4.0/3.0;
		public static double SSTOP_4_INTEGRAL_INVERSE = 5.0/4.0;
		public static double SSTOP_5_INTEGRAL_INVERSE = 6.0/5.0;
		public static double SSTEP_2_INTEGRAL_INVERSE = 2.0;
		public static double SSTEP_3_INTEGRAL_INVERSE = 2.0;
		public static double SSTEP_4_INTEGRAL_INVERSE = 2.0;
		public static double SSTEP_5_INTEGRAL_INVERSE = 2.0;
		
		public static double crossfade(double A, double B, double t) {
			return A - t * (A - B);
		}
		
		public static int discreteCrossfade(int A, int B, double t) {
			return (int) (A - t * (A - B));
		}
		
		public static double clamp(double in, double top, double bottom) {
			return (in > top) ? top : ((in < bottom) ? bottom : in);
		}
		
		public static double remap(double in, double bottomThreshold, double topThreshold, double newBottomThreshold, double newTopThreshold) {
			return (((in - bottomThreshold) / (topThreshold - bottomThreshold)) * (newTopThreshold - newBottomThreshold)) + newBottomThreshold;
		}
		
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
			return 1 - ((1 - t) * (1 - t));
		}
		
		public static double smoothStop3(double t) {
			return 1 - ((1 - t) * (1 - t) * (1 - t));
		}
		
		public static double smoothStop4(double t) {
			return 1 - ((1 - t) * (1 - t) * (1 - t) * (1 - t));
		}
		
		public static double smoothStop5(double t) {
			return 1 - ((1 - t) * (1 - t) * (1 - t) * (1 - t) * (1 - t));
		}
		
		public static double smoothStep2(double t) {
			return (-2 * t + 3) * t * t;
		}
		
		public static double smoothStep3(double t) {
			return ((6 * t - 15) * t + 10) * t * t * t;
		}
		
		public static double smoothStep4(double t) {
			return ((-20 * t + 70) * t - 84) * t * t * t * t;
		}

		public static double smoothStep5(double t) {
			return (((((-252 * t + 1386) * t - 3080) * t + 3465) * t - 1980) * t + 462) * t * t * t * t * t;
		}
		
		public static double arch2(double t) {
			return t * (1 - t) * 4.0;
		}
		
		public static double smoothStartArch3(double t) {
			return t * t * (1 - t) * 6.75;
		}
		
		public static double smoothStopArch3(double t) {
			return t * (1 - t) * (1 - t) * 6.75;
		}
		
		public static double bellCurve(double t) {
			return smoothStart3(t) * (1 - smoothStop3(t)) * 64.0;
		}
	}

	public static final class MiscMath{
		
		public static double angleBetween(Point2D a, Point2D b) {
			return Math.toDegrees(Math.atan2(a.getY() - b.getY(), a.getX() - b.getX()));
		}
		
		public static Point2D rotateAround(Point2D src, double distance, double angle) {
			return new Point2D.Double(src.getX() + distance * (Math.cos(Math.toRadians(angle))), src.getY() + distance * (Math.sin(Math.toRadians(angle))));
		}
	}
}
