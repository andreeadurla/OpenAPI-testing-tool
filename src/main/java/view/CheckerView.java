package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * The view of an OpenAPI Checker
 */
public class CheckerView extends JDialog {

    private JFrame frame;

    //InputURL
    private JTextField inputUrlField = new JTextField();
    private JButton browseURLButton = new JButton("\uD83D\uDCC1");
    private JButton confirmURLButton = new JButton(	"\u2713");

    //OutputURL
    private JTextField outputFileField = new JTextField();
    private JButton browseOutputButton = new JButton("\uD83D\uDCC1");

    //Services
    private JRadioButton currencyRB = new JRadioButton("currency");
    private JRadioButton testRB = new JRadioButton("test");

    //Ports
    private JComboBox portsComboBox = new JComboBox();

    //Operations
    private JComboBox operationsComboBox = new JComboBox();

    //Checkboxes
    private JCheckBox requiredContentCB = new JCheckBox("Only required content");
    private JCheckBox includeExamplestCB = new JCheckBox("Include examples");

    //Buttons
    private JButton sendButton = new JButton("Send");
    private JButton cancelButton = new JButton("Cancel");

    /**
     * Class constructor used to create Checker view.
     * @param frame the frame from which the dialog is displayed
     */
    public CheckerView(JFrame frame) {
        super(frame, "OpenAPI Checker");

        this.frame = frame;

        //Style JDialog
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setPreferredSize(new Dimension(680, 600));
        this.setLocationRelativeTo(frame);

        //Create Form
        this.add(createForm());

        this.pack();
        this.setVisible(true);
    }

    /**
     * Creates the main panel
     * @return panel created
     */
    private JPanel createForm() {
        JPanel form = new JPanel();

        //Create form's layout
        form.setLayout(new GridBagLayout());

        //Define constraints
        GridBagConstraints c = new GridBagConstraints();
        c.anchor = GridBagConstraints.LINE_START;
        c.insets.set(10, 10, 10, 10);

        //OpenAPI URL
        c.gridx = 0; c.gridy = 0;
        form.add(new JLabel("OpenAPI URL:"), c);

        c.gridx = 1; c.gridy = 0;
        form.add(chooseURLForm(), c);

        //Services
        c.gridx = 0; c.gridy = 1;
        form.add(new JLabel("Services:"), c);

        c.gridx = 1; c.gridy = 1;
        form.add(createRadioButtonsForm(), c);

        //Ports
        c.gridx = 0; c.gridy = 2;
        form.add(new JLabel("Ports:"), c);

        c.gridx = 1; c.gridy = 2;
        Style.styleComboBox(portsComboBox, new Dimension(200, 25), new Font("Arial", Font.PLAIN, 13));
        form.add(portsComboBox, c);

        //Operations
        c.gridx = 0; c.gridy = 3;
        form.add(new JLabel("Operations:"), c);

        c.gridx = 1; c.gridy = 3;
        Style.styleComboBox(operationsComboBox, new Dimension(200, 25), new Font("Arial", Font.PLAIN, 13));
        form.add(operationsComboBox, c);

        //Check boxes
        c.gridwidth = 2;
        c.gridx = 0; c.gridy = 4;
        form.add(createCheckBoxesForm(), c);

        //Output File
        c.gridwidth = 1;
        c.gridx = 0; c.gridy = 5;
        form.add(new JLabel("Output File:"), c);

        c.gridx = 1; c.gridy = 5;
        form.add(chooseOutputFileForm(), c);

        //Buttons' group
        c.anchor = GridBagConstraints.LAST_LINE_END;
        c.insets.set(40, 10, 10, 10);
        c.gridx = 1; c.gridy = 6;
        form.add(createButtonsGroup(), c);

        return form;
    }

    /**
     * Creates the form for URL and the corresponding button
     * @return panel created
     */
    private JPanel chooseURLForm() {
        JPanel form = new JPanel();

        Style.styleTextField(inputUrlField, new Dimension(400, 25), new Font("Arial", Font.ITALIC, 13));
        form.add(inputUrlField);

        form.add(browseURLButton);
        form.add(confirmURLButton);

        return form;
    }

    /**
     * Creates the panel for radio buttons
     * @return panel created
     */
    private JPanel createRadioButtonsForm() {
        JPanel form = new JPanel();

        //add RadioButtons into group
        ButtonGroup group = new ButtonGroup();
        group.add(currencyRB);
        group.add(testRB);

        form.add(currencyRB);
        form.add(testRB);

        return form;
    }

    /**
     * Creates the panel for check boxes
     * @return panel created
     */
    private JPanel createCheckBoxesForm() {
        JPanel form = new JPanel();

        form.setLayout(new BoxLayout(form, BoxLayout.Y_AXIS));

        form.add(requiredContentCB);
        form.add(includeExamplestCB);

        return form;
    }

    /**
     * Creates the form for the path of output file and the corresponding button
     * @return panel created
     */
    private JPanel chooseOutputFileForm() {
        JPanel form = new JPanel();

        //add components
        Style.styleTextField(outputFileField, new Dimension(400, 25), new Font("Arial", Font.ITALIC, 13));
        form.add(outputFileField);

        form.add(browseOutputButton);

        return form;
    }

    /**
     * Creates the panel for SEND and CANCEL buttons
     * @return panel created
     */
    private JPanel createButtonsGroup() {
        JPanel form = new JPanel();

        form.add(sendButton);
        form.add(cancelButton);

        return form;
    }

    /**
     * Removes all items from the operations ComboBox
     */
    public void clearOperationsComboBox() {
        operationsComboBox.removeAllItems();
    }

    public String getInputUrl() {
        return inputUrlField.getText();
    }

    public String getOutputFile() {
        return outputFileField.getText();
    }

    public void closeDialog() {
        this.dispose();
    }

    public void insertInPortsComboBox(String port) {
        portsComboBox.addItem(port);
    }

    public void insertInOperationsComboBox(String operation) {
        operationsComboBox.addItem(operation);
    }

    public void setInputUrlField(String url) {
        inputUrlField.setText(url);
    }

    public void setOutputUrlField(String filePath) {
        outputFileField.setText(filePath);
    }

    public void addBrowseURLButtonListener(ActionListener e) {
        browseURLButton.addActionListener(e);
    }

    public void addConfirmURLButtonListener(ActionListener e) {
        confirmURLButton.addActionListener(e);
    }

    public void addBrowseOutputButtonListener(ActionListener e) {
        browseOutputButton.addActionListener(e);
    }

    public void addSendButtonListener(ActionListener e) {
        sendButton.addActionListener(e);
    }

    public void addCancelButtonListener(ActionListener e) {
        cancelButton.addActionListener(e);
    }
}
