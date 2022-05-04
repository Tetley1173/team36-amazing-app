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
    private final String defaultImagePath = "src/gui/imageAssets/DefaultImageSelection200x200.jpg";
    private final GridBagConstraints constraints = new GridBagConstraints();

    private JLabel entryLabel = new JLabel();
    private JLabel exitLabel = new JLabel();
    private JLabel logo1Label = new JLabel();
    private JLabel logo2Label = new JLabel();

    private ImageIcon entryIcon = new ImageIcon();
    private ImageIcon exitIcon = new ImageIcon();
    private ImageIcon logo1Icon = new ImageIcon();
    private ImageIcon logo2Icon = new ImageIcon();


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


        String entryImagePath = defaultImagePath;
        String exitImagePath = defaultImagePath;
        String logo1ImagePath = defaultImagePath;
        String logo2ImagePath = defaultImagePath;

        // This method adds all the buttons et cetera, to the assets panel.
        widgetAdder(constraints, entryImagePath, exitImagePath, logo1ImagePath, logo2ImagePath);

        String explorePath = "src/gui/imageAssets";

        selectEntryImage.addActionListener( e -> imageExplorer(explorePath, entryImagePath, entryLabel, entryIcon) );
        selectExitImage.addActionListener( e -> imageExplorer(explorePath, exitImagePath, exitLabel, exitIcon) );
        logo1stImage.addActionListener( e -> imageExplorer(explorePath, logo1ImagePath, logo1Label, logo1Icon) );
        logo2ndImage.addActionListener( e -> imageExplorer(explorePath, logo2ImagePath, logo2Label, logo2Icon) );

//        selectExitImage.addActionListener( e -> JOptionPane.showMessageDialog( assetPanel,"Button worked."));
//        logo1stImage.addActionListener( e -> JOptionPane.showMessageDialog( assetPanel,"Button worked."));
//        logo2ndImage.addActionListener( e -> JOptionPane.showMessageDialog( assetPanel,"Button worked."));

    }

    private void imageExplorer(String path, Object imagePath, JLabel label, ImageIcon icon) {
        // consider turning this variable into a singleton?
        FileDialog fd = new FileDialog(new JFrame(), "Select Image");
        fd.setMultipleMode(false);
        // This line doesn't work for some reason
        fd.setBackground(Color.darkGray);
        fd.setVisible(true);
        fd.setFile(path);
        File[] f = fd.getFiles();
        if(f.length > 0){
            String aPath = fd.getFiles()[0].getAbsolutePath();
            System.out.println(aPath);
//            imagePath = aPath;

            BufferedImage c = null;
            try {
                c = ImageIO.read(new File(aPath));
                icon.setImage(c);
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this,
                        "Invalid file selected, please select an appropriate image file.",
                        "Invalid image selection: Error", JOptionPane.ERROR_MESSAGE);
            }

//            addImageLabel(assetPanel, label , icon,2,0,1,1);
        }

        imagePath = defaultImagePath;

    }

    /**
     * Helper method used for hiding repeated calls to the addToPanel and addImageLabel methods.
     * @param constraints constraints for the grid bag layout.
     * @param entryImagePath variable that contains the file path of the image being added.
     * @param exitImagePath variable that contains the file path of the image being added.
     * @param logo1ImagePath variable that contains the file path of the image being added.
     * @param logo2ImagePath variable that contains the file path of the image being added.
     */
    private void widgetAdder(GridBagConstraints constraints, String entryImagePath, String exitImagePath, String logo1ImagePath, String logo2ImagePath) {
        // Add the elements defined above to the Asset panel that's inside the Assets tab.
        addToPanel(assetPanel, selectEntryImage, constraints,0,0,2,1);
        addImageLabel(assetPanel, entryLabel, entryIcon,2,0,1,1);

        addToPanel(assetPanel, selectExitImage, constraints,0,1,2,1);
        addImageLabel(assetPanel, exitLabel, exitIcon,2,1,1,1);

        addToPanel(assetPanel, logo1stImage, constraints,0,2,2,1);
        addImageLabel(assetPanel, logo1Label, logo1Icon,2,2,1,1);

        addToPanel(assetPanel, logo2ndImage, constraints,0,3,2,1);
        addImageLabel(assetPanel, logo2Label, logo2Icon,2,3,1,1);
    }

    /**
     * Method that loads an image then passes the object to the addToPanel
     * method to render it on the screen. This method will catch the IOException while loading the image.
     * If it does so the creation of an image label is cancelled and an error window will pop up.
     * @param jp JPanel to add the image to.
     * @param label label that will contain the image icon.
     * @param icon image icon that holds a loaded image.
     * @param x the x grid position of the image.
     * @param y the y grid position of the image.
     * @param w number of grid positions the image occupies width wise.
     * @param h number of grid positions the image occupies height wise.
     * @author Shannon Tetley
     */
    private void addImageLabel(JPanel jp, JLabel label, ImageIcon icon, int x, int y, int w, int h) {

        // ImageIO.read() requires error catching, or it throws an error.
        try {
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
