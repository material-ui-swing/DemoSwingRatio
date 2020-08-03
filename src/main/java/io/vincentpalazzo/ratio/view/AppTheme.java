/**
 * MIT License
 *
 * Copyright (c) 2020 Vincenzo Palazzo vincenzopalazzodev@gmail.com
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
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

    static {
        JDialog.setDefaultLookAndFeelDecorated(true);
    }

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
