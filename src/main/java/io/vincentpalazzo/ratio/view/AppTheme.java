package io.vincentpalazzo.ratio.view;

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

    public void configureTheme(){
        try {
            JDialog.setDefaultLookAndFeelDecorated(true);
            UIManager.setLookAndFeel(new MaterialLookAndFeel(theme));
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
    }

}
