package components;

import javax.swing.*;
import java.awt.*;

public class CustomizedButton extends JButton {

    private final int width, height;
    private final int locationX;
    private final int locationY;
    private final String name;

    // simple buttons
    public CustomizedButton(String str, int x, int y, int width, int height) {
        this.locationX = x;
        this.locationY = y;
        this.width = width;
        this.height = height;
        this.name = str;
    }

    @Override
    protected void paintComponent (Graphics g) {
        // TODO Auto-generated method stub
        super.paintComponent(g);
        //g.setColor(new Color(15, 117, 175, 255));
        //g.drawRoundRect(0,0,this.width,this.height,20,20);
        //g.fillRoundRect(0, 0, this.width, this.height, 5, 5);
        paintBorder(g);
        setBorderPainted(true);
        //setBackground(new Color(255, 116, 0));
        setText(this.name);
    }

    @Override
    protected void paintBorder(Graphics g) {
        super.paintBorder(g);
        ((Graphics2D) g).setRenderingHint(
                RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        // g.setColor(new Color(255, 116, 0));
        // g.drawRoundRect(0, 0, this.width, this.height, 40, 40);
    }
}
