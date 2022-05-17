package mazeFunctions;

public class ImageAssetFile {

    // Set once asset is in the database.
    private String uniqueKey = null;
    // A reference to an image in the ImageAssetFile table.
    private String imageFile = "change to image literal file";
    private String name = "set this with the constructor";

    /**
     * No args constructor.
     */
    public ImageAssetFile() {
    }

    /**
     * Constructor to set values for the assets details.
     * @param type the type of asset this is. Options are logo1, logo2, image1, image2. (names need updating)
     */
    public ImageAssetFile(String name, String type) {
        this.uniqueKey = "code to set the unique ID";
        this.imageFile = "the file to be added to the database.";
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
    public String getImageFile() {
        return imageFile;
    }
    /**
     *
     * @param imageFile
     */
    public void setImageFile(String imageFile) {
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
}
