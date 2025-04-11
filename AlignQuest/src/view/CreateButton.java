package view;

import javax.swing.*;

public class CreateButton {
  public static JButton createButton(String iconPath, String name, String command) {
    JButton button = new JButton(name);
    if (iconPath != null) {
      try {
        ImageIcon icon = new ImageIcon(iconPath);
        button.setIcon(icon);
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
      }
      catch (Exception e) {
        System.out.println(e.getMessage());
      }
    }

    if (command != null) {
      button.setActionCommand(command);
    }
    return button;
  }
}
