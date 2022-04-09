package game.instructions;

import java.util.HashMap;
import java.util.Map;

import game.Hazard;

public abstract class Instruction {
		
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
	
	public void invoke() {
		invoke(null, 0.0, 0.0, 0.0);
	}
	
	public void invoke (Hazard entity) {
		invoke(entity, 0.0, 0.0, 0.0);
	}
	
	public void invoke(Hazard entity, double delta) {
		invoke(entity, 0.0, 0.0, delta);
	}

	public void invoke(double startTime, double currentTime, double delta) {
		invoke(null, startTime, currentTime, delta);
	}

	public abstract void invoke(Hazard entity, double startTime, double currentTime, double delta);
}
