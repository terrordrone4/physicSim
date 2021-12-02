
package physicsim;

import java.util.ArrayList;
import physicsim.shape.Shape;


public class PhysicControl {
    private ArrayList<Shape> shapeList;
    private TranslationCalculator translationCalculator;
    
    public PhysicControl(){
        shapeList = new ArrayList<>();
        translationCalculator = new TranslationCalculator();
    }

    public double getCurrentTime() {
        return translationCalculator.getCurrentTime();
    }
    
    void run() {
        Thread t = new Thread(new Runnable(){
            @Override
            public void run() {
                while(true){
                    translationCalculator.updateTimeElapsed();
                    if(translationCalculator.isTimePassed()){
                        applyPhysicToShapes();
                    }
                }
            }

            
        });
        t.start();
    }
    
    private void applyPhysicToShapes() {
        Shape shape;
        for(int i = 0; i < shapeList.size(); i++){
            shape = shapeList.get(i);
            shape.increaseVelocity(0, translationCalculator.getDeltaVelocityY(shape));
            shape.move(translationCalculator.getElapsedTime());
            if(shape.y <= 0){
                shapeList.remove(shape);
                i--;
            }
        }
    }
    
    void add(ArrayList<Shape> shapeList) {
        this.shapeList.addAll(shapeList);
    }

    void add(Shape shape) {
        this.shapeList.add(shape);
    }



}
