package mazeFunctions;

import java.awt.image.BufferedImage;

public class ImageAssetFile {

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
}
