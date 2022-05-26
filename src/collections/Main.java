package collections;
import gui.UserInterface;
import mazeFunctions.ImageAssetFile;
import database.ImageAssetDataSource;
import database.AssetsDataInterface;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Main {

    public static ImageAssetFile mockImageObject;
    public static ImageAssetFile loadedMockImageFile;

    /**
     * Entry point for the program. The user interface is called from here.
     * @param args Accepts an array of strings that can be used to change startup settings for the app.
     * @author Shannon Tetley, (Everyone put your name here I'm sure we will all work on this one)
     */
    public static void main (String[] args){

        // Create the image asset table.
        ImageAssetDataSource assetsTable = new ImageAssetDataSource();
        // Start the user interface.
        //mainInterface();
        UserInterface frame = new UserInterface();
        frame.setVisible(true);


        // Test code to see if the database works
        BufferedImage c = null;
        try {
            c = ImageIO.read(new File("src/gui/imageAssets/DatabaseTest.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        mockImageObject = new ImageAssetFile("databaseTester", c);

        System.out.print("ImageAssetFile name:  " + mockImageObject.getName());
        // next step is to make this image go into the database.
        assetsTable.addImageFile(mockImageObject);
        // then load it from the database and display in the assetsTab.
        loadedMockImageFile = assetsTable.getImageFile("databaseTester");
        System.out.println("loaded mock image name:" + loadedMockImageFile.getName());

    }

}
