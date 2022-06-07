package mazeFunctions;

import java.awt.image.BufferedImage;
import java.io.Serializable;

/**
 * This class is used for storing images in the database and is necessary because multiple instances
 * of the ImageAsset class can refer to a single instance of this class.
 *
 * @author Shannon Tetley
 */
public class ImageAssetFile implements Comparable<ImageAssetFile>, Serializable {

    // Set once asset is in the database.
    private String uniqueKey = null;
    // A reference to an image in the ImageAssetFile table.
    private BufferedImage imageFile;
    private String name = "set this with the constructor";

    /**
     * No args constructor.
     */
    public ImageAssetFile() {
    }

    /**
     * Constructor to set values for the assets details.
     *
     * @param name String - the name of the image file being created.
     * @param i the BufferedImage that will be put in the database.
     */
    public ImageAssetFile(String name, BufferedImage i) {
        this.uniqueKey = "code to set the unique ID";
        this.imageFile = i;
        this.name = name;
    }

    // These are planned but unimplemented methods for adding and removing the asset to the database.
//    public void addToDatabase(){
//
//    }
//
//    public void deleteFromDatabase() {
//
//    }

    /**
     * It was planned to use this field as the primary key for the database table.
     * Time constraints prevented its implementation.
     */
    public String getUniqueKey() {
        return uniqueKey;
    }
    /**
     * It was planned to use this field as the primary key for the database table.
     * Time constraints prevented its implementation.
     */
    public void setUniqueKey(String uniqueKey) {
        this.uniqueKey = uniqueKey;
    }

    /**
     * @return the imageFile object this asset is associated with.
     */
    public BufferedImage getImageFile() {
        return imageFile;
    }
    /**
     * @param imageFile the imageFile that this asset points to.
     */
    public void setImageFile(BufferedImage imageFile) {
        this.imageFile = imageFile;
    }

    /**
     * @return the name of the asset.
     */
    public String getName() {
        return name;
    }
    /**
     * @param name String - the name this asset will be given.
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
