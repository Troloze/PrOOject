package game;

import java.awt.Image;
import java.awt.Point;
import java.awt.geom.Point2D;

//import engine.Renderer;

public final class Sprite {
    private Image sprite = null;
    private Point screenPosition = null;
    private Point offset = null;
    private Point2D scale = null;
    private double zPosition = 10.0;
    private double currentZPosition = 10.0;
    private double rotation = 0.0;
    private Entity body = null;

    public void update() {
        updateZ();
    }
    
    private void updateZ() {
        if (currentZPosition != zPosition) {
            currentZPosition = zPosition;
            //Renderer.updateBuffer(this);
        }
    }
    
    public double getCurrentZPosition() {
        return currentZPosition;
    }
    
}
