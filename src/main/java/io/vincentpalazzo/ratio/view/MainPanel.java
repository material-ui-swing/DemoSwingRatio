package io.vincentpalazzo.ratio.view;

import io.vincentpalazzo.ratio.App;
import io.vincentpalazzo.ratio.view.eception.ViewException;

import javax.swing.*;
import java.awt.*;

public class MainPanel extends JSplitPane implements IMainPanel{

    private static final int DIMENSION_X = 500;
    private static final int DIMENSION_Y = 1200;

    private JPanel panelSetting;
    private JPanel panelImages;

    @Override
    public void initView() throws ViewException {
        initComponent();
    }

    @Override
    public void initComponent() throws ViewException {
        setDividerLocation(0.3);
        panelSetting = (JPanel) App.getInstance().getInstanceObject(IPanelSetting.class);
        panelImages = new JPanel();
        panelImages.add(new JLabel("This contains only the setting information"));
        super.setLeftComponent(panelSetting);
        super.setRightComponent(new JScrollPane(panelImages));
    }

    @Override
    public void initActions() throws ViewException {
        //do nothing at the moment
    }
}
