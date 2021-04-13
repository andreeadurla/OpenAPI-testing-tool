package view;

import javax.swing.*;
import java.awt.*;

/**
 * Defines methods used to edit components.
 */
public class Style {

    /**
     * Changes the aspect of a JTextField (dimension, font)
     * @param text component to be modified
     * @param dimension new preferred size
     * @param font new preferred font
     */
    public static void styleTextField(JTextField text, Dimension dimension, Font font) {
        text.setFont(font);
        text.setHorizontalAlignment(JTextField.CENTER);
        text.setPreferredSize(dimension);
    }

    /**
     * Changes the aspect of a JComboBox (dimension, font)
     * @param comboBox component to be modified
     * @param dimension new preferred size
     * @param font new preferred font
     */
    public static void styleComboBox(JComboBox comboBox, Dimension dimension, Font font) {
        comboBox.setFont(font);
        comboBox.setPreferredSize(dimension);
    }
}
