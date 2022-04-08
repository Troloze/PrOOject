package game;

public abstract class Pattern {


	private Instruction onStart, onEnd, onDeath;
	
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
