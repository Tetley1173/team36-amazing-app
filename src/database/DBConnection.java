package database;

import javax.swing.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import static collections.Main.frame;

// This class was copied from the week 6 practical.

public class DBConnection {

    /**
     * The singleton instance of the database connection.
     */
    private static Connection instance = null;

    /**
     * Constructor initializes the connection.
     */
    @SuppressWarnings("SpellCheckingInspection")
    private DBConnection() {

        Properties props = new Properties();
        FileInputStream in = null;
        try {
            in = new FileInputStream("./db.props");
            props.load(in);
            in.close();

            // specify the data source, username and password
            String url = props.getProperty("jdbc.url");
            String username = props.getProperty("jdbc.username");
            String password = props.getProperty("jdbc.password");
            String schema = props.getProperty("jdbc.schema");

            // get a connection
            instance = DriverManager.getConnection(url + "/" + schema, username,
                    password);
            // Put meaningful exception behavior here. ##########################################################
        } catch (SQLException sqle) {
            System.err.println();
            sqle.printStackTrace();
            JOptionPane.showMessageDialog(frame,
                    "An error has occured connecting to the database",
                    "Database connection: Error", JOptionPane.ERROR_MESSAGE);

        } catch (FileNotFoundException fnfe) {
            System.err.println();
            fnfe.printStackTrace();
            JOptionPane.showMessageDialog(frame,
                    "An error has occured loading the database props file.",
                    "Props file: Error", JOptionPane.ERROR_MESSAGE);
        } catch (IOException ex) {
            System.err.println();
            ex.printStackTrace();
            JOptionPane.showMessageDialog(frame,
                    "An error has occured while loading the database props file.",
                    "JavaIO: Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Provides global access to the singleton instance of the UrlSet.
     *
     * @return a handle to the singleton instance of the UrlSet.
     */
    @SuppressWarnings("InstantiationOfUtilityClass")
    public static Connection getInstance() {
        if (instance == null) {
            new DBConnection();
        }
        return instance;
    }

}