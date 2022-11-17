package it.unibo.mvc;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

/**
 * Application controller. Performs the I/O.
 */
public class Controller {

    /* point 1 */
    private static final String PATH = System.getProperty("user.home") + File.separator
    + "output.txt";
    private File curFile = new File(PATH);

    /* point 5 */
    public void setFile(final File file) {
        final File daddy = file.getParentFile();
        if(daddy.exists()) {
            curFile = file;
        } else {
            throw new IllegalArgumentException("Cannot save the file in a non-existing folder");
        }
    }

    /* caso in cui ci da la stringa, cioè il path del file*/
    public void setFile(final String filepath) {
        setFile(new File(filepath));
    }

    /* point 2 */
    public File getCurrentFile() {
        return curFile;
    }

    /* point 3 */
    public String getCurrentFilePath() {
        return curFile.getPath();
    }

    /* point 4 */
    public void save(final String text) throws IOException {
        try (PrintStream output = new PrintStream(curFile, StandardCharsets.UTF_8)) {
            output.println(text);
        }
    }

    

    
}
