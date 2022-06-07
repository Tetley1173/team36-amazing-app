package collections;
import gui.UserInterface;
import database.ImageAssetDataSource;

public class Main {
    // Create the image asset table.
    public static ImageAssetDataSource assetsTable = new ImageAssetDataSource();
    public static UserInterface frame;

    /**
     * Entry point for the program. The user interface is called from here.
     * @param args Accepts an array of strings that can be used to change startup settings for the app.
     * @author Shannon Tetley, Dilshan Perera, Eric, Gorgia
     */
    public static void main (String[] args){

        // Start the user interface.
        frame = new UserInterface();
        frame.setVisible(true);

    }

}
