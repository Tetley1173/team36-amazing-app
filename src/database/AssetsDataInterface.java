package database;

import mazeFunctions.ImageAssetFile;

import java.util.Set;

/**
 * Interface that defines the functionality of classes that store assets into the database.
 * Adapted from the week 6 tutorial.
 * @author Shannon Tetley
 */
public class AssetsDataInterface {

    /**
     * Adds a ImageAssetFile to the database, if they are not already in the database.
     *
     * @param i ImageAssetFile to add
     */
//    void addImageFile(ImageAssetFile i);

    /**
     * Extracts a ImageAssetFile from the database and creates an instance of the class, extraction is
     * done using a name to find it in the database.
     *
     * @param name The name as a String to search for.
     * @return all details in a Person object for the name
     */
//    ImageAssetFile getImageFile(String name);

    /**
     * Gets the number of addresses in the address book.
     * Probably won't implement this one delete once confirmed ############################################
     *
     * @return size of address book.
     */
//    int getSize();

    /**
     * Deletes a Person from the address book.
     * Make sure this is done someware in the system to remove unneeded assets ################################
     * Maybe manually by the user or conditionally when a maze is deleted.
     *
     * @param name The name to delete from the address book.
     */
//    void deleteImageFile(String name);

    /**
     * Finalizes any resources used by the data source and ensures data is
     * persisited.
     */
//    void close();

    /**
     * Retrieves a set of names from the data source that are used in
     * the name list.
     *
     * @return set of names.
     */
//    Set<String> nameSet();
}
