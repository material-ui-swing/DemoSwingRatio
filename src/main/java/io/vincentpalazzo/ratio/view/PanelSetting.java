package io.vincentpalazzo.ratio.view;

import io.vincentpalazzo.ratio.App;
import io.vincentpalazzo.ratio.util.AppResourceManager;
import io.vincentpalazzo.ratio.util.Constant;
import io.vincentpalazzo.ratio.util.IAppResourceManager;
import io.vincentpalazzo.ratio.view.eception.ViewException;

import javax.swing.*;
import java.awt.*;

public class PanelSetting extends JPanel implements IPanelSetting {

    private static final int MAX_WITH = 150;

    private IAppResourceManager resourceManager = (IAppResourceManager) App.getInstance().getInstanceObject(AppResourceManager.class);
    private GroupLayout layout;
    private JComboBox<String> ratiosValue;
    private JTextField widthComponent;
    private JTextField hightComponent;
    private JButton submitSetting;
    private JLabel description;

    public PanelSetting() {
        initView();
    }

    //This method init the View
    @Override
    public void initView() throws ViewException {
        initComponent();

        super.setVisible(true);
    }

    @Override
    public void initComponent() throws ViewException {
        //Init component
        ratiosValue = new JComboBox<>();
        initComboBox(ratiosValue);

        widthComponent = new JTextField();
        hightComponent = new JTextField();

        submitSetting = new JButton("attending action");

        layout = new GroupLayout(this);
        super.setLayout(layout);

        description = new JLabel(resourceManager.getResourceString(Constant.DESCRIPTION_SETTING_PANEL));

        layout.setAutoCreateContainerGaps(true);
        layout.setAutoCreateGaps(true);
        //Set position of component
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addGap(15)
                        .addComponent(description, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE)
                        .addGap(15)
                        .addComponent(ratiosValue, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE)
                        .addGap(15)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(widthComponent, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE)
                                .addComponent(hightComponent, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE))
                        .addGap(25)
                        .addComponent(submitSetting, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE)

        );

        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addGap(15)
                        .addComponent(description, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addGap(25)
                        .addComponent(ratiosValue, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addGap(50)
                        .addGroup(layout.createParallelGroup()
                                .addComponent(widthComponent, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addComponent(hightComponent, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                        )
                        .addGap(25)
                        .addComponent(submitSetting, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addGap(25)
        );

    }

    private void initComboBox(JComboBox<String> ratiosValue) {
        ratiosValue.addItem("16:9");
        ratiosValue.addItem("16:10");
    }

    @Override
    public void initActions() throws ViewException {

    }
}
