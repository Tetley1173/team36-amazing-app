package gui;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Defines what is rendered on the Assets Tab in the user interface.
 * @author Shannon Tetley
 */
public class AssetsTab extends JFrame {
    // Panel
    private final JPanel assetPanel;
    // Buttons
    private final JButton selectEntryImage,selectExitImage,logo1stImage,logo2ndImage;

    /**
     * Method that defines the contents of the Assets tab. Is called by the UserInterfaceClass.
     * @param tabbedPane is the JTabbedPane that this content will be rendered in.
     * @author Shannon Tetley
     */
    public AssetsTab(JTabbedPane tabbedPane) {
        // Error catch to check that this is being called with a JTabbedPane as its argument.
        // Consider weather that should happen here or in the method that calls it.

        // Create a panel that goes into the Assets tab.
        assetPanel = new JPanel(new GridBagLayout());
        tabbedPane.add("Assets", assetPanel);

        // Define the constraints for the grid bag layout.
        GridBagConstraints constraints = new GridBagConstraints();
        //Defaults
        constraints.fill = GridBagConstraints.NONE;
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.weightx = 100;
        constraints.weighty = 100;


        selectEntryImage = new JButton("Select Entry Image");
        selectExitImage = new JButton("Select Exit Image");
        logo1stImage = new JButton("Select Logo 1 Image");
        logo2ndImage = new JButton("Select Logo 2 Image");

        String defaultImagePath = "src/gui/imageAssets/DefaultImageSelection200x200.jpg";
        String entryImagePath = defaultImagePath;
        String exitImagePath = defaultImagePath;
        String logo1ImagePath = defaultImagePath;
        String logo2ImagePath = defaultImagePath;

        // Add the elements defined above to the Asset panel that's inside the Assets tab.
        addToPanel(assetPanel, selectEntryImage,constraints,0,0,2,1);
        addImageLabel(assetPanel, entryImagePath, constraints,2,0,1,1);

        addToPanel(assetPanel, selectExitImage,constraints,0,1,2,1);
        addImageLabel(assetPanel, exitImagePath, constraints,2,1,1,1);

        addToPanel(assetPanel, logo1stImage,constraints,0,2,2,1);
        addImageLabel(assetPanel, logo1ImagePath, constraints,2,2,1,1);

        addToPanel(assetPanel, logo2ndImage,constraints,0,3,2,1);
        addImageLabel(assetPanel, logo2ImagePath, constraints,2,3,1,1);

    }

    /**
     * Method that loads an image then passes the object to the addToPanel
     * method to render it on the screen. This method will catch the IOException while loading the image.
     * If it does so the creation of an image label is cancelled and an error window will pop up.
     * @param jp JPanel to add the image to.
     * @param imagePath file path of the image to be loaded in string format.
     * @param constraints is an object that passes arguments used for the grid bag constraints.
     * @param x the x grid position of the image.
     * @param y the y grid position of the image.
     * @param w number of grid positions the image occupies width wise.
     * @param h number of grid positions the image occupies height wise.
     * @author Shannon Tetley
     */
    private void addImageLabel(JPanel jp, String imagePath, GridBagConstraints
            constraints, int x, int y, int w, int h) {

        // ImageIO.read() requires error catching, or it throws an error.
        try {
            BufferedImage c = ImageIO.read(new File(imagePath));
            JLabel tempPicLabel = new JLabel(new ImageIcon(c));
            addToPanel(assetPanel, tempPicLabel,constraints,x,y,w,h);
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this,
                    "Incorrect file path used to load image in the Assets tab. The affected image label will not be rendered.",
                    "Null asset image: Error", JOptionPane.ERROR_MESSAGE);
        }

    }

    /**
     *
     * A convenience method to add a component to given grid bag
     * layout locations. Code due to Cay Horstmann.
     *
     * @param c the component to add
     * @param constraints the grid bag constraints to use
     * @param x the x grid position
     * @param y the y grid position
     * @param w the grid width of the component
     * @param h the grid height of the component
     * @author Cay Horstmann
     */
    private void addToPanel(JPanel jp,Component c, GridBagConstraints
            constraints,int x, int y, int w, int h) {
        constraints.gridx = x;
        constraints.gridy = y;
        constraints.gridwidth = w;
        constraints.gridheight = h;
        jp.add(c, constraints);
    }


//    /**
//     * Method that adds content to the Assets Tab.
//     * @param p requires a JPanel object so that it knows where to render its content.
//     * @deprecated This is Shannon's original method for rendering the Assets tab. It got replaced with the AssetsTab
//     * method. Call the new method to render the Assets tab.
//     */
//    public static void assetContents(JPanel p) {
//        p.add(new JButton("Select Entry Image"));
//        p.add(new JButton("Select Exit Image"));
//        p.add(new JButton("Select Logo 1 Image"));
//        p.add(new JButton("Select Logo 2 Image"));
//    }

}
