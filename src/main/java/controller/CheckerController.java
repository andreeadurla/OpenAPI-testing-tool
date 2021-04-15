package controller;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import parser.SchemaParser;
import validator.FileValidator;
import validator.UrlValidator;
import view.CheckerView;
import view.fileChooser.FileChooserEnum;
import view.fileChooser.IJsonFileChooser;
import view.fileChooser.JsonFileFactory;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The controller of an OpenAPI Checker
 */
public class CheckerController {

    private CheckerView view;

    /**
     * Class Constructor used to create the view for OpenAPI Checker, insert the required data
     * and defines the behaviour of each button.
     */
    public CheckerController() {

        this.view = new CheckerView(null);

        insertRequiredData();
        addActionListeners();
    }

    private void insertRequiredData() {
        insertPorts();
        //insertOperations();
    }

    /**
     * Insert required data in PortsCombobox
     */
    private void insertPorts() {
        List<String> ports = new ArrayList<>();
        //initial values
        ports.add("Soap1");
        ports.add("Soap2");

        ports.forEach(p -> view.insertInPortsComboBox(p));
    }

    /**
     * Insert required data in OperationsCombobox
     */
    private void insertOperations() {

        List<String> operations = SchemaParser.getOperations("file:/C:/Users/Andreea/Desktop/OpenAPI/swagger-petstore.json");

        operations.forEach(op -> view.insertInOperationsComboBox(op));
    }

    /**
     * Defines behaviours of each button.
     */
    private void addActionListeners() {
        view.addBrowseURLButtonListener(e -> chooseInputFile());
        view.addConfirmURLButtonListener(e -> confirmAndFindOperations());
        view.addBrowseOutputButtonListener(e -> chooseOutputFile());

        view.addSendButtonListener(e -> sendData());
        view.addCancelButtonListener(e -> cancelChecker());
    }

    /**
     * Creates a JsonFileChooser in OPEN mode and add selected file path in JTextField (in view).
     */
    private void chooseInputFile() {
        IJsonFileChooser fileChooser = JsonFileFactory.getFileChooser(FileChooserEnum.OPEN);
        String filePath = fileChooser.getAbsolutePathOfSelectedFile();

        if (StringUtils.isNotEmpty(filePath))
            view.setInputUrlField(filePath);
    }

    /**
     * Validates the input URL and parse this to get the name of performed operations.
     * Operations are inserted OperationsCombobox.
     */
    private void confirmAndFindOperations() {
        String inputUrl = view.getInputUrl();

        if(UrlValidator.isNotValid(inputUrl)) {

            JOptionPane.showMessageDialog(view, "Invalid data",
                    "Error", JOptionPane.ERROR_MESSAGE);

            return ;
        }

        view.clearOperationsComboBox();

        List<String> operations = SchemaParser.getOperations(inputUrl);

        operations.forEach(op -> view.insertInOperationsComboBox(op));
    }

    /**
     * Creates a JsonFileChooser in SAVE mode and add selected file path in JTextField (in view).
     */
    private void chooseOutputFile() {
        IJsonFileChooser fileChooser = JsonFileFactory.getFileChooser(FileChooserEnum.SAVE);
        String filePath = fileChooser.getAbsolutePathOfSelectedFile();

        if(StringUtils.isNotEmpty(filePath))
            view.setOutputUrlField(filePath);
    }

    /**
     * Validates the path of output file and send data.
     */
    private void sendData() {
        String outputFile = view.getOutputFile();

        if(BooleanUtils.isTrue(FileValidator.isFilePath(outputFile))) {

            JOptionPane.showMessageDialog(view, "Data was sent successfully",
                    "Success", JOptionPane.INFORMATION_MESSAGE);

            view.closeDialog();

            return ;
        }

        JOptionPane.showMessageDialog(view, "Invalid output file",
                    "Error", JOptionPane.ERROR_MESSAGE);

    }

    /**
     * Close the dialog.
     */
    private void cancelChecker() {
        view.closeDialog();
    }
}
