package mazeFunctions;

import java.awt.image.BufferedImage;
import java.io.Serializable;

/**
 * This class creates the quickSaveFiles table that contains the images for the maze. It contains methods for loading, saving and deleting
 * data from the database. Note that this was copied from the week 6 tutorial and adapted to be compatible with this project.
 *
 * @author Dilshan Perera & Georgia Meszaros Simon
 */
public class QuickSaveFile implements Comparable<QuickSaveFile>, Serializable {

    private String mazeName;

    private String authorName;

    private String uniqueKey = null;

    private BufferedImage mazeImage = null;

    public QuickSaveFile() {}

    public QuickSaveFile(String mazeName, String authorName, BufferedImage i) {
        this.uniqueKey = "code to set the unique ID";
        this.mazeImage = i;
        this.mazeName = mazeName;
        this.authorName = authorName;
    }
    //public void insertToDatabase(){}

    //public void removeFromDatabase() {}

    /**
     *
     * @return
     */
    public String getUniqueKey() { return uniqueKey; }

    /**
     *
     * @param uniqueKey
     */
    public void setUniqueKey(String uniqueKey) { this.uniqueKey = uniqueKey; }

    /**
     *
     * @return
     */
    public BufferedImage getMazeImage() { return mazeImage; }

    /**
     *
     * @param imageFile
     */
    public void setMazeImage(BufferedImage imageFile) { this.mazeImage = mazeImage; }

    /**
     *
     * @return
     */
    public String getMazeName() { return mazeName; }

    /**
     *
     * @param mazeName
     */
    public void setMazeName(String mazeName) { this.mazeName = mazeName; }

    /**
     *
     * @return
     */
    public String getAuthorName() { return authorName; }

    /**
     *
     * @param authorName
     */
    public void setAuthorName(String authorName) { this.authorName = authorName; }

    /**
     *
     * @param other The other QuickSaveFile object to compare against.
     * @return
     */
    public int compareTo(QuickSaveFile other) { return this.mazeName.compareTo(other.mazeName); }

}