import NewmanSchedulesGUI.Panel;
import NewmanSchedulesGUI.Text;
import NewmanSchedulesGUI.TextInput;
import NewmanSchedulesGUI.Window;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

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

        LoginWindow(false);
    }

    // Login window
    static int loginWIDTH = 400;
    static int loginHEIGHT = 100;
    public static void LoginWindow(boolean retry)
    {
        // Create window
        JFrame loginWindow = Window.CreateWindow("Newman Schedules");
        loginWindow.setMinimumSize(new Dimension(loginWIDTH, loginHEIGHT));
        loginWindow.getContentPane().setBackground(Color.WHITE);
        loginWindow.setResizable(false);

        // Add header
        JPanel header = Panel.CreatePanel(WIDTH, 50, false);
        header.setBackground(Color.BLUE);
        JLabel label = Text.CreateLabel("Login", new Dimension(300, 40), SwingConstants.CENTER);
        label.setFont(ubuntuFontBold);
        label.setForeground(Color.WHITE);
        Panel.AddComponent(header, label, BorderLayout.CENTER);
        Window.AddComponent(loginWindow, header, BorderLayout.PAGE_START);

        // Login screen
        JPanel login = Panel.CreatePanel(loginWIDTH/2, loginHEIGHT/2, false);
        login.setBackground(Color.WHITE);
        Dimension boxDimensions = new Dimension(10, 15);
        float[] textBoxColor = new float[3];
        Color.RGBtoHSB(230, 230, 230, textBoxColor);

        JTextField emailBox = TextInput.CreateTextInput("Email...", boxDimensions, ubuntuFont);
        JTextField passBox = TextInput.CreatePasswordInput("", boxDimensions, ubuntuFont);
        emailBox.setBackground(Color.getHSBColor(textBoxColor[0], textBoxColor[1], textBoxColor[2]));
        passBox.setBackground(Color.getHSBColor(textBoxColor[0], textBoxColor[1], textBoxColor[2]));
        Panel.AddComponent(login, emailBox, BorderLayout.PAGE_START);
        Panel.AddComponent(login, passBox, BorderLayout.PAGE_END);

        // Button
        JButton submit = new JButton("LOGIN");
        submit.setSize(new Dimension(loginWIDTH, loginHEIGHT/2));
        submit.addActionListener(e -> {
            // Get input and perform web request
            String email = emailBox.getText();
            String pass = passBox.getText();

            String loginResult = "";
            try {
                loginResult = WebRequest.GET("/login.php?email=" + email + "&pass=" + pass);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            System.out.println(loginResult);

            if(loginResult.equals("false"))
            {
                // Reload window
                LoginWindow(true);
            }
        });

        Window.AddComponent(loginWindow, submit, BorderLayout.SOUTH);
        Window.AddComponent(loginWindow, login, BorderLayout.CENTER);

        if(retry)
        {
            JLabel retryAlert = Text.CreateLabel("Incorrect Email/Password", new Dimension(loginWIDTH, loginHEIGHT/4), SwingConstants.CENTER);
            Window.AddComponent(loginWindow, retryAlert, BorderLayout.SOUTH);
        }

        // Display it
        Window.DisplayWindow(loginWindow);
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
        JPanel header = Panel.CreatePanel(WIDTH, 50, false);
        header.setBackground(Color.BLUE);
        JLabel label = Text.CreateLabel("Newman Schedules", new Dimension(300, 40), SwingConstants.CENTER);
        label.setFont(ubuntuFontBold);
        label.setForeground(Color.WHITE);
        Panel.AddComponent(header, label, BorderLayout.CENTER);
        Window.AddComponent(mainWindow, header, BorderLayout.NORTH);

        // Center Text
        JLabel text = Text.CreateLabel("Loading Schedule... (jk not really, creating GUI in java is hard)", new Dimension(300, 40), SwingConstants.CENTER);
        Window.AddComponent(mainWindow, text, BorderLayout.CENTER);

        // Display it
        Window.DisplayWindow(mainWindow);

        // Test web request
        String testContents = "";
        try{
            testContents = WebRequest.GET("/getUser.php?ID=0&pass=password");
        }catch (IOException e)
        {
            e.printStackTrace();
            return;
        }
        System.out.println(testContents);
        JLabel test = Text.CreateLabel(testContents, new Dimension(200, 500), SwingConstants.LEFT);
        Window.AddComponent(mainWindow, test, BorderLayout.AFTER_LAST_LINE);
    }
}
