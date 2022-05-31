package components;

public class Cell {
    private final int row, col;
    private final int[] wall = {1, 1, 1, 1}; // the state of the wall (1 - ON, 0 - OFF)
    private int distance; // step from the cell to the entry;
    private Cell parent = null;
    private boolean isVisited = false;
    public Cell(int row, int col) {
        this.row = row;
        this.col = col;
        distance = -1;
    }

    // Invert the wall
    public void invertWall(int wallIndex) { this.wall[wallIndex] = 1 - this.wall[wallIndex]; }

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
    public void setDistance(int distance) {
        this.distance = distance;
    }
    public int getDistance() { return distance; }
    public void setParent(Cell parent) {
        this.parent = parent;
    }
    public Cell getParent() { return parent; }
    public void setVisited(boolean isVisited) {
        this.isVisited = isVisited;
    }
    public boolean getVisited() { return isVisited; }
}
