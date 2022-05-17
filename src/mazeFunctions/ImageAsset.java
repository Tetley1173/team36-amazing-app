package mazeFunctions;

import java.util.ArrayList;

public class ImageAsset {

    // Set once asset is in the database.
    private String uniqueKey = null;
    // A reference to an image in the ImageAssetFile table.
    private ImageAssetFile imageFile = null;
    private String name = "set this with the constructor";
    // 1 of 4 possible asset types.
    private String assetType = "set this in the constructor";
    // List of mazes this asset is used with.
    private ArrayList<String> mazeAssociation = new ArrayList<>();

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
    public void setName(String n) { this.uniqueKey = n; }
    /**
     * @return the name of the image object.
     */
    public String getName() {
        return name;
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

    // Make add to database method

}
