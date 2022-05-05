package gui;

import mazeFunctions.Maze;
import mazeFunctions.MazeGeneration;

import javax.swing.*;
import java.awt.*;

public class EditTab extends JFrame{
    // CONSTANTS
    private final int minNumberOfRowsCols = 1;
    private final int maxNumberOfRowsCols = 100;

    // Panel
    private final JPanel editTab;
    private final JPanel mazeEditPanel, setupAndInfoPanel, mazeInfoPanel, mazeSetupPanel;

    // Buttons
    private final JButton mazeGeneration;
    private final JButton BlankGenerate;
    private final JButton EraseButton;
    private final JButton RefreshButton;

    // Text field
    private final JSpinner rowDecision;
    private final JSpinner colDecision;

    // Slide bar for adjusting the size of the cell
    private final JSlider sizeSlideBar;

    // Toggle buttons
    private final JRadioButton toggleOptimumPath;
    private final JRadioButton toggleEntryExit;
    private final JRadioButton toggleIndicators;

    // Maze grid
    public static Maze maze;

    public EditTab(JTabbedPane tabbedPane) {
        // create the edit tab
        editTab = new JPanel(new BorderLayout());
        tabbedPane.add("Edit",editTab);

        // the maze visualization panel in the edit tab
        mazeEditPanel = new JPanel();
        mazeEditPanel.setBackground(Color.DARK_GRAY);
        editTab.add(mazeEditPanel, BorderLayout.CENTER);

        // the button panel in the edit tab
        setupAndInfoPanel = new JPanel(new BorderLayout());
        setupAndInfoPanel.setBackground(Color.DARK_GRAY);
        editTab.add(setupAndInfoPanel, BorderLayout.WEST);

        GridBagLayout gbl = new GridBagLayout();
        mazeSetupPanel = new JPanel(gbl);
        mazeSetupPanel.setPreferredSize(new Dimension(200, 400));
        GridBagConstraints c = new GridBagConstraints();
        mazeSetupPanel.setBackground(Color.GRAY);

        // Panel displaying info of the maze
        mazeInfoPanel = new JPanel(new GridLayout(3, 1));
        mazeInfoPanel.setBackground(Color.GRAY);

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
            maze = MazeGeneration.genMaze(new Maze((int)rowDecision.getValue(),(int)colDecision.getValue()));
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
            maze = new Maze((int)rowDecision.getValue(),(int)colDecision.getValue());
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
        toggleEntryExit = new JRadioButton("Show Entry/Exit");
        toggleEntryExit.addActionListener(event -> {
            if (toggleEntryExit.isSelected()) displayMaze.showEntryExit(mazeEditPanel, maze);
        });
        toggleEntryExit.setLocation(20,60);
        toggleEntryExit.setBackground(Color.GRAY);

        // Toggle the Entry and Exit indicators (An arrow)
        toggleIndicators = new JRadioButton("Indicate Entry/Exit");
        toggleIndicators.setLocation(20,90);
        toggleIndicators.setBackground(Color.GRAY);


        // Slide bar for adjusting the size of each cell (should have a default value)
        sizeSlideBar = new JSlider(SwingConstants.VERTICAL,10, 50, 30);
        sizeSlideBar.setBackground(Color.LIGHT_GRAY);
        sizeSlideBar.setForeground(Color.DARK_GRAY);
        sizeSlideBar.setMajorTickSpacing(5);
        sizeSlideBar.setPaintLabels(true);
        sizeSlideBar.setPaintTicks(true);

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
        //c.gridwidth = 2;
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
        mazeSetupPanel.add(toggleIndicators, c);


        // Add the size decision slider to the east of the borderLayout in the edit tab
        editTab.add(sizeSlideBar, BorderLayout.EAST);
    }



}