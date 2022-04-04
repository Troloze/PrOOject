package misc;

import java.awt.Point;
import java.awt.geom.Point2D;

public final class Misc {
	
	public static final class Other {
		
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
	
	public static final class Background {
		
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
	
	public static final class Transform {
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
				
		public static double interpolate(double a, double b, double t) {
			return a - t * (a - b);
		}
		
		public static int interpolateDiscrete(int a, int b, double t) {
			return (int) (a - t * (a - b));
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

	public static final class MyMath {
		
		public static final double toDeg = 180.0/Math.PI;
		public static final double toRad = Math.PI/180.0;
		public static final double overDeg = 1.0/360.0;
		public static final double overRad = 1.0/(2.0 * Math.PI);
		
		// Valores precomputados dos cossenos de todos os �ngulos entre 0 e 360 com fator de .25
		// Altamente recomendado n�o expandir essa tabela, pois ela possui 361 linhas, com 1441 elementos.
		public static final double cosTable[] = {
				1.0000000000, 0.9999904807, 0.9999619231, 0.9999143276, 
				0.9998476952, 0.9997620271, 0.9996573250, 0.9995335908, 
				0.9993908270, 0.9992290362, 0.9990482216, 0.9988483865, 
				0.9986295348, 0.9983916706, 0.9981347984, 0.9978589232, 
				0.9975640503, 0.9972501851, 0.9969173337, 0.9965655025, 
				0.9961946981, 0.9958049276, 0.9953961984, 0.9949685183, 
				0.9945218954, 0.9940563382, 0.9935718557, 0.9930684570, 
				0.9925461516, 0.9920049497, 0.9914448614, 0.9908658974, 
				0.9902680687, 0.9896513868, 0.9890158634, 0.9883615105, 
				0.9876883406, 0.9869963666, 0.9862856015, 0.9855560591, 
				0.9848077530, 0.9840406976, 0.9832549076, 0.9824503977, 
				0.9816271834, 0.9807852804, 0.9799247046, 0.9790454725, 
				0.9781476007, 0.9772311065, 0.9762960071, 0.9753423205, 
				0.9743700648, 0.9733792585, 0.9723699204, 0.9713420698, 
				0.9702957263, 0.9692309097, 0.9681476404, 0.9670459389, 
				0.9659258263, 0.9647873238, 0.9636304532, 0.9624552365, 
				0.9612616959, 0.9600498544, 0.9588197349, 0.9575713608, 
				0.9563047560, 0.9550199445, 0.9537169507, 0.9523957996, 
				0.9510565163, 0.9496991262, 0.9483236552, 0.9469301295, 
				0.9455185756, 0.9440890204, 0.9426414911, 0.9411760153, 
				0.9396926208, 0.9381913359, 0.9366721892, 0.9351352097, 
				0.9335804265, 0.9320078693, 0.9304175680, 0.9288095529, 
				0.9271838546, 0.9255405040, 0.9238795325, 0.9222009717, 
				0.9205048535, 0.9187912101, 0.9170600744, 0.9153114791, 
				0.9135454576, 0.9117620436, 0.9099612709, 0.9081431738, 
				0.9063077870, 0.9044551455, 0.9025852843, 0.9006982393, 
				0.8987940463, 0.8968727415, 0.8949343616, 0.8929789434, 
				0.8910065242, 0.8890171415, 0.8870108332, 0.8849876375, 
				0.8829475929, 0.8808907382, 0.8788171127, 0.8767267557, 
				0.8746197071, 0.8724960071, 0.8703556959, 0.8681988145, 
				0.8660254038, 0.8638355052, 0.8616291604, 0.8594064115, 
				0.8571673007, 0.8549118707, 0.8526401644, 0.8503522250, 
				0.8480480962, 0.8457278217, 0.8433914458, 0.8410390130, 
				0.8386705679, 0.8362861558, 0.8338858221, 0.8314696123, 
				0.8290375726, 0.8265897491, 0.8241261886, 0.8216469379, 
				0.8191520443, 0.8166415552, 0.8141155184, 0.8115739820, 
				0.8090169944, 0.8064446043, 0.8038568606, 0.8012538127, 
				0.7986355100, 0.7960020025, 0.7933533403, 0.7906895737, 
				0.7880107536, 0.7853169309, 0.7826081569, 0.7798844831, 
				0.7771459615, 0.7743926441, 0.7716245834, 0.7688418321, 
				0.7660444431, 0.7632324698, 0.7604059656, 0.7575649844, 
				0.7547095802, 0.7518398075, 0.7489557208, 0.7460573751, 
				0.7431448255, 0.7402181275, 0.7372773368, 0.7343225094, 
				0.7313537016, 0.7283709699, 0.7253743710, 0.7223639621, 
				0.7193398003, 0.7163019434, 0.7132504492, 0.7101853756, 
				0.7071067812, 0.7040147245, 0.7009092643, 0.6977904598, 
				0.6946583705, 0.6915130558, 0.6883545757, 0.6851829903, 
				0.6819983601, 0.6788007455, 0.6755902076, 0.6723668074, 
				0.6691306064, 0.6658816660, 0.6626200482, 0.6593458151, 
				0.6560590290, 0.6527597525, 0.6494480483, 0.6461239796, 
				0.6427876097, 0.6394390020, 0.6360782203, 0.6327053286, 
				0.6293203910, 0.6259234722, 0.6225146366, 0.6190939493, 
				0.6156614753, 0.6122172800, 0.6087614290, 0.6052939880, 
				0.6018150232, 0.5983246006, 0.5948227868, 0.5913096484, 
				0.5877852523, 0.5842496656, 0.5807029557, 0.5771451900, 
				0.5735764364, 0.5699967626, 0.5664062369, 0.5628049277, 
				0.5591929035, 0.5555702330, 0.5519369853, 0.5482932295, 
				0.5446390350, 0.5409744714, 0.5372996083, 0.5336145159, 
				0.5299192642, 0.5262139237, 0.5224985647, 0.5187732582, 
				0.5150380749, 0.5112930861, 0.5075383630, 0.5037739770, 
				0.5000000000, 0.4962165037, 0.4924235601, 0.4886212415, 
				0.4848096202, 0.4809887689, 0.4771587603, 0.4733196672, 
				0.4694715628, 0.4656145203, 0.4617486132, 0.4578739151, 
				0.4539904997, 0.4500984410, 0.4461978131, 0.4422886902, 
				0.4383711468, 0.4344452574, 0.4305110968, 0.4265687399, 
				0.4226182617, 0.4186597375, 0.4146932427, 0.4107188526, 
				0.4067366431, 0.4027466899, 0.3987490689, 0.3947438564, 
				0.3907311285, 0.3867109616, 0.3826834324, 0.3786486174, 
				0.3746065934, 0.3705574375, 0.3665012267, 0.3624380383, 
				0.3583679495, 0.3542910380, 0.3502073813, 0.3461170571, 
				0.3420201433, 0.3379167180, 0.3338068592, 0.3296906453, 
				0.3255681545, 0.3214394653, 0.3173046564, 0.3131638065, 
				0.3090169944, 0.3048642990, 0.3007057995, 0.2965415750, 
				0.2923717047, 0.2881962681, 0.2840153447, 0.2798290140, 
				0.2756373558, 0.2714404499, 0.2672383761, 0.2630312145, 
				0.2588190451, 0.2546019482, 0.2503800041, 0.2461532930, 
				0.2419218956, 0.2376858923, 0.2334453639, 0.2292003909, 
				0.2249510543, 0.2206974350, 0.2164396139, 0.2121776722, 
				0.2079116908, 0.2036417511, 0.1993679344, 0.1950903220, 
				0.1908089954, 0.1865240360, 0.1822355255, 0.1779435455, 
				0.1736481777, 0.1693495038, 0.1650476059, 0.1607425656, 
				0.1564344650, 0.1521233862, 0.1478094111, 0.1434926220, 
				0.1391731010, 0.1348509303, 0.1305261922, 0.1261989691, 
				0.1218693434, 0.1175373975, 0.1132032138, 0.1088668749, 
				0.1045284633, 0.1001880616, 0.0958457525, 0.0915016187, 
				0.0871557427, 0.0828082075, 0.0784590957, 0.0741084902, 
				0.0697564737, 0.0654031292, 0.0610485395, 0.0566927876, 
				0.0523359562, 0.0479781285, 0.0436193874, 0.0392598158, 
				0.0348994967, 0.0305385132, 0.0261769483, 0.0218148850, 
				0.0174524064, 0.0130895956, 0.0087265355, 0.0043633093, 
				0.0000000000, -0.0043633093, -0.0087265355, -0.0130895956, 
				-0.0174524064, -0.0218148850, -0.0261769483, -0.0305385132, 
				-0.0348994967, -0.0392598158, -0.0436193874, -0.0479781285, 
				-0.0523359562, -0.0566927876, -0.0610485395, -0.0654031292, 
				-0.0697564737, -0.0741084902, -0.0784590957, -0.0828082075, 
				-0.0871557427, -0.0915016187, -0.0958457525, -0.1001880616, 
				-0.1045284633, -0.1088668749, -0.1132032138, -0.1175373975, 
				-0.1218693434, -0.1261989691, -0.1305261922, -0.1348509303, 
				-0.1391731010, -0.1434926220, -0.1478094111, -0.1521233862, 
				-0.1564344650, -0.1607425656, -0.1650476059, -0.1693495038, 
				-0.1736481777, -0.1779435455, -0.1822355255, -0.1865240360, 
				-0.1908089954, -0.1950903220, -0.1993679344, -0.2036417511, 
				-0.2079116908, -0.2121776722, -0.2164396139, -0.2206974350, 
				-0.2249510543, -0.2292003909, -0.2334453639, -0.2376858923, 
				-0.2419218956, -0.2461532930, -0.2503800041, -0.2546019482, 
				-0.2588190451, -0.2630312145, -0.2672383761, -0.2714404499, 
				-0.2756373558, -0.2798290140, -0.2840153447, -0.2881962681, 
				-0.2923717047, -0.2965415750, -0.3007057995, -0.3048642990, 
				-0.3090169944, -0.3131638065, -0.3173046564, -0.3214394653, 
				-0.3255681545, -0.3296906453, -0.3338068592, -0.3379167180, 
				-0.3420201433, -0.3461170571, -0.3502073813, -0.3542910380, 
				-0.3583679495, -0.3624380383, -0.3665012267, -0.3705574375, 
				-0.3746065934, -0.3786486174, -0.3826834324, -0.3867109616, 
				-0.3907311285, -0.3947438564, -0.3987490689, -0.4027466899, 
				-0.4067366431, -0.4107188526, -0.4146932427, -0.4186597375, 
				-0.4226182617, -0.4265687399, -0.4305110968, -0.4344452574, 
				-0.4383711468, -0.4422886902, -0.4461978131, -0.4500984410, 
				-0.4539904997, -0.4578739151, -0.4617486132, -0.4656145203, 
				-0.4694715628, -0.4733196672, -0.4771587603, -0.4809887689, 
				-0.4848096202, -0.4886212415, -0.4924235601, -0.4962165037, 
				-0.5000000000, -0.5037739770, -0.5075383630, -0.5112930861, 
				-0.5150380749, -0.5187732582, -0.5224985647, -0.5262139237, 
				-0.5299192642, -0.5336145159, -0.5372996083, -0.5409744714, 
				-0.5446390350, -0.5482932295, -0.5519369853, -0.5555702330, 
				-0.5591929035, -0.5628049277, -0.5664062369, -0.5699967626, 
				-0.5735764364, -0.5771451900, -0.5807029557, -0.5842496656, 
				-0.5877852523, -0.5913096484, -0.5948227868, -0.5983246006, 
				-0.6018150232, -0.6052939880, -0.6087614290, -0.6122172800, 
				-0.6156614753, -0.6190939493, -0.6225146366, -0.6259234722, 
				-0.6293203910, -0.6327053286, -0.6360782203, -0.6394390020, 
				-0.6427876097, -0.6461239796, -0.6494480483, -0.6527597525, 
				-0.6560590290, -0.6593458151, -0.6626200482, -0.6658816660, 
				-0.6691306064, -0.6723668074, -0.6755902076, -0.6788007455, 
				-0.6819983601, -0.6851829903, -0.6883545757, -0.6915130558, 
				-0.6946583705, -0.6977904598, -0.7009092643, -0.7040147245, 
				-0.7071067812, -0.7101853756, -0.7132504492, -0.7163019434, 
				-0.7193398003, -0.7223639621, -0.7253743710, -0.7283709699, 
				-0.7313537016, -0.7343225094, -0.7372773368, -0.7402181275, 
				-0.7431448255, -0.7460573751, -0.7489557208, -0.7518398075, 
				-0.7547095802, -0.7575649844, -0.7604059656, -0.7632324698, 
				-0.7660444431, -0.7688418321, -0.7716245834, -0.7743926441, 
				-0.7771459615, -0.7798844831, -0.7826081569, -0.7853169309, 
				-0.7880107536, -0.7906895737, -0.7933533403, -0.7960020025, 
				-0.7986355100, -0.8012538127, -0.8038568606, -0.8064446043, 
				-0.8090169944, -0.8115739820, -0.8141155184, -0.8166415552, 
				-0.8191520443, -0.8216469379, -0.8241261886, -0.8265897491, 
				-0.8290375726, -0.8314696123, -0.8338858221, -0.8362861558, 
				-0.8386705679, -0.8410390130, -0.8433914458, -0.8457278217, 
				-0.8480480962, -0.8503522250, -0.8526401644, -0.8549118707, 
				-0.8571673007, -0.8594064115, -0.8616291604, -0.8638355052, 
				-0.8660254038, -0.8681988145, -0.8703556959, -0.8724960071, 
				-0.8746197071, -0.8767267557, -0.8788171127, -0.8808907382, 
				-0.8829475929, -0.8849876375, -0.8870108332, -0.8890171415, 
				-0.8910065242, -0.8929789434, -0.8949343616, -0.8968727415, 
				-0.8987940463, -0.9006982393, -0.9025852843, -0.9044551455, 
				-0.9063077870, -0.9081431738, -0.9099612709, -0.9117620436, 
				-0.9135454576, -0.9153114791, -0.9170600744, -0.9187912101, 
				-0.9205048535, -0.9222009717, -0.9238795325, -0.9255405040, 
				-0.9271838546, -0.9288095529, -0.9304175680, -0.9320078693, 
				-0.9335804265, -0.9351352097, -0.9366721892, -0.9381913359, 
				-0.9396926208, -0.9411760153, -0.9426414911, -0.9440890204, 
				-0.9455185756, -0.9469301295, -0.9483236552, -0.9496991262, 
				-0.9510565163, -0.9523957996, -0.9537169507, -0.9550199445, 
				-0.9563047560, -0.9575713608, -0.9588197349, -0.9600498544, 
				-0.9612616959, -0.9624552365, -0.9636304532, -0.9647873238, 
				-0.9659258263, -0.9670459389, -0.9681476404, -0.9692309097, 
				-0.9702957263, -0.9713420698, -0.9723699204, -0.9733792585, 
				-0.9743700648, -0.9753423205, -0.9762960071, -0.9772311065, 
				-0.9781476007, -0.9790454725, -0.9799247046, -0.9807852804, 
				-0.9816271834, -0.9824503977, -0.9832549076, -0.9840406976, 
				-0.9848077530, -0.9855560591, -0.9862856015, -0.9869963666, 
				-0.9876883406, -0.9883615105, -0.9890158634, -0.9896513868, 
				-0.9902680687, -0.9908658974, -0.9914448614, -0.9920049497, 
				-0.9925461516, -0.9930684570, -0.9935718557, -0.9940563382, 
				-0.9945218954, -0.9949685183, -0.9953961984, -0.9958049276, 
				-0.9961946981, -0.9965655025, -0.9969173337, -0.9972501851, 
				-0.9975640503, -0.9978589232, -0.9981347984, -0.9983916706, 
				-0.9986295348, -0.9988483865, -0.9990482216, -0.9992290362, 
				-0.9993908270, -0.9995335908, -0.9996573250, -0.9997620271, 
				-0.9998476952, -0.9999143276, -0.9999619231, -0.9999904807, 
				-1.0000000000, -0.9999904807, -0.9999619231, -0.9999143276, 
				-0.9998476952, -0.9997620271, -0.9996573250, -0.9995335908, 
				-0.9993908270, -0.9992290362, -0.9990482216, -0.9988483865, 
				-0.9986295348, -0.9983916706, -0.9981347984, -0.9978589232, 
				-0.9975640503, -0.9972501851, -0.9969173337, -0.9965655025, 
				-0.9961946981, -0.9958049276, -0.9953961984, -0.9949685183, 
				-0.9945218954, -0.9940563382, -0.9935718557, -0.9930684570, 
				-0.9925461516, -0.9920049497, -0.9914448614, -0.9908658974, 
				-0.9902680687, -0.9896513868, -0.9890158634, -0.9883615105, 
				-0.9876883406, -0.9869963666, -0.9862856015, -0.9855560591, 
				-0.9848077530, -0.9840406976, -0.9832549076, -0.9824503977, 
				-0.9816271834, -0.9807852804, -0.9799247046, -0.9790454725, 
				-0.9781476007, -0.9772311065, -0.9762960071, -0.9753423205, 
				-0.9743700648, -0.9733792585, -0.9723699204, -0.9713420698, 
				-0.9702957263, -0.9692309097, -0.9681476404, -0.9670459389, 
				-0.9659258263, -0.9647873238, -0.9636304532, -0.9624552365, 
				-0.9612616959, -0.9600498544, -0.9588197349, -0.9575713608, 
				-0.9563047560, -0.9550199445, -0.9537169507, -0.9523957996, 
				-0.9510565163, -0.9496991262, -0.9483236552, -0.9469301295, 
				-0.9455185756, -0.9440890204, -0.9426414911, -0.9411760153, 
				-0.9396926208, -0.9381913359, -0.9366721892, -0.9351352097, 
				-0.9335804265, -0.9320078693, -0.9304175680, -0.9288095529, 
				-0.9271838546, -0.9255405040, -0.9238795325, -0.9222009717, 
				-0.9205048535, -0.9187912101, -0.9170600744, -0.9153114791, 
				-0.9135454576, -0.9117620436, -0.9099612709, -0.9081431738, 
				-0.9063077870, -0.9044551455, -0.9025852843, -0.9006982393, 
				-0.8987940463, -0.8968727415, -0.8949343616, -0.8929789434, 
				-0.8910065242, -0.8890171415, -0.8870108332, -0.8849876375, 
				-0.8829475929, -0.8808907382, -0.8788171127, -0.8767267557, 
				-0.8746197071, -0.8724960071, -0.8703556959, -0.8681988145, 
				-0.8660254038, -0.8638355052, -0.8616291604, -0.8594064115, 
				-0.8571673007, -0.8549118707, -0.8526401644, -0.8503522250, 
				-0.8480480962, -0.8457278217, -0.8433914458, -0.8410390130, 
				-0.8386705679, -0.8362861558, -0.8338858221, -0.8314696123, 
				-0.8290375726, -0.8265897491, -0.8241261886, -0.8216469379, 
				-0.8191520443, -0.8166415552, -0.8141155184, -0.8115739820, 
				-0.8090169944, -0.8064446043, -0.8038568606, -0.8012538127, 
				-0.7986355100, -0.7960020025, -0.7933533403, -0.7906895737, 
				-0.7880107536, -0.7853169309, -0.7826081569, -0.7798844831, 
				-0.7771459615, -0.7743926441, -0.7716245834, -0.7688418321, 
				-0.7660444431, -0.7632324698, -0.7604059656, -0.7575649844, 
				-0.7547095802, -0.7518398075, -0.7489557208, -0.7460573751, 
				-0.7431448255, -0.7402181275, -0.7372773368, -0.7343225094, 
				-0.7313537016, -0.7283709699, -0.7253743710, -0.7223639621, 
				-0.7193398003, -0.7163019434, -0.7132504492, -0.7101853756, 
				-0.7071067812, -0.7040147245, -0.7009092643, -0.6977904598, 
				-0.6946583705, -0.6915130558, -0.6883545757, -0.6851829903, 
				-0.6819983601, -0.6788007455, -0.6755902076, -0.6723668074, 
				-0.6691306064, -0.6658816660, -0.6626200482, -0.6593458151, 
				-0.6560590290, -0.6527597525, -0.6494480483, -0.6461239796, 
				-0.6427876097, -0.6394390020, -0.6360782203, -0.6327053286, 
				-0.6293203910, -0.6259234722, -0.6225146366, -0.6190939493, 
				-0.6156614753, -0.6122172800, -0.6087614290, -0.6052939880, 
				-0.6018150232, -0.5983246006, -0.5948227868, -0.5913096484, 
				-0.5877852523, -0.5842496656, -0.5807029557, -0.5771451900, 
				-0.5735764364, -0.5699967626, -0.5664062369, -0.5628049277, 
				-0.5591929035, -0.5555702330, -0.5519369853, -0.5482932295, 
				-0.5446390350, -0.5409744714, -0.5372996083, -0.5336145159, 
				-0.5299192642, -0.5262139237, -0.5224985647, -0.5187732582, 
				-0.5150380749, -0.5112930861, -0.5075383630, -0.5037739770, 
				-0.5000000000, -0.4962165037, -0.4924235601, -0.4886212415, 
				-0.4848096202, -0.4809887689, -0.4771587603, -0.4733196672, 
				-0.4694715628, -0.4656145203, -0.4617486132, -0.4578739151, 
				-0.4539904997, -0.4500984410, -0.4461978131, -0.4422886902, 
				-0.4383711468, -0.4344452574, -0.4305110968, -0.4265687399, 
				-0.4226182617, -0.4186597375, -0.4146932427, -0.4107188526, 
				-0.4067366431, -0.4027466899, -0.3987490689, -0.3947438564, 
				-0.3907311285, -0.3867109616, -0.3826834324, -0.3786486174, 
				-0.3746065934, -0.3705574375, -0.3665012267, -0.3624380383, 
				-0.3583679495, -0.3542910380, -0.3502073813, -0.3461170571, 
				-0.3420201433, -0.3379167180, -0.3338068592, -0.3296906453, 
				-0.3255681545, -0.3214394653, -0.3173046564, -0.3131638065, 
				-0.3090169944, -0.3048642990, -0.3007057995, -0.2965415750, 
				-0.2923717047, -0.2881962681, -0.2840153447, -0.2798290140, 
				-0.2756373558, -0.2714404499, -0.2672383761, -0.2630312145, 
				-0.2588190451, -0.2546019482, -0.2503800041, -0.2461532930, 
				-0.2419218956, -0.2376858923, -0.2334453639, -0.2292003909, 
				-0.2249510543, -0.2206974350, -0.2164396139, -0.2121776722, 
				-0.2079116908, -0.2036417511, -0.1993679344, -0.1950903220, 
				-0.1908089954, -0.1865240360, -0.1822355255, -0.1779435455, 
				-0.1736481777, -0.1693495038, -0.1650476059, -0.1607425656, 
				-0.1564344650, -0.1521233862, -0.1478094111, -0.1434926220, 
				-0.1391731010, -0.1348509303, -0.1305261922, -0.1261989691, 
				-0.1218693434, -0.1175373975, -0.1132032138, -0.1088668749, 
				-0.1045284633, -0.1001880616, -0.0958457525, -0.0915016187, 
				-0.0871557427, -0.0828082075, -0.0784590957, -0.0741084902, 
				-0.0697564737, -0.0654031292, -0.0610485395, -0.0566927876, 
				-0.0523359562, -0.0479781285, -0.0436193874, -0.0392598158, 
				-0.0348994967, -0.0305385132, -0.0261769483, -0.0218148850, 
				-0.0174524064, -0.0130895956, -0.0087265355, -0.0043633093, 
				-0.0000000000, 0.0043633093, 0.0087265355, 0.0130895956, 
				0.0174524064, 0.0218148850, 0.0261769483, 0.0305385132, 
				0.0348994967, 0.0392598158, 0.0436193874, 0.0479781285, 
				0.0523359562, 0.0566927876, 0.0610485395, 0.0654031292, 
				0.0697564737, 0.0741084902, 0.0784590957, 0.0828082075, 
				0.0871557427, 0.0915016187, 0.0958457525, 0.1001880616, 
				0.1045284633, 0.1088668749, 0.1132032138, 0.1175373975, 
				0.1218693434, 0.1261989691, 0.1305261922, 0.1348509303, 
				0.1391731010, 0.1434926220, 0.1478094111, 0.1521233862, 
				0.1564344650, 0.1607425656, 0.1650476059, 0.1693495038, 
				0.1736481777, 0.1779435455, 0.1822355255, 0.1865240360, 
				0.1908089954, 0.1950903220, 0.1993679344, 0.2036417511, 
				0.2079116908, 0.2121776722, 0.2164396139, 0.2206974350, 
				0.2249510543, 0.2292003909, 0.2334453639, 0.2376858923, 
				0.2419218956, 0.2461532930, 0.2503800041, 0.2546019482, 
				0.2588190451, 0.2630312145, 0.2672383761, 0.2714404499, 
				0.2756373558, 0.2798290140, 0.2840153447, 0.2881962681, 
				0.2923717047, 0.2965415750, 0.3007057995, 0.3048642990, 
				0.3090169944, 0.3131638065, 0.3173046564, 0.3214394653, 
				0.3255681545, 0.3296906453, 0.3338068592, 0.3379167180, 
				0.3420201433, 0.3461170571, 0.3502073813, 0.3542910380, 
				0.3583679495, 0.3624380383, 0.3665012267, 0.3705574375, 
				0.3746065934, 0.3786486174, 0.3826834324, 0.3867109616, 
				0.3907311285, 0.3947438564, 0.3987490689, 0.4027466899, 
				0.4067366431, 0.4107188526, 0.4146932427, 0.4186597375, 
				0.4226182617, 0.4265687399, 0.4305110968, 0.4344452574, 
				0.4383711468, 0.4422886902, 0.4461978131, 0.4500984410, 
				0.4539904997, 0.4578739151, 0.4617486132, 0.4656145203, 
				0.4694715628, 0.4733196672, 0.4771587603, 0.4809887689, 
				0.4848096202, 0.4886212415, 0.4924235601, 0.4962165037, 
				0.5000000000, 0.5037739770, 0.5075383630, 0.5112930861, 
				0.5150380749, 0.5187732582, 0.5224985647, 0.5262139237, 
				0.5299192642, 0.5336145159, 0.5372996083, 0.5409744714, 
				0.5446390350, 0.5482932295, 0.5519369853, 0.5555702330, 
				0.5591929035, 0.5628049277, 0.5664062369, 0.5699967626, 
				0.5735764364, 0.5771451900, 0.5807029557, 0.5842496656, 
				0.5877852523, 0.5913096484, 0.5948227868, 0.5983246006, 
				0.6018150232, 0.6052939880, 0.6087614290, 0.6122172800, 
				0.6156614753, 0.6190939493, 0.6225146366, 0.6259234722, 
				0.6293203910, 0.6327053286, 0.6360782203, 0.6394390020, 
				0.6427876097, 0.6461239796, 0.6494480483, 0.6527597525, 
				0.6560590290, 0.6593458151, 0.6626200482, 0.6658816660, 
				0.6691306064, 0.6723668074, 0.6755902076, 0.6788007455, 
				0.6819983601, 0.6851829903, 0.6883545757, 0.6915130558, 
				0.6946583705, 0.6977904598, 0.7009092643, 0.7040147245, 
				0.7071067812, 0.7101853756, 0.7132504492, 0.7163019434, 
				0.7193398003, 0.7223639621, 0.7253743710, 0.7283709699, 
				0.7313537016, 0.7343225094, 0.7372773368, 0.7402181275, 
				0.7431448255, 0.7460573751, 0.7489557208, 0.7518398075, 
				0.7547095802, 0.7575649844, 0.7604059656, 0.7632324698, 
				0.7660444431, 0.7688418321, 0.7716245834, 0.7743926441, 
				0.7771459615, 0.7798844831, 0.7826081569, 0.7853169309, 
				0.7880107536, 0.7906895737, 0.7933533403, 0.7960020025, 
				0.7986355100, 0.8012538127, 0.8038568606, 0.8064446043, 
				0.8090169944, 0.8115739820, 0.8141155184, 0.8166415552, 
				0.8191520443, 0.8216469379, 0.8241261886, 0.8265897491, 
				0.8290375726, 0.8314696123, 0.8338858221, 0.8362861558, 
				0.8386705679, 0.8410390130, 0.8433914458, 0.8457278217, 
				0.8480480962, 0.8503522250, 0.8526401644, 0.8549118707, 
				0.8571673007, 0.8594064115, 0.8616291604, 0.8638355052, 
				0.8660254038, 0.8681988145, 0.8703556959, 0.8724960071, 
				0.8746197071, 0.8767267557, 0.8788171127, 0.8808907382, 
				0.8829475929, 0.8849876375, 0.8870108332, 0.8890171415, 
				0.8910065242, 0.8929789434, 0.8949343616, 0.8968727415, 
				0.8987940463, 0.9006982393, 0.9025852843, 0.9044551455, 
				0.9063077870, 0.9081431738, 0.9099612709, 0.9117620436, 
				0.9135454576, 0.9153114791, 0.9170600744, 0.9187912101, 
				0.9205048535, 0.9222009717, 0.9238795325, 0.9255405040, 
				0.9271838546, 0.9288095529, 0.9304175680, 0.9320078693, 
				0.9335804265, 0.9351352097, 0.9366721892, 0.9381913359, 
				0.9396926208, 0.9411760153, 0.9426414911, 0.9440890204, 
				0.9455185756, 0.9469301295, 0.9483236552, 0.9496991262, 
				0.9510565163, 0.9523957996, 0.9537169507, 0.9550199445, 
				0.9563047560, 0.9575713608, 0.9588197349, 0.9600498544, 
				0.9612616959, 0.9624552365, 0.9636304532, 0.9647873238, 
				0.9659258263, 0.9670459389, 0.9681476404, 0.9692309097, 
				0.9702957263, 0.9713420698, 0.9723699204, 0.9733792585, 
				0.9743700648, 0.9753423205, 0.9762960071, 0.9772311065, 
				0.9781476007, 0.9790454725, 0.9799247046, 0.9807852804, 
				0.9816271834, 0.9824503977, 0.9832549076, 0.9840406976, 
				0.9848077530, 0.9855560591, 0.9862856015, 0.9869963666, 
				0.9876883406, 0.9883615105, 0.9890158634, 0.9896513868, 
				0.9902680687, 0.9908658974, 0.9914448614, 0.9920049497, 
				0.9925461516, 0.9930684570, 0.9935718557, 0.9940563382, 
				0.9945218954, 0.9949685183, 0.9953961984, 0.9958049276, 
				0.9961946981, 0.9965655025, 0.9969173337, 0.9972501851, 
				0.9975640503, 0.9978589232, 0.9981347984, 0.9983916706, 
				0.9986295348, 0.9988483865, 0.9990482216, 0.9992290362, 
				0.9993908270, 0.9995335908, 0.9996573250, 0.9997620271, 
				0.9998476952, 0.9999143276, 0.9999619231, 0.9999904807, 
				1.0000000000
		};
		
		// Cosseno R�pido, sem nenhuma divis�o, ou opera��o demorada.
		public static double fcosDeg(double angle) {
			double t, a, b;
			if (angle < 0) angle = 180 - angle;
			int k, n = (int) (angle * overDeg);
			angle = angle - 360 * n;
			k = (int) (4 * angle);
			t = 4 * angle - k;
			a = cosTable[k];
			b = cosTable[k + 1];
			return Transform.interpolate(a, b, t);
		}
		
		public static double fcosRad(double angle) {
			return fcosDeg(angle * toDeg);
		}
		
		public static double fsinDeg(double angle) {
			return fcosDeg(angle - 90.0);
		}
		
		public static double fsinRad(double angle) {
			return fcosDeg(angle * toDeg - 90.0);
		}
		
	}
}
