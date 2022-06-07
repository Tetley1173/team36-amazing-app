package mazeFunctions;

import gui.displayMaze;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;
import java.util.concurrent.ExecutionException;

/***
 * This class extends the abstract maze class which is used for creating a maze object with entry and exit image
 *
 * @author Eric Ng
 */
public class MazeWithImage extends Maze{
    ImageIcon entryImage = new ImageIcon();
    ImageIcon exitImage = new ImageIcon();

    /***
     * Constructor of MazeWithImage
     * This is the class for the maze with image entry/exit
     * @param rows the number of rows (height) of the maze
     * @param cols the number of columns (width) of the maze
     */
    public MazeWithImage(int rows, int cols) {
        super(rows, cols);
    }

    private void setEntryImage(BufferedImage img){
        entryImage = new ImageIcon(img.getScaledInstance(displayMaze.CELL_WIDTH + displayMaze.OFFSET_X, displayMaze.CELL_HEIGHT + displayMaze.OFFSET_Y, Image.SCALE_DEFAULT));
    }
    private void setExitImage(BufferedImage img){
        exitImage = new ImageIcon(img.getScaledInstance(displayMaze.CELL_WIDTH + displayMaze.OFFSET_X, displayMaze.CELL_HEIGHT + displayMaze.OFFSET_Y, Image.SCALE_DEFAULT));
    }
}
