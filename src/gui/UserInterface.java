package gui;
import javax.swing.*;
import java.awt.*;

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

        // Buttons "Toggle OP" & "Generate Maze"
        JPanel buttonpanel = new JPanel();
        buttonpanel.add(new JButton("Toggle OP"));
        buttonpanel.add(new JButton("Generate Maze"));

        JTabbedPane panel = new JTabbedPane();
        //panel.setBorder(BorderFactory.createTitledBorder("Label Names"));

        // Assets Tab
        JPanel interiorPanel = new JPanel();
        panel.add("Assets", interiorPanel);
        panel.add("Edit", buttonpanel);
        panel.add("Export", new JLabel());




        // Finalising program

        jframe.getContentPane().add(panel);
        jframe.pack();
        jframe.setLocationRelativeTo(null);
        //jframe.setLocation(new Point(1000, 400));
        jframe.setVisible(true);
    }
}