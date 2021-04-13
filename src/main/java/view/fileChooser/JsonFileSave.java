package view.fileChooser;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import java.io.File;

/**
 * Creates a JFileChooser in SAVE mode used to select only JSON files.
 */
public class JsonFileSave extends JFileChooser implements IJsonFileChooser {

    private int returnState = -1;

    /**
     * Class constructor used to create a JFileChooser in SAVE mode for JSON files
     */
    public JsonFileSave() {
        super(FileSystemView.getFileSystemView().getHomeDirectory());

        this.setAcceptAllFileFilterUsed(false);
        FileNameExtensionFilter filter = new FileNameExtensionFilter("*.json", "json");
        this.addChoosableFileFilter(filter);

        returnState = this.showSaveDialog(null);
    }

    /**
     * Returns the path of selected file. If a file name is entered and don't have extension, the JSON extension is added.
     * @return Absolute path of selected file or an empty string if nothing is selected.
     */
    public String getAbsolutePathOfSelectedFile() {

        if(returnState == JFileChooser.APPROVE_OPTION) {
            File selectedFile = this.getSelectedFile();

            String fileExtension = FilenameUtils.getExtension(selectedFile.getName());

            if(StringUtils.equalsIgnoreCase(fileExtension, "json")) {
                    return selectedFile.getAbsolutePath();
            }

            if(StringUtils.isEmpty(fileExtension))
                return selectedFile.getAbsolutePath() + ".json";
        }

        return StringUtils.EMPTY;
    }

    /**
     * Called when the user hits the SAVE button. If user select a file and file already exists on disk,
     * user have to decide to overwrite it or not.
     */
    @Override
    public void approveSelection() {

        File selectedFile = getSelectedFile();

        if (selectedFile.exists()) {
            String message = selectedFile.getName() + " already exists\n"
                    + "Do you want to overwrite it?";
            String title = "Confirm Save As";

            int result = JOptionPane.showConfirmDialog(this, message, title, JOptionPane.YES_NO_OPTION);

            switch (result) {
                case JOptionPane.YES_OPTION:
                    super.approveSelection();
                    return;
                case JOptionPane.CANCEL_OPTION:
                    super.cancelSelection();
                    return;
                default:
                    return;
            }
        }
        super.approveSelection();
    }
}
