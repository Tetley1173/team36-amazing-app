package mazeFunctions;

import components.Cell;

import java.util.*;

public class MazeSolution {
    private final Queue<Cell> queue =new LinkedList<>();
//    private final List<Map.Entry<Cell, Cell>> queue2 = new ArrayList<>();
    ArrayList<Cell> optimalPath = new ArrayList<>();
    private Cell currentCell, goal;

    public MazeSolution(Maze maze) {
        Solve(maze);
        computeOptimalPath(maze);
    }
    private void Solve(Maze maze) {
        ArrayList<Cell> validNeighbourCells = new ArrayList<>(4);
        currentCell = maze.getEntryCell();
        currentCell.setVisited(true);
        queue.offer(currentCell);
        while(currentCell != maze.getExitCell()) {
            currentCell = queue.poll();
//            System.out.println(currentCell.getRow() + ", " + currentCell.getCol() + ", " + currentCell.getDistance() + "\n");
            int currentRow = currentCell.getRow();
            int currentCol = currentCell.getCol();
            if (currentRow == 0) {
                if (currentCell.getWall(2) == 0) {
                    validNeighbourCells.add(maze.getCell(currentRow + 1 , currentCol));
                }
                if (currentCol == 0) {
                    if (currentCell.getWall(1) == 0) {
                        validNeighbourCells.add(maze.getCell(currentRow, currentCol + 1));
                    }
                }
                else if (currentCol == maze.getCols() - 1) {
                    if (currentCell.getWall(3) == 0) {
                        validNeighbourCells.add(maze.getCell(currentRow, currentCol - 1));
                    }
                }
                else {
                    if (currentCell.getWall(1) == 0) {
                        validNeighbourCells.add(maze.getCell(currentRow, currentCol + 1));
                    }
                    if (currentCell.getWall(3) == 0) {
                        validNeighbourCells.add(maze.getCell(currentRow, currentCol - 1));
                    }
                }
            }
            else if (currentRow == maze.getRows() - 1) {
                if (currentCell.getWall(0) == 0) {
                    validNeighbourCells.add(maze.getCell(currentRow - 1 , currentCol));
                }
                if (currentCol == 0) {
                    if (currentCell.getWall(1) == 0) {
                        validNeighbourCells.add(maze.getCell(currentRow, currentCol + 1));
                    }
                }
                else if (currentCol == maze.getCols() - 1) {
                    if (currentCell.getWall(3) == 0) {
                        validNeighbourCells.add(maze.getCell(currentRow, currentCol - 1));
                    }
                }
                else {
                    if (currentCell.getWall(1) == 0) {
                        validNeighbourCells.add(maze.getCell(currentRow, currentCol + 1));
                    }
                    if (currentCell.getWall(3) == 0) {
                        validNeighbourCells.add(maze.getCell(currentRow, currentCol - 1));
                    }
                }
            }
            else if (currentCol == 0) {
                if (currentCell.getWall(1) == 0) {
                    validNeighbourCells.add(maze.getCell(currentRow, currentCol + 1));
                }
                if (currentRow != maze.getRows() - 1) {
                    if (currentCell.getWall(2) == 0) {
                        validNeighbourCells.add(maze.getCell(currentRow + 1 , currentCol));
                    }
                }
                if (currentCell.getWall(0) == 0) {
                    validNeighbourCells.add(maze.getCell(currentRow - 1 , currentCol));
                }
            }
            else if (currentCol == maze.getCols() - 1) {
                if (currentCell.getWall(3) == 0) {
                    validNeighbourCells.add(maze.getCell(currentRow, currentCol - 1));
                }
                if (currentRow != maze.getRows() - 1) {
                    if (currentCell.getWall(2) == 0) {
                        validNeighbourCells.add(maze.getCell(currentRow + 1 , currentCol));
                    }
                }
                if (currentCell.getWall(0) == 0) {
                    validNeighbourCells.add(maze.getCell(currentRow - 1 , currentCol));
                }
            }
            else {
                if (currentCell.getWall(2) == 0) {
                    validNeighbourCells.add(maze.getCell(currentRow + 1 , currentCol));
                }
                if (currentCell.getWall(0) == 0) {
                    validNeighbourCells.add(maze.getCell(currentRow - 1 , currentCol));
                }
                if (currentCell.getWall(1) == 0) {
                    validNeighbourCells.add(maze.getCell(currentRow, currentCol + 1));
                }
                if (currentCell.getWall(3) == 0) {
                    validNeighbourCells.add(maze.getCell(currentRow, currentCol - 1));
                }
            }
            for (Cell c : validNeighbourCells) {
                if (c.getDistance() == -1 && !c.getVisited()) {
                    c.setDistance(currentCell.getDistance() + 1);
                    c.setParent(currentCell);
                    c.setVisited(true);
                    queue.offer(c);
                    if (c == maze.getExitCell()) goal = c;
                }
            }
        }
    }
    private void computeOptimalPath(Maze maze){
        currentCell = goal;
        while( currentCell!= maze.getEntryCell()) {
            System.out.println(currentCell.getRow() + ", " + currentCell.getCol() + ", " + currentCell.getDistance() + "\n");
            optimalPath.add(currentCell);
            currentCell = currentCell.getParent();
        }
    }
    public ArrayList<Cell> getOptimalPath() { return optimalPath; }
}
