package game;

import java.awt.Point;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;

import engine.ImageBufferHandler;
import engine.MyPanel;
import engine.RenderSettings;
import engine.Renderer;
import engine.SpriteHolder;
import misc.ImageData;
import misc.Misc;
import misc.Transform;

public final class Sprite {
	
	private static Renderer renderer;
	private static final Point screenSize = new Point(MyPanel.PANEL_WIDTH, MyPanel.PANEL_HEIGHT);
	
	private ImageData sprite;
	private Point2D screenPosition;
	private Point2D screenScale;
	private Point2D position;
	private Point2D scale;
	
	private Point2D offsetPosition;
	private Point2D offsetScale;
	private double offsetRotation;
	private double offsetZPosition;
	
	private float alpha;
	private float alphaFade;
	private double zPosition;
	private double currentZPosition;
	private double rotation;
	private SpriteHolder body;
	
	private boolean destroyed = false;
	
	public boolean isDestroyed() {
		return destroyed;
	}

	public void setAlpha(float alpha) {
		this.alpha = alpha;
	}
	
	public Sprite(SpriteHolder body) {
		if (renderer == null) renderer = Renderer.getInstance();
		this.sprite = new ImageData();
		this.screenPosition = new Point2D.Double();
		this.screenScale = new Point2D.Double();
		this.position = new Point2D.Double();
		this.scale = new Point2D.Double();
		this.alpha = 1.0f;
		this.zPosition = 10.0;
		this.currentZPosition = 10.0;
		this.rotation = 0.0;
		this.body = body;
		this.offsetPosition = new Point2D.Double();
		this.offsetScale = new Point2D.Double();
		this.offsetRotation = 0;
		this.offsetZPosition = 0;
		this.destroyed = false;
		renderer.add(this);
	}
	
	public ImageData getRenderInfo() {
		//System.out.println(screenPosition);
		if (destroyed) return null;
		update();
		return sprite;
	}
	
	public void setOffset(Transform transform) {
		if (transform == null) {
			offsetZPosition = 0.0;
			offsetRotation = 0.0;
			offsetScale.setLocation(0, 0);
			offsetPosition.setLocation(0, 0);
			System.out.println("NULL SPRITE OFFSET TRANSFORM");
			return;
		}
		offsetZPosition = transform.zPosition;
		offsetRotation = transform.rotation;
		offsetScale.setLocation(transform.scale, transform.scale);
		offsetScale.setLocation(0, 0);
		if (transform.position != null) offsetPosition.setLocation(transform.position);
		offsetPosition.setLocation(0, 0);
	}
	
	public void set(int type, int color) {
		changeSprite(type);
		changeColor(color);
	}
	
	public void changeSprite(int type) {
		sprite.type = type;
	}
	
	public void changeColor(int color) {
		sprite.color = color;
	}
	
	public void update() {
		sprite.visible = true;
		updateBody();
		updateZ();
		updatePositionTransform();
		updateData();
		updateTransform();
		updateVisible();
	}
	
	private void updateBody() {
		if (body == null) return;
		Transform transform = body.getTransform();
		Point2D newScale = new Point2D.Double();
		if (transform.defaultScale != null) 
			newScale.setLocation(transform.defaultScale.getX() * transform.scale, transform.defaultScale.getY() * transform.scale);
		scale.setLocation(newScale.getX() + offsetScale.getX(), newScale.getY() + offsetScale.getY());
		
		if (transform.position != null) position.setLocation(transform.position.getX() + offsetPosition.getX(), transform.position.getY() + offsetPosition.getY());
		else position.setLocation(offsetPosition);
		rotation = transform.rotation + offsetRotation;
		zPosition = transform.zPosition + offsetZPosition;
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
		double zRate = (camPosZ - (40 - 40 * RenderSettings.PANEL_RATE));
		if ((camPosZ - zPosition) > 1) {
			zRate /= (camPosZ - zPosition);
		}
		else {
			zRate /= 1;
			sprite.visible = false;
		}
		
		
		alphaFade = (float) Misc.Transform.clamp((camPosZ - zPosition) - 1, 1.0, 0.0);
		
		
		
		screenScale.setLocation(scale.getX() * zRate, scale.getY() * zRate);
		screenPosition.setLocation(
			(position.getX() - camPosX) * zRate + screenSize.getX()/2.0,
			(position.getY() - camPosY) * zRate + screenSize.getY()/2.0
		);

	}
	
	private void updateData() {
		sprite.alpha = alpha * alphaFade;
		if (alpha <= 0f) sprite.visible = false;
		ImageBufferHandler.getImageData(sprite, sprite.type, screenScale.getX());
	}
	
	private void updateTransform() {
		if (!sprite.visible) return;
		double cos = Misc.Other.fcosDeg(rotation);
		double sin = Misc.Other.fsinDeg(rotation);

		double xScale = screenScale.getX() * sprite.invScaleRates.getX();
		double yScale = screenScale.getY() * sprite.invScaleRates.getY();
		
		double xOffset = sprite.offset.getX() * sprite.size.getX();
		double yOffset = sprite.offset.getY() * sprite.size.getY();
		
		sprite.at = new AffineTransform(
				xScale * cos,
				xScale * sin,
			   -yScale * sin,
			   	yScale * cos,
			   	xScale * cos * xOffset - yScale * sin * yOffset + screenPosition.getX(),
			   	xScale * sin * xOffset + yScale * cos * yOffset + screenPosition.getY() 	
		);
	}
	
	private void updateVisible() {
		if (!sprite.visible)return; 
		boolean visible = true;
		
		double xPos = screenPosition.getX();
		double yPos = screenPosition.getY();
		
		double x0 = -sprite.offset.getX() * screenScale.getX();
		double y0 = -sprite.offset.getY() * screenScale.getY();
		double x1 = (sprite.offset.getX() - 1) * screenScale.getX();
		double y1 = (sprite.offset.getY() - 1) * screenScale.getY();
		
		// Caso a posi��o na tela esteja menor que 0, ou maior q a dimens�o, n�o � vis�vel
		boolean cond = (xPos + x0 <= 0) || (yPos + y0 <= 0) || 
				(xPos + x1 > screenSize.getX()) || (yPos + y1 > screenSize.getY());
		
		if (cond) {
			visible = false;
		}
		
		
		sprite.visible = visible;
	}
		
	public double getCurrentZPosition() {
		return currentZPosition;
	}
	
	public void destroy() {
		destroyed = true;
		renderer.destroy(this);
	}
}
