
package physicsim.shape;

import java.awt.Graphics;



public class Oval extends Shape {

    private final double radius;
    private int ovalSpan;
    private int topLeftX;
    private int topLeftY;
    
    public Oval(double x, double y, double radius){
        super(x, y);
        this.radius = radius;
    }

    @Override
    public void drawSelf(java.awt.Graphics2D g, int screenWidth, int screenHeight) {
        g.setColor(color);
        ovalSpan = (int) (radius * 2 );
        topLeftX = (int)  ((x - radius ) );
        topLeftY = (int)  ((y - radius ) );
        g.fillOval( topLeftX , topLeftY, ovalSpan, ovalSpan);
    }

    public int getHeight() {
        return (int) (radius * 2);
    }
}
