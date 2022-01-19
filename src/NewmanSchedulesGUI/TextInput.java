package NewmanSchedulesGUI;

import javax.swing.*;
import java.awt.*;

public class TextInput {
    public static JTextField CreateTextInput(String prompt, Dimension dimension, Font font)
    {
        JTextField field = new JTextField(prompt, dimension.width);
        field.setSize(dimension);
        field.setFont(font);
        return field;
    }

    public static JPasswordField CreatePasswordInput(String prompt, Dimension dimension, Font font)
    {
        JPasswordField field = new JPasswordField(prompt, dimension.width);
        field.setSize(dimension);
        field.setFont(font);
        return field;
    }
}
