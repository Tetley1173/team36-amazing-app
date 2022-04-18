package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserInterface extends JFrame implements ActionListener {
    // Menus
    private JMenuBar menuBar;
    private JMenu mainMenu;
    private JMenu editMenu;
    private JMenu exportMenu;
    private JMenu assetsMenu;
    // Tabbed Panels
    private final JTabbedPane tabbedPane;
    // Tabs
    private final EditTab edit;
    private final AssetsTab asset;
    private final ExportTab export;

    // Gui.Grid
    Grid grid;
    public UserInterface() {
        setTitle("Amazing software");
        setPreferredSize(new Dimension(1000, 800));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);


        guiMenuBar();
        setLayout(new BorderLayout(5, 5));

        tabbedPane = new JTabbedPane();
        asset = new AssetsTab(tabbedPane);
        edit = new EditTab(tabbedPane);
        export = new ExportTab(tabbedPane);
        getContentPane().add(tabbedPane);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
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




    public static void mainInterface()
    {
        // Setting up canvas
        JFrame jframe = new JFrame("Maze Application");
        jframe.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jframe.setPreferredSize(new Dimension(600,600));

        // Adding Menu option (Files)
        JMenuBar menuBar = new JMenuBar();

        JMenu FileMenu = new JMenu("File");
        FileMenu.add("New");
        FileMenu.add("Load");
        FileMenu.add("Exit");
        menuBar.add(FileMenu);

        // ######### Maybe we should make this a place to toggle things like what type of exit is being used?
        // Adding Menu option (Edit)
        JMenu editMenu = new JMenu("Edit");
        editMenu.add("Cut");
        editMenu.add("Copy");
        editMenu.add("Paste");
        menuBar.add(editMenu);
        jframe.setJMenuBar(menuBar);

        // Edit tab
        // Buttons "Toggle OP" & "Generate Maze"
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(new JButton("Toggle OP"));
        buttonPanel.add(new JButton("Generate Maze"));

        JTabbedPane panel = new JTabbedPane();
        //panel.setBorder(BorderFactory.createTitledBorder("Label Names"));

        // Assets Tab
        JPanel assetsPanel = new JPanel();
        panel.add("Assets", assetsPanel);
        //assetContents(assetsPanel);

        panel.add("Edit", buttonPanel);
        panel.add("Export", new JLabel());

        // Finalising program

        jframe.getContentPane().add(panel);
        jframe.pack();
        jframe.setLocationRelativeTo(null);
        //jframe.setLocation(new Point(1000, 400));
        jframe.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}