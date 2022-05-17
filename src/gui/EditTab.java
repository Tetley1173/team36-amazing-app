package gui;

import mazeFunctions.Maze;
import mazeFunctions.MazeGeneration;
import mazeFunctions.MazeWithoutImage;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.MissingResourceException;
import java.util.concurrent.CancellationException;

public class EditTab extends JFrame{
    public final static int MAZE_SETUP_PANEL_WIDTH = 200;
    public final static int MAZE_SETUP_PANEL_HEIGHT = 400;
    private final static String IMAGE_OUTPUT_FORMAT = "png";

    // Panel
    private final JPanel editTab;
    private final JPanel mazeEditPanel, setupAndInfoPanel, mazeInfoPanel, mazeSetupPanel;

    // Buttons
    private final JButton mazeGeneration;
    private final JButton BlankGenerate;
    private final JButton EraseButton;
    private final JButton RefreshButton;
    private final JButton ScreenShot;

    // Text field
    private final JSpinner rowDecision;
    private final JSpinner colDecision;

    // Slide bar for adjusting the size of the cell
    //private final JSlider sizeSlideBar;

    // Toggle buttons
    private final JRadioButton toggleOptimumPath;
    private final JRadioButton toggleEntryExit;

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
        editTab.add(mazeEditPanel, BorderLayout.CENTER);

        // the button panel in the edit tab
        setupAndInfoPanel = new JPanel(new BorderLayout());
        setupAndInfoPanel.setBackground(Color.DARK_GRAY);
        editTab.add(setupAndInfoPanel, BorderLayout.WEST);

        GridBagLayout gbl = new GridBagLayout();
        mazeSetupPanel = new JPanel(gbl);
        mazeSetupPanel.setPreferredSize(new Dimension(MAZE_SETUP_PANEL_WIDTH, MAZE_SETUP_PANEL_HEIGHT));
        GridBagConstraints c = new GridBagConstraints();
        mazeSetupPanel.setBackground(Color.GRAY);

        // Panel displaying info of the maze
        mazeInfoPanel = new JPanel(new GridLayout(3, 1));
        mazeInfoPanel.setBackground(Color.GRAY);


        // Temporary Panel, will be using it in the future
        JPanel temp = new JPanel();
        temp.setBackground(Color.BLACK);
        temp.setPreferredSize(new Dimension(200,200));
        editTab.add(temp, BorderLayout.EAST);

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
        mazeGeneration.addActionListener(event -> {
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

        // Button for blank maze
        BlankGenerate = new JButton("Blank");
        BlankGenerate.addActionListener(event -> {
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
        // Button to erase the maze display
        EraseButton = new JButton("Erase");
        EraseButton.addActionListener(event -> {
            mazeEditPanel.removeAll();
            mazeEditPanel.repaint();
            mazeEditPanel.validate();
            mazeInfoPanel.removeAll();
            mazeInfoPanel.repaint();
            mazeInfoPanel.validate();
        });

        RefreshButton = new JButton("Refresh");
        RefreshButton.addActionListener(event -> {

            mazeEditPanel.removeAll();
            displayMaze.drawMaze(mazeEditPanel, maze);
            mazeEditPanel.repaint();
            mazeEditPanel.validate();

            mazeInfoPanel.removeAll();
            displayMaze.showDeadEndPercentage(mazeInfoPanel, maze);
            displayMaze.showDimensionOfMaze(mazeInfoPanel, maze);
            mazeInfoPanel.repaint();
            mazeInfoPanel.revalidate();
        });

        // Toggle the optimum path (should be a colored line)
        toggleOptimumPath = new JRadioButton("Optimum Solution");
        toggleOptimumPath.setLocation(20,30);
        toggleOptimumPath.setBackground(Color.GRAY);

        // Toggle the Entry and Exit (Red - Entry, Green, Exit)
        var ref = new Object() {
            boolean isToggled = false;
        };
        toggleEntryExit = new JRadioButton("Show Entry/Exit");
        toggleEntryExit.addActionListener(event -> {
            if (!ref.isToggled)  {
                displayMaze.showEntryExit(mazeEditPanel, maze);
            }
            else {
                mazeEditPanel.removeAll();
                displayMaze.drawMaze(mazeEditPanel, maze);
            }
            ref.isToggled = !ref.isToggled;
            mazeEditPanel.repaint();
            mazeEditPanel.validate();
        });
        toggleEntryExit.setLocation(20,60);
        toggleEntryExit.setBackground(Color.GRAY);

        ScreenShot = new JButton("Screen-Shot");
        ScreenShot.addActionListener(event -> {
                FolderExplorer();
//            try {
//                BufferedImage img = new BufferedImage(mazeEditPanel.getHeight(), mazeEditPanel.getHeight(), BufferedImage.TYPE_INT_RGB);
//                mazeEditPanel.paint(img.getGraphics());
//                ImageIO.write(img, "png", new File("src/mazeFunctions/SCREENSHOT_TESTING/testing.png") );
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
        });


        // Slide bar for adjusting the size of each cell (should have a default value)
//        sizeSlideBar = new JSlider(SwingConstants.VERTICAL,10, 50, 30);
//        sizeSlideBar.setBackground(Color.LIGHT_GRAY);
//        sizeSlideBar.setForeground(Color.DARK_GRAY);
//        sizeSlideBar.setMajorTickSpacing(5);
//        sizeSlideBar.setPaintLabels(true);
//        sizeSlideBar.setPaintTicks(true);

        // Adding components into buttonPanel
        setupAndInfoPanel.add(mazeSetupPanel, BorderLayout.CENTER);
        setupAndInfoPanel.add(mazeInfoPanel, BorderLayout.SOUTH);

        c.anchor = GridBagConstraints.LINE_END;
        c.weightx = 3;
        c.weighty = 0.5;
        c.gridx = 0;
        c.gridy = 0;
        mazeSetupPanel.add(new JLabel("ROWS:"), c);
        c.gridy = 1;
        mazeSetupPanel.add(new JLabel("COLS:"), c);

        c.anchor = GridBagConstraints.LINE_START;
        c.gridx = 1;
        c.gridy = 0;
        c.fill = GridBagConstraints.HORIZONTAL;
        mazeSetupPanel.add(rowDecision, c);
        c.gridy = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        mazeSetupPanel.add(colDecision, c);

        c.gridx = 0;
        c.gridy = 2;
        c.weightx = 2;
        mazeSetupPanel.add(mazeGeneration, c);
        c.gridx = 1;
        mazeSetupPanel.add(BlankGenerate, c);

        c.gridx = 0;
        c.gridy = 3;
        mazeSetupPanel.add(EraseButton, c);
        c.gridx = 1;
        mazeSetupPanel.add(RefreshButton, c);

        c.gridx = 0;
        c.gridy = 4;
        c.gridwidth = 2;
        mazeSetupPanel.add(toggleOptimumPath, c);
        c.gridy = 5;
        mazeSetupPanel.add(toggleEntryExit, c);

        c.gridy = 6;
        c.weighty = 20;
        c.anchor = GridBagConstraints.FIRST_LINE_START;
        mazeSetupPanel.add(ScreenShot, c);


        // Add the size decision slider to the east of the borderLayout in the edit tab
//        editTab.add(sizeSlideBar, BorderLayout.EAST);
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