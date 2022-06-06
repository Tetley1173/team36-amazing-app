package mazeFunctions;

import java.util.ArrayList;

/***
 * This class is used for creating a collection to store different maze
 *
 * @author Eric Ng
 */
public class mazeCollection {

    private ArrayList<Maze> mazes;

    /***
     * The constructor the mazeCollection. Create an ArrayList with elements of Maze object. The ArrayList is empty by default
     */
    public mazeCollection() {
        mazes = new ArrayList<>();
    }

    /***
     * Get the ArrayList that stores the maze
     * @return the ArrayList that stores the maze
     */
    public ArrayList<Maze> getMazeList() { return mazes; }

    /***
     * Get the number of stored maze
     * @return the number of stored maze in the mazeCollection object
     */
    public int getNumberOfMaze() { return mazes.size(); }

    /***
     * Add a maze into the mazeCollection object
     * @param maze a maze object
     */
    public void addMaze(Maze maze) {
        mazes.add(maze);
    }

}
