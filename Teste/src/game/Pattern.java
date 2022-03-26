package game;

public abstract class Pattern {

	public Instruction onStart;
	public Instruction onEnd;
	public Instruction onDeath;
	
	public void onStart() {
		if (onStart != null) onStart.cast();
	}
	
	public void onEnd() {
		if (onEnd != null) onEnd.cast();
	}
	
	public void onDeath() {
		if (onDeath != null) onDeath.cast();
	}
	
}
