package gui;

import mazeFunctions.Maze;
import mazeFunctions.MazeGeneration;
import mazeFunctions.MazeWithoutImage;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class EditTab extends JFrame{
    public final static int MAZE_SETUP_PANEL_WIDTH = 200;
    public final static int MAZE_SETUP_PANEL_HEIGHT = 200;
    public final static int LOGO_PANEL_WIDTH = 200;
    public final static int LOGO_SETUP_PANEL_HEIGHT = 100;
    private final static String IMAGE_OUTPUT_FORMAT = "png";

    // Panel
    private final JPanel editTab;
    private final JPanel mazeEditPanel, setupAndInfoPanel, mazeSetupPanel, logoSetupPanel, mazeInfoPanel;

    // Buttons
    private final JButton mazeGeneration;
    private final JButton BlankGenerate;
    private final JButton EraseButton;
//    private final JButton RefreshButton;
    private final JButton ScreenShot;

    // Text field
    private final JSpinner rowDecision;
    private final JSpinner colDecision;
    private final JSpinner logoRows;
    private final JSpinner logoCols;

    // Toggle buttons
    private final JRadioButton toggleOptimumPath;
    private final JRadioButton toggleEntryExit;
    private final JRadioButton insertLogo;

    // Maze grid
    public static Maze maze;

    public EditTab(JTabbedPane tabbedPane) {
        // create the edit tab
        editTab = new JPanel(new BorderLayout());
        tabbedPane.add("Edit",editTab);

        // the maze visualization panel in the edit tab
        mazeEditPanel = new JPanel();
        mazeEditPanel.setBackground(Color.DARK_GRAY);
        mazeEditPanel.setPreferredSize(new Dimension(mazeEditPanel.getMaximumSize().height, mazeEditPanel.getMaximumSize().height));

        // the button panel in the edit tab
        setupAndInfoPanel = new JPanel(new BorderLayout());
        setupAndInfoPanel.setBackground(Color.DARK_GRAY);

        mazeSetupPanel = new JPanel(new GridBagLayout());
        mazeSetupPanel.setPreferredSize(new Dimension(MAZE_SETUP_PANEL_WIDTH, MAZE_SETUP_PANEL_HEIGHT));
        GridBagConstraints c = new GridBagConstraints();
        mazeSetupPanel.setBackground(Color.GRAY);
        mazeSetupPanel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 3));

        // Panel for the logo setup
        logoSetupPanel = new JPanel(new GridBagLayout());
        logoSetupPanel.setPreferredSize(new Dimension(LOGO_PANEL_WIDTH, LOGO_SETUP_PANEL_HEIGHT));
        GridBagConstraints d = new GridBagConstraints();
        logoSetupPanel.setBackground(Color.GRAY);
        logoSetupPanel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 3));

        insertLogo = new JRadioButton("Insert logo");
        insertLogo.setBackground(Color.GRAY);

        // Spinners for rows and cols inputs
        // number of rows occupied by the logo
        logoRows = new JSpinner(new SpinnerNumberModel(1, 1,5, 1));
        logoRows.setPreferredSize(new Dimension(70,20));
        logoRows.setEnabled(false);
        logoRows.setBackground(Color.LIGHT_GRAY);
        logoRows.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        ((JSpinner.DefaultEditor) logoRows.getEditor()).getTextField().setEditable(false);
        ((JSpinner.DefaultEditor) logoRows.getEditor()).getTextField().setHorizontalAlignment(SwingConstants.CENTER);
        ((JSpinner.DefaultEditor) logoRows.getEditor()).getTextField().setBackground(Color.GRAY);
        ((JSpinner.DefaultEditor) logoRows.getEditor()).getTextField().setForeground(Color.WHITE);
        // number of cols occupied by the logo
        logoCols = new JSpinner(new SpinnerNumberModel(1, 1, 5, 1));
        logoCols.setPreferredSize(new Dimension(70,20));
        logoCols.setEnabled(false);
        logoCols.setBackground(Color.LIGHT_GRAY);
        logoCols.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        ((JSpinner.DefaultEditor) logoCols.getEditor()).getTextField().setEditable(false);
        ((JSpinner.DefaultEditor) logoCols.getEditor()).getTextField().setHorizontalAlignment(SwingConstants.CENTER);
        ((JSpinner.DefaultEditor) logoCols.getEditor()).getTextField().setBackground(Color.GRAY);
        ((JSpinner.DefaultEditor) logoCols.getEditor()).getTextField().setForeground(Color.WHITE);


        d.anchor = GridBagConstraints.LINE_START;
        d.weightx = 2; d.weighty = 0.5;
        d.gridx = 1; d.gridy = 0; d.gridwidth = 2;
        logoSetupPanel.add(insertLogo);
        d.anchor = GridBagConstraints.LINE_END;
        d.gridx = 0; d.gridy = 1; d.gridwidth = 1;
        logoSetupPanel.add(new JLabel("ROWS:"), d);
        d.gridy = 2;
        logoSetupPanel.add(new JLabel("COLS:"), d);

        d.anchor = GridBagConstraints.LINE_START;
        d.gridx = 1; d.gridy = 1;
        d.fill = GridBagConstraints.HORIZONTAL;
        logoSetupPanel.add(logoRows, d);
        d.gridy = 2;
        d.fill = GridBagConstraints.HORIZONTAL;
        logoSetupPanel.add(logoCols, d);

        // Panel displaying info of the maze
        mazeInfoPanel = new JPanel(new GridLayout(3, 1));
        mazeInfoPanel.setBackground(Color.GRAY);
        mazeInfoPanel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY,3));

        // Temporary Panel, will be using it in the future
        JPanel temp = new JPanel();
        temp.setBackground(Color.BLACK);
        temp.setPreferredSize(new Dimension(200,200));

        // Spinners for rows and cols inputs
        // rows
        rowDecision = new JSpinner(new SpinnerNumberModel(20, 2, 100, 1));
        rowDecision.setPreferredSize(new Dimension(70,20));
        rowDecision.setBackground(Color.LIGHT_GRAY);
        rowDecision.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        ((JSpinner.DefaultEditor) rowDecision.getEditor()).getTextField().setEditable(false);
        ((JSpinner.DefaultEditor) rowDecision.getEditor()).getTextField().setHorizontalAlignment(SwingConstants.CENTER);
        ((JSpinner.DefaultEditor) rowDecision.getEditor()).getTextField().setBackground(Color.GRAY);
        ((JSpinner.DefaultEditor) rowDecision.getEditor()).getTextField().setForeground(Color.WHITE);
        // cols
        colDecision = new JSpinner(new SpinnerNumberModel(20, 2, 100, 1));
        colDecision.setPreferredSize(new Dimension(70,20));
        colDecision.setBackground(Color.LIGHT_GRAY);
        colDecision.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        ((JSpinner.DefaultEditor) colDecision.getEditor()).getTextField().setEditable(false);
        ((JSpinner.DefaultEditor) colDecision.getEditor()).getTextField().setHorizontalAlignment(SwingConstants.CENTER);
        ((JSpinner.DefaultEditor) colDecision.getEditor()).getTextField().setBackground(Color.GRAY);
        ((JSpinner.DefaultEditor) colDecision.getEditor()).getTextField().setForeground(Color.WHITE);

        // Button for auto-gen maze
        mazeGeneration = new JButton("Generate");

        // Button for blank maze
        BlankGenerate = new JButton("Blank");

        // Button to erase the maze display
        EraseButton = new JButton("Erase");
        EraseButton.setEnabled(false);

        // RefreshButton = new JButton("Refresh");

        // Toggle the optimum path (should be a colored line)
        toggleOptimumPath = new JRadioButton("Optimum Solution");
        toggleOptimumPath.setLocation(20,30);
        toggleOptimumPath.setBackground(Color.GRAY);
        toggleOptimumPath.setEnabled(false);

        // Toggle the Entry and Exit (Red - Entry, Green, Exit)
        toggleEntryExit = new JRadioButton("Show Entry/Exit");
        toggleEntryExit.setLocation(20,60);
        toggleEntryExit.setBackground(Color.GRAY);
        toggleEntryExit.setEnabled(false);

        ScreenShot = new JButton("Screen-Shot");
        ScreenShot.setEnabled(false);
        c.anchor = GridBagConstraints.LINE_END;
        c.weightx = 3; c.weighty = 0.5;
        c.gridx = 0; c.gridy = 0;
        mazeSetupPanel.add(new JLabel("ROWS:"), c);
        c.gridy = 1;
        mazeSetupPanel.add(new JLabel("COLS:"), c);

        c.anchor = GridBagConstraints.LINE_START;
        c.gridx = 1; c.gridy = 0;
        c.fill = GridBagConstraints.HORIZONTAL;
        mazeSetupPanel.add(rowDecision, c);
        c.gridy = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        mazeSetupPanel.add(colDecision, c);

        c.gridx = 0; c.gridy = 2; c.weightx = 2;
        mazeSetupPanel.add(mazeGeneration, c);
        c.gridx = 1;
        mazeSetupPanel.add(BlankGenerate, c);

        c.gridx = 0; c.gridy = 3; c.fill= GridBagConstraints.HORIZONTAL;
        c.gridwidth = 2;
        mazeSetupPanel.add(EraseButton, c);

        c.gridx = 0; c.gridy = 4; c.gridwidth = 2;
        mazeSetupPanel.add(toggleOptimumPath, c);
        c.gridy = 5;
        mazeSetupPanel.add(toggleEntryExit, c);

        c.gridy = 6;
        mazeSetupPanel.add(ScreenShot, c);

        editTab.add(mazeEditPanel, BorderLayout.CENTER);
        editTab.add(setupAndInfoPanel, BorderLayout.WEST);
        editTab.add(temp, BorderLayout.EAST);
        setupAndInfoPanel.add(mazeSetupPanel, BorderLayout.NORTH);

        JPanel pnl = new JPanel(new BorderLayout());
        pnl.setBackground(Color.GRAY);
        setupAndInfoPanel.add(pnl, BorderLayout.CENTER);
        pnl.add(logoSetupPanel, BorderLayout.NORTH);

        setupAndInfoPanel.add(mazeInfoPanel, BorderLayout.SOUTH);

        mazeGeneration.addActionListener(event -> {
            EraseButton.setEnabled(true);
            toggleEntryExit.setEnabled(true);
            toggleOptimumPath.setEnabled(true);
            ScreenShot.setEnabled(true);
            mazeGeneration.setEnabled(false);
            BlankGenerate.setEnabled(false);
            rowDecision.setEnabled(false);
            colDecision.setEnabled(false);
            // remove all the things in the maze panel
            mazeEditPanel.removeAll();
            // Display the auto-gen maze
            maze = MazeGeneration.genMaze(new MazeWithoutImage((int)rowDecision.getValue(),(int)colDecision.getValue()));
            displayMaze.drawMaze(mazeEditPanel, maze);
            mazeEditPanel.repaint();
            mazeEditPanel.validate();

            mazeInfoPanel.removeAll();
            displayMaze.showDeadEndPercentage(mazeInfoPanel, maze);
            displayMaze.showDimensionOfMaze(mazeInfoPanel, maze);
            mazeInfoPanel.repaint();
            mazeInfoPanel.revalidate();

        });

        BlankGenerate.addActionListener(event -> {
            EraseButton.setEnabled(true);
            toggleEntryExit.setEnabled(true);
            toggleOptimumPath.setEnabled(true);
            ScreenShot.setEnabled(true);
            mazeGeneration.setEnabled(false);
            BlankGenerate.setEnabled(false);
            rowDecision.setEnabled(false);
            colDecision.setEnabled(false);

            mazeEditPanel.removeAll();
            maze = new MazeWithoutImage((int)rowDecision.getValue(),(int)colDecision.getValue());
            displayMaze.drawMaze(mazeEditPanel, maze);
            mazeEditPanel.repaint();
            mazeEditPanel.validate();

            mazeInfoPanel.removeAll();
            displayMaze.showDeadEndPercentage(mazeInfoPanel, maze);
            displayMaze.showDimensionOfMaze(mazeInfoPanel, maze);
            mazeInfoPanel.repaint();
            mazeInfoPanel.revalidate();
        });

        EraseButton.addActionListener(event -> {
            EraseButton.setEnabled(false);
            toggleEntryExit.setEnabled(false);
            toggleEntryExit.setSelected(false);
            toggleOptimumPath.setEnabled(false);
            toggleOptimumPath.setSelected(false);
            ScreenShot.setEnabled(false);
            mazeGeneration.setEnabled(true);
            BlankGenerate.setEnabled(true);
            rowDecision.setEnabled(true);
            colDecision.setEnabled(true);

            mazeEditPanel.removeAll();
            mazeEditPanel.repaint();
            mazeEditPanel.validate();
            mazeInfoPanel.removeAll();
            mazeInfoPanel.repaint();
            mazeInfoPanel.validate();
        });

        toggleEntryExit.addActionListener(event -> {
            if (toggleEntryExit.isSelected())  {
                displayMaze.showEntryExit(mazeEditPanel, maze);
                System.out.println(toggleEntryExit.isEnabled());
            }
            else {
                mazeEditPanel.removeAll();
                displayMaze.drawMaze(mazeEditPanel, maze);
            }
            mazeEditPanel.repaint();
            mazeEditPanel.validate();
        });

        ScreenShot.addActionListener(event -> FolderExplorer());

        insertLogo.addActionListener(event -> {
            logoRows.setEnabled(!logoRows.isEnabled());
            logoCols.setEnabled(!logoCols.isEnabled());
        });

    }

    private void FolderExplorer() {
        FileDialog folder = new FileDialog(new Frame(), "Save Image", FileDialog.SAVE);
        folder.setMultipleMode(false);
        folder.setVisible(true);
        if(folder.getDirectory().length() > 0){
            String aPath = folder.getFile();
            System.out.println(aPath);
            try {
                BufferedImage img = new BufferedImage(mazeEditPanel.getHeight(), mazeEditPanel.getHeight(), BufferedImage.TYPE_INT_RGB);
                mazeEditPanel.paint(img.getGraphics());
                ImageIO.write(img, IMAGE_OUTPUT_FORMAT, new File(folder.getDirectory(), folder.getFile() + "." + IMAGE_OUTPUT_FORMAT));
                JOptionPane.showMessageDialog(this,
                        "Done!",
                        "Image Export", JOptionPane.INFORMATION_MESSAGE);
            } catch (NullPointerException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this,
                        "Missing image file, please select an appropriate image file.",
                        "Missing image file: Error", JOptionPane.ERROR_MESSAGE);
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this,
                        "Invalid folder selected, please select an appropriate file.",
                        "Invalid folder selection: Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        else {
            JOptionPane.showMessageDialog(this,
                    "Missing image file, please select an appropriate image file.",
                    "Missing image file: Error", JOptionPane.ERROR_MESSAGE);
        }
    }

}