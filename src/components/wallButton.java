package components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class wallButton extends JButton {
    private static Color wallColor = new Color(125, 116, 0);
    private int wallIndex;
    private final int width, height;
    private final int locationX, locationY;
    private final int row, col;
    public wallButton(int row, int col, int x, int y, int width, int height, int wallIndex){
        this.row = row;
        this.col = col;
        this.locationX = x;
        this.locationY = y;
        this.width = width;
        this.height = height;
        this.wallIndex = wallIndex;
        this.setBackground(Color.ORANGE);
        this.setBorderPainted(false);

    }

    @Override
    protected void paintComponent(Graphics g){
        // TODO Auto-generated method stub
        super.paintComponent(g);
        setPreferredSize(new Dimension(this.width, this.height));
        setLocation(locationX, locationY);
        if (this.wallIndex == 0) {
            g.drawRoundRect(0, 0,this.width, this.height,2,2);
        }
        if (this.wallIndex == 2){
            g.drawRoundRect(0, 0,this.width, this.height,2,2);
        }
        if (this.wallIndex == 1) {
            g.drawRoundRect(0, 0,this.width, this.height,2,2);
        }
        if (this.wallIndex == 3){
            g.drawRoundRect(0, 0,this.width, this.height,2,2);
        }

    }

    public int getRow() { return this.row; }
    public int getCol() { return this.col; }
    public int getWallIndex() { return this.wallIndex; }

}
