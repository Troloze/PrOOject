package game;

public abstract class Pattern {

	@SuppressWarnings("unused")
	private double setTime[];
	@SuppressWarnings("unused")
	private Instruction set[];
	private Instruction onStart, onEnd, onDeath;
	
	public void onStart(Hazard entity) {
		onStart.invoke(entity);
	}
	
	public void onEnd(Hazard entity) {
		onEnd.invoke(entity);
	}
	
	public void onDeath(Hazard entity) {
		onDeath.invoke(entity);
	}
	
	public void cast(double startTime, double currentTime) {
		cast(null, startTime, currentTime);
	}
	
	public void cast(Hazard entity) {
		cast(entity, 0.0, 0.0);
	}
	
	public abstract void cast(Hazard entity, double startTime, double currentTime);
}
