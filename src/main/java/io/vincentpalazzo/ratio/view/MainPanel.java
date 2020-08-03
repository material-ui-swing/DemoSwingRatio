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

import com.google.inject.Inject;
import com.google.inject.Singleton;
import io.vincentpalazzo.ratio.model.ModelMediator;
import io.vincentpalazzo.ratio.model.RatioValue;
import io.vincentpalazzo.ratio.util.Constant;
import io.vincentpalazzo.ratio.view.eception.ViewException;
import mdlaf.utils.MaterialBorders;
import mdlaf.utils.MaterialColors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.*;

@Singleton
public class MainPanel extends JSplitPane implements IMainPanel {

    private static final Logger LOGGER = LoggerFactory.getLogger(MainPanel.class);

    private IPanelSetting panelSetting;
    private JPanel panelContainer;
    private PanelPresentation panelContent;
    private ModelMediator model;

    @Inject
    public MainPanel(IPanelSetting panelSetting, PanelPresentation panelContent, ModelMediator model) {
        this.panelSetting = panelSetting;
        this.panelContent = panelContent;
        this.model = model;
        initView();
    }

    @Override
    public void initView() throws ViewException {
        initComponent();
    }

    @Override
    public void initComponent() throws ViewException {
        setDividerLocation(0.3);
        panelContainer = new JPanel();
        //panelSetting.addObserver(panelContainer);

        this.initStyleComponent();

        panelContainer.setLayout(new GridBagLayout());
        panelContainer.add(panelContent, new GridBagConstraints());

        super.setLeftComponent((JPanel) panelSetting);
        super.setRightComponent(new JScrollPane(panelContainer));
    }

    private void initStyleComponent() {
        RatioValue ratio;
        if(model == null){
          ratio = RatioValue.ONE_ONE;
        }else{
            ratio = (RatioValue) model.getBean(Constant.RATIO_SELECTED);
        }
        int dimensionX = 1280;
        int dimensionY = 720;
        if (ratio != null) {
            LOGGER.debug("Dimension selected: " + ratio.toString());
            dimensionX = ratio.getWidth();
            dimensionY = ratio.getHeight();
        }

        if (panelContainer != null) {
            panelContent.setPreferredSize(new Dimension(dimensionX, dimensionY));
            panelContent.setMaximumSize(new Dimension(dimensionX, dimensionY));
            panelContent.setMinimumSize(new Dimension(dimensionX, dimensionY));

            panelContent.setBackground(MaterialColors.COSMO_STRONG_GRAY);
            panelContent.setBorder(MaterialBorders.LIGHT_SHADOW_BORDER);
        }
    }

    @Override
    public void initActions() throws ViewException {
        //do nothing at the moment
    }

    @Override
    public void repaint() {
        super.repaint();
        initStyleComponent();
    }

    @Override
    public void refreshUI() {
        panelContainer.revalidate();
        this.repaint();
    }
}
