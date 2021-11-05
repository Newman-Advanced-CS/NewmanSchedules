import NewmanSchedulesGUI.Window;

import javax.swing.*;
import javax.swing.UIManager;
import java.awt.*;

public class NewmanSchedules {
    public static void main(String[] args)  {
        // Set theme
        try{
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
        }catch (Exception e)
        {
            e.printStackTrace();
        }

        // Create window
        JFrame mainWindow = Window.CreateWindow("Newman Schedules");
        Window.AddText(mainWindow, "Newman Schedules", new Dimension(300, 40), BorderLayout.PAGE_START, SwingConstants.CENTER, Font.BOLD);
        Window.DisplayWindow(mainWindow);
    }
}
