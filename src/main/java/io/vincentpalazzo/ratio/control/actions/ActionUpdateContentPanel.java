package io.vincentpalazzo.ratio.control.actions;

import io.vincentpalazzo.ratio.App;
import io.vincentpalazzo.ratio.model.ModelMediator;
import io.vincentpalazzo.ratio.model.RatioValue;
import io.vincentpalazzo.ratio.util.Constant;
import io.vincentpalazzo.ratio.util.aop.annotations.UpdateContentPanelAOP;
import io.vincentpalazzo.ratio.view.IMainPanel;
import io.vincentpalazzo.ratio.view.IPanelSetting;
import io.vincentpalazzo.ratio.view.MainPanel;
import io.vincentpalazzo.ratio.view.PanelPresentation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ActionUpdateContentPanel extends AbstractAction implements ActionListener{

    private static final Logger LOGGER = LoggerFactory.getLogger(ActionUpdateContentPanel.class);

    @Override
    //@UpdateContentPanelAOP
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
