package collections;

import javax.imageio.ImageIO;
import javax.sql.rowset.serial.SerialBlob;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.Optional;

public class HelperMethods {

    /**
     * Converts a BufferedImage into a Blob by first converting it into a byte array.
     * @param bi the BufferedImage to be converted.
     * @param imageType converting an image into a byte array needs a string specifying the image file type.
     * @return a Blob representation of an image for insertion into the database.
     * @throws IOException ImageIO.write() throws this exception to make sure a proper BufferedImage is provided.
     * @throws SQLException .toByteArray() throws this exception to make sure a proper output stream is provided.
     * @author Shannon Tetley
     */
    public static Blob bufferedImageToBlob(BufferedImage bi, String imageType) throws IOException, SQLException {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(bi, imageType, baos);
        byte[] bytes = baos.toByteArray();

        Blob blob = new SerialBlob( bytes );
        baos.close();
        return blob;
    }

    /**
     * Converts a Blob back into a BufferedImage object. Used for extracting images from the database.
     * @param blobby a Blob that has been returned from a database query.
     * @return BufferedImage is returned that can be used in a JLabel or other appropriate use.
     * @throws SQLException is thrown if a problem with the Blob parameter is found.
     * @throws IOException is thrown if there is a problem reading the BinaryStream that the Blob gets converted to.
     */
    public static BufferedImage blobToBufferedImage(Blob blobby) throws SQLException, IOException {

        InputStream in = blobby.getBinaryStream();
        // Not sure if this input stream needs to be closed. Investigate this as a stretch goal. ####################

        return ImageIO.read(in);
    }

    /**
     *
     * @param bi
     * @param imageType
     * @return
     * @throws IOException
     */
    public static byte[] bufferedImageToByte(BufferedImage bi, String imageType) throws IOException {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(bi, imageType, baos);
        byte[] bytes = baos.toByteArray();

        baos.close();
        return bytes;
    }

    public static BufferedImage bytesToBufferedImage(byte[] hasTeeth) throws IOException {

        ByteArrayInputStream bis = new ByteArrayInputStream(hasTeeth);

        return ImageIO.read(bis);
    }

    // Returns the file extension of the string passed to it.
    public static String getExtension(String fileName) {

        String extension = Optional.of(fileName)
                .filter(f -> f.contains("."))
                .map(f -> f.substring(fileName.lastIndexOf(".") + 1))
                .orElse("");

        return extension;
    }

}