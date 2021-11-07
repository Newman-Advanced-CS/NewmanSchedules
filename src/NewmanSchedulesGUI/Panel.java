package NewmanSchedulesGUI;

import javax.swing.*;

public class Panel {
    public static JPanel CreatePanel(int width, int height){
        JPanel jPanel = new JPanel();
        jPanel.setSize(width, height);
        return jPanel;
    }

    public static void AddText(JPanel panel, JLabel text, String layout)
    {
        panel.add(text, layout);
    }
}
