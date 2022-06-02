package database;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Set;
import java.util.TreeSet;

import static collections.HelperMethods.*;

public class QuickSaveDataSource implements QuickSaveInterface {

    public static final String CREATE_TABLE =
            "CREATE TABLE IF NOT EXISTS quickSaveFiles ("
                    + "idx INTEGER PRIMARY KEY /*!40101 AUTO_INCREMENT */ NOT NULL UNIQUE," // from https://stackoverflow.com/a/41028314
                    + "name VARCHAR(30) NOT NULL UNIQUE;";

    private static final String INSERT_QUICKSAVE = "INSERT INTO assetFiles (name, imageFile) VALUES (?, ?);";

    private static final String GET_NAMES = "SELECT name FROM assetFiles";

    private static final String GET_QUICKSAVE = "SELECT * FROM assetFiles WHERE name=?";

    private static final String DELETE_QUICKSAVE = "DELETE FROM assetFiles WHERE name=?";

    private static final String COUNT_ROWS = "SELECT COUNT(*) FROM assetFiles";

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
}
