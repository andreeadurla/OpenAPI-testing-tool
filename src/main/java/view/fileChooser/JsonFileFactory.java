package view.fileChooser;

/**
 * Creates objects JsonFileChooser.
 */
public class JsonFileFactory {

    /**
     * Creates a JsonFileChooser with selection mode indicated by parameter.
     * @param dialogType type of JsonFileChooser to be created
     * @return object JsonFileChooser created
     */
    public static IJsonFileChooser getFileChooser(FileChooserEnum dialogType) {
        IJsonFileChooser fileChooser = null;

        switch(dialogType) {
            case SAVE:
                fileChooser = new JsonFileSave();
                break;
            case OPEN:
                fileChooser = new JsonFileOpen();
                break;
        }

        return fileChooser;
    }
}
