package components;

public class Cell {
    private final int row, col;
    private final int[] wall = {1, 1, 1, 1};
    public Cell(int row, int col) {
        this.row = row;
        this.col = col;
    }
    public void knockWall(int wallIndex) { this.wall[wallIndex] = 1 - this.wall[wallIndex]; }

    public int getRow() {
        return row;
    }
    public int getCol() {
        return col;
    }
    public int sumWall() {
        int sum = 0;
        for(int i : wall) sum += i;
        return sum;
    }
    public int getWall(int wallIndex) { return wall[wallIndex]; }
}
