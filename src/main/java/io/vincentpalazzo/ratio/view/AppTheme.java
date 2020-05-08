package io.vincentpalazzo.ratio.view;

import com.google.inject.Inject;
import io.swingsnackbar.view.BasicSnackBarUI;
import mdlaf.MaterialLookAndFeel;
import mdlaf.themes.JMarsDarkTheme;
import mdlaf.themes.MaterialLiteTheme;
import mdlaf.themes.MaterialTheme;
import mdlaf.utils.MaterialColors;

import javax.swing.*;
import java.awt.*;

/**
 * @author https://github.com/vincenzopalazzo
 */
public class AppTheme extends JFrame {

    private MaterialTheme theme;

    public AppTheme(){
        theme = new JMarsDarkTheme();
    }

    public MaterialTheme getTheme() {
        return theme;
    }

    public void changeThemeApp(MaterialTheme newTheme){
        MaterialLookAndFeel.changeTheme(newTheme);
        SwingUtilities.updateComponentTreeUI(this);
        this.theme = newTheme;
    }

    public void configureTheme(){
        try {
            JDialog.setDefaultLookAndFeelDecorated(true);
            UIManager.put("SnackBarUI", BasicSnackBarUI.class.getCanonicalName());
            UIManager.put("SnackBar.background", MaterialColors.COSMO_LIGTH_GRAY);
            UIManager.put("SnackBar.foreground", MaterialColors.BLACK);
            UIManager.put("SnackBar.border", BorderFactory.createEmptyBorder(5,5,5,5));
            UIManager.put("SnackBar.arc", 3);
            UIManager.setLookAndFeel(new MaterialLookAndFeel(theme));
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
    }

}
