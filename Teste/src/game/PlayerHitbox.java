package game;

import engine.ImageBufferHandler;
import misc.Transform;

public class PlayerHitbox extends Entity {
	
	private Entity follow;
	private Sprite spr;
	
	public static PlayerHitbox newInstance(Entity follow) {
		PlayerHitbox ret = new PlayerHitbox();
		ret.setFollow(follow);
		return ret;
	}
	
	private PlayerHitbox() {
		transform = new Transform();
		transform.getDefaultScale().setLocation(20, 20);
		spr = new Sprite(this);
		spr.set(ImageBufferHandler.SHINE, ImageBufferHandler.B_ORANGE);
	}
	
	private void setFollow(Entity follow) {
		this.follow = follow;
	}
	
	@Override
	public Transform getTransform() {
		return transform;
	}

	@Override
	public void update(double delta) {}
	
	public void move() {
		transform.follow(follow.getTransform(), Transform.FOLLOW_NOT_DEFAULT_SCALE);
		transform.setZPosition(transform.getZPosition() + 0.01);
	}

	public void show() {
		spr.setAlpha(1.0f);
	}
	
	public void hide() {
		spr.setAlpha(0.0f);
	}
	
	@Override
	public void destroy() {
		if (destroyed) return;
		if (spr != null) spr.destroy();
		spr = null;
		follow = null;
		destroyed = true;
	}
	
}
