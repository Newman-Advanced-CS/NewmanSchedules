import DSYS.IO;
import NewmanSchedulesGUI.Panel;
import NewmanSchedulesGUI.Text;
import NewmanSchedulesGUI.TextInput;
import NewmanSchedulesGUI.Window;
import com.sun.tools.javac.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class NewmanSchedules {

    // Fonts
    static Font ubuntuFontBold;
    static Font ubuntuFont;

    public static void main(String[] args) {
        // Set theme
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");

            // Load font
            ubuntuFontBold = Font.createFont(Font.TRUETYPE_FONT, new File("src/Resources/Ubuntu-Bold.ttf")).deriveFont(15f);
            ubuntuFont = Font.createFont(Font.TRUETYPE_FONT, new File("src/Resources/Ubuntu-Regular.ttf")).deriveFont(15f);
        } catch (Exception e) {
            e.printStackTrace();
        }

        MainWindow();
    }

    // Main window
    static int WIDTH = 500;
    static int HEIGHT = 500;

    public static void MainWindow() {
        // Create window
        JFrame mainWindow = Window.CreateWindow("Newman Schedules");
        mainWindow.setMinimumSize(new Dimension(WIDTH, HEIGHT));
        mainWindow.getContentPane().setBackground(Color.WHITE);
        mainWindow.setResizable(false);

        // Add header with users first name
        JPanel header = Panel.CreatePanel(WIDTH, 50, false);
        header.setBackground(Color.BLUE);
        JLabel label = Text.CreateLabel("Your Schedule:", new Dimension(300, 40), SwingConstants.CENTER);
        label.setFont(ubuntuFontBold);
        label.setForeground(Color.WHITE);
        Panel.AddComponent(header, label, BorderLayout.CENTER);
        Window.AddComponent(mainWindow, header, BorderLayout.NORTH);

        // Get schedule
        String schedule = "ERROR";
        schedule = "<html>" + IO.read("src/schedule.txt") + "</html>";

        // Center Text
        JLabel text = Text.CreateLabel(schedule, new Dimension(WIDTH/20, HEIGHT), SwingConstants.CENTER);
        text.setVerticalAlignment(SwingConstants.CENTER);
        Window.AddComponent(mainWindow, text, BorderLayout.CENTER);

        // Display it
        Window.DisplayWindow(mainWindow);
    }
}

