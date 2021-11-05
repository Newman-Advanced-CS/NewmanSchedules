package NewmanSchedulesGUI;

import javax.swing.*;
import java.awt.*;

public class Window {
    public static JFrame CreateWindow(String windowName)
    {
        JFrame window = new JFrame(windowName);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        return window;
    }

    public static void DisplayWindow(JFrame toDisplay)
    {
        toDisplay.setLocationRelativeTo(null);
        toDisplay.pack();
        toDisplay.setVisible(true);
    }

    public static void AddText(JFrame window, String text, Dimension size, String layout, int swingConstant, int fontType)
    {
        JLabel label = new JLabel(text, swingConstant);
        Font font = new Font("Roboto", fontType,12);
        label.setFont(font);
        label.setPreferredSize(size);
        window.getContentPane().add(label, layout);
    }
}
