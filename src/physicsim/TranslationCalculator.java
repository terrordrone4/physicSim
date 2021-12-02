
package physicsim;

import java.text.DecimalFormat;
import java.time.Duration;
import java.time.Instant;
import physicsim.shape.Shape;



class TranslationCalculator {
    
    public static final double G = -9.87 * Math.pow(10, -9);
    
    private long timeElapsed, currentTime;
    
    private Instant curr, prev;
    
    public TranslationCalculator(){
        prev = Instant.now();
    }
    
    
    protected void updateTimeElapsed(){
        curr = Instant.now();
        timeElapsed = Duration.between(prev, curr).toNanos();
        
        if(isTimePassed()){
            currentTime += timeElapsed;
            prev = curr;
        }
    }

    protected boolean isTimePassed() {
        return timeElapsed > 0;
    }

    protected double getDeltaVelocityY(Shape shape) {
        return G * timeElapsed;
    }

    double getElapsedTime() {
        return timeElapsed * Math.pow(10, -9);
    }

    double getCurrentTime() {
        return currentTime * Math.pow(10, -9);
    }
}
