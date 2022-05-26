package mazeFunctions;

import javax.swing.*;
import java.awt.*;
import java.util.Random;
import java.util.concurrent.ExecutionException;


public class MazeWithImage extends Maze{
    ImageIcon entryImage = null;
    ImageIcon exitImage = null;

    public MazeWithImage(int rows, int cols) {
        super(rows, cols);
    }

    //    private int chooseLocation(int rows, int cols) {
//        Random random = new Random();
//        while(true) {
//            int cell_ID = random.nextInt(rows*cols);
//            if (cell_ID != 0){
//                if (rowSize >= this.getRows() || colSize >= this.getCols()) return -1;
//                if (rows - cell_ID/rows > rowSize && cols - cell_ID%rows > colSize) return cell_ID;
//            }
//        }
//    }
//    private void spareLocation(int row, int col) {
//        for (int i = row + 1; i < row + rowSize - 1; i++) {
//            for (int j = col + 1; j < col + colSize - 1; j++) {
//                this.getCell(i, j).invertWall(0);
//                this.getCell(i, j).invertWall(1);
//                this.getCell(i, j).invertWall(2);
//                this.getCell(i, j).invertWall(3);
//            }
//        }
//        for (int j = col + 1; j < col + colSize - 1; j++) {
//            this.getCell(row, j).invertWall(1);
//            this.getCell(row, j).invertWall(2);
//            this.getCell(row, j).invertWall(3);
//
//            this.getCell(row + rowSize - 1, j).invertWall(0);
//            this.getCell(row + rowSize - 1, j).invertWall(1);
//            this.getCell(row + rowSize - 1, j).invertWall(3);
//
//        }
//        for (int i = row + 1; i < row + rowSize - 1; i++) {
//            this.getCell(i, col).invertWall(0);
//            this.getCell(i, col).invertWall(1);
//            this.getCell(i, col).invertWall(2);
//
//            this.getCell(i, col + colSize - 1).invertWall(0);
//            this.getCell(i, col + colSize - 1).invertWall(2);
//            this.getCell(i, col + colSize - 1).invertWall(3);
//        }
//        this.getCell(row, col).invertWall(1);
//        this.getCell(row, col).invertWall(2);
//
//        this.getCell(row, col + colSize - 1).invertWall(2);
//        this.getCell(row, col + colSize - 1).invertWall(3);
//
//        this.getCell(row + rowSize - 1, col).invertWall(0);
//        this.getCell(row + rowSize - 1, col).invertWall(1);
//
//
//        this.getCell(row + rowSize - 1, col + colSize - 1).invertWall(0);
//        this.getCell(row + rowSize - 1, col + colSize - 1).invertWall(3);
//
//    }
    private void setEntryImage(Image img){}
    private void setExitImage(Image img){}
}
