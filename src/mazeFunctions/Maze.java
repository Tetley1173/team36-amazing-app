package mazeFunctions;

import components.Cell;
import gui.displayMaze;

import javax.swing.*;
import java.util.Random;


public abstract class Maze {
    private final int rows, cols;
    private final Cell[][] maze;
    private Cell entryCell, exitCell;

    private boolean hasLogo = false;
    private ImageIcon logo = null;
    private int logoRow, logoCol;
    private int logoHeight = -1;
    private int logoWidth = -1;


    public Maze(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        maze = new Cell[rows][cols];
        for (int row = 0; row < rows; row++)
            for (int col = 0; col < cols; col++)
                maze[row][col] = new Cell(row, col);
        entryCell = maze[0][0];
        exitCell = maze[rows - 1][cols - 1];
    }

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
    public void spareLocation(int row, int col, int logoHeight, int logoWidth) {
        for (int i = row + 1; i < row + logoHeight - 1; i++) {
            for (int j = col + 1; j < col + logoWidth - 1; j++) {
                getCell(i, j).invertWall(0);
                getCell(i, j).invertWall(1);
                getCell(i, j).invertWall(2);
                getCell(i, j).invertWall(3);
                disableButton(i, j, 0);
                disableButton(i, j, 1);
                disableButton(i, j, 2);
                disableButton(i, j, 3);

            }
        }
        for (int j = col + 1; j < col + logoWidth - 1; j++) {
            getCell(row, j).invertWall(1);
            getCell(row, j).invertWall(2);
            getCell(row, j).invertWall(3);
            disableButton(row, j, 1);
            disableButton(row, j, 2);
            disableButton(row, j, 3);

            getCell(row + logoHeight - 1, j).invertWall(0);
            getCell(row + logoHeight - 1, j).invertWall(1);
            getCell(row + logoHeight - 1, j).invertWall(3);
            disableButton(row + logoHeight - 1, j, 1);
            disableButton(row + logoHeight - 1, j, 2);
            disableButton(row + logoHeight - 1, j, 3);

        }
        for (int i = row + 1; i < row + logoHeight - 1; i++) {
            getCell(i, col).invertWall(0);
            getCell(i, col).invertWall(1);
            getCell(i, col).invertWall(2);
            disableButton(i, col, 0);
            disableButton(i, col, 1);
            disableButton(i, col, 2);

            getCell(i, col + logoWidth - 1).invertWall(0);
            getCell(i, col + logoWidth - 1).invertWall(2);
            getCell(i, col + logoWidth - 1).invertWall(3);
            disableButton(i, col + logoWidth - 1, 0);
            disableButton(i, col + logoWidth - 1, 2);
            disableButton(i, col + logoWidth - 1, 3);
        }
        getCell(row, col).invertWall(1);
        getCell(row, col).invertWall(2);
        disableButton(row, col,1);
        disableButton(row, col,2);

        getCell(row, col + logoWidth - 1).invertWall(2);
        getCell(row, col + logoWidth - 1).invertWall(3);
        disableButton(row, col + logoWidth - 1,2);
        disableButton(row, col + logoWidth - 1,3);

        getCell(row + logoHeight - 1, col).invertWall(0);
        getCell(row + logoHeight - 1, col).invertWall(1);
        disableButton(row + logoHeight - 1, col,0);
        disableButton(row + logoHeight - 1, col,1);


        getCell(row + logoHeight - 1, col + logoWidth - 1).invertWall(0);
        getCell(row + logoHeight - 1, col + logoWidth - 1).invertWall(3);
        disableButton(row, col + logoWidth - 1,2);
        disableButton(row, col + logoWidth - 1,3);

    }
    // Disable the wall buttons in the logo area
    private void disableButton(int row, int col, int index) {
        displayMaze.wallButtons[row][col].getWallButton(index).setVisible(false);
    }

    public void setHasLogo(boolean hasLogo) { this.hasLogo = hasLogo;}
    public boolean getHasLogo() { return hasLogo;}
    public void setLogo(ImageIcon logo) { this.logo = logo; }
    public ImageIcon getLogo() { return logo; }
    public void setLogoDimension(int logoHeight, int logoWidth) {
        this.logoHeight = logoHeight;
        this.logoWidth = logoWidth;
    }
    public int getLogoHeight() { return logoHeight; }
    public int getLogoWidth() { return logoWidth; }
    public void setLogoLocation(int row, int col) {
        logoRow = row;
        logoCol = col;
    }
    public int getLogoRow() { return logoRow; }
    public int getLogoCol() { return logoCol; }

    public void invertWall(int row, int col, int index) { maze[row][col].invertWall(index);}
    // Get method
    public int getRows() { return rows; }
    public int getCols() { return cols; }
    public Cell getCell(int row, int col) { return maze[row][col]; }
    public int getWallSum(int row, int col) { return maze[row][col].sumWall(); }
    public Cell getEntryCell() { return entryCell; }
    public Cell getExitCell() { return exitCell; }

    public void setEntryCell() { entryCell = MazeGeneration.setEntryExit(this).get(0); }
    public void setExitCell() { exitCell = MazeGeneration.setEntryExit(this).get(1); }
}
