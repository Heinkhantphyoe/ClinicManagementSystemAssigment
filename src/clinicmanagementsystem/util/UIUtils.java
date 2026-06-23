package clinicmanagementsystem.util;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import javax.swing.JButton;

public class UIUtils {
    public static void styleButtons(Container container) {
        for (Component comp : container.getComponents()) {
            if (comp instanceof JButton) {
                JButton btn = (JButton) comp;
                
                // Determine button type based on text to assign specific colors
                String text = btn.getText().toLowerCase();
                
                if (text.contains("delete") || text.contains("clear") || text.contains("back") || text.contains("reject")) {
                    btn.setBackground(new Color(220, 53, 69)); // Danger Red
                    btn.setForeground(Color.WHITE);
                } else if (!text.contains("update appointment status") && (text.contains("add") || text.contains("save") || text.contains("update") || text.contains("approve"))) {
                    btn.setBackground(new Color(40, 167, 69)); // Success Green
                    btn.setForeground(Color.WHITE);
                } else if (text.contains("search") || text.contains("login")) {
                    btn.setBackground(new Color(0, 123, 255)); // Primary Blue
                    btn.setForeground(Color.WHITE);
                } else {
                    btn.setBackground(new Color(108, 117, 125)); // Secondary Gray
                    btn.setForeground(Color.WHITE);
                }
                
                btn.setFocusPainted(false);
                btn.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 12));
            } else if (comp instanceof Container) {
                styleButtons((Container) comp);
            }
        }
    }
}
