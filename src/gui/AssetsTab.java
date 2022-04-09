package gui;

import javax.swing.*;

/**
 * Defines what is rendered on the Assets Tab in the user interface.
 * @author Shannon Tetley
 */
class AssetsTab extends UserInterface {

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
