package game.patterns;

import game.Hazard;
import game.instructions.ForwardInstruction;
import game.instructions.Instruction;
import game.instructions.RadialSpreadInstruction;
import misc.GenGet;

public final class GenericEnemyPattern extends Pattern{

	private static GenericEnemyPattern instance;
	
	
	
	Instruction shootingPattern;
	Instruction forward;
	
	private GenericEnemyPattern() {
		setup();
		forward = ForwardInstruction.getInstance();
		shootingPattern = RadialSpreadInstruction.getInstance();
	}
	
	public static GenericEnemyPattern getInstance() {
		if (instance == null) {
			instance = new GenericEnemyPattern();
		}
		return instance;
	}
	
	@Override
	public void cast(Hazard entity, double startTime, double currentTime, double delta) {
		int t, interval, n ;
		String[] KeyList;
		Object[] ArgList;
		forward.invoke(entity, delta);
		t = new GenGet<Integer>(Integer.class).get(readArgument("T"), 0);
		interval = new GenGet<Integer>(Integer.class).get(readArgument("Sleep"), 1);
		n = new GenGet<Integer>(Integer.class).get(readArgument("ArgN"), 0);
		KeyList = new GenGet<String[]>(String[].class).get(readArgument("ArgKeys"), null);
		ArgList = new GenGet<Object[]>(Object[].class).get(readArgument("ArgVals"), null);
		if (t%interval == interval - 1) {
			for (int i = 0; i < n; i++) if (shootingPattern != null) shootingPattern.setArgument(KeyList[i], ArgList[i]);
			if (shootingPattern != null) shootingPattern.invoke(entity, delta);
		}
		resetArguments();
	}

}
