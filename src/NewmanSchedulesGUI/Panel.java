package NewmanSchedulesGUI;

import javax.swing.*;
import java.awt.*;

public class Panel {
    public static JPanel CreatePanel(int width, int height, boolean boxLayout){
        JPanel jPanel = new JPanel();
        jPanel.setSize(width, height);
        if(boxLayout)
            jPanel.setLayout(new BoxLayout(jPanel, BoxLayout.PAGE_AXIS));
        return jPanel;
    }

    public static void AddComponent(JPanel panel, Component component, String layout)
    {
        panel.add(component, layout);
    }
}
