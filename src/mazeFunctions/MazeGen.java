package mazeFunctions;

import components.Cell;

import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

public class MazeGen {
    private final int rows;
    private final int cols;
    private final Cell[][] cells;
    ArrayList<Cell> distanceDeadEndStart = new ArrayList<>();
    public MazeGen(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.cells = new Cell[rows][cols];
    }

    public void createMaze() {
        for (int i = 0; i < this.rows; i++)
            for (int j = 0; j < this.cols; j++)
                this.cells[i][j] = new Cell(i,j);
    }

    public Cell[] genMaze(){
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
        Cell[] startEndCell = new Cell[2];
        startEndCell[0] = currentCell;
        System.out.println("Starting cell: " + currentRow + ", "+currentCol);
        int visitedCells = 1;

        while (visitedCells < totalCells) {
            // Find no of neighbour cell which has 4 intact walls
            ArrayList<int[]> neighbourCellList = new ArrayList<>();
            if (currentRow == 0) {
                if (cells[currentRow + 1][currentCol].sumWall() == 4) {
                    neighbourCellList.add(new int[]{ currentRow + 1 , currentCol });
                }
                if (currentCol == 0) {
                    if (cells[currentRow][currentCol + 1].sumWall() == 4) {
                        neighbourCellList.add(new int[]{ currentRow , currentCol + 1 });
                    }
                }
                else if (currentCol == this.cols - 1) {
                    if (cells[currentRow][currentCol - 1].sumWall() == 4) {
                        neighbourCellList.add(new int[]{ currentRow , currentCol - 1 });
                    }
                }
                else {
                    if (cells[currentRow][currentCol + 1].sumWall() == 4) {
                        neighbourCellList.add(new int[]{ currentRow , currentCol + 1 });
                    }
                    if (cells[currentRow][currentCol - 1].sumWall() == 4) {
                        neighbourCellList.add(new int[]{ currentRow , currentCol - 1 });
                    }
                }
            }
            else if (currentRow == this.rows - 1) {
                if (cells[currentRow - 1][currentCol].sumWall() == 4) {
                    neighbourCellList.add(new int[]{ currentRow - 1 , currentCol });
                }
                if (currentCol == 0) {
                    if (cells[currentRow][currentCol + 1].sumWall() == 4) {
                        neighbourCellList.add(new int[]{ currentRow , currentCol + 1 });
                    }
                }
                else if (currentCol == this.cols - 1) {
                    if (cells[currentRow][currentCol - 1].sumWall() == 4) {
                        neighbourCellList.add(new int[]{ currentRow , currentCol - 1 });
                    }
                }
                else {
                    if (cells[currentRow][currentCol + 1].sumWall() == 4) {
                        neighbourCellList.add(new int[]{ currentRow , currentCol + 1 });
                    }
                    if (cells[currentRow][currentCol - 1].sumWall() == 4) {
                        neighbourCellList.add(new int[]{ currentRow , currentCol - 1 });
                    }
                }
            }
            else if (currentCol == 0) {
                if (cells[currentRow][currentCol + 1].sumWall() == 4) {
                    neighbourCellList.add(new int[]{ currentRow , currentCol + 1 });
                }
                if (currentRow != this.rows - 1) {
                    if (cells[currentRow + 1][currentCol].sumWall() == 4) {
                        neighbourCellList.add(new int[]{currentRow + 1, currentCol});

                    }
                }
                if (cells[currentRow - 1][currentCol].sumWall() == 4) {
                    neighbourCellList.add(new int[]{ currentRow - 1 , currentCol });
                }
            }
            else if (currentCol == this.cols - 1) {
                if (cells[currentRow][currentCol - 1].sumWall() == 4) {
                    neighbourCellList.add(new int[]{ currentRow , currentCol - 1 });
                }
                if (currentRow != this.rows - 1) {
                    if (cells[currentRow + 1][currentCol].sumWall() == 4) {
                        neighbourCellList.add(new int[]{currentRow + 1, currentCol});
                    }
                }
                if (cells[currentRow - 1][currentCol].sumWall() == 4) {
                    neighbourCellList.add(new int[]{ currentRow - 1 , currentCol });
                }
            }
            else {
                if (cells[currentRow + 1][currentCol].sumWall() == 4) {
                    neighbourCellList.add(new int[]{ currentRow + 1 , currentCol });
                }
                if (cells[currentRow - 1][currentCol].sumWall() == 4) {
                    neighbourCellList.add(new int[]{ currentRow - 1 , currentCol });
                }
                if (cells[currentRow][currentCol + 1].sumWall() == 4) {
                    neighbourCellList.add(new int[]{ currentRow , currentCol + 1 });
                }
                if (cells[currentRow][currentCol - 1].sumWall() == 4) {
                    neighbourCellList.add(new int[]{ currentRow , currentCol - 1 });
                }
            }
            int noOfCells = neighbourCellList.size();
            System.out.println("number of cell: " + noOfCells);
            // Randomly choose 1 neighbourCell
            int[] nextNeighbour;
            if (noOfCells >= 1) {
                if (noOfCells == 1) nextNeighbour = neighbourCellList.get(0);
                else nextNeighbour = neighbourCellList.get(rand.nextInt(noOfCells));
                if (nextNeighbour[0] < currentRow) {
                    cells[currentRow][currentCol].knockWall(0);
                    cells[nextNeighbour[0]][nextNeighbour[1]].knockWall(2);
                }
                else if (nextNeighbour[0] > currentRow) {
                    cells[currentRow][currentCol].knockWall(2);
                    cells[nextNeighbour[0]][nextNeighbour[1]].knockWall(0);
                }
                else if (nextNeighbour[1] < currentCol) {
                    cells[currentRow][currentCol].knockWall(3);
                    cells[nextNeighbour[0]][nextNeighbour[1]].knockWall(1);
                }
                else if (nextNeighbour[1] > currentCol) {
                    cells[currentRow][currentCol].knockWall(1);
                    cells[nextNeighbour[0]][nextNeighbour[1]].knockWall(3);
                }
                cellStack.push(cells[currentRow][currentCol]);
                currentRow = nextNeighbour[0];
                currentCol = nextNeighbour[1];
                currentCell = cells[currentRow][currentCol];
                System.out.println("CurrentCell: " + nextNeighbour[0] + ", " + nextNeighbour[1]);
                visitedCells++;
            }
            else {
                distanceDeadEndStart.add(currentCell);
                currentCell = cellStack.pop();
                currentRow = currentCell.getRow();
                currentCol = currentCell.getCol();
            }
        }
        int temp1, max = 0;
        Cell temp2 = null;
        for (Cell c: distanceDeadEndStart) {
            temp1 = c.getRow() - startEndCell[0].getRow() + c.getCol() - startEndCell[0].getCol();
            if (temp1 > max) {
                max = temp1;
                temp2 = c;
            }
        }
        startEndCell[1] = temp2;
        return startEndCell;
    }

    public int mazeWall(int row, int col, int wallIndex) { return cells[row][col].getWall(wallIndex); }
}
