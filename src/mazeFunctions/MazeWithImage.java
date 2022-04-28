package mazeFunctions;

import java.awt.*;

public class MazeWithImage extends Maze{
    private final int[] entryImageLocation, exitImageLocation;

    public MazeWithImage(int rows, int cols, int size, int[] entryLocation, int[] exitLocation) {
        super(rows, cols);
        entryImageLocation = entryLocation;
        exitImageLocation = exitLocation;
    }

    private void setEntryImage(Image img){}
    private void setExitImage(Image img){}
}
