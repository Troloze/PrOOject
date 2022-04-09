package game;

public class Collider {
	
	public static final int FLAG_PLAYER = 1;
	public static final int FLAG_ENEMY = 2;
	public static final int FLAG_ENEMY_BULLET = 4;
	public static final int FLAG_PLAYER_BULLET = 8;
	public static final int FLAG_MISC = 16;
	private double hitbox;
	private double damagebox;
	
	private int hazardFlags;
	private int targetFlags;
	
	private boolean isHazard;
	private boolean isTarget;
	
	public Collider() {
		this.setHitbox(0);
		this.setDamagebox(0);
		
		this.setHitFlags(0);
		this.setDamageFlags(0);
		
		this.isHazard= false;
		this.isTarget = false;
	}

	public Collider(double hit, double dam, int hitFl, int damFl, boolean isHit, boolean isDam) {
		this.hitbox = hit;
		this.damagebox = dam;
		this.hazardFlags = hitFl;
		this.targetFlags = damFl;
		this.isHazard = isHit;
		this.isTarget = isDam;
	}
	
	public void update(double hit, double dam, int hitFl, int damFl, boolean isHit, boolean isDam) {
		this.hitbox = hit;
		this.damagebox = dam;
		this.hazardFlags = hitFl;
		this.targetFlags = damFl;
		this.isHazard = isHit;
		this.isTarget = isDam;
	}
	
	public void update(Collider col) {
		hitbox = col.getHitbox();
		damagebox = col.getDamagebox();
		hazardFlags = col.getHitFlags();
		targetFlags = col.getDamageFlags();
		isHazard = col.isHazard();
		isTarget = col.isTarget();
	}
	
	
	public double getHitbox() {
		return hitbox;
	}

	public void setHitbox(double hitbox) {
		this.hitbox = hitbox;
	}

	public double getDamagebox() {
		return damagebox;
	}

	public void setDamagebox(double damagebox) {
		this.damagebox = damagebox;
	}

	public int getHitFlags() {
		return hazardFlags;
	}

	public void setHitFlags(int hazardFlags) {
		this.hazardFlags = hazardFlags;
	}

	public int getDamageFlags() {
		return targetFlags;
	}

	public void setDamageFlags(int targetFlags) {
		this.targetFlags = targetFlags;
	}

	public boolean isHazard() {
		return isHazard;
	}

	public void toggleHazard(boolean isHazard) {
		this.isHazard = isHazard;
	}

	public boolean isTarget() {
		return isTarget;
	}

	public void toggleTarget(boolean isTarget) {
		this.isTarget = isTarget;
	}
}
