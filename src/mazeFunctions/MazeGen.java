package mazeFunctions;

import components.Cell;
import components.CustomizedButton;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

public class MazeGen {
    // Constant
    private final int OFFSET = 10;

    private final int rows, cols;
    private final int width = 30;
    private final int height = 5;
    private final int size;
    private final Cell[][] maze;
    private Cell[] entryExitLocation;
    ArrayList<Cell> distanceBetweenDeadEndAndStart = new ArrayList<>();
    public MazeGen(int rows, int cols, int size) {
        this.rows = rows;
        this.cols = cols;
        this.maze = new Cell[rows][cols];
        this.size = size;
        
        this.createMaze(rows, cols);
        this.genMaze();
        entryExitLocation = this.getEntryExitLocation();
        
    }

    public void createMaze(int rows, int cols) {
        for (int i = 0; i < rows; i++)
            for (int j = 0; j < cols; j++)
                this.maze[i][j] = new Cell(i,j);
    }
    public void genMaze(){
    /*
    Pseudo Code:
        Create a CellStack (LIFO) to hold a list of cell locations
        set totalCells = number of cells in grid
        choose a cell at random and call it currentCell
        set visitedCells = 1

        while visitedCells < totalCells
            find all neighbours of currentCell with all walls intact
            if one or more found
                choose one at random
                knock down the wall between it and currentCell
                push currentCell location on the cellStack
                make the new cell currentCell
                add 1 to visitedCells
            else
                pop the most recent cell entry off the CellStack
                make it currentCell
            endif
        endWhile
 */
        Stack<Cell> cellStack = new Stack<>();
        Random rand = new Random();

        int totalCells = this.rows * this.cols;

        int currentRow = 0;
        int currentCol = 0;
        Cell currentCell = new Cell(currentRow, currentCol);
        entryExitLocation = new Cell[2];
        entryExitLocation[0] = currentCell;
        // print the starting point of the algorithm
        //System.out.println("Starting cell: " + currentRow + ", "+currentCol);
        int visitedCells = 1;

        while (visitedCells < totalCells) {
            // Find no of neighbour cell which has 4 intact walls
            ArrayList<int[]> neighbourCellList = new ArrayList<>();
            if (currentRow == 0) {
                if (maze[currentRow + 1][currentCol].sumWall() == 4) {
                    neighbourCellList.add(new int[]{ currentRow + 1 , currentCol });
                }
                if (currentCol == 0) {
                    if (maze[currentRow][currentCol + 1].sumWall() == 4) {
                        neighbourCellList.add(new int[]{ currentRow , currentCol + 1 });
                    }
                }
                else if (currentCol == this.cols - 1) {
                    if (maze[currentRow][currentCol - 1].sumWall() == 4) {
                        neighbourCellList.add(new int[]{ currentRow , currentCol - 1 });
                    }
                }
                else {
                    if (maze[currentRow][currentCol + 1].sumWall() == 4) {
                        neighbourCellList.add(new int[]{ currentRow , currentCol + 1 });
                    }
                    if (maze[currentRow][currentCol - 1].sumWall() == 4) {
                        neighbourCellList.add(new int[]{ currentRow , currentCol - 1 });
                    }
                }
            }
            else if (currentRow == this.rows - 1) {
                if (maze[currentRow - 1][currentCol].sumWall() == 4) {
                    neighbourCellList.add(new int[]{ currentRow - 1 , currentCol });
                }
                if (currentCol == 0) {
                    if (maze[currentRow][currentCol + 1].sumWall() == 4) {
                        neighbourCellList.add(new int[]{ currentRow , currentCol + 1 });
                    }
                }
                else if (currentCol == this.cols - 1) {
                    if (maze[currentRow][currentCol - 1].sumWall() == 4) {
                        neighbourCellList.add(new int[]{ currentRow , currentCol - 1 });
                    }
                }
                else {
                    if (maze[currentRow][currentCol + 1].sumWall() == 4) {
                        neighbourCellList.add(new int[]{ currentRow , currentCol + 1 });
                    }
                    if (maze[currentRow][currentCol - 1].sumWall() == 4) {
                        neighbourCellList.add(new int[]{ currentRow , currentCol - 1 });
                    }
                }
            }
            else if (currentCol == 0) {
                if (maze[currentRow][currentCol + 1].sumWall() == 4) {
                    neighbourCellList.add(new int[]{ currentRow , currentCol + 1 });
                }
                if (currentRow != this.rows - 1) {
                    if (maze[currentRow + 1][currentCol].sumWall() == 4) {
                        neighbourCellList.add(new int[]{currentRow + 1, currentCol});

                    }
                }
                if (maze[currentRow - 1][currentCol].sumWall() == 4) {
                    neighbourCellList.add(new int[]{ currentRow - 1 , currentCol });
                }
            }
            else if (currentCol == this.cols - 1) {
                if (maze[currentRow][currentCol - 1].sumWall() == 4) {
                    neighbourCellList.add(new int[]{ currentRow , currentCol - 1 });
                }
                if (currentRow != this.rows - 1) {
                    if (maze[currentRow + 1][currentCol].sumWall() == 4) {
                        neighbourCellList.add(new int[]{currentRow + 1, currentCol});
                    }
                }
                if (maze[currentRow - 1][currentCol].sumWall() == 4) {
                    neighbourCellList.add(new int[]{ currentRow - 1 , currentCol });
                }
            }
            else {
                if (maze[currentRow + 1][currentCol].sumWall() == 4) {
                    neighbourCellList.add(new int[]{ currentRow + 1 , currentCol });
                }
                if (maze[currentRow - 1][currentCol].sumWall() == 4) {
                    neighbourCellList.add(new int[]{ currentRow - 1 , currentCol });
                }
                if (maze[currentRow][currentCol + 1].sumWall() == 4) {
                    neighbourCellList.add(new int[]{ currentRow , currentCol + 1 });
                }
                if (maze[currentRow][currentCol - 1].sumWall() == 4) {
                    neighbourCellList.add(new int[]{ currentRow , currentCol - 1 });
                }
            }
            int noOfCells = neighbourCellList.size();
            // Check the number of available neighbour cells
            //System.out.println("number of cell: " + noOfCells);

            // Randomly choose 1 neighbourCell
            int[] nextNeighbour;
            if (noOfCells >= 1) {
                if (noOfCells == 1) nextNeighbour = neighbourCellList.get(0);
                else nextNeighbour = neighbourCellList.get(rand.nextInt(noOfCells));
                if (nextNeighbour[0] < currentRow) {
                    maze[currentRow][currentCol].invertWall(0);
                    maze[nextNeighbour[0]][nextNeighbour[1]].invertWall(2);
                }
                else if (nextNeighbour[0] > currentRow) {
                    maze[currentRow][currentCol].invertWall(2);
                    maze[nextNeighbour[0]][nextNeighbour[1]].invertWall(0);
                }
                else if (nextNeighbour[1] < currentCol) {
                    maze[currentRow][currentCol].invertWall(3);
                    maze[nextNeighbour[0]][nextNeighbour[1]].invertWall(1);
                }
                else if (nextNeighbour[1] > currentCol) {
                    maze[currentRow][currentCol].invertWall(1);
                    maze[nextNeighbour[0]][nextNeighbour[1]].invertWall(3);
                }
                cellStack.push(maze[currentRow][currentCol]);
                currentRow = nextNeighbour[0];
                currentCol = nextNeighbour[1];
                currentCell = maze[currentRow][currentCol];
                // Check the current location
                //System.out.println("CurrentCell: " + nextNeighbour[0] + ", " + nextNeighbour[1]);
                visitedCells++;
            }
            else {
                distanceBetweenDeadEndAndStart.add(currentCell);
                currentCell = cellStack.pop();
                currentRow = currentCell.getRow();
                currentCol = currentCell.getCol();
            }
        }
    }
    public Cell[] getEntryExitLocation(){
        int distance, max = 0;
        for (Cell c: distanceBetweenDeadEndAndStart) {
            // Using the manhattan distance
            distance = c.getRow() - entryExitLocation[0].getRow() + c.getCol() - entryExitLocation[0].getCol();
            if (distance > max) {
                max = distance;
                entryExitLocation[1] = c;
            }
        }
        return entryExitLocation;
    }

    public void drawMaze(JPanel pnl) {
        Graphics g = pnl.getGraphics();
        g.setColor(Color.white);
        for (int row = 0; row < this.rows; row++) {
            for (int col = 0; col < this.cols; col++) {
                if (row == 0 && this.mazeWall(row, col, 0) == 1) pnl.add(new CustomizedButton((col * (width + height)) + height + OFFSET, OFFSET, width, height, 0));
                if (col == 0 && this.mazeWall(row, col, 3) == 1) pnl.add(new CustomizedButton(OFFSET, (row * (width + height)) + height + OFFSET, height, width, 3));
                if (this.mazeWall(row, col, 2) == 1) pnl.add(new CustomizedButton(col * (width + height) + height + OFFSET, (row + 1) * (width + height) + OFFSET, width, height, 2));
                if (this.mazeWall(row, col, 1) == 1) pnl.add(new CustomizedButton((col + 1) * (width + height) + OFFSET, row * (width + height) + height + OFFSET, height, width, 1));
//                if (row == 0 && this.mazeWall(row, col, 0) == 1) g.drawLine(col * this.size + OFFSET, OFFSET,(col + 1) * this.size + OFFSET, OFFSET);
//                if (col == 0 && this.mazeWall(row, col, 3) == 1) g.drawLine(OFFSET,row * this.size + OFFSET, OFFSET,(row + 1) * this.size + OFFSET);
//                if (this.mazeWall(row, col, 2) == 1) g.drawLine(col * this.size + OFFSET,(row + 1) * this.size + OFFSET,(col + 1) * this.size + OFFSET,(row + 1) * this.size + OFFSET);
//                if (this.mazeWall(row, col, 1) == 1) g.drawLine((col + 1) * this.size + OFFSET,row * this.size + OFFSET,(col + 1) * this.size + OFFSET,(row + 1) * this.size + OFFSET);
            }
        }
        //pnl.add(new CustomizedButton(10 + OFFSET, 40 + OFFSET, 30, 10, 2));
    }
    public void showEntryExit(JPanel pnl) {
        Graphics g = pnl.getGraphics();
        g.setColor(Color.RED);
        g.fillRect(entryExitLocation[0].getCol() * width + width/4 + height + OFFSET,entryExitLocation[0].getRow() * width + width/4 + height + OFFSET,width / 2,width / 2);
        g.setColor(Color.GREEN);
        g.fillRect(entryExitLocation[1].getCol() * (width + height) + width/4 + height + OFFSET,entryExitLocation[1].getRow() * (width + height) + width/4 + height + OFFSET,width / 2,width / 2);
    }
    private void showEntryExitIndicator(JPanel pnl){}
    private void showOptimumPath(JPanel pnl){}
    private void showOptimumPathReachingPercentage(JPanel pnl){}
    private void showDeadEndPercentage(JPanel pnl){}

    private void computeOptimumPath(){}
    private double computeDeadEndPercentage(){
        double sumOfDeadEnd = 0;
        for (int row = 0; row < rows; row++)
            for (int col = 0; col < cols; col++)
                if (maze[row][col].sumWall() == 3)
                    sumOfDeadEnd += maze[row][col].sumWall();
        return (double)100 * sumOfDeadEnd/(rows*cols);
    }
    private int mazeWall(int row, int col, int wallIndex) { return maze[row][col].getWall(wallIndex); }
}
