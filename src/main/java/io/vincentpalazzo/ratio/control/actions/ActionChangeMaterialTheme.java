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
package io.vincentpalazzo.ratio.control.actions;

import com.google.inject.Inject;
import io.materialtheme.darkstackoverflow.DarkStackOverflowTheme;
import io.vincentpalazzo.ratio.util.Constant;
import io.vincentpalazzo.ratio.util.IAppResourceManager;
import io.vincentpalazzo.ratio.view.IFrameApp;
import mdlaf.themes.JMarsDarkTheme;
import mdlaf.themes.MaterialLiteTheme;
import mdlaf.themes.MaterialOceanicTheme;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ActionChangeMaterialTheme extends AbstractAction implements ActionListener {

    @Inject
    private IFrameApp frameApp;

    @Override
    public void actionPerformed(ActionEvent e) {
        JRadioButtonMenuItem origin = (JRadioButtonMenuItem) e.getSource();
        if(origin == frameApp.getJmarDark()){
            frameApp.changeThemeApp(new JMarsDarkTheme());
        }else if(origin == frameApp.getMaterialLite()){
            frameApp.changeThemeApp(new MaterialLiteTheme());
        }else if(origin == frameApp.getMaterialOceanic()){
            frameApp.changeThemeApp(new MaterialOceanicTheme());
        } else if (origin == frameApp.getStackoverflowDark()){
            frameApp.changeThemeApp(new DarkStackOverflowTheme());
        }
    }

    @Inject
    public void setResourceManager(IAppResourceManager resourceManager) {
        putValue(Action.NAME, resourceManager.getResourceString(Constant.TEXT_CHANGE_LANDF));
    }
}
