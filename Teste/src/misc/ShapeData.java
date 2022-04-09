package misc;

public enum ShapeData {
	
	SHOOTER (1,
		new int[][][] {
			{{0,0}},
			{{0,0}},
			{{0,0}}},
		new double[] {0},
		new double[] {0},
		new double[] {0} 
	),
	
	MEGA_SHOOTER (4,
		new int[][][] {
			{{0,0}, {0, -1}, {1, 0}, {-1, 0}},
			{{0,0}, {-1, 0}, {0, -1}, {1, 0}},
			{{0,0}, {1, 0}, {-1, 0}, {0, -1}}},
		new double[] {0.0, 0.0, 0.0},
		new double[] {0.0, 0.0, 0.0},
		new double[] {0.0, 120.0, 240.0} 
	),
	
	CRYSTAL (2,
		new int[][][] {
			{{0,0}, {0, -1}},
			{{0,0}, {1, 0}},
			{{0,0}, {-1, 0}}},
		new double[] {-1.0/3.0, 1.0/6.0, 1.0/6.0},
		new double[] {0.0, 1.0/2.0, -1.0/2.0},
		new double[] {0.0, 120.0, 240.0}
	),
	
	MEGA_CRYSTAL (8,
		new int[][][] {
			{{0,0}, {0, -1}, {-1, 0}, {1, 0}, {0, 1}, {0, 2}, {1, 1}, {-1, 1}},
			{{0,0}, {-1, 0}, {1, 0}, {0, -1}, {2, -1}, {3, -1},	{1, -1}, {2, 0}},
			{{0,0}, {1, 0}, {0, -1}, {-1, 0}, {-2, 1}, {-3, -1}, {-2, 0}, {-1, -1}}},
		new double[] {2.0/3.0, -1.0/3.0, -1.0/3.0},
		new double[] {0.0, -1.0/2.0, 1.0/2.0},
		new double[] {0.0, 120.0, 240.0} 
	),
	
	ZOOMER (6,
		new int[][][] {
			{{0, 0}, {-1, 0}, {1, 0}, {0, -1}, {-1, -1}, {1, -1}},
			{{0, 0}, {1, 0}, {0, -1}, {-1, 0}, {-1, 1}, {2, 0}},
			{{0, 0}, {0, -1}, {-1, 0}, {1, 0}, {2, 0} , {-1, -1}}},
		new double[] {-1.0/3.0, 1.0/6.0, 1.0/6.0}, 
		new double[] {0, -1.0/2.0, 1.0/2.0},
		new double[] {0.0, 120.0, 240.0}
	),
	
	CURVY (4,
		new int[][][] {
			{{0, 0}, {-1, 0}, {0, -1}, {-1, -1}},
			{{0, 0}, {1, 0}, {-1, 0}, {-1, 1}},
			{{0, 0}, {0, -1}, {1, 0}, {2, 0}}},
		new double[] {-1.0/3.0, 2.0/3.0, -1.0/3.0}, 
		new double[] {-1.0/2.0, 0.0, 1.0/2.0},
		new double[] {0.0, 120.0, 240.0}
	),
	
	CANNON (6,
		new int[][][] {
			{{0, 0}, {0, -1}, {-1, 0}, {1, 0}, {-1, 1}, {1, 1}},
			{{0, 0}, {-1, 0}, {1, 0}, {0, -1}, {2, 0}, {1, -1}},
			{{0, 0}, {1, 0}, {0, -1}, {-1, 0}, {-1, -1}, {-2, 0}}},
		new double[] {2.0/3.0, -1.0/3.0, -1.0/3.0}, 
		new double[] {0.0, 1.0/2.0, -1.0/2.0},
		new double[] {0.0, 120.0, 240.0}
	),
	
	VAMPIRE3 (7,
		new int[][][] {
			{{0, 0}, {1, 0}, {-1, 0}, {2, 0}, {-2, 0}, {2, -1}, {-2, -1}},
			{{0, 0}, {0, -1}, {1, 0}, {-1, -1}, {1, 1}, {-2, -1}, {0, 1}},
			{{0, 0}, {-1, 0}, {0, -1}, {-1, 1}, {1, -1}, {0, 1}, {2, -1}}},
		new double[] {1.0/6.0, -1.0/12.0, -1.0/12.0}, 
		new double[] {0.0, 3.0/24.0, -3.0/24.0},
		new double[] {0.0, 120.0, 240.0}
	),
	
	VAMPIRE4 (9,
		new int[][][] {
			{{0, 0}, {1, 0}, {-1, 0}, {2, 0}, {-2, 0}, {3, 0}, {-3, 0}, {3, 1}, {-3, 1}},
			{{0, 0}, {0, -1}, {1, 0}, {-1, -1}, {1, 1}, {-1, -2}, {2, 1}, {0, -2}, {3, 1}},
			{{0, 0}, {-1, 0}, {0, -1}, {-1, 1}, {1, -1}, {-2, 1}, {1, -2}, {-3, 1}, {0, -2}}},
		new double[] {1.0/6.0,-1.0/12.0, -1.0/12.0}, 
		new double[] {0.0, 3.0/24.0, -3.0/24.0},
		new double[] {180.0, 300.0, 60.0}
	),
	
	VAMPIRE5 (11,
		new int[][][] {
			{{0, 0}, {1, 0}, {-1, 0}, {2, 0}, {-2, 0}, {3, 0}, {-3, 0}, {4, 0}, {-4, 0}, {4, -1}, {-4, -1}},
			{{0, 0}, {0, -1}, {1, 0}, {-1, -1}, {1, 1}, {-1, -2}, {2, 1}, {-2, -2}, {2, 2}, {-3, -2}, {1, 2}},
			{{0, 0}, {-1, 0}, {0, -1}, {-1, 1}, {1, -1}, {-2, 1}, {1, -2}, {-2, 2}, {2, -2}, {-1, 2}, {3, -2}}},
		new double[] {1.0/6.0,-1.0/12.0, -1.0/12.0}, 
		new double[] {0.0, 3.0/24.0, -3.0/24.0},
		new double[] {0.0, 120.0, 240.0}
	),
	
	HEX (6,
		new int[][][] {
			{{0, 0}, {1, 0}, {-1, 0}, {0, 1}, {1, 1}, {-1, 1}},
			{{0, 0}, {0, -1}, {1, 0}, {2, -1}, {1, -1}, {2,0}},
			{{0, 0}, {-1, 0}, {0, -1}, {-2, -1}, {-2, 0}, {-1, -1}}},
		new double[] {2.0/3.0, -1.0/3.0, -1.0/3.0}, 
		new double[] {0.0, 1.0/2.0, -1.0/2.0},
		new double[] {0.0, 120.0, 240.0}
	),
	
	HEXSTAR (12,
		new int[][][] {
			{{0, 0}, {1, 0}, {-1, 0}, {0, 1}, {1, 1}, {-1, 1}, {0, -1}, {2, 0}, {2, 1}, {0, 2}, {-2, 1}, {-2, 0}},
			{{0, 0}, {0, -1}, {1, 0}, {2, -1}, {1, -1}, {2,0}, {-1, 0}, {-1, -1}, {1, -2}, {3, -1}, {3, 0}, {1, 1}},
			{{0, 0}, {-1, 0}, {0, -1}, {-2, -1}, {-2, 0}, {-1, -1}, {1, 0}, {-1, 1}, {-3, 0}, {-3, -1}, {-1, -2}, {1, -1}}},
		new double[] {-1.0/3.0, 2.0/3.0, -1.0/3.0}, 
		new double[] {-1.0/2.0, 0.0, 1.0/2.0},
		new double[] {0.0, 120.0, 240.0}
	),
	
	RING (18,
		new int[][][] {
			{{0, -1}, {1, -1}, {-1, -1}, {2, -1}, {-2, -1}, {2, 0}, {-2, 0}, {3, 0}, {-3, 0},
			 {0, 2}, {1, 2}, {-1, 2}, {2, 2,}, {-2, 2}, {2, 1}, {-2, 1}, {3, 1}, {-3, 1}},
			{{-1, 0}, {-2, 0}, {-1, 1}, {-2, -1}, {0, 1}, {-1, -1}, {1, 1}, {-1, -2}, {2, 1},
			 {3, -1}, {3, -2}, {4, -1}, {2, -2}, {4, 0}, {1, -2}, {3, 0}, {0, -2}, {3, 1}},
			{{1, 0}, {1, 1}, {2, 0}, {0, 1}, {2, -1}, {-1, 1}, {1, -1}, {-3, 1}, {1, -2},
			 {-3, -1}, {-4, -1}, {-3, -2}, {-4, 0}, {-2, -2}, {-3, 0}, {-1, -2}, {-3, 1}, {0, -2}}},
		new double[] {2.0/3.0, -1.0/3.0, -1.0/3.0},
		new double[] {0.0, 1.0/2.0, -1.0/2.0},
		new double[] {0.0, 120.0, 240.0}
	);
	
	public final int size;
	public final int shape[][][];
	public final double offsetY[];
	public final double offsetX[];
	public final double angle[];
	
	ShapeData(int size, int shape[][][], double offY[], double offX[], double angle[]) {
		this.size = size;
		this.shape = shape;
		this.offsetY = offY;
		this.offsetX = offX;
		this.angle = angle;
	}
	
}
