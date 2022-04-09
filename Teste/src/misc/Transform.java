package misc;

import java.awt.geom.Point2D;

public class Transform {
	
	public static final int FOLLOW_POSITION = 1;
	public static final int FOLLOW_SCALE = 2;
	public static final int FOLLOW_DEFAULT_SCALE = 4;
	public static final int FOLLOW_ROTATION = 8;
	public static final int FOLLOW_ZPOS = 16;
	public static final int FOLLOW_NOT_DEFAULT_SCALE = 27;
	
	private Point2D position;
	private double scale;
	private Point2D defaultScale;
	private double rotation;
	private double zPosition;
	
	public Point2D getPosition() {
		return position;
	}

	public double getScale() {
		return scale;
	}

	public void setScale(double scale) {
		this.scale = scale;
	}

	public Point2D getDefaultScale() {
		return defaultScale;
	}
	


	public double getRotation() {
		return rotation;
	}

	public void setRotation(double rotation) {
		while (rotation < 360) {
			rotation += 360;
		}
		while (rotation >= 720) {
			rotation -= 360;
		}
		this.rotation = rotation;
	}

	public double getZPosition() {
		return zPosition;
	}

	public void setZPosition(double zPosition) {
		this.zPosition = zPosition;
	}

	public Transform(Point2D position, Point2D defaultScale, double scale, double rotation, double zPosition) {
		this.position = new Point2D.Double();
		this.defaultScale = new Point2D.Double();
		this.scale = scale;
		if (position != null) this.position.setLocation(position);
		if (defaultScale != null) this.defaultScale.setLocation(defaultScale);
		while (rotation < 360) {
			rotation += 360;
		}
		while (rotation >= 720) {
			rotation -= 360;
		}
		this.rotation = rotation;
		this.zPosition = zPosition;
	}
	
	public Transform(Transform transform) {
		if (transform == null) {
			this.position = new Point2D.Double();
			this.defaultScale = new Point2D.Double(1, 1);
			this.scale = 1.0;
			this.rotation = 360.0;
			this.zPosition = 0.0;
			return;
		}
		this.position = new Point2D.Double();
		this.defaultScale = new Point2D.Double();
		this.scale = transform.scale;
		if (transform.position != null) this.position.setLocation(transform.position);
		if (defaultScale != null) this.defaultScale.setLocation(transform.defaultScale);
		this.rotation = transform.rotation;
		this.zPosition = transform.zPosition;
	}
		
	public Transform() {
		this.position = new Point2D.Double();
		this.defaultScale = new Point2D.Double(1, 1);
		this.scale = 1.0;
		this.rotation = 360.0;
		this.zPosition = 0.0;
	}
	
	public void follow(Transform transform, int flags) {
		if (transform == null) return;
		if ((flags & FOLLOW_POSITION) != 0)
			if (transform.position != null) this.position.setLocation(transform.position);
		if ((flags & FOLLOW_SCALE) != 0)
			this.scale = transform.scale;
		if ((flags & FOLLOW_ROTATION) != 0)
			this.rotation = transform.rotation;
		if ((flags & FOLLOW_ZPOS) != 0) 
			this.zPosition = transform.zPosition;
	}
	
	@Override
	public String toString() {
		return "Transform: [position: " + position + ", scale: " + scale + ", defaultScale: " + defaultScale + ", rotation: " + rotation + ", zPosition: " + zPosition + "]";
	}
	
}
