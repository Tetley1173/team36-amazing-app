package mazeFunctions;

import components.Cell;
import gui.displayMaze;

import javax.swing.*;
import java.time.LocalDateTime;
import java.util.Random;

import static gui.AssetsTab.getLogo1;
import static gui.AssetsTab.getLogo2;


public abstract class Maze {
    private String mazeName = null;

    private final int rows, cols;
    private int reachableCells = -1;
    private final Cell[][] maze;
    private Cell entryCell, exitCell;

    private boolean hasLogo = false;
    private ImageIcon logoIcon1;

    private int logoRow, logoCol;
    private int logoHeight = -1;
    private int logoWidth = -1;
    private ImageIcon logoIcon2;

    /***
     *
     * @param mazeName name of the current maze
     */
    public void setMazeName(String mazeName) {
        this.mazeName = mazeName;
    }

    /***
     *
     * @return the name of the current maze
     */
    public String getMazeName() { return mazeName; }
    private String authorName = null;

    /***
     *
     * @param authorName the author name
     */
    public void setAuthor(String authorName) {
        this.authorName = authorName;
    }

    /***
     *
     * @return the author name of the current maze
     */
    public String getAuthor() { return authorName; }

    private LocalDateTime createdDateTime = null;

    /***
     *
     * @return the created datetime of the current maze
     */
    public LocalDateTime getCreatedDateTime() { return createdDateTime; }
    private LocalDateTime lastEditedDateTime = null;

    /***
     *
     * @param lastEditedDateTime the last edited datetime of the current maze
     */
    public void setLastEditedDateTime(LocalDateTime lastEditedDateTime) {
        this.lastEditedDateTime = lastEditedDateTime;
    }

    /***
     *
     * @return the last edited datetime of the current maze
     */
    public LocalDateTime getLastEditedDateTime() { return lastEditedDateTime; }


    /*
    ImageAsset fields for all logos, entry and exit
     */

//    private ImageAsset logo1;
//    private ImageAsset logo2;
//    private ImageAsset entryIcon = null;
//    private ImageAsset exitIcon = null;

//    /*
//    Fields added by Shannon to facilitate import of assets.
//     */
//    private boolean hasEntry = false;   // Indicates if an image has been allocated to the entry.
//    private ImageAsset entryAsset;     // ImageAsset object that defines what image is used for the entry.
//    private ImageIcon entryIcon = null; // The ImageIcon that the asset will be displayed in.
//    private int entryRow, entryCol;
//    private int entryHeight = -1;
//    private int entryWidth = -1;
//    // Note to Shannon most of these fields should be fields of the ImageAsset class instead.
//
//    private boolean hasExit = false;
//    private ImageAsset entry1Asset;
//    private ImageIcon exitIcon = null;
//    private int exitRow, exitCol;
//    private int exitHeight = -1;
//    private int exitWidth = -1;
//
//    private boolean hasLogo1 = false;
//    private ImageAsset logo1Asset;
//    private ImageIcon logo1Icon = null;
//    private int logo1Row, logo1Col;
//    private int logo1Height = -1;
//    private int logo1Width = -1;
//
//    private boolean hasLogo2 = false;
//    private ImageAsset logo2Asset;
//    private ImageIcon logo2Icon = null;
//    private int logo2Row, logo2Col;
//    private int logo2Height = -1;
//    private int logo2Width = -1;

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

//        logoIcon1.setImage(getLogo1().getAsset());
//        logoIcon2.setImage(getLogo2().getAsset());

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
    public void setHasLogo(boolean hasLogo) { this.hasLogo = hasLogo;}
    public boolean getHasLogo() { return hasLogo;}
    public void setLogoIcon1(ImageIcon logoIcon1) { this.logoIcon1 = logoIcon1; }
    public ImageIcon getLogoIcon1() { return logoIcon1; }
    public void setLogoDimension(int logoHeight, int logoWidth) { // can be removed
        this.logoHeight = logoHeight;
        this.logoWidth = logoWidth;
    }
    public int getLogoHeight() { return logoHeight; }// can be removed
    public int getLogoWidth() { return logoWidth; }// can be removed
    public void setLogoLocation(int row, int col) {// can be removed
        logoRow = row;
        logoCol = col;
    }
    public int getLogoRow() { return logoRow; }
    public int getLogoCol() { return logoCol; }
    /*
    End of Logo Methods ########################################################################
     */


    public void invertWall(int row, int col, int index) { maze[row][col].invertWall(index);}
    // Get method
    public int getRows() { return rows; }
    public int getCols() { return cols; }

    public Cell getCell(int row, int col) { return maze[row][col]; }
    public int getWallSum(int row, int col) { return maze[row][col].sumWall(); }
    public Cell getEntryCell() { return entryCell; }
    public Cell getExitCell() { return exitCell; }

    public void setEntryCell(Cell entryCell) { this.entryCell = entryCell; }
    public void setExitCell(Cell exitCell) { this.exitCell = exitCell; }
}
