package components;

import gui.EditTab;

import javax.swing.*;
import java.awt.*;

public class wallButton extends JButton {
    public static final Color wallColor = EditTab.BUTTON_COLOR;
    private final int wallIndex;
    private final int row, col;
    public wallButton(int row, int col, int wallIndex){
        this.row = row;
        this.col = col;
        this.wallIndex = wallIndex;
        this.setBackground(wallColor);
        this.setBorderPainted(false);
    }

    public int getRow() { return this.row; }
    public int getCol() { return this.col; }
    public int getWallIndex() { return this.wallIndex; }

}
