package mazeFunctions;

import components.Cell;
import gui.displayMaze;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.time.LocalDateTime;
import java.util.Random;

import static gui.AssetsTab.getLogo1;

/***
 * This is an abstract class used for creating a maze object
 *
 * @author Eric Ng, minor additions by Shannon Tetley
 */
public abstract class Maze {
    private String mazeName = null;

    private final int rows, cols;
    private int reachableCells = -1;
    private final Cell[][] maze;
    private Cell entryCell, exitCell;

    private boolean hasLogo = false;
    private BufferedImage logoIcon1;

    private int logoRow, logoCol;
    private int logoHeight = -1;
    private int logoWidth = -1;
    private ImageIcon logoIcon2;

    /***
     * Set the maze name
     * @param mazeName name of the current maze
     */
    public void setMazeName(String mazeName) {
        this.mazeName = mazeName;
    }

    /***
     * Get the maze name
     * @return the name of the current maze
     */
    public String getMazeName() { return mazeName; }
    private String authorName = null;

    /***
     * Set the author name
     * @param authorName the author name
     */
    public void setAuthor(String authorName) {
        this.authorName = authorName;
    }

    /***
     * Get the author name
     * @return the author name of the current maze
     */
    public String getAuthor() { return authorName; }

    private LocalDateTime createdDateTime;

    /***
     * Get the created time
     * @return the created datetime of the current maze
     */
    public LocalDateTime getCreatedDateTime() { return createdDateTime; }
    private LocalDateTime lastEditedDateTime;

    /***
     * Set the last edited time
     * @param lastEditedDateTime the last edited datetime of the current maze
     */
    public void setLastEditedDateTime(LocalDateTime lastEditedDateTime) {
        this.lastEditedDateTime = lastEditedDateTime;
    }

    /***
     * Get the last edited time
     * @return the last edited datetime of the current maze
     */
    public LocalDateTime getLastEditedDateTime() { return lastEditedDateTime; }

    /***
     * Constructor of a maze object
     * @param rows the number of rows (height) of the maze
     * @param cols the number of columns (width) of the maze
     */
    public Maze(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        maze = new Cell[rows][cols];
        for (int row = 0; row < rows; row++)
            for (int col = 0; col < cols; col++)
                maze[row][col] = new Cell(row, col);
        entryCell = maze[0][0];
        exitCell = maze[rows - 1][cols - 1];

        createdDateTime = LocalDateTime.now();
        lastEditedDateTime = createdDateTime;
    }

    /***
     * Randomly choose a location for the logo
     * @param rows the number of rows (height) of the maze
     * @param cols the number of columns (width) of the maze
     * @param logoHeight the number of rows occupied by the logo (height)
     * @param logoWidth the number of columns occupied by the logo (width)
     * @return the decided position for the logo
     */
    public int chooseLocation(int rows, int cols, int logoHeight, int logoWidth) {
        Random random = new Random();
        while(true) {
            int cell_ID = random.nextInt(rows*cols);
            if (cell_ID != 0){
                if (logoHeight >= this.getRows() || logoWidth >= this.getCols()) return -1;
                if (rows - cell_ID/rows > logoHeight && cols - cell_ID%rows > logoWidth) return cell_ID;
            }
        }
    }
    // Spare the area for logo

    /***
     * Spare the space for the logo. The location would be the top-left corner of the logo.
     *
     * @param row the starting y-coordinate of the logo
     * @param col the starting x-coordinate of the logo
     * @param logoHeight the number of rows occupied by the logo (height)
     * @param logoWidth the number of columns occupied by the logo (width)
     */
    public void spareLocation(int row, int col, int logoHeight, int logoWidth) {
        for (int i = row + 1; i < row + logoHeight - 1; i++) {
            for (int j = col + 1; j < col + logoWidth - 1; j++) {
                getCell(i, j).invertWall(0);
                getCell(i, j).invertWall(1);
                getCell(i, j).invertWall(2);
                getCell(i, j).invertWall(3);
                hideButton(i, j, 0);
                hideButton(i, j, 1);
                hideButton(i, j, 2);
                hideButton(i, j, 3);

            }
        }
        for (int j = col + 1; j < col + logoWidth - 1; j++) {
            getCell(row, j).invertWall(1);
            getCell(row, j).invertWall(2);
            getCell(row, j).invertWall(3);
            disableButton(row, j, 0);
            hideButton(row, j, 1);
            hideButton(row, j, 2);
            hideButton(row, j, 3);

            getCell(row + logoHeight - 1, j).invertWall(0);
            getCell(row + logoHeight - 1, j).invertWall(1);
            getCell(row + logoHeight - 1, j).invertWall(3);
            hideButton(row + logoHeight - 1, j, 0);
            hideButton(row + logoHeight - 1, j, 1);
            hideButton(row + logoHeight - 1, j, 3);
            disableButton(row + logoHeight - 1, j, 2);

        }
        for (int i = row + 1; i < row + logoHeight - 1; i++) {
            getCell(i, col).invertWall(0);
            getCell(i, col).invertWall(1);
            getCell(i, col).invertWall(2);
            hideButton(i, col, 0);
            hideButton(i, col, 1);
            hideButton(i, col, 2);
            disableButton(i, col, 3);

            getCell(i, col + logoWidth - 1).invertWall(0);
            getCell(i, col + logoWidth - 1).invertWall(2);
            getCell(i, col + logoWidth - 1).invertWall(3);
            hideButton(i, col + logoWidth - 1, 0);
            hideButton(i, col + logoWidth - 1, 2);
            hideButton(i, col + logoWidth - 1, 3);
            disableButton(i, col + logoWidth - 1, 1);
        }
        getCell(row, col).invertWall(1);
        getCell(row, col).invertWall(2);
        hideButton(row, col,1);
        hideButton(row, col,2);
        disableButton(row, col, 0);
        disableButton(row, col, 3);

        getCell(row, col + logoWidth - 1).invertWall(2);
        getCell(row, col + logoWidth - 1).invertWall(3);
        hideButton(row, col + logoWidth - 1,2);
        hideButton(row, col + logoWidth - 1,3);
        disableButton(row, col, 0);
        disableButton(row, col, 1);

        getCell(row + logoHeight - 1, col).invertWall(0);
        getCell(row + logoHeight - 1, col).invertWall(1);
        hideButton(row + logoHeight - 1, col,0);
        hideButton(row + logoHeight - 1, col,1);
        disableButton(row, col, 2);
        disableButton(row, col, 3);


        getCell(row + logoHeight - 1, col + logoWidth - 1).invertWall(0);
        getCell(row + logoHeight - 1, col + logoWidth - 1).invertWall(3);
        hideButton(row, col + logoWidth - 1,0);
        hideButton(row, col + logoWidth - 1,3);
        disableButton(row, col, 1);
        disableButton(row, col, 2);
    }
    // Disable the wall buttons in the logo area
    private void hideButton(int row, int col, int index) {
        displayMaze.wallButtons[row][col].getWallButton(index).setVisible(false);
    }
    private void disableButton(int row, int col, int index) {
        displayMaze.wallButtons[row][col].getWallButton(index).setEnabled(false);
    }

    /*
    Logo Methods ###########################################################################
    */

    /***
     * Set the state of logo of the maze
     * @param hasLogo true if the current maze has logo, false otherwise
     */
    public void setHasLogo(boolean hasLogo) { this.hasLogo = hasLogo; }

    /***
     * Return the state of logo of the maze
     * @return true if the current maze has logo, false otherwise
     */
    public boolean hasLogo() { return hasLogo; }

    /***
     * Set the logo of the maze
     * @param logoIcon1 an ImageIcon object
     */
    public void setLogoIcon1(BufferedImage logoIcon1) { this.logoIcon1 = logoIcon1; }

    /***
     * Get the logo of the maze
     * @return an ImageIcon object
     */
    public ImageIcon getLogoIcon1() {
        return new ImageIcon(logoIcon1.getScaledInstance(displayMaze.CELL_WIDTH * logoWidth + displayMaze.OFFSET_X, displayMaze.CELL_HEIGHT * logoHeight  + displayMaze.OFFSET_Y, Image.SCALE_DEFAULT)); }

    /***
     * Set the preferred logo size on the maze (height and width) of the maze
     * @param logoHeight the number of rows occupied by the logo
     * @param logoWidth the number of columns occupied by the logo
     */
    public void setLogoDimension(int logoHeight, int logoWidth) { // can be removed
        this.logoHeight = logoHeight;
        this.logoWidth = logoWidth;
    }

    /***
     * Get the height of the logo on the maze
     * @return the number of rows occupied by the logo
     */
    public int getLogoHeight() { return logoHeight; }// can be removed
    /***
     * Get the width of the logo on the maze
     * @return the number of columns occupied by the logo
     */
    public int getLogoWidth() { return logoWidth; }// can be removed

    /***
     * Set the top-left corner location of the logo on the maze
     * @param row the top row of the logo on the maze
     * @param col the most left col of the logo on the maze
     */
    public void setLogoLocation(int row, int col) {// can be removed
        logoRow = row;
        logoCol = col;
    }

    /***
     * Get the top row of the logo on the maze
     * @return the top row of the logo on the maze
     */
    public int getLogoRow() { return logoRow; }

    /***
     * Get the most left col of the logo on the maze
     * @return the most left col of the logo on the maze
     */
    public int getLogoCol() { return logoCol; }
    /*
    End of Logo Methods ########################################################################
     */

    /***
     * Invert the wall of a specific cell
     * @param row the row of the cell
     * @param col the col of the cell
     * @param index the wall index of the cell (0 - top, 1 - right, 2 - bottom, 3 - left)
     */
    public void invertWall(int row, int col, int index) { maze[row][col].invertWall(index);}

    /***
     * Get the rows number (height) of the maze
     * @return the rows number of the maze
     */
    // Get method
    public int getRows() { return rows; }
    /***
     * Get the columns number (width) of the maze
     * @return the columns number of the maze
     */
    public int getCols() { return cols; }

    /***
     * Get the cell object in a specific location (row, col)
     * @param row the number of row of the wanted cell
     * @param col the number of column of the wanted cell
     * @return the cell object in (row, col) in the maze
     */
    public Cell getCell(int row, int col) { return maze[row][col]; }
    /***
     * Get the number of walls that is on in a specific cell
     * @param row the number of row of the wanted cell
     * @param col the number of column of the wanted cell
     * @return the number of walls that is on in the cell object in (row, col)
     */
    public int getWallSum(int row, int col) { return maze[row][col].sumWall(); }

    /***
     * Get the location of the entry
     * @return a cell object represent the entry of the maze
     */
    public Cell getEntryCell() { return entryCell; }
    /***
     * Get the location of the exit
     * @return a cell object represent the exit of the maze
     */
    public Cell getExitCell() { return exitCell; }

    /***
     * Set the entry of a maze
     * @param entryCell the cell that represents the preferred entry
     */
    public void setEntryCell(Cell entryCell) { this.entryCell = entryCell; }

    /***
     * Set the exit of a maze
     * @param exitCell the cell that represents the preferred exit
     */
    public void setExitCell(Cell exitCell) { this.exitCell = exitCell; }
}
