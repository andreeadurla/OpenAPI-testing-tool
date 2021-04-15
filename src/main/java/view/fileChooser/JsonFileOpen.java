package view.fileChooser;

import org.apache.commons.lang3.StringUtils;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.net.URL;

/**
 * Creates a JFileChooser in OPEN mode used to select only JSON files.
 */
public class JsonFileOpen extends JFileChooser implements IJsonFileChooser {

    private int returnState = -1;

    /**
     * Class constructor used to create a JFileChooser in OPEN mode for JSON files
     */
    public JsonFileOpen() {
        super(FileSystemView.getFileSystemView().getHomeDirectory());

        this.setAcceptAllFileFilterUsed(false);
        FileNameExtensionFilter filter = new FileNameExtensionFilter("*.json", "json");
        this.addChoosableFileFilter(filter);

        returnState = this.showOpenDialog(null);
    }

    /**
     * Returns the path of selected file.
     * @return URL of selected file or an empty string if nothing is selected.
     */
    public String getAbsolutePathOfSelectedFile() {

        if(returnState == JFileChooser.APPROVE_OPTION) {
            File selectedFile = this.getSelectedFile();
            try {
                URL fileURL = selectedFile.toURI().toURL();
                return fileURL.toString();
            } catch (Exception e) {
                return StringUtils.EMPTY;
            }
        }

        return StringUtils.EMPTY;
    }
}