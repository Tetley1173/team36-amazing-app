package gui;

import javax.swing.*;
import java.awt.*;

public class ExportTab extends JFrame{
    private final JPanel temp;
    private final JLabel label;
    public ExportTab(JTabbedPane tabbedPane) {
        label = new JLabel("This is the label for ExportTab");
        temp = new JPanel(new BorderLayout());
        temp.add(label, BorderLayout.SOUTH);
        tabbedPane.add("Export",temp);
    }
}
