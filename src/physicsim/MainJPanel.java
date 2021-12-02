package physicsim;

import physicsim.shape.Text2D;
import physicsim.shape.Arrow;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import physicsim.shape.Oval;
import physicsim.shape.Rectangle;
import physicsim.shape.Shape;

public class MainJPanel extends javax.swing.JPanel {

    private PhysicControl physicController;
    private DrawerThread drawerThread;
    private ArrayList<Shape> shapeList;
    private double zoomModifier = 1;
    private Graphics2D g2d;
    private Dimension windowSize = new Dimension(900, 600);
    private Color arrowColor = new Color(25, 25, 25, 190);
    private Color textColor = Color.BLACK;

    public MainJPanel() {
        initComponents();
    }

    private void initComponents() {
        this.setPreferredSize(windowSize);
        physicController = new PhysicControl();
        addShapes();
        physicController.run();
        drawerThread = new DrawerThread();
        drawerThread.start();
        this.addMouseWheelListener(new MouseWheelListener() {
            int notches;

            public void mouseWheelMoved(MouseWheelEvent e) {
                notches = e.getWheelRotation();
                zoomModifier = zoomModifier - (notches * 0.06);
                System.out.println(".mouseWheelMoved() zoomModifier = " + zoomModifier);
            }
        });
        this.addMouseListener(clickAndDragListener);

    }

    private void addShapes() {
        shapeList = new ArrayList<>();

        Shape shape = new Rectangle(35, 145, 85, 60);
        shape.setColor(Color.blue);
        physicController.add(shape);
        shapeList.add(shape);

        Shape arrow = new Arrow(125, 175);
        arrow.setColor(arrowColor);
        arrow.attachTo(shape);
        shapeList.add(arrow);

        Text2D text = new Text2D(125, 165, 13);
        text.setColor(textColor);
        text.attachTo(shape);
        text.setTextAsLocationOf(shape);
        shapeList.add(text);

        shape = new Oval(275, 320, 8);
        shape.setColor(Color.cyan);
        physicController.add(shape);
        shapeList.add(shape);

        arrow = new Arrow(285, 320);
        arrow.setColor(arrowColor);
        arrow.attachTo(shape);
        shapeList.add(arrow);

        text = new Text2D(275, 321, 13);
        text.setColor(textColor);
        text.attachTo(shape);
        text.setTextAsLocationOf(shape);
        shapeList.add(text);

        addStaticShapes();
    }

    private void addStaticShapes() {
        Shape shape = new Rectangle(0, 0, 25, 90);
        shape.setColor(Color.red);
        shapeList.add(shape);

        shape = new Rectangle(160, 0, 85, 30);
        shape.setColor(Color.GREEN);
        shapeList.add(shape);

        shape = new Rectangle(500, 0, 25, 150);
        shape.setColor(Color.ORANGE);
        shapeList.add(shape);

        Text2D timeLine = new Text2D(windowSize.getWidth() - 140, windowSize.getHeight() - 60, 11);
        timeLine.setColor(Color.BLACK);
        timeLine.setTextAsTimeElapsed(physicController);
        shapeList.add(timeLine);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        clear(g);

        g2d = (Graphics2D) g;
        g2d.scale(1 * zoomModifier, -1 * zoomModifier);
        g2d.translate(0, -1 * getHeight());
        for (Shape shape : shapeList) {
            shape.drawSelf(g2d, getWidth(), getHeight());
        }
        drawBorder(g2d);
        g2d.dispose();

    }

    public void clear(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, getWidth(), getHeight());
    }

    private void drawBorder(Graphics g) {
        g.setColor(Color.BLACK);
        g.drawRect(0, 0, this.getWidth(), getHeight());
    }

    private class DrawerThread extends Thread {

        private DrawerThread() {
        }

        @Override
        public void run() {
            while (true) {
                repaint();
            }
        }
    }

    private MouseListener clickAndDragListener = new MouseListener() {
        Point atPressed = new Point();
        Point atReleased  = new Point();
        
        final int minDistance = 25;
        
        @Override
        public void mouseClicked(MouseEvent e) {
            spawnRandomShapeAt(e.getX(), (int) windowSize.getHeight() - e.getY());
        }

        @Override
        public void mousePressed(MouseEvent e) {
            atPressed.x = e.getX();
            atPressed.y = e.getY();
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            atReleased.x = e.getX();
            atReleased.y = e.getY();
            if( atReleased.distance(atPressed) > minDistance){
                Shape s = spawnRandomShapeAt(e.getX(), (int) windowSize.getHeight() - e.getY());
                
                s.applyVelocity(atReleased.x - atPressed.x, 
                        -atReleased.y + atPressed.y);
            }
        }

        @Override
        public void mouseEntered(MouseEvent e) {
        }

        @Override
        public void mouseExited(MouseEvent e) {
        }

    };
    
    private Shape spawnRandomShapeAt(int x, int y) {
        int width = Utils.getRandomizeInt(10, 80);
        int height = Utils.getRandomizeInt(10, 50);
        
        Shape shape = Utils.randomizeNewShape(x, y,width ,height); 
        shape.setColor(Utils.getRandomColor());
        physicController.add(shape);
        shapeList.add(shape);

        
        Shape arrow = new Arrow(x + width, y  - 4);
        arrow.setColor(arrowColor);
        arrow.attachTo(shape);
        shapeList.add(arrow);

        Text2D text = new Text2D(x + width , y  -4 , 13);
        text.setColor(textColor);
        text.attachTo(shape);
        text.setTextAsLocationOf(shape);
        shapeList.add(text);
        return shape;
    }
}
