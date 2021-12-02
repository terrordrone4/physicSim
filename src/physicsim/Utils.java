
package physicsim;

import java.awt.Color;
import java.util.Random;
import physicsim.shape.Oval;
import physicsim.shape.Rectangle;
import physicsim.shape.Shape;



class Utils {

    static Random r = new Random();
    
    static Shape randomizeNewShape(int x, int y, int size1, int size2) {
        int choice = r.nextInt(2);
        Shape s = null;
        switch(choice){
            case 0:
                s = new Rectangle(x, y, size1, size2);
                break;
            case 1:
                if(Math.max(size1, size2) > 35){
                    size1 = 35;
                }
                s = new Oval(x, y, size1 );
                break;
            default:
                System.err.println("Unknown random choice: " + choice);
        }
        return s;
    }
    
    static int getRandomizeInt(int min, int max) {
        return r.nextInt(max + 1 - min) + min;
    }
    static int getRandomizeInt(int max) {
        return getRandomizeInt(0, max);
    }

    static Color getRandomColor() {
        return new Color(getRandomizeInt(255),
                getRandomizeInt(255),
                getRandomizeInt(255),
                getRandomizeInt(100, 255));
    }

    private Utils(){
        
    }
}
