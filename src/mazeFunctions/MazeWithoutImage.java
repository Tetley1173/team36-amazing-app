package mazeFunctions;

/***
 * This class extends the abstract maze class which is used for creating a maze object without entry and exit image
 *
 * @author Eric Ng
 */
public class MazeWithoutImage extends Maze{
    /***
     * Constructor of MazeWithoutImage
     * This is the class for mazes without image entry/exit (default maze class)
     * @param rows the number of rows (height) of the maze
     * @param cols the number of columns (width) of the maze
     */
    public MazeWithoutImage(int rows, int cols) {
        super(rows, cols);
    }



}
