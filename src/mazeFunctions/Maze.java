package mazeFunctions;

import components.Cell;


public abstract class Maze {

    private final int rows, cols;
    private final Cell[][] maze;
    private Cell entryCell, exitCell;
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




    public void invertWall(int row, int col, int index) { this.maze[row][col].invertWall(index);}
    // Get method
    public int getRows() { return this.rows; }
    public int getCols() { return this.cols; }
    public Cell getCell(int row, int col) { return this.maze[row][col]; }
    public int getWallSum(int row, int col) { return this.maze[row][col].sumWall(); }
    public Cell getEntryCell() { return this.entryCell; }
    public Cell getExitCell() { return this.exitCell; }

    public void setEntryCell() { entryCell = MazeGeneration.setEntryExit(this).get(0); }
    public void setExitCell() { exitCell = MazeGeneration.setEntryExit(this).get(1); }
}
