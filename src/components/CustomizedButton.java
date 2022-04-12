package components;

import javax.swing.*;
import java.awt.*;

public class CustomizedButton extends JButton {
    private enum buttonTypes {simpleButton, wallButton}
    private int width = 20;
    private int height = 40;
    private int row;
    private int col;

    // size of horizontal walls
    private final int widthHorizontal = 40;
    private final int heightHorizontal = 5;
    // size of vertical walls
    private final int widthVertical = 5;
    private final int heightVertical = 40;
    private int wallIndex = 4;
    private final int locationX;
    private final int locationY;
    private final buttonTypes type;
    private String name = "";

    // wall buttons
    public CustomizedButton(int row, int col, int wallIndex) {
        this.row = row;
        this.col = col;
        if (wallIndex == 0 || wallIndex == 2) {
            this.locationX = row * (widthHorizontal + heightHorizontal);
            this.locationY = col * (widthHorizontal + heightHorizontal);
        }
        else {
            this.locationX = row * (widthVertical + heightVertical);
            this.locationY = col * (widthVertical + heightVertical);
        }
        this.type = buttonTypes.wallButton;
        this.wallIndex = wallIndex;
    }
    // simple buttons
    public CustomizedButton(String str, int x, int y, int width, int height) {
        this.locationX = x;
        this.locationY = y;
        this.width = width;
        this.height = height;
        this.type = buttonTypes.simpleButton;
        this.name = str;
    }

    @Override
    protected void paintComponent (Graphics g) {
        // TODO Auto-generated method stub
        super.paintComponent(g);

        if (this.type == buttonTypes.wallButton) {
            g.setColor(new Color(125, 116, 0));
            if (this.wallIndex == 0) {
                setPreferredSize(new Dimension(this.widthHorizontal, this.heightHorizontal));
                setLocation(locationX, locationY);
                g.fillRoundRect(0, 0,this.widthHorizontal,this.heightHorizontal,2,2);
            }
            if (this.wallIndex == 2){
                setPreferredSize(new Dimension(this.widthVertical, this.heightVertical));
                g.fillRoundRect(0, 0,this.widthVertical,this.heightVertical,2,2);
            }
            if (this.wallIndex == 1) {
                setPreferredSize(new Dimension(this.widthHorizontal, this.heightHorizontal));
                g.fillRoundRect(0, 0,this.widthHorizontal,this.heightHorizontal,2,2);
            }
            if (this.wallIndex == 3){
                setPreferredSize(new Dimension(this.widthVertical, this.heightVertical));
                g.fillRoundRect(0, 0,this.widthVertical,this.heightVertical,2,2);
            }
            //setBorderPainted(false);
        }
        if (this.type == buttonTypes.simpleButton) {
            //g.setColor(new Color(15, 117, 175, 255));
            //g.drawRoundRect(0,0,this.width,this.height,20,20);
            //g.fillRoundRect(0, 0, this.width, this.height, 5, 5);
            paintBorder(g);
            setBorderPainted(true);
            //setBackground(new Color(255, 116, 0));
            setText(this.name);
        }
    }

    @Override
    protected void paintBorder(Graphics g) {
        super.paintBorder(g);
        if (this.type == buttonTypes.simpleButton) {
            ((Graphics2D) g).setRenderingHint(
                    RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g.setColor(new Color(255, 116, 0));
            g.drawRoundRect(0, 0, this.width, this.height, 40, 40);
        }
    }

    public int getRow() {return this.row;}
    public int getCol() {return this.col;}
}
