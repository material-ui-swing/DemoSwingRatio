package io.vincentpalazzo.ratio.view;

import mdlaf.MaterialLookAndFeel;
import mdlaf.themes.JMarsDarkTheme;
import mdlaf.themes.MaterialTheme;
import mdlaf.utils.MaterialColors;
import org.material.component.swingsnackbar.view.BasicSnackBarUI;
import javax.swing.*;

/**
 * @author https://github.com/vincenzopalazzo
 */
public class AppTheme extends JFrame {


    protected MaterialTheme theme;
    protected LookAndFeel actualLaF;

    public AppTheme(){
        //TODO configure with file
        theme = new JMarsDarkTheme();
        actualLaF = new MaterialLookAndFeel(theme);
    }

    public MaterialTheme getTheme() {
        return theme;
    }

    public void changeThemeApp(MaterialTheme newTheme){
        MaterialLookAndFeel.changeTheme(newTheme);
        this.theme = newTheme;
        SwingUtilities.updateComponentTreeUI(this);
    }

    public void changeLookAndFeel(LookAndFeel lookAndFeel){
        try {
            if(lookAndFeel instanceof MaterialLookAndFeel && theme != null){
                MaterialLookAndFeel materialLookAndFeel = (MaterialLookAndFeel) lookAndFeel;
                materialLookAndFeel.setTheme(theme);
            }
            UIManager.setLookAndFeel(lookAndFeel);
            actualLaF = lookAndFeel;
            SwingUtilities.updateComponentTreeUI(this);
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
    }

    public void configureTheme(){
        try {
            JDialog.setDefaultLookAndFeelDecorated(true);
            UIManager.put("SnackBarUI", BasicSnackBarUI.class.getCanonicalName());
            UIManager.put("SnackBar.background", MaterialColors.COSMO_LIGTH_GRAY);
            UIManager.put("SnackBar.foreground", MaterialColors.BLACK);
            UIManager.put("SnackBar.border", BorderFactory.createEmptyBorder(5,5,5,5));
            UIManager.put("SnackBar.arc", 3);
            UIManager.setLookAndFeel(actualLaF);
            //UIManager.setLookAndFeel(new MetalLookAndFeel());
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
    }

}
