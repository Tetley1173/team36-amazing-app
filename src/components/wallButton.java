package components;

import javax.swing.*;
import java.awt.*;

public class wallButton extends JButton {
    private static Color wallColor = new Color(125, 116, 0);
    private int wallIndex;
    private final int width, height;
    private final int locationX;
    private final int locationY;
    public wallButton(int x, int y, int width, int height, int wallIndex){
        this.locationX = x;
        this.locationY = y;
        this.width = width;
        this.height = height;
        this.wallIndex = wallIndex;
    }

    @Override
    protected void paintComponent(Graphics g){
        // TODO Auto-generated method stub
        super.paintComponent(g);

        g.setColor(wallColor);
        setPreferredSize(new Dimension(this.width, this.height));
        setLocation(locationX, locationY);
        if (this.wallIndex == 0) {
            g.fillRoundRect(0, 0,this.width, this.height,2,2);
        }
        if (this.wallIndex == 2){
            g.fillRoundRect(0, 0,this.width, this.height,2,2);
        }
        if (this.wallIndex == 1) {
            g.fillRoundRect(0, 0,this.width, this.height,2,2);
        }
        if (this.wallIndex == 3){
            g.fillRoundRect(0, 0,this.width, this.height,2,2);
        }

    }
}
