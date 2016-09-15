/*
 * @class Type:  Util Class
 * @description: This class is a collections of methods relating to direction 
 *               properties.
 */

package numbersliderpuzzlegame.util;

/**
 * project: A desktop based number sliding puzzle game application
 * email:   chenc5@andrew.cmu.edu
 * @author  Chen Chen
 * Created in 2016, at the University of Nottingham, UK  
 */

public class Direction {
    
    /**
     * Default Constructor
     * <p> Empty method
     */
    public Direction(){}
    
    /**
     * Getter
     * <p> Get random direction
     * 
     * @return random direction
     */
    public static int getInitialRandomDirection(){
        return (int) (Math.random()*4);
    } // end method
    
    /**
     * Get next random direction
     * 
     * @param _random_index current direction
     * @return next direction
     */
    public static int getNextRandomDirection(int _random_index){
        _random_index = _random_index + 1;
        if(_random_index == TOTAL_DIRECTION) return 0; // roll over
        else return _random_index;
    } // end method
    
    public static final int TOP = 0;
    public static final int BOTTOM = 1;
    public static final int LEFT = 2;
    public static final int RIGHT = 3;
    private static final int TOTAL_DIRECTION = 4;
    
} // end class
