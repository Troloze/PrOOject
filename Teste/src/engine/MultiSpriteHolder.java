package engine;

import java.awt.geom.Point2D;

import game.Sprite;

public interface MultiSpriteHolder {
	
	public abstract Point2D positionOffset(Sprite spr);
	public abstract Point2D scaleOffset(Sprite spr);
	public abstract double rotationOffset(Sprite spr);
}
