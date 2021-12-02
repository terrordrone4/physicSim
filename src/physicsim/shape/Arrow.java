
package physicsim.shape;

import java.awt.Graphics;
import java.awt.Polygon;
import physicsim.shape.Shape;



public class Arrow extends Shape {

    private Polygon p;
    private static final int[] X_POINTS = new int[7];
    private static final int[] Y_POINTS = new int[7];
    private static final int N_POINT = 7;
    private int prev_x;
    private int prev_y;
    
    public Arrow(int x, int y){
        super(x, y);
        constructPolygon();
        prev_x = x ;
        prev_y = y;
    }

    private void constructPolygon() {
        X_POINTS[0] = (int) x;
        X_POINTS[1] = (int) x + 10;
        X_POINTS[2] = (int) x + 10;
        X_POINTS[3] = (int) x + 10 + 45;
        X_POINTS[4] = (int) x + 10 + 45;
        X_POINTS[5] = (int) x + 10;
        X_POINTS[6] = (int) x + 10;
        Y_POINTS[0] = (int) y;
        Y_POINTS[1] = (int) y - 5;
        Y_POINTS[2] = (int) y - 3;
        Y_POINTS[3] = (int) y - 3;
        Y_POINTS[4] = (int) y + 3;
        Y_POINTS[5] = (int) y + 3;
        Y_POINTS[6] = (int) y + 5;
        
         p = new Polygon(X_POINTS, Y_POINTS, N_POINT);
    }
    
    @Override
    public void drawSelf(java.awt.Graphics2D g, int screenWidth, int screenHeight) {
        p.translate((int)x - prev_x, (int) y - prev_y);
        prev_x = (int) x;
        prev_y = (int) y;
        g.setColor(color);
        g.fillPolygon(p);
    }


}
