package misc;

import java.awt.geom.Point2D;

import engine.ImageBufferHandler;
import game.instructions.RadialSpreadInstruction;
import game.patterns.GenericEnemyPattern;

public class WaveParams {
	
	
	private final double TOP_SPAWN = -400;
	private final double LEFT_SPAWN = -500;
	private final double RIGHT_SPAWN = 500;
	private final double[] TOP_POSSIBILITIES = {0, 200, -200};
	private final double[] SIDE_POSSIBILITIES = {0, -100, -200};
	
	
	
	public double tInterval; 	
	public int number;
	
	public Transform startPos;
	public Transform maxOffset;
	
	public ImageData enemies;
	
	public InstanceParams instPar;
	
	public PatternParams patPar;
	
	private boolean getRandom(int max, int threshold) {
		return (Math.random() * max < threshold);
	}
	
	private ImageData randomBullet() {
		ImageData nID = new ImageData();
		nID.type = (int) Math.round(Math.random() * 8);
		nID.color = (int) Math.round(Math.random() * 7);
		if (nID.type == 1 || nID.type == 5) nID.type++;
		return nID;
	}
	

	
	private InstanceParams generateBullet1() {
		InstanceParams instPar = new InstanceParams();
		boolean rotates = getRandom(10, 3);
		instPar.speed = 500;
		instPar.transform.getDefaultScale().setLocation(50, 50);
		instPar.transform.setScale(Math.random() * 1.75 + 0.75);
		if (rotates) instPar.rotationSpeed = (Math.random() * 9 + 1) * (getRandom(2, 1) ? 1 : -1);
		instPar.speed = Math.random() * 400 + 200;
		instPar.lifeTime = 10;
		instPar.spriteData = randomBullet();
		String[] keys = {"Scale"};
		Object[] args = {Math.random() * 1.5 + 0.5};
		instPar.patPar = new PatternParams(1, keys, args);
		instPar.pattern = GenericEnemyPattern.getInstance();
		return instPar;
	}
	
	private PatternParams generatePattern1() {
		String[] pKeys = {
				"InstParams",
				"Amount",
				"FollowPlayer",
				"Spread",
				"RandomDir"
		};
		Object[] pArgs = {
				generateBullet1(),
				(int) (Math.random() * 10) + (getRandom(5, 1) ?  1 : 5),
				false,
				(getRandom(2,1)) ? (getRandom(2, 1) ? 0 : 360) : (Math.random() * 60 + 30),
				true
		};
		String[] keys = {
				"Sleep",
				"Pattern",
				"ArgKeys",
				"ArgVals",
				"ArgN"
		};
		Object[] args = {
				(int) (Math.random() * 60 + 60), // Sleep
				RadialSpreadInstruction.getInstance(),
				pKeys,
				pArgs,
				5
		};
		PatternParams retPar = new PatternParams(5, keys, args);
		return retPar;
	}
	
	private InstanceParams generateBarrageBullet() {
		InstanceParams instPar = new InstanceParams();
		instPar.transform.getDefaultScale().setLocation(50, 50);
		instPar.speed = Math.random() * 500 + 100;
		instPar.lifeTime = 10;
		instPar.spriteData = randomBullet();
		String[] keys = {"Scale"};
		Object[] args = {Math.random() * 0.25 + 0.75};
		instPar.patPar = new PatternParams(1, keys, args);
		instPar.pattern = GenericEnemyPattern.getInstance();
		return instPar;
	}
	
	private PatternParams generateBarrage1() {
		String[] pKeys = {
				"InstParams",
				"Amount",
				"FollowPlayer",
				"Spread",
				"RandomDir",
				"RandomRate"
		};
		Object[] pArgs = {
				generateBarrageBullet(),
				(int) (Math.random() * 8 + 8),
				true,
				(Math.random() * 60 + 45),
				true,
				30.0
		};
		String[] keys = {
				"Sleep",
				"Pattern",
				"ArgKeys",
				"ArgVals",
				"ArgN"
		};
		Object[] args = {
				(int) (Math.random() * 100 + 100), // Sleep
				RadialSpreadInstruction.getInstance(),
				pKeys,
				pArgs,
				6
		};
		PatternParams retPar = new PatternParams(5, keys, args);
		return retPar;
	}
	
	private PatternParams generateBarrage2() {
		String[] pKeys = {
				"InstParams",
				"Amount",
				"FollowPlayer",
				"Spread",
				"RandomDir"
		};
		Object[] pArgs = {
				generateBarrageBullet(),
				(int) Math.round(Math.random() * 4) + 1,
				true,
				(Math.random() * 30 + 15),
				false
		};
		String[] keys = {
				"Sleep",
				"Pattern",
				"ArgKeys",
				"ArgVals",
				"ArgN"
		};
		Object[] args = {
				(int) (Math.random() * 40 + 40), // Sleep
				RadialSpreadInstruction.getInstance(),
				pKeys,
				pArgs,
				5
		};
		PatternParams retPar = new PatternParams(5, keys, args);
		return retPar;
	}
	
	private InstanceParams generateStormBullet() {
		InstanceParams instPar = new InstanceParams();
		boolean rotates = getRandom(10, 3);
		instPar.transform.getDefaultScale().setLocation(50, 50);
		instPar.transform.setScale(Math.random() * 1.75 + 0.75);
		if (rotates) instPar.rotationSpeed = (Math.random() * 15 + 2) * (getRandom(2, 1) ? 1 : -1);
		instPar.speed = Math.random() * 600 + 100;
		instPar.lifeTime = 10;
		instPar.spriteData = randomBullet();
		String[] keys = {"Scale"};
		Object[] args = {Math.random() * 1.5 + 0.5};
		instPar.patPar = new PatternParams(1, keys, args);
		instPar.pattern = GenericEnemyPattern.getInstance();
		return instPar;
	}
	
	private PatternParams generateStorm() {
		String[] pKeys = {
				"InstParams",
				"Amount",
				"FollowPlayer",
				"Spread",
				"RandomDir",
				"RandomRate"
		};
		Object[] pArgs = {
				generateStormBullet(),
				1,
				false,
				0.0,
				true,
				360.0
		};
		String[] keys = {
				"Sleep",
				"Pattern",
				"ArgKeys",
				"ArgVals",
				"ArgN"
		};
		Object[] args = {
				(int) (Math.random() * 8 + 2), // Sleep
				RadialSpreadInstruction.getInstance(),
				pKeys,
				pArgs,
				6
		};
		PatternParams retPar = new PatternParams(5, keys, args);
		return retPar;
	}

	private InstanceParams generateBlastBullet() {
		InstanceParams instPar = new InstanceParams();
		boolean rotates = getRandom(10, 5);
		instPar.transform.getDefaultScale().setLocation(50, 50);
		if (rotates) instPar.rotationSpeed = (Math.random() * 10 + 5) * (getRandom(2, 1) ? 1 : -1);
		instPar.speed = Math.random() * 400 + 200;
		instPar.lifeTime = 10;
		instPar.spriteData = randomBullet();
		String[] keys = {"Scale"};
		Object[] args = {Math.random() * 2.5 + 0.5};
		instPar.patPar = new PatternParams(1, keys, args);
		instPar.pattern = GenericEnemyPattern.getInstance();
		return instPar;
	}

	private PatternParams generateBlast() {
		String[] pKeys = {
				"InstParams",
				"Amount",
				"FollowPlayer",
				"Spread",
				"RandomDir",
				"RandomRate"
		};
		Object[] pArgs = {
				generateBlastBullet(),
				(int) (Math.random() * 20) + 5,
				false,
				360.0,
				true,
				360.0
		};
		String[] keys = {
				"Sleep",
				"Pattern",
				"ArgKeys",
				"ArgVals",
				"ArgN"
		};
		Object[] args = {
				(int) (Math.random() * 60 + 30), // Sleep
				RadialSpreadInstruction.getInstance(),
				pKeys,
				pArgs,
				6
		};
		PatternParams retPar = new PatternParams(5, keys, args);
		return retPar;
	}
	
	public WaveParams() {
		int chance;
		int side = (int) Math.round(Math.random());
		boolean hasTransform = getRandom(10, 1);
		boolean hasRotate = getRandom(10, 2);
		Point2D maxOffPos = new Point2D.Double();
		Point2D spawnPos = new Point2D.Double();
		
		double direction = 0;
		
		switch (side) {
			case 0:
				direction = 360 + (Math.random() * 6 - 3);
				maxOffPos.setLocation(0, Math.random() * 50 + 50);
				spawnPos.setLocation(LEFT_SPAWN, SIDE_POSSIBILITIES[(int) Math.round(Math.random() * 2)]);
			break;
			case 1:
				direction = 360 + (Math.random() * 6 + 177);
				maxOffPos.setLocation(0, Math.random() * 50 + 50);
				spawnPos.setLocation(RIGHT_SPAWN, SIDE_POSSIBILITIES[(int) Math.round(Math.random() * 2)]);
			break;
		}
		
		tInterval = Math.random() * 0.8 + 0.5;
		number = (int) (Math.random() * 5) + 4;
		startPos = new Transform();
		instPar = new InstanceParams();
		instPar.transform.setZPosition(10);
		instPar.transform.getDefaultScale().setLocation(50, 43);
		instPar.transform.getPosition().setLocation(spawnPos);
		instPar.transform.setScale(1);
		instPar.transform.setRotation(0);
		
		if (hasTransform) {
			maxOffset = new Transform();
			maxOffset.getPosition().setLocation(maxOffPos);
		}
		
		instPar.transform.getPosition().setLocation(
				instPar.transform.getPosition().getX() + ((Math.random() - 0.5) * maxOffPos.getX()),
				instPar.transform.getPosition().getY() + ((Math.random() - 0.5) * maxOffPos.getY())
		);

		instPar.spriteData.type = ImageBufferHandler.TRIANGLE;
		instPar.spriteData.color = (int) Math.floor(Math.random() * 3) + 2;
		if (instPar.spriteData.color >= 5) enemies.color += 2;

		instPar.life = 20;
		instPar.lifeTime = 10;
		instPar.value = 5;
		instPar.speed = Math.random() * 200 + 100;
		if (hasRotate) instPar.rotationSpeed = (Math.random() * 5 + 5) * ((getRandom(2, 1))? 1 : -1);
		instPar.direction = direction;
		
		
		chance = (int) (Math.random() * 100);

		if (chance < 10) {
			instPar.patPar = generateBarrage1();
		} else if (chance < 30) {
			instPar.patPar = generateBarrage2();
		} else if (chance < 70) {
			instPar.patPar = generateBlast();
		} else {
			instPar.patPar = generateStorm();
		}
		
		
		
	}
}
