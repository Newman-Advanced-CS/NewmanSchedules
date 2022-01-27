import NewmanSchedulesGUI.Panel;
import NewmanSchedulesGUI.Text;
import NewmanSchedulesGUI.TextInput;
import NewmanSchedulesGUI.Window;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class NewmanSchedules {

    // current session user
    protected static User user;

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

        LoginWindow();
    }

    // Login window
    static int loginWIDTH = 400;
    static int loginHEIGHT = 100;

    public static void LoginWindow() {
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
        JPanel login = Panel.CreatePanel(loginWIDTH / 2, loginHEIGHT / 2, false);
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
        submit.setSize(new Dimension(loginWIDTH, loginHEIGHT / 2));
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

            Window.RemoveWindow(loginWindow);
            if (loginResult.equals("false")) {
                // Alert
                Alert("Incorrect Email/Password", e1 -> {
                    LoginWindow();
                });
            }else{
                // Log in
                try {
                    user = new User(Integer.parseInt(loginResult), pass);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                // Load main window
                MainWindow();
            }
        });

        Window.AddComponent(loginWindow, submit, BorderLayout.SOUTH);
        Window.AddComponent(loginWindow, login, BorderLayout.CENTER);

        // Display it
        Window.DisplayWindow(loginWindow);
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
        JLabel label = Text.CreateLabel("Hi " + user.getFirstName() + "!", new Dimension(300, 40), SwingConstants.CENTER);
        label.setFont(ubuntuFontBold);
        label.setForeground(Color.WHITE);
        Panel.AddComponent(header, label, BorderLayout.CENTER);
        Window.AddComponent(mainWindow, header, BorderLayout.NORTH);

        // Get schedule
        String schedule = "ERROR";
        try {
            schedule = "<html>Your Classes: <br><hr>" + WebRequest.GET("/getSchedule.php?ID=" + user.getID() + "&pass=" + user.getPassword()) + "</html>";
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Center Text
        JLabel text = Text.CreateLabel(schedule, new Dimension(WIDTH/2, HEIGHT), SwingConstants.CENTER);
        text.setVerticalAlignment(SwingConstants.CENTER);
        Window.AddComponent(mainWindow, text, BorderLayout.CENTER);

        // Display it
        Window.DisplayWindow(mainWindow);
    }

    public static void Alert(String prompt, ActionListener callback)
    {
        JFrame alert = Window.CreateWindow("Alert");
        alert.setMinimumSize(new Dimension(100, 100));
        alert.getContentPane().setBackground(Color.WHITE);
        alert.setResizable(false);

        JLabel retryAlert = Text.CreateLabel(prompt, new Dimension(loginWIDTH, loginHEIGHT / 4), SwingConstants.CENTER);
        Window.AddComponent(alert, retryAlert, BorderLayout.CENTER);

        JButton OK = new JButton("OK");
        OK.addActionListener(callback);
        OK.addActionListener(e -> {
            Window.RemoveWindow(alert);
        });
        Window.AddComponent(alert, OK, BorderLayout.SOUTH);

        Window.DisplayWindow(alert);
    }
}
