package mazeFunctions;

import java.util.ArrayList;

public class mazeCollection {
    private ArrayList<Maze> mazes;
    public mazeCollection() {
        mazes = new ArrayList<>();
    }

    public ArrayList<Maze> getMazeList() { return mazes; }
    public int getNumberOfMaze() { return mazes.size(); }
    public void addMaze(Maze maze) {
        mazes.add(maze);
    }

}
