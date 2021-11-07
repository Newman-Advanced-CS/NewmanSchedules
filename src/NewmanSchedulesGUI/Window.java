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

    public static void AddText(JFrame window, JLabel text, String layout)
    {
        window.getContentPane().add(text, layout);
    }

    public static void AddPanel(JFrame window, JPanel panel, String layout)
    {
        window.add(panel, layout);
    }
}
