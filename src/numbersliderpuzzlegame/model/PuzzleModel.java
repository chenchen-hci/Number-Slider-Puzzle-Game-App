/*
 * @class Type:  Model Class
 * @description: This class is the combinations of methods for logic calculations
 *               used in this game.
 */

package numbersliderpuzzlegame.model;

import java.awt.Point;
import numbersliderpuzzlegame.util.Direction;
import numbersliderpuzzlegame.view.GamePanel;
import numbersliderpuzzlegame.view.NumberSliderPuzzleGameMainFrame;


/**
 * project: A desktop based number sliding puzzle game application
 * email:   chenc5@andrew.cmu.edu
 * @author  Chen Chen 
 * Created in 2016, at the University of Nottingham, UK  
 */

public class PuzzleModel {

    /**
     * Constructor
     * <p> Initialisation of puzzle model
     */
    public PuzzleModel(){
        maxMoveCount = 0; 
        GamePanel.set_block_pixel_size(GamePanel.GAME_PANEL_PIXEL_SIZE/PUZZLE_SIZE);
        blocks = new Block[PUZZLE_SIZE][PUZZLE_SIZE];   // index starts from 0
        reset();
    } // end method
    
    /**
     * Getter
     * <p> Get specific block number in string type
     * @param r block row index number
     * @param c block column index number
     * @return specific block number in string type
     */
    public String getBlockNumber(int r, int c) {
        return blocks[r][c].toString();
    } // end method
        
    /**
     * Reset puzzle model
     */
    public void reset() {
        generateBlocks();
        // the empty space is set to final cell position
        empty_space = blocks[PUZZLE_SIZE - 1][PUZZLE_SIZE - 1];
        empty_space.setBlockNumber(-1); //null       
        shuffleBlocks();
    } // end method
    
    /**
     * Generate solved puzzle
     */
    public void generateBlocks(){
        for (int r=0; r<PUZZLE_SIZE; r++) {
            for (int c=0; c<PUZZLE_SIZE; c++) {
                blocks[r][c] = new Block(r, c, r*PUZZLE_SIZE+c); // index start from 0
            } // end for
        } // end for
    } // end method
    
    /**
     * Shuffle puzzle
     */
    public void shuffleBlocks() {
        // for performance analysis only
        long startTime = System.currentTimeMillis();                // for performance evaluation only
        
        int random_direction;
        
        // shuffle the puzzle by tracking the empty space
        // reshuffle if puzzle is not shuffled successfully
        // the shuffling method may need be improved in future work
        while(true) {
            for (int i = 0; i < SHUFFLE_NUMBER; ++i) {
                random_direction = Direction.getInitialRandomDirection();
                while(!randomExchangeEmptyBlock(random_direction)) 
                    random_direction = Direction.getNextRandomDirection(random_direction);

            } // end for
            if(!isCompleteCurrentRound()) break; // jump out if successfully shuffled
        } // end while
        
        System.out.println(System.currentTimeMillis() - startTime); // for performance evaluation only
    }
    
    /**
     * Exchange empty blocks
     * 
     * @param _random_direction current random direction
     * @return whether block is successfully exchanged with empty space
     */
    private boolean randomExchangeEmptyBlock(int _random_direction){
        // change block empty block with one of adjacent blocks 
        switch(_random_direction){
            case(Direction.TOP):
                if(!isValidateBlock(this.getTopBlock(getEmptyBlockRowCol()).y, this.getTopBlock(getEmptyBlockRowCol()).x)) return false;
                exchangeBlocks(this.getEmptyBlockRowCol().y, 
                               this.getEmptyBlockRowCol().x, 
                               this.getTopBlock(getEmptyBlockRowCol()).y, 
                               this.getTopBlock(getEmptyBlockRowCol()).x);
                break;
            case(Direction.BOTTOM):
                if(!isValidateBlock(this.getBottomBlock(getEmptyBlockRowCol()).y, this.getBottomBlock(getEmptyBlockRowCol()).x)) return false;
                exchangeBlocks(this.getEmptyBlockRowCol().y, 
                               this.getEmptyBlockRowCol().x, 
                               this.getBottomBlock(getEmptyBlockRowCol()).y, 
                               this.getBottomBlock(getEmptyBlockRowCol()).x);
                break;
            case(Direction.LEFT):
                if(!isValidateBlock(this.getBottomBlock(getEmptyBlockRowCol()).y, this.getLeftBlock(getEmptyBlockRowCol()).x)) return false;
                exchangeBlocks(this.getEmptyBlockRowCol().y, 
                               this.getEmptyBlockRowCol().x, 
                               this.getLeftBlock(getEmptyBlockRowCol()).y, 
                               this.getLeftBlock(getEmptyBlockRowCol()).x);
                break;
            case(Direction.RIGHT):
                if(!isValidateBlock(this.getBottomBlock(getEmptyBlockRowCol()).y, this.getRightBlock(getEmptyBlockRowCol()).x)) return false;
                exchangeBlocks(this.getEmptyBlockRowCol().y, 
                               this.getEmptyBlockRowCol().x, 
                               this.getRightBlock(getEmptyBlockRowCol()).y, 
                               this.getRightBlock(getEmptyBlockRowCol()).x);
                break;
        } // end switch
        return true;
    } // end method
    
    /**
     * Getter
     * <p> Get top block of empty space
     * 
     * @param block block row and column position
     * @return acquired block position
     */
    private Point getTopBlock(Point block){ 
        return new Point(block.x,block.y-1);
    } // end method
    
    /**
     * Getter
     * <p> Get bottom block of empty space
     * 
     * @param block block row and column position
     * @return acquired block position
     */
    private Point getBottomBlock(Point block){ 
        return new Point(block.x,block.y+1);
    } // end method
    
    /**
     * Getter
     * <p> Get left block of empty space
     * 
     * @param block block row and column position
     * @return acquired block position
     */
    private Point getLeftBlock(Point block){ 
        return new Point(block.x-1,block.y);
    } // end method
    
    /**
     * Getter
     * <p> Get right block of empty space
     * 
     * @param block block row and column position
     * @return acquired block position
     */
    private Point getRightBlock(Point block){ 
        return new Point(block.x+1,block.y);
    } // end method
    
    /**
     * Exchange block with adjacent empty space
     *
     * @param r1 block 1 row index
     * @param c1 block 2 column index
     * @param r2 block 3 row index
     * @param c2 block 4 column index
     */
    public void exchangeBlocks(int r1, int c1, int r2, int c2) {
        Block temp = blocks[r1][c1];
        blocks[r1][c1] = blocks[r2][c2];
        blocks[r2][c2] = temp;
    }//end method
    
    /**
     * Getter
     * <p> Get empty space row and index position
     * 
     * @return empty space position
     * <p> Point.X ==> column index
     * <p> Point.Y ==> row index
     */
    public Point getEmptyBlockRowCol(){
        int r_empty = 0;
        int c_empty = 0;
        // search empty blcok
        for(int r = 0; r < PUZZLE_SIZE; ++r) {
            for(int c = 0; c < PUZZLE_SIZE; ++c) {
                if(blocks[r][c].getBlockNumber() == null) {
                    r_empty = r;
                    c_empty = c;
                    break;
                } //end if
            } // end for
        } // end for
        return new Point(c_empty, r_empty); // note row = y
    } // end method

    /**
     * Check whether block is valid
     * 
     * @param r row index position 
     * @param c column index position
     * @return whether block is within the panel
     */
    public boolean isValidateBlock(int r, int c) {
        return (r < PUZZLE_SIZE && r >= 0 && c < PUZZLE_SIZE && c >= 0); 
    } // end method
    
    /**
     * Check whether block can be changed with empty space
     * 
     * @param r row index position 
     * @param c column index position
     * @return whether block can be changed with empty space
     */
    public boolean isBlockExchangable(int r, int c) {
        int empty_row = getEmptyBlockRowCol().y;
        int empty_column = getEmptyBlockRowCol().x;
        return ( (  ((Math.abs(r - empty_row) == 1) && (c == empty_column))
                   |((Math.abs(c - empty_column) == 1) && (r == empty_row)))
                && isValidateBlock(r,c) == true);    
    } // end method
    
    /**
     * Check whether current game is successfully completed
     * 
     * @return whether current game is successfully completed
     */
    public boolean isCompleteCurrentRound() {
        for(int r = 0; r < PUZZLE_SIZE; ++r) {
            for(int c = 0; c < PUZZLE_SIZE; ++c) {
                // if at least one block does not at correct position then return false               
                if(!blocks[r][c].isBlockOnCorrectPosition(r,c)) return false;
            } // end for
        } // end for
        // record time consumed and number of movement
        move = maxMoveCount; 
        time_consumed = NumberSliderPuzzleGameMainFrame.clockTime;
        return true; // all blocks at correct position
    } // end method
    
    /**
     * Setter
     * <p> Set puzzle size
     * 
     * @param _puzzle_size row and column of the generated puzzle 
     */
    public static void set_puzzle_size(int _puzzle_size){
        PuzzleModel.PUZZLE_SIZE = _puzzle_size;
    } // end method
    
    /**
     * Getter
     * <p> Get puzzle size (row and column of the generated puzzle)
     * 
     * @return puzzle size (row and column of the generated puzzle)
     */
    public static int get_puzzle_size(){
        return PuzzleModel.PUZZLE_SIZE;
    } // end method
    
    /**
     * Setter
     * <p> Setting number of shuffles
     * 
     * @param _shuffle_number number of shuffles
     */
    public static void set_shuffle_number(int _shuffle_number){
        PuzzleModel.SHUFFLE_NUMBER = _shuffle_number;
    } // end method
    
    /**
     * Getter
     * <p> Get number of shuffles
     * 
     * @return number of shuffles 
     */
    public static int get_shuffle_number(){
        return PuzzleModel.SHUFFLE_NUMBER;
    } // end method
    
    /**
     * Getter
     * <p> Get max move count
     * 
     * @return max move count 
     */
    public static int get_max_move_count(){
        return PuzzleModel.maxMoveCount;
    } // end method
    
    /**
     * Setter
     * <p> Set max move count
     */
    public static void reset_max_move_count(){
        PuzzleModel.maxMoveCount = 0;
    } // end method
    
    /**
     * Increase max move count by 1
     */
    public static void increase_max_move_count(){
        PuzzleModel.maxMoveCount ++;
    } // end method
    
    /**
     * Getter
     * <p> Get number of moves
     * 
     * @return number of moves
     */
    public static int get_move(){
        return PuzzleModel.move;
    } // end method
    
    /**
     * Reset number of moves
     */
    public static void reset_move(){
        PuzzleModel.move = 0;
    } // end method
    
    /**
     * Getter
     * <p> Get elapsed time in double
     * 
     * @return elapsed time
     */
    public static double get_time_consumed(){
        return PuzzleModel.time_consumed;
    } // end method
    
    // default is set to 0, null, false
    private static int PUZZLE_SIZE = 2;                  // row and column of squared puzzle model
    private final Block[][] blocks;                           // array of number blocks/sliders
    private Block empty_space;                          // define the empty space
    private static int SHUFFLE_NUMBER = 1000;            // total number of shuffles
    private static int maxMoveCount;                     // max move count of each round of game
    private static int move;                             // move counter
    private static double time_consumed;                 // record time in second
    
} // end class
