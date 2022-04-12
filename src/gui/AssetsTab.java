package gui;

import javax.swing.*;
import java.awt.*;

/**
 * Defines what is rendered on the Assets Tab in the user interface.
 * @author Shannon Tetley
 */
public class AssetsTab extends JFrame {
    // Panel
    private final JPanel assetPanel;
    // Buttons
    private final JButton selectEntryImage,selectExitImage,logo1stImage,logo2ndImage;

    public AssetsTab(JTabbedPane tabbedPane) {
        assetPanel = new JPanel(new FlowLayout());
        tabbedPane.add("Assets", assetPanel);
        selectEntryImage = new JButton("Select Entry Image");
        selectExitImage = new JButton("Select Exit Image");
        logo1stImage = new JButton("Select Logo 1 Image");
        logo2ndImage = new JButton("Select Logo 2 Image");
        assetPanel.add(selectEntryImage);
        assetPanel.add(selectExitImage);
        assetPanel.add(logo1stImage);
        assetPanel.add(logo2ndImage);

    }


    /**
     * Method that adds content to the Assets Tab.
     * @param p requires a JPanel object so that it knows where to render its content.
     */
    public static void assetContents(JPanel p) {
        p.add(new JButton("Select Entry Image"));
        p.add(new JButton("Select Exit Image"));
        p.add(new JButton("Select Logo 1 Image"));
        p.add(new JButton("Select Logo 2 Image"));
    }

}
