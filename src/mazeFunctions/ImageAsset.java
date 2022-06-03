package mazeFunctions;

import javax.swing.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * This class is for keeping track of the images used in the maze as logos etc.
 * @author Shannon Tetley
 */
public class ImageAsset {

    // Set once asset is in the database.
    private String uniqueKey;
    // A reference to an image in the ImageAssetFile table.
    private ImageAssetFile imageFile;
    private String name;
    // 1 of 4 possible asset types. entry, exit, logo1, and logo2.
    private String assetType;
    // List of mazes this asset is used with.
    private ArrayList<String> mazeAssociation = new ArrayList<>();

    // Maze related variables.
    private int row, col;
    private int height = -1;
    private int width = -1;

    // Add methods to check if the file is in the database already then load that one instead.
    // Constructor
    /**
     * No args constructor.
     */
    public ImageAsset() {
    }

    /**
     * Constructor to set values for the assets details.
     * @param name the name of the asset instance.
     * @param type the type of asset this is. Options are logo1, logo2, image1, image2. (names need updating)
     * @param file the file used for this asset, check that a duplicate isn't being created, reference an existing file if possible.
     */
    public ImageAsset(String name, String type, ImageAssetFile file) {
        // Add code to find file name and set it
        this.imageFile = file;
        this.name = name;
        this.assetType = type;
    }

    /**
     * @param k set the uniqueKey
     */
    public void setUniqueKey(String k) { this.uniqueKey = k; }
    /**
     * @return the uniqueKey
     */
    public String getUniqueKey() {
        return uniqueKey;
    }

    /**
     * @param n set the name of the image object.
     */
    public void setName(String n) { this.name = n; }
    /**
     * @return the name of the image object.
     */
    public String getName() {
        return name;
    }

    /**
     * @param f set the name of the image object.
     */
    public void setImageFile(ImageAssetFile f) { this.imageFile = f; }
    /**
     * @return the ImageAssetFile of the image object.
     */
    public ImageAssetFile getImageFile() {
        return imageFile;
    }

    /**
     * Set the asset that the ImageAssetFile will hold.
     *
     * @param b BufferedImage to save in this object.
     */
    public  void setAsset(BufferedImage b) { imageFile.setImageFile(b); }
    /**
     * Extracts the BufferedImage object held by the ImageAssetFile associated with this object.
     * @return BufferedImage that this object represents.
     */
    public BufferedImage getAsset() {
//        ImageAssetFile temp = new ImageAssetFile();
//        temp = this.getImageFile();
        return imageFile.getImageFile();
    }

    /**
     * @param t set the type of asset the object is.
     */
    public void setAssetType(String t) { this.assetType = t; }
    /**
     * @return the asset objects type.
     */
    public String getAssetType() {
        return assetType;
    }

    /**
     * @param a add an association with a maze name.
     */
    public void addMazeAssociation(String a) { this.mazeAssociation.add(a); }
    /**
     * Deletes an association with a maze name.
     */
    public void deleteMazeAssociation(String d) { this.mazeAssociation.remove(d); }
    /**
     * @return the ArrayList containing all the mazes associated with this object.
     */
    public ArrayList<String> listMazeAssociation() { return this.mazeAssociation; }

    /**
     * @param r set the name of the image object.
     */
    public void setRow(int r) { this.row = r; }
    /**
     * @return the name of the image object.
     */
    public int getRow() {
        return row;
    }

    /**
     * @param c set the name of the image object.
     */
    public void setCol(int c) { this.col = c; }
    /**
     * @return the name of the image object.
     */
    public int getCol() {
        return col;
    }

    /**
     *
     * @param h set the name of the image object.
     */
    public void setHeight(int h) { this.height = h; }
    /**
     * @return the name of the image object.
     */
    public int getHeight() {
        return height;
    }

    /**
     * @param w set the name of the image object.
     */
    public void setWidth(int w) { this.width = w; }
    /**
     * @return the name of the image object.
     */
    public int getWidth() {
        return width;
    }

    // Stretch gaol make add to database method

}
