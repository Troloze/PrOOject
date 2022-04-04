package game;

import java.awt.Point;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;

import engine.ImageBufferHandler;
import engine.ImageData;
import engine.MultiSpriteHolder;
import engine.MyPanel;
import engine.Renderer;
import engine.SpriteHolder;
import misc.Misc;

public final class Sprite {
	
	private static Renderer renderer;
	private static final Point screenSize = new Point(MyPanel.PANEL_WIDTH, MyPanel.PANEL_HEIGHT);
	
	private ImageData sprite;
	private Point2D screenPosition;
	private Point2D screenScale;
	private Point2D position;
	private Point2D scale;
	private float alpha;
	private double zPosition;
	private double currentZPosition;
	private double rotation;
	private SpriteHolder body;

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
		renderer.add(this);
	}
	
	public ImageData getRenderInfo() {
		//System.out.println(screenPosition);
		update();
		return sprite;
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
		updateBody();
		updateZ();
		updatePositionTransform();
		updateData();
		updateTransform();
		updateVisible();
	}
	
		
	private void updateBody() {
		if (body == null) return;
		if (body instanceof MultiSpriteHolder) {
			MultiSpriteHolder mSH = (MultiSpriteHolder) body;
			double xPos, yPos;
			xPos = mSH.positionOffset(this).getX() * body.getScale().getX() * Misc.MyMath.fcosDeg(body.getRotation());
			yPos = mSH.positionOffset(this).getY() * body.getScale().getY() * Misc.MyMath.fsinDeg(body.getRotation());
			position = new Point2D.Double(body.getPosition().getX() + xPos, body.getPosition().getY() + yPos);
			rotation = body.getRotation() + mSH.rotationOffset(this);
			scale = new Point2D.Double(body.getScale().getX() + mSH.scaleOffset(this).getX(), body.getScale().getY() + mSH.scaleOffset(this).getY());
		} 
		else {
			scale = body.getScale();
			position = body.getPosition();
			rotation = body.getRotation();
		}
		zPosition = body.getZPos();
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
			(position.getX() - camPosX) * zRate + screenSize.getX()/2.0,
			(position.getY() - camPosY) * zRate + screenSize.getY()/2.0
		);
	}
	
	private void updateData() {
		sprite.alpha = alpha;
		if (alpha <= 0f) sprite.visible = false;
		ImageBufferHandler.getImageData(sprite, sprite.type, screenScale.getX());
	}
	
	private void updateTransform() {
		double cos = Misc.MyMath.fcosDeg(rotation);
		double sin = Misc.MyMath.fsinDeg(rotation);

		double xScale = this.screenScale.getX() * this.sprite.invScaleRates.getX();
		double yScale = this.screenScale.getY() * this.sprite.invScaleRates.getY();
		
		double xOffset = this.sprite.offset.getX() * this.sprite.size.getX();
		double yOffset = this.sprite.offset.getY() * this.sprite.size.getY();
		
		this.sprite.at = new AffineTransform(
				xScale * cos,
				xScale * sin,
			   -yScale * sin,
			   	yScale * cos,
			   	xScale * cos * xOffset - yScale * sin * yOffset + this.screenPosition.getX(),
			   	xScale * sin * xOffset + yScale * cos * yOffset + this.screenPosition.getY() 	
		);
	}
	
	private void updateVisible() {
		boolean visible = true;
		
		double xPos = screenPosition.getX();
		double yPos = screenPosition.getY();
		
		double x0 = -sprite.offset.getX() * screenScale.getX();
		double y0 = -sprite.offset.getY() * screenScale.getY();
		double x1 = (sprite.offset.getX() - 1) * screenScale.getX();
		double y1 = (sprite.offset.getY() - 1) * screenScale.getY();
		
		// Caso a posição na tela esteja menor que 0, ou maior q a dimensão, não é visível
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
}
