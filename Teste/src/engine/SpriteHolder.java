package engine;

import java.awt.geom.Point2D;

public interface SpriteHolder {
	
	//public abstract getTransform();
	
	public abstract Point2D getScale();
	public abstract Point2D getPosition();
	public abstract double getRotation();
	public abstract double getZPos();
}
