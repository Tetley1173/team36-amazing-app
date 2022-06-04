package database;

import mazeFunctions.QuickSaveFile;
import java.util.Set;

/**
 * Interface that defines the functionality of classes that store mazes into the database.
 * @author Dilshan Perera & Georgia Meszaros simon
 */

public interface QuickSaveInterface {

    /**
     * Adds an QuickSaveFile to the database, if they are not already in the database.
     *
     * @param i QuickSaveFile to add
     */
    void AddQuickSave(QuickSaveFile i);

    /**
     * Extracts a QuickSaveFile from the database and creates an instance of the class, extraction is
     * done using a name to find it in the database.
     *
     * @param name The name(string) to search for.
     * @return all details in a Person object for the name
     */
    QuickSaveFile getMazeImage(String name);

    /**
     * Gets the number of addresses in the address book.
     * @return size of address book.
     */
    int getSize();

    /**
     * Deletes a Person from the address book.
     * Make sure this is done somewhere in the system to remove unneeded assets ################################
     * Maybe manually by the user or conditionally when a maze is deleted.
     *
     * @param name The name to delete from the address book.
     */
    void deleteAsset(String name);

    /**
     * Finalizes any resources used by the data source and ensures data is
     * persisited.
     */
    void close();

    /**
     * Retrieves a set of names from the data source that are used in
     * the name list.
     *
     * @return set of names.
     */
    Set<String> nameSet();

}







