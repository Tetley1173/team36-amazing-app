package collections;
import gui.UserInterface;

public class Main {

    /**
     * Entry point for the program. The user interface is called from here.
     * @param args Accepts an array of strings that can be used to change startup settings for the app.
     * @author Shannon Tetley, (Everyone put your name here I'm sure we will all work on this one)
     */
    public static void main (String[] args){

        // Start the user interface.
        //mainInterface();
        UserInterface frame = new UserInterface();
        frame.setVisible(true);

    }


}
