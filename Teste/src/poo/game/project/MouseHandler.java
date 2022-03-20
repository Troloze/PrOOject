package poo.game.project;

import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.SwingUtilities;

public final class MouseHandler implements MouseListener{

	private static MouseHandler instance;
	private Point mousePosition;
	
	private int mouseStatus = 0;
	
	private MouseHandler() {
		
	}
	
	public static MouseHandler getInstance() {
		if (instance == null) {
			instance = new MouseHandler();
		}
		return instance;
	}
	
	public void updateMousePosition() {
		mousePosition = MouseInfo.getPointerInfo().getLocation();
	}
	
	public Point getMousePosition() {
		return mousePosition;
	}
	
	public int getMouseButton(int key) {
		return mouseStatus & key;
	}
		
	@Override
	public void mouseClicked(MouseEvent e) {}

	@Override
	public void mousePressed(MouseEvent e) {
		if (SwingUtilities.isLeftMouseButton(e)) 
			mouseStatus = mouseStatus | 0b100;
		
		if (SwingUtilities.isRightMouseButton(e)) 
			mouseStatus = mouseStatus | 0b010;
		
		if (SwingUtilities.isMiddleMouseButton(e)) 
			mouseStatus = mouseStatus | 0b001;
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (SwingUtilities.isLeftMouseButton(e)) 
			mouseStatus = mouseStatus & 0b011;
		
		if (SwingUtilities.isRightMouseButton(e)) 
			mouseStatus = mouseStatus & 0b101;
		
		if (SwingUtilities.isMiddleMouseButton(e)) 
			mouseStatus = mouseStatus & 0b110;
	}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}

}
