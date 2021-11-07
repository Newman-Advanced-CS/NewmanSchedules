package NewmanSchedulesGUI;

import javax.swing.*;
import java.awt.*;

public class Text {
    public static JLabel CreateLabel(String text, Dimension size, int swingConstant)
    {
        JLabel label = new JLabel(text, swingConstant);
        label.setPreferredSize(size);
        return label;
    }

    public static void ChangeNewFont(JLabel label, int fontType, String fontName, int fontSize)
    {
        Font font = new Font(fontName, fontType, fontSize);
        label.setFont(font);
    }
}
