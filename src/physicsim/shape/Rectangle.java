package physicsim.shape;

import java.awt.Color;
import java.awt.Graphics;

public class Rectangle extends Shape {

    private final int width;
    private final int height;
    
    public Rectangle(double x, double y, int width, int height) {
        super(x, y);
        this.width = width;
        this.height = height;
    }

    @Override
    public void drawSelf(java.awt.Graphics2D g, int screenWidth, int screenHeight) {
        g.setColor(color);
        g.fillRect((int) x,
                (int) y  ,
                (int) width, 
                (int) height);
    }
    
    private double zoomed(double location, double zoomModifier){
        return location * zoomModifier;
    }

    
}
