package mazeFunctions;

import components.Cell;

import java.util.*;

/***
 * This class is used for all solution-related methods for a maze
 *
 * @author Eric Ng
 */
public class MazeSolution {
    private Queue<Cell> queue = new LinkedList<>();
    ArrayList<Cell> optimalPath = new ArrayList<>();
    private Cell goal = null;
    private boolean hasSolution;
    private double pathPercentage = -1;

    /***
     * Constructor of MazeSolution.
     * @param maze a maze object
     */
    public MazeSolution(Maze maze) {
        Solve(maze);
        computeOptimalPath(maze);
    }
    private void Solve(Maze maze) {
        ArrayList<Cell> validNeighbourCells = new ArrayList<>();
        maze.getEntryCell().setVisited(true);
        Cell currentCell = maze.getEntryCell();
        currentCell.setVisited(true);
        queue.offer(currentCell);
        while(currentCell != maze.getExitCell()) {

            try {
                currentCell = queue.poll();
                int currentRow = currentCell.getRow();
                int currentCol = currentCell.getCol();
                if (currentRow == 0) {
                    if (currentCell.getWall(2) == 0) {
                        validNeighbourCells.add(maze.getCell(currentRow + 1, currentCol));
                    }
                    if (currentCol == 0) {
                        if (currentCell.getWall(1) == 0) {
                            validNeighbourCells.add(maze.getCell(currentRow, currentCol + 1));
                        }
                    } else if (currentCol == maze.getCols() - 1) {
                        if (currentCell.getWall(3) == 0) {
                            validNeighbourCells.add(maze.getCell(currentRow, currentCol - 1));
                        }
                    } else {
                        if (currentCell.getWall(1) == 0) {
                            validNeighbourCells.add(maze.getCell(currentRow, currentCol + 1));
                        }
                        if (currentCell.getWall(3) == 0) {
                            validNeighbourCells.add(maze.getCell(currentRow, currentCol - 1));
                        }
                    }
                } else if (currentRow == maze.getRows() - 1) {
                    if (currentCell.getWall(0) == 0) {
                        validNeighbourCells.add(maze.getCell(currentRow - 1, currentCol));
                    }
                    if (currentCol == 0) {
                        if (currentCell.getWall(1) == 0) {
                            validNeighbourCells.add(maze.getCell(currentRow, currentCol + 1));
                        }
                    } else if (currentCol == maze.getCols() - 1) {
                        if (currentCell.getWall(3) == 0) {
                            validNeighbourCells.add(maze.getCell(currentRow, currentCol - 1));
                        }
                    } else {
                        if (currentCell.getWall(1) == 0) {
                            validNeighbourCells.add(maze.getCell(currentRow, currentCol + 1));
                        }
                        if (currentCell.getWall(3) == 0) {
                            validNeighbourCells.add(maze.getCell(currentRow, currentCol - 1));
                        }
                    }
                } else if (currentCol == 0) {
                    if (currentCell.getWall(1) == 0) {
                        validNeighbourCells.add(maze.getCell(currentRow, currentCol + 1));
                    }
                    if (currentRow != maze.getRows() - 1) {
                        if (currentCell.getWall(2) == 0) {
                            validNeighbourCells.add(maze.getCell(currentRow + 1, currentCol));
                        }
                    }
                    if (currentCell.getWall(0) == 0) {
                        validNeighbourCells.add(maze.getCell(currentRow - 1, currentCol));
                    }
                } else if (currentCol == maze.getCols() - 1) {
                    if (currentCell.getWall(3) == 0) {
                        validNeighbourCells.add(maze.getCell(currentRow, currentCol - 1));
                    }
                    if (currentRow != maze.getRows() - 1) {
                        if (currentCell.getWall(2) == 0) {
                            validNeighbourCells.add(maze.getCell(currentRow + 1, currentCol));
                        }
                    }
                    if (currentCell.getWall(0) == 0) {
                        validNeighbourCells.add(maze.getCell(currentRow - 1, currentCol));
                    }
                } else {
                    if (currentCell.getWall(2) == 0) {
                        validNeighbourCells.add(maze.getCell(currentRow + 1, currentCol));
                    }
                    if (currentCell.getWall(0) == 0) {
                        validNeighbourCells.add(maze.getCell(currentRow - 1, currentCol));
                    }
                    if (currentCell.getWall(1) == 0) {
                        validNeighbourCells.add(maze.getCell(currentRow, currentCol + 1));
                    }
                    if (currentCell.getWall(3) == 0) {
                        validNeighbourCells.add(maze.getCell(currentRow, currentCol - 1));
                    }
                }
            } catch (Exception e) {

            } finally {
                if (validNeighbourCells.isEmpty()) break;
                else {
                    for (Cell c : validNeighbourCells) {

                        if (c.getDistance() == -1 && !c.isVisited()) {
                            c.setDistance(currentCell.getDistance() + 1);
                            c.setParent(currentCell);
                            c.setVisited(true);
                            queue.offer(c);
                            if (c == maze.getExitCell()) {
                                hasSolution = true;
                                goal = c;
                                break;
                            }
                        }
                    }
                    validNeighbourCells.clear();

                }

            }


        }
        for (int row = 0; row < maze.getRows(); row++)
            for (int col = 0; col < maze.getCols(); col++) {
                if( maze.getCell(row, col).isVisited() ) maze.getCell(row, col).setVisited(false);
                if( maze.getCell(row, col).getDistance() != -1 ) maze.getCell(row, col).setDistance(-1);
            }
    }
    private void computeOptimalPath(Maze maze){
        if (goal == null) {
            hasSolution = false;
        }
        else {
            hasSolution = true;
            Cell currentCell = goal;
            while( currentCell!= maze.getEntryCell()) {
//                System.out.println(currentCell.getRow() + ", " + currentCell.getCol() + ", " + currentCell.getDistance());
                optimalPath.add(currentCell);
                currentCell = currentCell.getParent();
            }
            optimalPath.add(maze.getEntryCell());
            pathPercentage = optimalPath.size() / (double)(maze.getRows() * maze.getCols()) * 100;
        }


    }

    /***
     * Get the optimal path of a maze
     * @return an ArrayList with elements of cell object. The ArrayList represents the optimal path of the maze
     */
    public ArrayList<Cell> getOptimalPath() { return optimalPath; }

    /***
     * Get the percentage of the optimal path in the maze
     * The equation to get the percentage:
     *              percentage = number of cell in the optimal path / total cell in maze * 100 %
     * @return the percentage of the optimal path
     */
    public double getPathPercentage() { return pathPercentage; }

    /***
     *
     * @return true if the mazeSolution object has a solution, false if it hasn't
     */
    public boolean isHasSolution() { return hasSolution; }
}
