package components;

import gui.EditTab;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class wallButtonCollection implements ActionListener {
    private final wallButton[] wallButtons = new wallButton[4];
    public wallButtonCollection(int row, int col, int WIDTH, int HEIGHT, int OFFSET) {
        wallButtons[0] = new wallButton(row, col,(col * (WIDTH + HEIGHT)) + HEIGHT + OFFSET, OFFSET, WIDTH, HEIGHT, 0);
        wallButtons[3] = new wallButton(row, col,OFFSET, (row * (WIDTH + HEIGHT)) + HEIGHT + OFFSET, HEIGHT, WIDTH, 3);
        wallButtons[2] = new wallButton(row, col,col * (WIDTH + HEIGHT) + HEIGHT + OFFSET, (row + 1) * (WIDTH + HEIGHT) + OFFSET, WIDTH, HEIGHT, 2);
        wallButtons[1] = new wallButton(row, col,(col + 1) * (WIDTH + HEIGHT) + OFFSET, row * (WIDTH + HEIGHT) + HEIGHT + OFFSET, HEIGHT, WIDTH, 1);
        for (wallButton wb: wallButtons) wb.addActionListener(this);
    }
    public wallButton getWallButton(int index) { return wallButtons[index]; }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Action for wallButtons
        System.out.println("pressed a wall");
        wallButton button = (wallButton) e.getSource();
        int row = button.getRow();
        int col = button.getCol();
        int wallIndex = button.getWallIndex();
        Cell c = EditTab.maze.getCell(row, col);
        button.setContentAreaFilled(c.getWall(wallIndex) != 1);
        EditTab.maze.getCell(row, col).invertWall(wallIndex);
    }
}
