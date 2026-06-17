package clinicmanagementsystem.util;

import javax.swing.UIManager;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Shared Swing helpers. Centralizes the Nimbus look-and-feel setup that is
 * otherwise copy-pasted into every screen's main() method.
 */
public final class UI {

    private static final Logger logger = Logger.getLogger(UI.class.getName());

    private UI() { }

    /** Apply the Nimbus look and feel if available; otherwise stay default. */
    public static void setNimbus() {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(Level.WARNING, "Nimbus look and feel unavailable", ex);
        }
    }
}
