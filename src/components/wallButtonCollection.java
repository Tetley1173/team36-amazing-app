package components;

import gui.EditTab;
import gui.displayMaze;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class wallButtonCollection implements ActionListener {
    private final wallButton[] wallButtons = new wallButton[4];
    public wallButtonCollection(int row, int col) {
        wallButtons[0] = new wallButton(row, col, 0);
        wallButtons[3] = new wallButton(row, col, 3);
        wallButtons[2] = new wallButton(row, col, 2);
        wallButtons[1] = new wallButton(row, col, 1);
        for (wallButton wb: wallButtons) wb.addActionListener(this);
    }
    public wallButton getWallButton(int index) { return wallButtons[index]; }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Action for wallButtons
        // System.out.println("pressed a wall");
        wallButton button = (wallButton) e.getSource();
        int row = button.getRow();
        int col = button.getCol();
        int wallIndex = button.getWallIndex();

        // Changes the color between the background and the wall color
        if (button.getBackground() == wallButton.wallColor) button.setBackground(displayMaze.bgColor);
        else if (button.getBackground() == displayMaze.bgColor) button.setBackground(wallButton.wallColor);
        EditTab.getMaze().getCell(row, col).invertWall(wallIndex);
//        Test if the wall state is updated after the actionPerformed
//        System.out.println(EditTab.maze.getCell(row, col).sumWall());
    }
}
