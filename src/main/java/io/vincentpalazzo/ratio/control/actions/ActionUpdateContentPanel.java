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

import io.vincentpalazzo.ratio.App;
import io.vincentpalazzo.ratio.model.ModelMediator;
import io.vincentpalazzo.ratio.model.RatioValue;
import io.vincentpalazzo.ratio.util.Constant;
import io.vincentpalazzo.ratio.view.IMainPanel;
import io.vincentpalazzo.ratio.view.IPanelSetting;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ActionUpdateContentPanel extends AbstractAction implements ActionListener{

    private static final Logger LOGGER = LoggerFactory.getLogger(ActionUpdateContentPanel.class);

    @Override
    //@UpdateContentPanelAOP Don't work
    public void actionPerformed(ActionEvent e) {
        IPanelSetting panelSetting = (IPanelSetting) App.getInstance().getInstanceObject(IPanelSetting.class);
        ModelMediator model = (ModelMediator) App.getInstance().getInstanceObject(ModelMediator.class);
        JComboBox comboBox = panelSetting.getRatiosValue();
        LOGGER.debug("Value combo box selected: " + comboBox.getSelectedItem().toString());
        model.putBean(Constant.RATIO_SELECTED, comboBox.getSelectedItem());
        IMainPanel main = (IMainPanel) App.getInstance().getInstanceObject(IMainPanel.class);
        main.refreshUI();
        RatioValue ratioValue = (RatioValue) comboBox.getSelectedItem();
        JTextField width = panelSetting.getWidthComponent();
        width.setText(ratioValue.getWidth() + "");
        JTextField height = panelSetting.getHightComponent();
        height.setText(ratioValue.getHeight() + "");
    }
}
