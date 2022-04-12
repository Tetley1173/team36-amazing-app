import components.customizedButton;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class guiFrame extends JFrame implements ActionListener {
    // Menus
    private JMenuBar menuBar;
    private JMenu mainMenu;
    private JMenu editMenu;
    private JMenu exportMenu;
    private JMenu assetsMenu;
    // Panels
    private final JPanel gridPanel;
    private final JPanel buttonPanel;
    // Buttons
    private final JButton generateMazeButton;
    // Toggle buttons
    private final JRadioButton toggleOptimumPath;
    private final JRadioButton toggleEntryExit;
    private final JRadioButton toggleIndicators;

    // Grid
    Grid grid;
    public guiFrame() {
        setTitle("Amazing software");
        setPreferredSize(new Dimension(800, 800));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);


        guiMenuBar();
        setLayout(new BorderLayout(5, 5));
        gridPanel = createPanel(Color.BLACK);
        getContentPane().add(gridPanel, BorderLayout.CENTER);
        gridPanel.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));

        buttonPanel = createPanel(Color.BLACK);
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.setPreferredSize(new Dimension(150, 10));
        generateMazeButton = new customizedButton("Start", 20, 0, 70, 20);
        generateMazeButton.addActionListener(event -> fill());

        toggleOptimumPath = new JRadioButton("Optimum Solution");
        toggleOptimumPath.setLocation(20,30);
        toggleOptimumPath.setBackground(Color.BLACK);
        toggleOptimumPath.setForeground(Color.WHITE);
        toggleEntryExit = new JRadioButton("Show Entry/Exit");
        toggleEntryExit.addActionListener(event -> {
                if (toggleEntryExit.isSelected()) grid.showEntryExit(gridPanel);
        });
        toggleEntryExit.setLocation(20,60);
        toggleEntryExit.setBackground(Color.BLACK);
        toggleEntryExit.setForeground(Color.WHITE);
        toggleIndicators = new JRadioButton("Indicate Entry/Exit");
        toggleIndicators.setLocation(20,90);
        toggleIndicators.setBackground(Color.BLACK);
        toggleIndicators.setForeground(Color.WHITE);

        buttonPanel.add(generateMazeButton);
        buttonPanel.add(toggleOptimumPath);
        buttonPanel.add(toggleEntryExit);
        buttonPanel.add(toggleIndicators);
        getContentPane().add(buttonPanel, BorderLayout.WEST);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) { fill(); }

    private void fill() {
        grid = new Grid(20,20);
        grid.drawMaze(gridPanel);
    }

    private JPanel createPanel(Color c) {
        JPanel temp = new JPanel();
        temp.setBackground(c);
        return temp;
    }

    private void guiMenuBar() {
        menuBar = new JMenuBar();
        mainMenu = new JMenu("Menu");
        mainMenu.add("New");
        mainMenu.add("Load");
        mainMenu.add("Exit");
        editMenu = new JMenu("Edit");
        editMenu.add("Copy");
        editMenu.add("Cut");
        editMenu.add("Paste");
        exportMenu = new JMenu("Export");
        exportMenu.add("Export the maze as png");
        assetsMenu = new JMenu("Assets");
        menuBar.add(mainMenu);
        menuBar.add(editMenu);
        menuBar.add(exportMenu);
        menuBar.add(assetsMenu);
        setJMenuBar(menuBar);
    }
}
