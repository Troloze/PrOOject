package game.patterns;

import java.util.HashMap;
import java.util.Map;

import game.Hazard;
import game.instructions.Instruction;

public abstract class Pattern {


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
		if (key == null || value == null || arguments == null) return; 
		if (arguments.containsKey(key)) {
			arguments.replace(key, value);
		}
		else arguments.put(key, value);
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
