
package physicsim.shape;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.geom.AffineTransform;
import java.text.DecimalFormat;
import physicsim.PhysicControl;
import physicsim.shape.Shape;



public class Text2D extends Shape {

    private String str;
    private Shape shapeToAttach;
    private Font font;
    private AffineTransform oldGraphicTransform;
    private PhysicControl physicControl;
    private static final DecimalFormat df = new DecimalFormat("#");;
    
    public Text2D(int x, int y, int font_size){
        super(x, y);
        this.font = new Font(Font.SANS_SERIF, Font.ITALIC, font_size);
        df.setMaximumFractionDigits(2);
    }
    
    public Text2D(double x, double y, int font_size){
        super((int) x, (int)y);
        this.font = new Font(Font.SANS_SERIF, Font.ITALIC, font_size);
        df.setMaximumFractionDigits(2);
    }

    @Override
    public void drawSelf(java.awt.Graphics2D g, int screenWidth, int screenHeight) {
        updateText();
        g.setColor(color);
        g.setFont(font);
        
        oldGraphicTransform = g.getTransform();
        
        g.scale(1, -1);
        g.drawString(str,(int) x + 30, -(int)y - 22);
        
        g.setTransform(oldGraphicTransform);
    }
    
    public void setTextAsLocationOf(Shape shape) {
        this.shapeToAttach = shape;
    }

    public void setTextAsTimeElapsed(PhysicControl physicControl) {
        this.physicControl = physicControl;
    }

    private void updateText() {
        if(shapeToAttach != null){ 
            str = "y = " + (int)shapeToAttach.y;
            return;
        }
        if(physicControl != null){
            str = "currentTime: " + df.format(physicControl.getCurrentTime());
            return;
        }
    }
}
