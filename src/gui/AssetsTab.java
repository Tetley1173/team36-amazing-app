package gui;

import mazeFunctions.ImageAsset;
import mazeFunctions.ImageAssetFile;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

import static collections.HelperMethods.getExtension;
import static collections.Main.*;

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

    public static ImageAssetFile noSelectionImage = new ImageAssetFile();
    public static ImageAsset entry = new ImageAsset("entry", "entry", noSelectionImage);
    public static ImageAsset exit = new ImageAsset("exit", "exit", noSelectionImage);
    public static ImageAsset logo1 = new ImageAsset("logo1", "logo1", noSelectionImage);
    public static ImageAsset logo2 = new ImageAsset("logo2", "logo2", noSelectionImage);




    /**
     * Constructor that defines the contents of the Assets tab.
     *
     * @param tabbedPane is the JTabbedPane that this content will be rendered in.
     * @author Shannon Tetley
     */
    @SuppressWarnings("SpellCheckingInspection")
    public AssetsTab(JTabbedPane tabbedPane) {

        // Set up the default image.
        noSelectionImage.setName("default");
        final String defaultImagePath = "src/gui/imageAssets/DefaultImageSelection200x200.png";
        BufferedImage c = null;
        try {
            c = ImageIO.read(new File(defaultImagePath));
        } catch (IOException e) {
            // The file path is hard coded therefore it shouldn't throw an error when running the read method.
            e.printStackTrace();
        }
        noSelectionImage.setImageFile(c);

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

        selectEntryImage.addActionListener( e -> imageExplorer(entryIcon, entry) );
        selectExitImage.addActionListener( e -> imageExplorer(exitIcon, exit) );
        logo1stImage.addActionListener( e -> imageExplorer(logo1Icon, logo1) );
        logo2ndImage.addActionListener( e -> imageExplorer(logo2Icon, logo2) );

    }

    // This method opens a file explorer for the user and handles its behavior.
    // It requires an image icon, so it knows where to put the image in the interface.
    @SuppressWarnings("UnnecessaryReturnStatement")
    private void imageExplorer(ImageIcon icon, ImageAsset asset) {
        final int maxWidth = 220;
        final int maxHeight = 220;

        FileDialog fd = new FileDialog(new JFrame(), "Select Image");
        // Set a filter
        fd.setFile("*.png");
        fd.setMultipleMode(false);

        fd.setVisible(true);
        File[] f = fd.getFiles();
        // Check the file got loaded/exists. File length of 0 implies that no file was loaded.
        if(f.length > 0){
            String aPath = fd.getFiles()[0].getAbsolutePath();
            BufferedImage c;
            try {
                c = ImageIO.read(new File(aPath));

                // Check that the file selected is a png.
                if (Objects.equals(getExtension(aPath), "png") || Objects.equals(getExtension(aPath), "PNG")) {
                    // Change image to a 220x220 size
                    Image scaleImage = c.getScaledInstance(maxWidth, maxHeight,  java.awt.Image.SCALE_SMOOTH);

                    icon.setImage(scaleImage);
                    assetPanel.repaint();
                    userSelectsImage(asset, c);
                }
                else {
                    JOptionPane.showMessageDialog(this,
                            "Invalid image type, please select a png image.",
                            "Incorrect image type: Warning", JOptionPane.WARNING_MESSAGE);
                    return;
                }
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this,
                        "Invalid file selected, please select an appropriate image file.",
                        "Invalid image selection: Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        else {
                JOptionPane.showMessageDialog(this,
                        "Missing image file, please select an appropriate png image file.",
                        "Missing image file: Error", JOptionPane.ERROR_MESSAGE);
        }

    }

    // This method allows the user to select an image with the imageExplorer method. It then puts the returned image into
    // the database and an asset object.
    private void userSelectsImage(ImageAsset asset, BufferedImage image) {

        if (asset != null) {
            asset.setAsset(image); // put the image arg into the image file object.

            // Add the asset file to the database
            assetsTable.addImageFile(asset.getImageFile());
        }
        // Else do nothing, it's possible to be passed a null asset if the user picks non.
    }

    // Helper method used for hiding repeated calls to the addToPanel and addImageLabel methods.
    // @param constraints, constraints for the grid bag layout.
    private void widgetAdder(GridBagConstraints constraints) {
        // Add the elements defined above to the Asset panel that's inside the Assets tab.
        addToPanel(assetPanel, selectEntryImage, constraints,0,0,2,1);
        addImageLabel(entryLabel, entryIcon, entry,2,0,1,1);

        addToPanel(assetPanel, selectExitImage, constraints,0,1,2,1);
        addImageLabel(exitLabel, exitIcon, exit,2,1,1,1);

        addToPanel(assetPanel, logo1stImage, constraints,0,2,2,1);
        addImageLabel(logo1Label, logo1Icon, logo1,2,2,1,1);

        addToPanel(assetPanel, logo2ndImage, constraints,0,3,2,1);
        addImageLabel(logo2Label, logo2Icon, logo2,2,3,1,1);
    }

    /**
     * Sets up the preview images and loads the default images (assuming they haven't been changed from their default value).
     *
     * @param label label that will contain the image icon.
     * @param icon  image icon that holds a loaded image.
     * @param asset the image asset object that will populate the image icon.
     * @param x     the x grid position of the image.
     * @param y     the y grid position of the image.
     * @param w     number of grid positions the image occupies width wise.
     * @param h     number of grid positions the image occupies height wise.
     * @author Shannon Tetley
     */
    @SuppressWarnings("SameParameterValue")
    private void addImageLabel(JLabel label, ImageIcon icon, ImageAsset asset, int x, int y, int w, int h) {
        icon.setImage(asset.getAsset());
        label.setIcon(icon);
        addToPanel(assetPanel, label,constraints,x,y,w,h);
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

    /**
     * @param e set the name of the image object.
     */
    public static void setEntry(ImageAsset e) { entry = e; }
    /**
     * @return the name of the image object.
     */
    public static ImageAsset getEntry() {
        return entry;
    }

    /**
     * @param x set the name of the image object.
     */
    public static void setExit(ImageAsset x) { exit = x; }
    /**
     * @return the name of the image object.
     */
    public static ImageAsset getExit() {
        return exit;
    }

    /**
     * @param l set the name of the image object.
     */
    @SuppressWarnings("unused")
    public static void setLogo1(ImageAsset l) { logo1 = l; }
    /**
     * @return the name of the image object.
     */
    public static ImageAsset getLogo1() {
        return logo1;
    }

    /**
     * @param l set the name of the image object.
     */
    @SuppressWarnings("unused")
    public static void setLogo2(ImageAsset l) { logo2 = l; }
    /**
     * @return the name of the image object.
     */
    @SuppressWarnings("unused")
    public static ImageAsset getLogo2() {
        return logo2;
    }

}
