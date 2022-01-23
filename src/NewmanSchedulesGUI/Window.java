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

    public static void RemoveWindow(JFrame toRemove)
    {
        toRemove.setVisible(false);
    }

    public static void AddComponent(JFrame window, Component component, String layout)
    {
        window.getContentPane().add(component, layout);
    }
}
