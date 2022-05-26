package mazeFunctions;

import components.Cell;

import java.util.*;

public class MazeSolution {
    private final Queue<Cell> queue =new LinkedList<>();
    private Cell current;

    public MazeSolution(Maze maze) {
        Solve(maze);
    }
    private void Solve(Maze maze) {
        Maze tempMaze = maze;
        current = tempMaze.getEntryCell();

    }
    private static ArrayList<Cell> computeOptimumPath(Maze maze){ return new ArrayList<>();}
}
