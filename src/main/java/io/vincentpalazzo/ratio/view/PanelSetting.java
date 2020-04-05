package io.vincentpalazzo.ratio.view;

import io.vincentpalazzo.ratio.App;
import io.vincentpalazzo.ratio.control.MediatorActions;
import io.vincentpalazzo.ratio.model.ModelMediator;
import io.vincentpalazzo.ratio.model.RatioValue;
import io.vincentpalazzo.ratio.util.AppResourceManager;
import io.vincentpalazzo.ratio.util.Constant;
import io.vincentpalazzo.ratio.util.IAppResourceManager;
import io.vincentpalazzo.ratio.view.eception.ViewException;
import mdlaf.components.button.MaterialButtonUI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Singleton;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

@Singleton
public class PanelSetting extends JPanel implements IPanelSetting {

    private static final Logger LOGGER = LoggerFactory.getLogger(PanelSetting.class);

    private IAppResourceManager resourceManager = (IAppResourceManager) App.getInstance().getInstanceObject(AppResourceManager.class);
    private GroupLayout layout;
    private JComboBox<RatioValue> ratiosValue;
    private JTextField widthComponent;
    private JTextField hightComponent;
    private JButton submitSetting;
    private JLabel description;

    private Observable observable;

    private MediatorActions actions = (MediatorActions) App.getInstance().getInstanceObject(MediatorActions.class);

    public PanelSetting() {
        observable = new Observable();
        initView();
    }

    //This method init the View
    @Override
    public void initView() throws ViewException {
        initComponent();
        initActions();
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
        submitSetting.setUI(new IconButtonUI());

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

    private void initComboBox(JComboBox<RatioValue> ratiosValue) {
        ratiosValue.addItem(RatioValue.FOR_NINE);
        ratiosValue.addItem(RatioValue.ONE_ONE);
        ratiosValue.addItem(RatioValue.SEXTEEN_NINE);
        ModelMediator model = (ModelMediator) App.getInstance().getInstanceObject(ModelMediator.class);
        model.putBean(Constant.RATIO_SELECTED, ratiosValue.getSelectedItem());
    }

    @Override
    public void initActions() throws ViewException {
        ratiosValue.addActionListener(actions.getAction(Constant.ACTION_UPDATE_CONTENT_PANE));
        submitSetting.setAction(actions.getAction(Constant.ACTION_GENERATE_IMAGE));
    }

    @Override
    public void addObserver(Observer observer) {
        observable.addObserver(observer);
    }

    // getter and setter
    public JComboBox<RatioValue> getRatiosValue() {
        return ratiosValue;
    }

    public JTextField getWidthComponent() {
        return widthComponent;
    }

    public JTextField getHightComponent() {
        return hightComponent;
    }

    public class IconButtonUI extends MaterialButtonUI{

        @Override
        public void installUI(JComponent c) {
            super.installUI(c);
            buttonBorderToAll = true;
        }
    }
}
