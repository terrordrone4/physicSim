package physicsim.shape;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public abstract class Shape {
    public static final Color DEFAULT_COLOR = Color.BLACK;
    
    public Color color = DEFAULT_COLOR;
    public double x,y;
    public double drawOffset_x,drawOffset_y;
    public double curr_velocity_x, curr_velocity_y;
    private ArrayList<Shape> childList;
    
    public Shape(double x, double y){
        this.x = x;
        this.y = y;
    }
    
    public double getY(){
        return y;
    }
    
    public void setColor(Color c){
        this.color = c;
    }
    
    public void applyVelocity(int v_x, int v_y) {
        increaseVelocity(v_x, v_y);
    }
    
    public void increaseVelocity(double delta_velocity_x, double delta_velocity_y){
        curr_velocity_x += delta_velocity_x;
        curr_velocity_y += delta_velocity_y;
    }
    
    public void move(double second) {
        x += curr_velocity_x * second;
        y += curr_velocity_y * second;
        
        for(Shape child : childList){
            child.x += curr_velocity_x * second;
            child.y += curr_velocity_y * second;
        }
    }
    
    int toPixelLocation(double x){
        return (int) (x);
    }
    
    public abstract void drawSelf(java.awt.Graphics2D g, int screenWidth, int screenHeight);
    
    @Override
    public String toString(){
        return "Rectangle(" + x + ", " + y + ")";
    }

    public void attachTo(Shape shape) {
        shape.addChild(this);
    }

    private void addChild(Shape child) {
        if(childList == null){
            childList = new ArrayList<>();
        }
        childList.add(child);
    }





}
