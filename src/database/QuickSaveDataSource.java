package database;

import mazeFunctions.ImageAssetFile;
import mazeFunctions.Maze;
import mazeFunctions.MazeWithoutImage;
import mazeFunctions.QuickSaveFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Set;
import java.util.TreeSet;

import static collections.HelperMethods.*;

/**
 * This class is used for storing images in the database and is necessary because multiple instances
 * of the ImageAsset class can refer to a single instance of this class.
 * @author Dilshan Perera Georgia Meszaros simon
 */

public abstract class QuickSaveDataSource implements QuickSaveInterface {

    public static final String CREATE_TABLE =
            "CREATE TABLE IF NOT EXISTS quickSaveFiles ("
                    + "idx INTEGER PRIMARY KEY /*!40101 AUTO_INCREMENT */ NOT NULL UNIQUE," // from https://stackoverflow.com/a/41028314
                    + "name VARCHAR(30) NOT NULL UNIQUE;";

    private static final String INSERT_QUICKSAVE = "INSERT INTO quickSaveFiles (name, imageFile) VALUES (?, ?);";

    private static final String GET_NAMES = "SELECT name FROM quickSaveFiles";

    private static final String GET_QUICKSAVE = "SELECT * FROM quickSaveFiles WHERE name=?";

    private static final String DELETE_QUICKSAVE = "DELETE FROM quickSaveFiles WHERE name=?";

    private static final String COUNT_ROWS = "SELECT COUNT(*) FROM quickSaveFiles";

    private final Connection connection;

    private PreparedStatement addQuickSave;

    private PreparedStatement getNameList;

    private PreparedStatement getQuickSave;

    private PreparedStatement deleteQuickSave;

    private PreparedStatement rowCount;


    public QuickSaveDataSource() {
        connection = DBConnection.getInstance();
        try {
            Statement st = connection.createStatement();
            st.execute(CREATE_TABLE);
            addQuickSave = connection.prepareStatement(INSERT_QUICKSAVE);
            getNameList = connection.prepareStatement(GET_NAMES);
            getQuickSave = connection.prepareStatement(GET_QUICKSAVE);
            deleteQuickSave = connection.prepareStatement(DELETE_QUICKSAVE);
            rowCount = connection.prepareStatement(COUNT_ROWS);
        } catch (SQLException ex) {
            // Improve this behavior ########################################################
            ex.printStackTrace();
        }
    }


    public void AddQuickSave(QuickSaveFile image){
    try{
        addQuickSave.setString(1,image.getMazeName());
        addQuickSave.setBytes(2,bufferedImageToByte(image.getMazeImage(), "png"));
        addQuickSave.execute();
        }
    catch (SQLException ex) {
        ex.printStackTrace();
        }
    catch (IOException e) {
        e.printStackTrace(); // Improve this behavior###########################################
        }
    }



    public QuickSaveFile getMazeImage(String mazeName) {

        QuickSaveFile a = new QuickSaveFile();
        ResultSet resultSet = null;
        try {
            getQuickSave.setString(1, mazeName);
            resultSet = getQuickSave.executeQuery();
            resultSet.next();
            a.setMazeName(resultSet.getString("mazeName"));
            a.setMazeImage(bytesToBufferedImage(resultSet.getBytes("mazeImage")));
        } catch (SQLException ex) {
            ex.printStackTrace(); // Improve error handling behavior ############################################
        } catch (IOException e) {
            e.printStackTrace();
        }
        return a;
    }


    /**
     * Returns the size of the QuickSave table.
     *
     * @return an integer representing the number of rows in the imageAsset table.
     */
    public int getSize() {
        ResultSet resultSet = null;
        int rows = 0;

        try {
            resultSet = rowCount.executeQuery();
            resultSet.next();
            rows = resultSet.getInt(1);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return rows;
    }

    public void deleteQuickSave(String mazeName) {
        try {
            deleteQuickSave.setString(1, mazeName);
            deleteQuickSave.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void close() {
        try {
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
