/*
 * @class Type:  Util Class
 * @description: This class is a collections of methods and field for 
 *               file related operation.
 */

package numbersliderpuzzlegame.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

/**
 * project: A desktop based number sliding puzzle game application
 * email:   chenc5@andrew.cmu.edu
 * @author  Chen Chen
 * Created in 2016, at the University of Nottingham, UK  
 */

public class FileOperator {

    /**
     * Default Constructor
     * <p> Empty method
     */
    public FileOperator(){}
    
    /**
     * Get file customised file path by file chooser dialog 
     * 
     * @param jframe
     * @return whether file path is acquired successfully
     */
    private static boolean getFilePathUsingFileDialog(JFrame jframe){
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Specify the file path to save");   
 
        int userSelection = fileChooser.showSaveDialog(jframe);

        if (userSelection == 0 && userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();
            filepath = fileToSave.getAbsolutePath();
            filepath = filepath.concat(".txt"); // include file format suffix (.txt)
            return true;
            
        } // end if
        else {
            return false;
        } // end else
    } // end method
    
    /**
     * Create backup file
     * 
     * @param jframe 
     */
    public static void setBackUpFile(JFrame jframe) {
        try{
            file_backup = new File(backupfilename);
        
            if(!file_backup.exists())
                file_backup.createNewFile();
            
            bufwriter = new BufferedWriter(new FileWriter(file_backup.getAbsoluteFile()));
        } catch (IOException e) {
            Object[] objects = {UIManager.getIcon("OptionPane.information"),
                                "Error: File cannot be created!",
                                "Press OK to exit!"};
 
            // appear frame parent message
            JOptionPane.showMessageDialog(jframe, objects, 
                                     "File Error",
                                      JOptionPane.ERROR_MESSAGE);
            System.exit(0); // exit program if this fatal errors occurred
        }                
    } // end method
    
    /**
     * Write content to backup file
     * 
     * @param _content pass via string type
     * @param jframe 
     */
    public static void writeBackupFile(String _content, JFrame jframe) {
        try {
            bufwriter.write(_content);
        } catch(IOException e){
            Object[] objects = {UIManager.getIcon("OptionPane.information"),
                                "Error: File cannot be written!",
                                "Press OK to exit!"};
 
            // appear frame parent message
            JOptionPane.showMessageDialog(jframe, objects, 
                                     "File Error",
                                      JOptionPane.ERROR_MESSAGE);
            System.exit(0); // exit program if this fatal error occurred
        }
    } // end method
    
    /**
     * Close back up file
     * 
     * @param jframe 
     */
    private static void closeBackupFile(JFrame jframe) {
        try {
            bufwriter.close(); // save data to file
        } catch(IOException e){
            Object[] objects = {UIManager.getIcon("OptionPane.information"),
                                "Error: File cannot be closed!",
                                "Press OK to exit!"};
 
            // appear frame parent message
            JOptionPane.showMessageDialog(jframe, objects, 
                                     "File Error",
                                      JOptionPane.ERROR_MESSAGE);
            System.exit(0); // exit program if this fatal error occurred
        }
    } // end method
    
    /**
     * Delete back up file
     */
    private static void deleteBackupFile() {
        if(file_backup.exists()){
            try{
                file_backup.delete(); 
            } catch(Exception e){
                System.out.println("Fatal Error: the program will automatically exit!");
                System.exit(0);
            }
        } // end if
    } // end method
    
    /**
     * Copy backup file to designated path
     * 
     * @param jframe
     * @return whether file is printed successfully
     */
    public static boolean printFinalFile(JFrame jframe){
        InputStream inStream = null;
        OutputStream outStream = null;
        
        // if successfully get file path
        if(getFilePathUsingFileDialog(jframe)){
            try{
                // print current date and time (for future reference)
                bufwriter.write("\r\n\r\n\r\nPrint Time:" + DateTimeOperator.getCurrentDateTime()); // type print time
                closeBackupFile(jframe);
                
                // copy file in directory to designated path/location
                file_target = new File(filepath);
                file_backup = new File(backupfilename);

                // create new file if target file does not exist
                if(!file_target.exists())
                    file_target.createNewFile();

                inStream = new FileInputStream(file_backup);
                outStream = new FileOutputStream(file_target);

                byte[] buffer = new byte[1024];

                int length;
                while((length = inStream.read(buffer)) > 0) {
                    outStream.write(buffer, 0 ,length);
                } // end while

                inStream.close();
                outStream.close();
                deleteBackupFile();
                return true;
                    
            }catch(IOException e){
                // error information
                Object[] objects = {UIManager.getIcon("OptionPane.information"),
                        "Error: File cannot be saved!",
                        "Press OK to continue!"};
 
                // appear frame parent message
                JOptionPane.showMessageDialog(jframe, objects, 
                        "File Error",
                        JOptionPane.ERROR_MESSAGE);
                    
                return false;
            }
        } else {
                return false;
        }
    } // end method
    
    private static String filepath;
    private static final String backupfilename = "backup.txt";
    private static File file_backup;
    private static File file_target;
    private static BufferedWriter bufwriter;   // buffer for sending to backup file
} // end class
