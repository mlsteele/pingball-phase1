package client;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Class to facilitate easy reading of files on disk.
 *
 * This class is threadsafe.
 * There is one static method which does not use persistent data.
 * The input to the method is an immutable String.
 * Therefore this class is threadsafe because all data is confined
 * except for the input which is immutable.
 */
public class SimpleFileReader {

    /**
     * Read the text from a file.
     * This method may not be suitable for very large files.
     *
     * @param  file
     *         The path to the file to read.
     * @return The contents of the file as a string.
     * @throws FileNotFoundException
     *         If the file is not found on disk.
     * @throws IOException
     *         If an error occurs while reading the file.
     */
    public static final String readFile(File file) throws FileNotFoundException, IOException {
        try(BufferedReader br = new BufferedReader(new FileReader(file))) {
            // Derived from this stackoverflow post:
            // http://stackoverflow.com/questions/4716503/best-way-to-read-a-text-file
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
                line = br.readLine();
            }

            String everything = sb.toString();
            return everything;
        }
    }
}
