package gui;

import javax.swing.*;
import javax.swing.BorderFactory;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

    // Title for tab
    private final TitledBorder titlelabel;

    // Radio buttons -> options for export features
        // Export with solution or without
    private final JRadioButton solButton1, solButton2;

        // Export pixel size
    private final JRadioButton sizeButton1, sizeButton2;

        // Export as what file format - NOT FINISHED
    private final JRadioButton pdfButton, pngButton;

    // Button for export
    private final JButton exOnly,  // => exOnly: only export - does NOT save to database
            exSave,         // => exSave: saves to database only
            exSaveAs;      // => exSaveAs: saves to database and asks user where to save file
        // Dialog Button for export
    private final JDialog dialogWin;

    public ExportTab(JTabbedPane tabbedPane) {
        layOut = new JPanel(new GridLayout());
        tabbedPane.add("Export",layOut);
        titlelabel = BorderFactory.createTitledBorder("Export Configuration");
        layOut.setBorder(titlelabel);

        /**
         *  Buttons:
         */
        solButton1 = new JRadioButton("With Solution");
        solButton2 = new JRadioButton("Without Solution");
        ButtonGroup g1 = new ButtonGroup(); // Group
        g1.add(solButton1);
        g1.add(solButton2);

        sizeButton1 = new JRadioButton("800x800px");
        sizeButton2 = new JRadioButton("400x400px");
        ButtonGroup g2 = new ButtonGroup(); // Group
        g2.add(sizeButton1);
        g2.add(sizeButton2);

        pdfButton = new JRadioButton("Export as PDF?");
        pngButton = new JRadioButton("Export as PNG?");
        ButtonGroup g3 = new ButtonGroup(); // Group
        g3.add(pdfButton);
        g3.add(pngButton);

        exOnly = new JButton("Export As only?");
        exSave = new JButton("Save Maze to database?");
        exSaveAs = new JButton("Export As - as well as save to database)?");
        exOnly.addActionListener(new ActionListener() {
            @Override   // Open export dialog window
            public void actionPerformed(ActionEvent e) {
                dialogWin.setVisible(true);
            }
        });

        /**
         *  This section for pop up window after pressing export:
         */
        JFrame f = new JFrame();
        dialogWin = new JDialog(f, "Dialog Example", true);
        dialogWin.setLayout(new GridLayout(3,1,0,50));
        dialogWin.add(new JLabel("Export Successful"));
        JButton dialogExit = new JButton("OK");
        dialogExit.setPreferredSize(new Dimension(150, 35));
        dialogWin.add(dialogExit);
        dialogWin.setSize(300,300);
        dialogExit.addActionListener(new ActionListener() {
            @Override   // Close export dialog window
            public void actionPerformed(ActionEvent e) {
                dialogWin.setVisible(false);
            }
        });

        /**
         *  Grid layout:
         */
        JPanel gridPanel = new JPanel(new GridLayout(0,1));
        gridPanel.add(new JLabel("Export with solution?"));
        gridPanel.add(solButton1);
        gridPanel.add(solButton2);
        gridPanel.add(new JLabel("Export size:"));
        gridPanel.add(sizeButton1);
        gridPanel.add(sizeButton2);
        gridPanel.add(new JLabel("Export file:"));
        gridPanel.add(pdfButton);
        gridPanel.add(pngButton);

        gridPanel.add(exOnly);
        gridPanel.add(exSave);
        gridPanel.add(exSaveAs);
        layOut.add(gridPanel, BorderLayout.WEST);
    }
}
