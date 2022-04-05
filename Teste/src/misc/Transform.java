package misc;

import java.awt.geom.Point2D;


public class Transform {
	public Point2D position;
	public Point2D scale;
	public double rotation;
	public double zPosition;
	
	public Transform(Point2D position, Point2D scale, double rotation, double zPosition) {
		this.position = new Point2D.Double();
		this.scale = new Point2D.Double();
		if (position != null) this.position.setLocation(position);
		if (scale != null) this.scale.setLocation(scale);
		this.rotation = rotation;
		this.zPosition = zPosition;
	}
	
	public Transform(Transform transform) {
		if (transform == null) {
			this.position = new Point2D.Double();
			this.scale = new Point2D.Double();
			this.rotation = 0.0;
			this.zPosition = 0.0;
			return;
		}
		this.position = new Point2D.Double();
		this.scale = new Point2D.Double();
		if (transform.position != null) this.position.setLocation(transform.position);
		if (transform.scale != null) this.scale.setLocation(transform.scale);
		this.rotation = transform.rotation;
		this.zPosition = transform.zPosition;
	}
	
	public Transform() {
		this.position = new Point2D.Double();
		this.scale = new Point2D.Double();
		this.rotation = 0.0;
		this.zPosition = 0.0;
	}
}
