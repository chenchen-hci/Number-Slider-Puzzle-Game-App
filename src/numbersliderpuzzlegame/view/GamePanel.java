/*
 * @class Type:  View Class
 * @description: This class aims to construct the GUI interface (Swing/JPanel) 
*                of game panel.
 */

package numbersliderpuzzlegame.view;

import numbersliderpuzzlegame.model.PuzzleModel;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Printable;

/**
 * project: A desktop based number sliding puzzle game application
 * email:   chenc5@andrew.cmu.edu
 * @author  Chen Chen
 * Created in 2016, at the University of Nottingham, UK  
 */

public class GamePanel extends javax.swing.JPanel implements Printable {


    /**
     * Create new game panel
    */
    public GamePanel() {
       initComponents();
       block_pixel_size = (int)GamePanel.GAME_PANEL_PIXEL_SIZE/PuzzleModel.get_puzzle_size();  
       puzzle_model = new PuzzleModel(); // create new puzzle using puzzle size and block pixel size
    } // end method
    
    /**
     * Paint puzzle
     * 
     * @param g Graphics
     */
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        // draw slider and empty space
        if(!NumberSliderPuzzleGameMainFrame.isPause) { // when pause button is not pressed
            for(int r = 0; r < PuzzleModel.get_puzzle_size(); ++r) {
                for(int c = 0; c < PuzzleModel.get_puzzle_size(); ++c) {
                    String block_number = puzzle_model.getBlockNumber(r, c);
                    // paint and skip empty space
                    if(block_number != null) {
                        // draw block
                        g.setColor(Color.GRAY);
                        g.fillRect(c*block_pixel_size, r*block_pixel_size, block_pixel_size, block_pixel_size);
                        // draw frame
                        g.setColor(Color.black);
                        g.drawRect(c*block_pixel_size, r*block_pixel_size, block_pixel_size, block_pixel_size);
                        // write number
                        g.setColor(Color.BLACK);
                        g.setFont(new Font("SansSerif", Font.BOLD, block_pixel_size/2));
                        g.drawString(block_number, c*block_pixel_size+7, r*block_pixel_size +(3*block_pixel_size)/4);
                    } // end if
                } // end for
            } // end for
        } // end if
        else {  // when pause button is pressed
            // shade the game panel for game fairness purpose
            g.setColor(Color.GRAY);
            g.fillRect(0,0,500,500);
            g.setColor(Color.BLACK);
            g.setFont(new Font("SansSerif", Font.BOLD, 20)); // set font size
            g.drawString(" Your program has been paused", 80, 250);
            g.drawString("     Press resume to continue!", 80, 280);
        } // end else  
    } // end method
    
    /**
     * Reset puzzle model
     */
    public void reset_reshuffle(){
        puzzle_model.reset();
    } // end method
        
    /**
     * Determine whether current game is completed
     * 
     * @return whether current game is completed
     */
    public boolean isCompleteCurrentRound(){
        return (puzzle_model.isCompleteCurrentRound());
    } // end method
    
    /**
     * Restart game by setting puzzle size to 2
     */
    public void restart(){
        PuzzleModel.set_puzzle_size(2);
        puzzle_model = new PuzzleModel();
        repaint();
    } // end method
    
    /**
     * pixel x value to column number converter 
     * 
     * @param pixel_coordinate_x
     * @return column position index
     */
    private int pixelToBlockIndexX(int pixel_coordinate_x) {
        return (int)(pixel_coordinate_x/block_pixel_size);
    } // end method
    
    /**
     * pixel y value to row number converter
     * 
     * @param pixel_coordinate_y
     * @return row position index
     */
    private int pixelToBlockIndexY(int pixel_coordinate_y) {
        return (int)(pixel_coordinate_y/block_pixel_size);
    } // end method
     
    /**
     * Create new puzzle after new block size and puzzle size being calculated 
     * 
     * @param puzzle_size row and column of puzzle size
     */
    public void createNewPuzzle(int puzzle_size) {
        PuzzleModel.set_puzzle_size(puzzle_size);  
        puzzle_model = new PuzzleModel();
        repaint();
    } // end method

    /**
     * send current view of solved/unsolved puzzle to printer (as printer job)
     * 
     * @param graphics
     * @param pageFormat
     * @param pageIndex
     * @return whether print successfully
     */
    @Override
    public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) {
        if(pageIndex != 0) return NO_SUCH_PAGE;
        Graphics2D g2 = (Graphics2D) graphics;
        g2.setPaint(Color.black);
        this.paint(g2);
        return PAGE_EXISTS;
    } // end method

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setPreferredSize(new java.awt.Dimension(500, 500));
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                formMousePressed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents
    
    /**
     * check whether puzzle is solved whenever panel is clicked
     * 
     * @param evt 
     */
    private void formMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMousePressed

        int selected_block_row = pixelToBlockIndexY(evt.getPoint().y);      // pixel coordinate y corresponds to row number
        int selected_block_column = pixelToBlockIndexX(evt.getPoint().x);   // pixel coordinate x corresponds to column number
        
        // move slider to empty space if exchangable 
        if(puzzle_model.isBlockExchangable(selected_block_row, selected_block_column)) {
            PuzzleModel.increase_max_move_count();
            
            puzzle_model.exchangeBlocks(selected_block_row, 
                                        selected_block_column, 
                                        puzzle_model.getEmptyBlockRowCol().y,
                                        puzzle_model.getEmptyBlockRowCol().x);
        
            repaint();
            
            // check if passed current round after each move
            if(puzzle_model.isCompleteCurrentRound()){
                // if so then create a new puzzle with increased puzzle size
                GamePanel.isCompleteCurrentGameFlag = true;  
                PuzzleModel.set_puzzle_size(PuzzleModel.get_puzzle_size()+1);
                createNewPuzzle(PuzzleModel.get_puzzle_size());
            } // end if
            else{
                GamePanel.isCompleteCurrentGameFlag = false;
            } // end else
        } // end if
    }//GEN-LAST:event_formMousePressed

    /**
     * Setter
     * <p> Method for setting pixel size of each number block
     * 
     * @param _block_pixel_size 
     */
    public static void set_block_pixel_size(int _block_pixel_size){
        GamePanel.block_pixel_size = _block_pixel_size;
    } // end method
    
    private static int block_pixel_size; // indicates the pixel size of each slider
    public static PuzzleModel puzzle_model;
    public static final int GAME_PANEL_PIXEL_SIZE = 500;      // indicates the row and column square game panel       
    public static boolean isCompleteCurrentGameFlag = false;  // flag showing whether current game is completed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables

} // end class
