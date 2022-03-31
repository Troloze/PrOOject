package game;

import java.awt.Image;
import java.awt.Point;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;

import engine.MyPanel;
import engine.Renderer;
import misc.Misc;

public final class Sprite {
	
	private static final Point screenSize = new Point(MyPanel.PANEL_WIDTH, MyPanel.PANEL_HEIGHT);
	
	private Image sprite = null;
	private Point2D screenPosition = new Point2D.Double();
	private Point2D screenScale = new Point2D.Double();
	private Point2D offset = new Point2D.Double();
	private Point2D position = new Point2D.Double();
	private Point2D scale = new Point2D.Double();
	private double zPosition = 10.0;
	private double currentZPosition = 10.0;
	private double rotation = 0.0;
	private Entity body = null;
	private AffineTransform transform = new AffineTransform();
	

	public AffineTransform getRenderInfo() {
		return transform;
	}
	
	private void updateTransform() {
		double cos = Misc.MyMath.fcosDeg(rotation);
		double sin = Misc.MyMath.fsinDeg(rotation);
		transform = new AffineTransform(
				screenScale.getX() * cos,
				screenScale.getX() * sin,
			   -screenScale.getY() * sin,
			   	screenScale.getY() * cos,
			   	screenScale.getX() * cos * offset.getX() - screenScale.getY() * sin * offset.getY() + screenPosition.getX(),
			   	screenScale.getX() * sin * offset.getX() + screenScale.getY() * cos * offset.getY() + screenPosition.getY() 	
		);
	}
	
	public void update() {
		updateZ();
		updatePositionTransform();
		updateTransform();
	}
	
	private void updateZ() {
		if (currentZPosition != zPosition) {
			currentZPosition = zPosition;
			Renderer.updateBuffer(this);
		}
	}
	
	private void updatePositionTransform() {
		Camera cam = Camera.getInstance();
		double camPosX = cam.getX();
		double camPosY = cam.getY();
		double camPosZ = cam.getZ();
		double zRate = camPosZ/zPosition;
		
		screenScale.setLocation(scale.getX() * zRate, scale.getY() * zRate);
		screenPosition.setLocation(
			(position.getX() - camPosX) * zRate,
			(position.getY() - camPosY) * zRate
		);
		
		
		
	}
		
	public double getCurrentZPosition() {
		return currentZPosition;
	}
	
}
