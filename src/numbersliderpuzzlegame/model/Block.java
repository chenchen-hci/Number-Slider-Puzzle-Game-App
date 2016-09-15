/*
 * @class Type:  Model Class
 * @description: This class aims to construct the instance of number block of
 *               of sliding puzzle which contains the information of block 
 *               position an relevant setter and getter method.
 */

package numbersliderpuzzlegame.model;

/**
 * project: A desktop based number slider puzzle game application
 * email:   chenc5@andrew.cmu.edu
 * @author  Chen Chen
 * Created in 2016, at the University of Nottingham, UK               
 */

public class Block {
    
    /**
     * Constructor 
     * <p> This constructor build the block contains position and number information
     * 
     * @param row_index row index of a specific block
     * @param column_index column index of a specific block
     * @param block_number number of a specific block
     */
    public Block(int row_index, int column_index, int block_number) {
        this.row_index = row_index;
        this.column_index = column_index;
        this.block_number = Integer.toString(block_number);
    } // end method
    
    /**
     * Setter
     * <p> Set number of slider,if value of -1 is assigned, making the block 
     * number become null
     * 
     * @param number number of block cell 
     */
    public void setBlockNumber(int number){
        if(number!=-1 )block_number = Integer.toString(number);
        else block_number = null;
    } // end method
    
    /**
     * Determine whether it is empty space
     * 
     * @return true if it is empty space
     */
    public boolean isEmpty() {
        return (block_number == null);
    } // end method
    
    /**
     * Getter
     * <p> Get block number
     * 
     * @return block number in string type
     */
    public String getBlockNumber() {
        return block_number;
    } // end method
    
    /**
     * Getter
     * <p> Get row position
     * 
     * @return block row index
     */
    public int getRowNumber(){
        return row_index;
    } // end method
    
    /**
     * Getter
     * <p> Get column position
     * 
     * @return block column index 
     */
    // getter - get column information
    public int getColumnNumber(){
        return column_index;
    } // end method
    
    /**
     * Override method of root class Object
     * 
     * @return block number in string type
     */
    @Override
    public  String toString(){
        return (block_number);
    } // end method
    
    /**
     * Determine whether block is at right position
     * 
     * @param current_row row index
     * @param current_column column index
     * @return whether block is at solved position
     */
    public boolean isBlockOnCorrectPosition(int current_row, int current_column){
        return (row_index == current_row && (column_index == current_column));
    } // end method
    
    private final int row_index;      // define the position of row
    private final int column_index;   // define the position of column
                                      // note: the row and column position is the
                                      // solved version which is defined whithout shuffling
    private String block_number;      // describe the number of slider
                                      // if the value of null is assigned, 
                                      // an empty space is generated.
    
} // end class


