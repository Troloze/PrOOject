package misc;

import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;


public class Transform {
	
	public static final int FOLLOW_POSITION = 1;
	public static final int FOLLOW_SCALE = 2;
	public static final int FOLLOW_DEFAULT_SCALE = 4;
	public static final int FOLLOW_ROTATION = 8;
	public static final int FOLLOW_ZPOS = 16;
	public static final int FOLLOW_NOT_DEFAULT_SCALE = 27;
	
	public Point2D position;
	public double scale;
	public Point2D defaultScale;
	public double rotation;
	public double zPosition;
	
	public Transform(Point2D position, Point2D defaultScale, double scale, double rotation, double zPosition) {
		this.position = new Point2D.Double();
		this.defaultScale = new Point2D.Double();
		this.scale = scale;
		if (position != null) this.position.setLocation(position);
		if (defaultScale != null) this.defaultScale.setLocation(defaultScale);
		this.rotation = rotation;
		this.zPosition = zPosition;
	}
	
	public Transform(Transform transform) {
		if (transform == null) {
			this.position = new Point2D.Double();
			this.defaultScale = new Point2D.Double();
			this.scale = 1.0;
			this.rotation = 0.0;
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
		this.scale = 1.0;
		this.rotation = 0.0;
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
	}
