package mazeFunctions;

import components.Cell;

import java.util.*;

/***
 * This class provides different methods for a maze object
 *
 * @author Eric Ng
 */
public class MazeGeneration {
    /***
     * Generate a maze automatically based on the empty maze
     * @param maze an empty maze object. (All the wall in the maze are on)
     * @return a maze-liked maze object
     */
    public static Maze genMaze(Maze maze){
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

        int rows = maze.getRows();
        int cols = maze.getCols();
        int totalCells = 0;
        for (int row = 0; row < rows; row++)
            for (int col = 0; col < cols; col++)
                if(maze.getWallSum(row, col) == 4) totalCells++;


        int currentRow = 0;
        int currentCol = 0;

        // Set the (0, 0) cell as default entry
        Cell currentCell = maze.getCell(currentRow, currentCol);
        maze.setEntryCell(currentCell);

        // print the starting point of the algorithm
        //System.out.println("Starting cell: " + currentRow + ", "+currentCol);
        int visitedCells = 1;

        while (visitedCells < totalCells) {
            // Find number of neighbour cell which has 4 intact walls
            ArrayList<int[]> neighbourCellList = new ArrayList<>();

            if (currentRow == 0) {
                if (maze.getWallSum(currentRow + 1, currentCol) == 4) {
                    neighbourCellList.add(new int[]{ currentRow + 1 , currentCol });
                }
                if (currentCol == 0) {
                    if (maze.getWallSum(currentRow, currentCol + 1) == 4) {
                        neighbourCellList.add(new int[]{ currentRow , currentCol + 1 });
                    }
                }
                else if (currentCol == cols - 1) {
                    if (maze.getWallSum(currentRow, currentCol - 1) == 4) {
                        neighbourCellList.add(new int[]{ currentRow , currentCol - 1 });
                    }
                }
                else {
                    if (maze.getWallSum(currentRow, currentCol + 1) == 4) {
                        neighbourCellList.add(new int[]{ currentRow , currentCol + 1 });
                    }
                    if (maze.getWallSum(currentRow, currentCol - 1) == 4) {
                        neighbourCellList.add(new int[]{ currentRow , currentCol - 1 });
                    }
                }
            }
            else if (currentRow == rows - 1) {
                if (maze.getWallSum(currentRow - 1, currentCol) == 4) {
                    neighbourCellList.add(new int[]{ currentRow - 1 , currentCol });
                }
                if (currentCol == 0) {
                    if (maze.getWallSum(currentRow, currentCol + 1) == 4) {
                        neighbourCellList.add(new int[]{ currentRow , currentCol + 1 });
                    }
                }
                else if (currentCol == cols - 1) {
                    if (maze.getWallSum(currentRow, currentCol - 1) == 4) {
                        neighbourCellList.add(new int[]{ currentRow , currentCol - 1 });
                    }
                }
                else {
                    if (maze.getWallSum(currentRow, currentCol + 1) == 4) {
                        neighbourCellList.add(new int[]{ currentRow , currentCol + 1 });
                    }
                    if (maze.getWallSum(currentRow, currentCol - 1) == 4) {
                        neighbourCellList.add(new int[]{ currentRow , currentCol - 1 });
                    }
                }
            }
            else if (currentCol == 0) {
                if (maze.getWallSum(currentRow, currentCol + 1) == 4) {
                    neighbourCellList.add(new int[]{ currentRow , currentCol + 1 });
                }
                if (currentRow != rows - 1) {
                    if (maze.getWallSum(currentRow + 1, currentCol) == 4) {
                        neighbourCellList.add(new int[]{currentRow + 1, currentCol});
                    }
                }
                if (maze.getWallSum(currentRow - 1, currentCol) == 4) {
                    neighbourCellList.add(new int[]{ currentRow - 1 , currentCol });
                }
            }
            else if (currentCol == cols - 1) {
                if (maze.getWallSum(currentRow, currentCol - 1) == 4) {
                    neighbourCellList.add(new int[]{ currentRow , currentCol - 1 });
                }
                if (currentRow != rows - 1) {
                    if (maze.getWallSum(currentRow + 1, currentCol) == 4) {
                        neighbourCellList.add(new int[]{currentRow + 1, currentCol});
                    }
                }
                if (maze.getWallSum(currentRow - 1, currentCol) == 4) {
                    neighbourCellList.add(new int[]{ currentRow - 1 , currentCol });
                }
            }
            else {
                if (maze.getWallSum(currentRow + 1, currentCol) == 4) {
                    neighbourCellList.add(new int[]{ currentRow + 1 , currentCol });
                }
                if (maze.getWallSum(currentRow - 1, currentCol) == 4) {
                    neighbourCellList.add(new int[]{ currentRow - 1 , currentCol });
                }
                if (maze.getWallSum(currentRow, currentCol + 1) == 4) {
                    neighbourCellList.add(new int[]{ currentRow , currentCol + 1 });
                }
                if (maze.getWallSum(currentRow, currentCol - 1) == 4) {
                    neighbourCellList.add(new int[]{ currentRow , currentCol - 1 });
                }
            }
            int noOfCells = neighbourCellList.size();

            // Randomly choose 1 neighbourCell
            int[] nextNeighbour;
            if (noOfCells >= 1) {
                    nextNeighbour = neighbourCellList.get(rand.nextInt(noOfCells));
                if (nextNeighbour[0] < currentRow) {
                    maze.invertWall(currentRow, currentCol, 0);
                    maze.invertWall(nextNeighbour[0], nextNeighbour[1], 2);
                }
                else if (nextNeighbour[0] > currentRow) {
                    maze.invertWall(currentRow, currentCol, 2);
                    maze.invertWall(nextNeighbour[0], nextNeighbour[1], 0);
                }
                else if (nextNeighbour[1] < currentCol) {
                    maze.invertWall(currentRow, currentCol, 3);
                    maze.invertWall(nextNeighbour[0], nextNeighbour[1], 1);
                }
                else if (nextNeighbour[1] > currentCol) {
                    maze.invertWall(currentRow, currentCol, 1);
                    maze.invertWall(nextNeighbour[0], nextNeighbour[1], 3);
                }
                cellStack.push(maze.getCell(currentRow, currentCol));
                currentRow = nextNeighbour[0];
                currentCol = nextNeighbour[1];
                visitedCells++;
            }
            else {
                currentCell = cellStack.pop();
                currentRow = currentCell.getRow();
                currentCol = currentCell.getCol();
            }
        }
        // Set the exitCell - the most far dead end from the entry (Using Manhattan distance)
        int max = 0;
        Cell maxCell = new Cell(maze.getRows() - 1, maze.getCols()-1);
        for (int i = 0; i < maze.getRows(); i++)
            for (int j = 0; j < maze.getCols(); j++) {
                if ( maze.getCell(i, j).sumWall() == 3 && i + j > max) {
                    max = i + j;
                    maxCell = maze.getCell(i, j);
                }
            }
        maze.setExitCell(maxCell);

        return maze;
    }

    /***
     * Calculate the percentage of dead-end cell in the maze (A cell with 3 walls on)
     * @param maze a maze object
     * @return the percentage of dead-end cell
     */
    public static double deadEndPercentage(Maze maze) {
        int sumOfDeadEnd = 0;
        int rows = maze.getRows();
        int cols = maze.getCols();
        for (int row = 0; row < rows; row++)
            for (int col = 0; col < cols; col++)
                if (maze.getWallSum(row, col) == 3)
                    sumOfDeadEnd++;
        return 100.0 * sumOfDeadEnd/(rows*cols);
    }
}
