package mazeFunctions;

import java.awt.image.BufferedImage;
import java.io.Serializable;

/**
 * This class is used for storing images in the database and is necessary because multiple instances
 * of the ImageAsset class can refer to a single instance of this class.
 * @author Shannon Tetley
 */
public class ImageAssetFile implements Comparable<ImageAssetFile>, Serializable {

    // Note that it is best practice implementing a serialVersionUID here.
    // This is an example of what to do. It's not deemed necessary at this stage.
    //private static final long serialVersionUID = -7092701502990374424L;

    // Set once asset is in the database.
    private String uniqueKey = null;
    // A reference to an image in the ImageAssetFile table.
    private BufferedImage imageFile = null;
    private String name = "set this with the constructor";

    /**
     * No args constructor.
     */
    public ImageAssetFile() {
    }

    /**
     * Constructor to set values for the assets details.
     * @param //type the type of asset this is. Options are logo1, logo2, image1, image2. (names need updating)
     */
    public ImageAssetFile(String name, BufferedImage i) {
        this.uniqueKey = "code to set the unique ID";
        this.imageFile = i;
        this.name = name;
    }

    public void addToDatabase(){

    }

    public void deleteFromDatabase() {

    }

    /**
     *
      * @return
     */
    public String getUniqueKey() {
        return uniqueKey;
    }
    /**
     *
     * @param uniqueKey
     */
    public void setUniqueKey(String uniqueKey) {
        this.uniqueKey = uniqueKey;
    }

    /**
     *
     * @return
     */
    public BufferedImage getImageFile() {
        return imageFile;
    }
    /**
     *
     * @param imageFile
     */
    public void setImageFile(BufferedImage imageFile) {
        this.imageFile = imageFile;
    }

    /**
     *
     * @return
     */
    public String getName() {
        return name;
    }
    /**
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Compares this object with the specified object for order. Returns a
     * negative integer, zero, or a positive integer as this object is less than,
     * equal to, or greater than the specified object.
     * Method copied from week 6 tutorial.
     *
     * @param other The other Person object to compare against.
     * @return a negative integer, zero, or a positive integer as this object is
     *         less than, equal to, or greater than the specified object.
     * @throws ClassCastException if the specified object's type prevents it from
     *            being compared to this object.
     */
    public int compareTo(ImageAssetFile other) {
        return this.name.compareTo(other.name);
    }


}
