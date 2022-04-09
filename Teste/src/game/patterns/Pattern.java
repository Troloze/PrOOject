package game.patterns;

import java.util.HashMap;
import java.util.Map;

import game.Hazard;
import game.instructions.Instruction;

public abstract class Pattern {

	public static final int DEFAULT = 0;
	public static final int START = 1;
	public static final int DEATH = 2;
	public static final int END = 3;

	private Instruction onStart, onEnd, onDeath;
	
	Map<String, Object> arguments;
	
	protected void setup() {
		arguments = new HashMap<>();
	}
	
	protected Object readArgument(String key) {
		if (key == null || arguments == null) return null;
		return arguments.get(key);
	}
	
	public void setArgument(String key, Object value) {
		setArgument(key, value, 0);
	}
	
	public void setArgument(String key, Object value, int type) {
		if (key == null || value == null || arguments == null) return; 
		switch (type) {
			case 0:
			if (arguments.containsKey(key)) {
				arguments.replace(key, value);
			}
			else arguments.put(key, value);
			break;
			case 1:
				if (onStart != null) onStart.setArgument(key, value);
			break;
			case 2:
				if (onDeath != null) onDeath.setArgument(key, value);
			break;
			case 3:
				if (onEnd != null) onEnd.setArgument(key, value);
			break;
		}
	}

	public void setSpecialInstruction(Instruction inst, int type) {
		switch (type) {
			case 1:
				onStart = inst;
			break;
			case 2:
				onDeath = inst;
			break;
			case 3:
				onEnd = inst;
		}
	}
	
	public void resetArguments() {
		if (arguments != null) arguments.clear();
	}
	
	public void onStart(Hazard entity) {
		if (onStart != null) onStart.invoke(entity);
	}
	
	public void onEnd(Hazard entity) {
		if (onEnd != null) onEnd.invoke(entity);
	}
	
	public void onDeath(Hazard entity) {
		if (onDeath != null) onDeath.invoke(entity);
	}
	
	public void cast(double startTime, double currentTime, double delta) {
		cast(null, startTime, currentTime, delta);
	}
	
	public void cast(Hazard entity) {
		cast(entity, 0.0, 0.0, 0.0);
	}
	
	public abstract void cast(Hazard entity, double startTime, double currentTime, double delta);
}
