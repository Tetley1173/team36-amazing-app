package components;

import javax.swing.*;
import java.awt.*;

public class wallButton extends JButton {
    public static final Color wallColor = new Color(125, 116, 0);
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
