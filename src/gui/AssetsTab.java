package gui;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

// Used for testing, delete when done !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
import static collections.HelperMethods.getExtension;
import static collections.Main.loadedMockImageFile;
import static collections.Main.mockImageObject;

/**
 * Defines what is rendered on the Assets Tab in the user interface.
 * @author Shannon Tetley
 */
public class AssetsTab extends JFrame {
    // Panel
    private final JPanel assetPanel;
    // Buttons
    private final JButton selectEntryImage,selectExitImage,logo1stImage,logo2ndImage;
    private final GridBagConstraints constraints = new GridBagConstraints();

    private final JLabel entryLabel = new JLabel();
    private final JLabel exitLabel = new JLabel();
    private final JLabel logo1Label = new JLabel();
    private final JLabel logo2Label = new JLabel();

    private final ImageIcon entryIcon = new ImageIcon();
    private final ImageIcon exitIcon = new ImageIcon();
    private final ImageIcon logo1Icon = new ImageIcon();
    private final ImageIcon logo2Icon = new ImageIcon();


    /**
     * Constructor that defines the contents of the Assets tab.
     * @param tabbedPane is the JTabbedPane that this content will be rendered in.
     * @author Shannon Tetley
     */
    public AssetsTab(JTabbedPane tabbedPane) {

        // Error catch to check that this is being called with a JTabbedPane as its argument.
        // Consider whether that should happen here or in the method that calls it.

        // Create a panel that goes into the Assets tab.
        assetPanel = new JPanel(new GridBagLayout());
        assetPanel.setBackground(Color.DARK_GRAY);
        tabbedPane.add("Assets", assetPanel);

         //Defaults
        constraints.fill = GridBagConstraints.NONE;
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.weightx = 100;
        constraints.weighty = 100;


        selectEntryImage = new JButton("Select Entry Image");
        selectExitImage = new JButton("Select Exit Image");
        logo1stImage = new JButton("Select Logo 1 Image");
        logo2ndImage = new JButton("Select Logo 2 Image");

        // This method adds all the buttons et cetera, to the assets panel.
        widgetAdder(constraints);

        selectEntryImage.addActionListener( e -> imageExplorer(entryIcon) );
        selectEntryImage.addActionListener( e -> dbImageTester());

        selectExitImage.addActionListener( e -> imageExplorer(exitIcon) );
        logo1stImage.addActionListener( e -> imageExplorer(logo1Icon) );
        logo2ndImage.addActionListener( e -> imageExplorer(logo2Icon) );

    }

    /**
     * This method opens a file explorer for the user and handles its behavior.
     * It requires an image icon, so it knows where to put the image in the interface.
     * @param icon is the ImageIcon that a scaled version of the selected image will be put into.
     * @return BufferedImage c which is the image selected by the user. Returns null if an error is thrown.
     */
    private BufferedImage imageExplorer(ImageIcon icon) {
        final int maxWidth = 220;
//        final int minWidth = 10;
        final int maxHeight = 220;
//        final int minHeight = 10;

        FileDialog fd = new FileDialog(new JFrame(), "Select Image");
        // Set a filter
        fd.setFile("*.png"); // Note that this causes a minor error in the interface, please document this. ############
        fd.setMultipleMode(false);

        fd.setVisible(true);
        File[] f = fd.getFiles();
        // Check the file got loaded/exists. File length of 0 implies that no file was loaded.
        if(f.length > 0){
            String aPath = fd.getFiles()[0].getAbsolutePath();
//            System.out.println(aPath);
            BufferedImage c;
            try {
                c = ImageIO.read(new File(aPath));
                // Once used for checking the size of the image.
//                int cWidth = c.getWidth();
//                int cHeight = c.getHeight();

                // Check that the file selected is a png.
                if (Objects.equals(getExtension(aPath), "png")) {
                    // Change image to a 220x220 size
                    Image scaleImage = c.getScaledInstance(maxWidth, maxHeight,  java.awt.Image.SCALE_SMOOTH);

                    icon.setImage(scaleImage);
                    assetPanel.repaint();
                    // Return the image, so it can be stored in the database and as an asset object.
                    return c;
                }
                else {
                    JOptionPane.showMessageDialog(this,
                            "Invalid image type, please select a png image.",
                            "Incorrect image type: Warning", JOptionPane.WARNING_MESSAGE);
                    return null;
                }
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this,
                        "Invalid file selected, please select an appropriate image file.",
                        "Invalid image selection: Error", JOptionPane.ERROR_MESSAGE);
                return null;
            }
        }
        else {
                JOptionPane.showMessageDialog(this,
                        "Missing image file, please select an appropriate png image file.",
                        "Missing image file: Error", JOptionPane.ERROR_MESSAGE);
                return null;
        }

    }

    // This method allows the user to select an image with the imageExplorer method. It then puts the returned image into
    // the database and an asset object.
    private void userSelectsImage(ImageIcon preview, String assetType) {

        BufferedImage asset = imageExplorer(preview);

        if (asset != null) {
            // set make a volatile(for now) ImageAsset
            // associate it with a ImageAssetFile that gets put into the database
            // once an asset table is made, make the image asset persistent.
        }

    }

    // Test method for learning how to load images from the database and use them.
    // Test method please delete when done ##########################################
    // Test method please delete when done ##########################################
    // Test method please delete when done ##########################################
    // Test method please delete when done ##########################################
    // Test method please delete when done ##########################################
    // Test method please delete when done ##########################################
    private void dbImageTester() {
//        logo2Icon.setImage(mockImageObject.getImageFile());
//        assetPanel.repaint();

        logo1Icon.setImage(loadedMockImageFile.getImageFile());
        assetPanel.repaint();

        /*
        check/load database table
        put selected image into logo object
        put selected image into asset object
        get connection instance
        put image in database
        load image from database
        close connection instance?
        put image into test JFrame
         */


    }

    /**
     * Helper method used for hiding repeated calls to the addToPanel and addImageLabel methods.
     *
     * @param constraints constraints for the grid bag layout.
     */
    private void widgetAdder(GridBagConstraints constraints) {
        // Add the elements defined above to the Asset panel that's inside the Assets tab.
        addToPanel(assetPanel, selectEntryImage, constraints,0,0,2,1);
        addImageLabel(entryLabel, entryIcon,2,0,1,1);

        addToPanel(assetPanel, selectExitImage, constraints,0,1,2,1);
        addImageLabel(exitLabel, exitIcon,2,1,1,1);

        addToPanel(assetPanel, logo1stImage, constraints,0,2,2,1);
        addImageLabel(logo1Label, logo1Icon,2,2,1,1);

        addToPanel(assetPanel, logo2ndImage, constraints,0,3,2,1);
        addImageLabel(logo2Label, logo2Icon,2,3,1,1);
    }

    /**
     * Method that loads an image then passes the object to the addToPanel
     * method to render it on the screen. This method will catch the IOException while loading the image.
     * If it does so the creation of an image label is cancelled and an error window will pop up.
     *
     * @param label label that will contain the image icon.
     * @param icon  image icon that holds a loaded image.
     * @param x     the x grid position of the image.
     * @param y     the y grid position of the image.
     * @param w     number of grid positions the image occupies width wise.
     * @param h     number of grid positions the image occupies height wise.
     * @author Shannon Tetley
     */
    private void addImageLabel(JLabel label, ImageIcon icon, int x, int y, int w, int h) {

        // ImageIO.read() requires error catching, or it throws an error.
        try {
            String defaultImagePath = "src/gui/imageAssets/DefaultImageSelection200x200.jpg";
            BufferedImage c = ImageIO.read(new File(defaultImagePath));
            icon.setImage(c);
            label.setIcon(icon);
            addToPanel(assetPanel, label,constraints,x,y,w,h);
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

}
