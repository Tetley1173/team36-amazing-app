package gui;
import javax.swing.*;
import java.awt.*;
import static gui.AssetsTab.assetContents;

public class UserInterface {

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
        assetContents(assetsPanel);

        panel.add("Edit", buttonPanel);
        panel.add("Export", new JLabel());

        // Finalising program

        jframe.getContentPane().add(panel);
        jframe.pack();
        jframe.setLocationRelativeTo(null);
        //jframe.setLocation(new Point(1000, 400));
        jframe.setVisible(true);
    }
}