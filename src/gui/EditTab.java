package gui;

import components.CustomizedButton;

import javax.swing.*;
import javax.swing.plaf.basic.BasicLookAndFeel;
import java.awt.*;

public class EditTab extends JFrame{
    // Panel
    private final JPanel editTab;
    private final JPanel mazeEditPanel, buttonPanel;

    // Buttons
    private final JButton mazeGeneration;
    private final JButton BlankGenerate;

    // Toggle buttons
    private final JRadioButton toggleOptimumPath;
    private final JRadioButton toggleEntryExit;
    private final JRadioButton toggleIndicators;

    // Maze grid
    Grid mazeGrid;
    public EditTab(JTabbedPane tabbedPane) {
        editTab = new JPanel(new BorderLayout());
        tabbedPane.add("Edit",editTab);

        mazeEditPanel = new JPanel();
        mazeEditPanel.setBackground(Color.BLACK);
        editTab.add(mazeEditPanel, BorderLayout.CENTER);

        buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.setBackground(Color.BLACK);
        editTab.add(buttonPanel, BorderLayout.WEST);
        buttonPanel.setPreferredSize(new Dimension(180, 400));

        // Generate Button
        mazeGeneration = new CustomizedButton("Generate",20,0,70,20);
        mazeGeneration.addActionListener(event -> fill());

        // Blank Button
        BlankGenerate = new CustomizedButton("Blank",120,0,70,20);

        toggleOptimumPath = new JRadioButton("Optimum Solution");
        toggleOptimumPath.setLocation(20,30);
        toggleOptimumPath.setBackground(Color.BLACK);
        toggleOptimumPath.setForeground(Color.WHITE);
        toggleEntryExit = new JRadioButton("Show Entry/Exit");
        toggleEntryExit.addActionListener(event -> {
            if (toggleEntryExit.isSelected()) mazeGrid.showEntryExit(mazeEditPanel);
        });
        toggleEntryExit.setLocation(20,60);
        toggleEntryExit.setBackground(Color.BLACK);
        toggleEntryExit.setForeground(Color.WHITE);
        toggleIndicators = new JRadioButton("Indicate Entry/Exit");
        toggleIndicators.setLocation(20,90);
        toggleIndicators.setBackground(Color.BLACK);
        toggleIndicators.setForeground(Color.WHITE);

        buttonPanel.add(mazeGeneration);
        buttonPanel.add(BlankGenerate);
        buttonPanel.add(toggleOptimumPath);
        buttonPanel.add(toggleEntryExit);
        buttonPanel.add(toggleIndicators);
    }
    private void fill() {
        mazeGrid = new Grid(20,20);
        mazeGrid.drawMaze(mazeEditPanel);
    }
}
