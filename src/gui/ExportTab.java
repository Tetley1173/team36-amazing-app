package gui;

import javax.swing.*;
import javax.swing.BorderFactory;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

/**
 * User Stories:
 *
 *  When I export a maze, I want to be able to have the option
 *  of whether to export the maze with the solution or not:
 *
 *  When I export a maze, I want to be able to export multiple mazes
 *  at once:
 *
 *  When I export a maze, I want it to be exported to a size relative
 *  to the dimensions of the maze:
 *
 *  When I export a maze, I want control of where I can save the file.
 *
 *  @author Georgia Meszaros Simon
 */

public class ExportTab extends JFrame{
    private final JPanel layOut;
    private final GridBagConstraints c = new GridBagConstraints();

    // Title for tab
    private final TitledBorder titlelabel;
    private final JLabel header1;
    // Buttons
    private final JRadioButton solution1, solution2;
    private final JRadioButton size1, size2;
    private final JRadioButton file1, file2;
    private final JButton quickSave, export;
    // Dialog window
    private JDialog dialogWin;

    public ExportTab(JTabbedPane tabbedPane) {
        layOut = new JPanel(new GridBagLayout());
        tabbedPane.add("Export",layOut);
        titlelabel = BorderFactory.createTitledBorder("Export Configuration");
        layOut.setBorder(titlelabel);

        // Default settings
        c.weightx = 1;
        c.weighty = 1;
        c.fill = GridBagConstraints.NONE; // Don't fill components at all:

        header1 = new JLabel("Export with solution?");
        solution1 = new JRadioButton("With Solution");
        solution2 = new JRadioButton("Without Solution");
        ButtonGroup g1 = new ButtonGroup(); // Add to group so only 1 radio button can be selected
        g1.add(solution1);
        g1.add(solution2);
        size1 = new JRadioButton("800x800px");
        size2 = new JRadioButton("400x400px");
        ButtonGroup g2 = new ButtonGroup(); // Add to group so only 1 radio button can be selected
        g2.add(size1);
        g2.add(size2);
        file1 = new JRadioButton("Export as PDF?");
        file2 = new JRadioButton("Export as PNG?");
        ButtonGroup g3 = new ButtonGroup(); // Add to group so only 1 radio button can be selected
        g3.add(file1);
        g3.add(file2);
        quickSave = new JButton("Quick Save");
        export = new JButton("Export");
        // Call method
        Adder(c);
    }

    private void Adder(GridBagConstraints c) {
        AddPanel(layOut, header1, c, 0, 0, 0, 1);
        AddPanel(layOut, solution1, c, 0, 1, 0, 1);
        AddPanel(layOut, solution2, c, 0, 2, 0,1 );
        AddPanel(layOut, size1, c, 0, 3, 0,1 );
        AddPanel(layOut, size2, c, 0, 4, 0,1 );
        AddPanel(layOut, file1, c, 0, 5, 0,1 );
        AddPanel(layOut, file2, c, 0, 6, 0,1 );

        AddPanel(layOut, quickSave, c, 0, 7, 1,1 );
        AddPanel(layOut, export, c, 1, 7, 1,1 );

    }
    private void AddPanel(JPanel jp, Component com, GridBagConstraints c, int x,
                          int y, int w, int h) {
        c.gridx = x;
        c.gridy = y;
        c.gridwidth = w;
        c.gridheight = h;
        jp.add(com, c);
    }
}
