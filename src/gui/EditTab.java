package gui;

import components.CustomizedButton;
import mazeFunctions.Maze;
import mazeFunctions.MazeGeneration;

import javax.swing.*;
import javax.swing.text.NumberFormatter;
import java.awt.*;
import java.text.NumberFormat;

public class EditTab extends JFrame{
    // CONSTANTS
    private final int minNumberOfRowsCols = 1;
    private final int maxNumberOfRowsCols = 100;
    // Panel
    private final JPanel editTab;
    private final JPanel mazeEditPanel, buttonPanel;

    // Buttons
    private final JButton mazeGeneration;
    private final JButton BlankGenerate;
    private final JButton EraseButton;

    // Text field
    private final JTextField rowDecision;
    private final JTextField colDecision;

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
        buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.setBackground(Color.DARK_GRAY);
        editTab.add(buttonPanel, BorderLayout.WEST);
        buttonPanel.setPreferredSize(new Dimension(200, 400));

        // The text field input setting the size of the maze
        NumberFormat format = NumberFormat.getIntegerInstance();
        format.setGroupingUsed(false);
        NumberFormatter numberFormatter = new NumberFormatter(format);
        rowDecision = new JFormattedTextField(numberFormatter);
        colDecision = new JFormattedTextField(numberFormatter);
        rowDecision.setPreferredSize(new Dimension(70,20));
        colDecision.setPreferredSize(new Dimension(70,20));
        rowDecision.setColumns(3);
        colDecision.setColumns(3);

        // Button for auto-gen maze
        mazeGeneration = new CustomizedButton("Generate",20,0,70,20);
        mazeGeneration.addActionListener(event -> {
            // remove all the things in the maze panel
            mazeEditPanel.removeAll();
            // Display the auto-gen maze
            maze = MazeGeneration.genMaze(new Maze(20,20));
            displayMaze.drawMaze(mazeEditPanel, maze);
            mazeEditPanel.repaint();
            mazeEditPanel.validate();

        });

        // Button for blank maze
        BlankGenerate = new CustomizedButton("Blank",120,0,70,20);
        BlankGenerate.addActionListener(event -> {
            maze = new Maze(20, 20);
            displayMaze.drawMaze(mazeEditPanel, maze);
            mazeEditPanel.repaint();
            mazeEditPanel.validate();
        });
        // Button to erase the maze display
        EraseButton = new CustomizedButton("Erase",20,120,70,20);
        EraseButton.addActionListener(event -> {
            mazeEditPanel.removeAll();
            mazeEditPanel.repaint();
            mazeEditPanel.validate();
        });


        // Toggle the optimum path (should be a colored line)
        toggleOptimumPath = new JRadioButton("Optimum Solution");
        toggleOptimumPath.setLocation(20,30);
        toggleOptimumPath.setBackground(Color.DARK_GRAY);
        toggleOptimumPath.setForeground(Color.WHITE);

        // Toggle the Entry and Exit (Red - Entry, Green, Exit)
        toggleEntryExit = new JRadioButton("Show Entry/Exit");
        toggleEntryExit.addActionListener(event -> {
            if (toggleEntryExit.isSelected()) displayMaze.showEntryExit(mazeEditPanel, maze);
        });
        toggleEntryExit.setLocation(20,60);
        toggleEntryExit.setBackground(Color.DARK_GRAY);
        toggleEntryExit.setForeground(Color.WHITE);

        // Toggle the Entry and Exit indicators (An arrow)
        toggleIndicators = new JRadioButton("Indicate Entry/Exit");
        toggleIndicators.setLocation(20,90);
        toggleIndicators.setBackground(Color.DARK_GRAY);
        toggleIndicators.setForeground(Color.WHITE);


        // Slide bar for adjusting the size of each cell (should have a default value)
        sizeSlideBar = new JSlider(SwingConstants.VERTICAL,10, 50, 30);
        sizeSlideBar.setBackground(Color.LIGHT_GRAY);
        sizeSlideBar.setForeground(Color.DARK_GRAY);
        sizeSlideBar.setMajorTickSpacing(5);
        sizeSlideBar.setPaintLabels(true);
        sizeSlideBar.setPaintTicks(true);

        // Adding components into buttonPanel
        buttonPanel.add(rowDecision);
        buttonPanel.add(colDecision);
        buttonPanel.add(mazeGeneration);
        buttonPanel.add(BlankGenerate);
        buttonPanel.add(toggleOptimumPath);
        buttonPanel.add(toggleEntryExit);
        buttonPanel.add(toggleIndicators);
        buttonPanel.add(EraseButton);

        // Add the size decision slider to the east of the borderLayout in the edit tab
        editTab.add(sizeSlideBar, BorderLayout.EAST);
    }

}
