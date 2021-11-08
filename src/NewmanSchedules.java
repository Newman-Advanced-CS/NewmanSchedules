import NewmanSchedulesGUI.Panel;
import NewmanSchedulesGUI.Text;
import NewmanSchedulesGUI.Window;

import javax.swing.*;
import javax.swing.UIManager;
import java.awt.*;
import java.io.*;

public class NewmanSchedules {

    static Font ubuntuFontBold;
    static Font ubuntuFont;
    public static void main(String[] args)  {
        // Set theme
        try{
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");

            // Load font
            ubuntuFontBold = Font.createFont(Font.TRUETYPE_FONT, new File("src/Resources/Ubuntu-Bold.ttf")).deriveFont(15f);
            ubuntuFont = Font.createFont(Font.TRUETYPE_FONT, new File("src/Resources/Ubuntu-Regular.ttf")).deriveFont(15f);
        }catch (Exception e)
        {
            e.printStackTrace();
        }

        MainWindow();
    }

    // Main window
    static int WIDTH = 500;
    static int HEIGHT = 700;
    public static void MainWindow(){
        // Create window
        JFrame mainWindow = Window.CreateWindow("Newman Schedules");
        mainWindow.setMinimumSize(new Dimension(WIDTH, HEIGHT));
        mainWindow.getContentPane().setBackground(Color.WHITE);
        mainWindow.setResizable(false);

        // Add header
        JPanel header = Panel.CreatePanel(WIDTH, 50);
        header.setBackground(Color.BLUE);
        JLabel label = Text.CreateLabel("Newman Schedules", new Dimension(300, 40), SwingConstants.CENTER);
        label.setFont(ubuntuFontBold);
        label.setForeground(Color.WHITE);
        Panel.AddText(header, label, BorderLayout.CENTER);
        Window.AddPanel(mainWindow, header, BorderLayout.NORTH);

        // Center Text
        JLabel text = Text.CreateLabel("Loading Schedule... (jk not really, creating GUI in java is hard)", new Dimension(300, 40), SwingConstants.CENTER);
        Window.AddText(mainWindow, text, BorderLayout.CENTER);

        // Display it
        Window.DisplayWindow(mainWindow);

        // Test web request
        try{
            WebRequest.GET("https://api.nasa.gov/planetary/apod?api_key=DEMO_KEY");
        }catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
