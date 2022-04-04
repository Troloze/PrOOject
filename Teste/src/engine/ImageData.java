package engine;

import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;

public class ImageData {
	public int type;
	public int quality;
	public int color;
	public float alpha;
	public Point2D offset;
	public Point2D size;
	public Point2D invScaleRates;	
	public AffineTransform at;
	public boolean visible;
	
	public ImageData() {
		reset();
	}
	
	public void reset() {
		this.type = 0;
		this.quality = 0;
		this.color = 0;
		this.alpha = 0.0f;
		this.visible = false;
		this.invScaleRates = new Point2D.Double();
		if (at == null) this.at = new AffineTransform();
		at.setToIdentity();
	}
	
	@Override
	public String toString() {
		return ("ImageData[type: " + type + ", color: " + color + ", quality: " + quality + ", alpha: " + alpha + ", visible: " + visible + "]");
	}
	
}
