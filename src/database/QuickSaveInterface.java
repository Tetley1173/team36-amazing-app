package database;

import mazeFunctions.QuickSaveFile;
import java.util.Set;

/**
 * Interface that defines the functionality of classes that store mazes into the database.
 * @author Dilshan Perera and Georgia Meszaros simon
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
     * Finalizes any resources used by the data source and ensures data is
     * persisited.
     */
    void close();



}







