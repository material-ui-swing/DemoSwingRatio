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
import io.vincentpalazzo.ratio.control.MediatorActions;
import io.vincentpalazzo.ratio.model.ModelMediator;
import io.vincentpalazzo.ratio.model.RatioValue;
import io.vincentpalazzo.ratio.util.Constant;
import io.vincentpalazzo.ratio.util.IAppResourceManager;
import io.vincentpalazzo.ratio.view.eception.ViewException;
import mdlaf.components.button.MaterialButtonUI;
import mdlaf.utils.MaterialImageFactory;
import mdlaf.utils.icons.MaterialIconFont;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.util.Observable;
import java.util.Observer;

@Singleton
public class PanelSetting extends JPanel implements IPanelSetting {

    private static final Logger LOGGER = LoggerFactory.getLogger(PanelSetting.class);

    private GroupLayout layout;
    private JComboBox<RatioValue> ratiosValue;
    private JTextField widthComponent;
    private JTextField hightComponent;
    private JButton submitSetting;
    private JLabel description;

    private Observable observable;

    private MediatorActions actions;
    private IAppResourceManager resourceManager;
    private ModelMediator model;

    @Inject
    public PanelSetting(MediatorActions actions, IAppResourceManager resourceManager, ModelMediator model) {
        this.actions = actions;
        this.resourceManager = resourceManager;
        this.model = model;
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

        submitSetting = new MaterialIconButton();
        submitSetting.setText(resourceManager.getResourceString(Constant.NAME_ACTION_GENERATE_IMAGE));

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
        model.putBean(Constant.RATIO_SELECTED, ratiosValue.getSelectedItem());
    }

    @Override
    public void initActions() throws ViewException {
        ratiosValue.addActionListener(actions.getAction(Constant.ACTION_UPDATE_CONTENT_PANE));
        submitSetting.addActionListener(actions.getAction(Constant.ACTION_GENERATE_IMAGE));
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


    public class MaterialIconButton extends JButton{

        public MaterialIconButton() {
            setUI(new IconButtonUI());
            setIcon(MaterialImageFactory.getInstance().getImage(
                    MaterialIconFont.PHOTO_CAMERA,
                    getForeground()
            ));
        }

        @Override
        public void updateUI() {
            setUI(new IconButtonUI());
            setIcon(MaterialImageFactory.getInstance().getImage(
                    MaterialIconFont.PHOTO_CAMERA,
                    getForeground()
            ));
        }

        public class IconButtonUI extends MaterialButtonUI{

            @Override
            public void installUI(JComponent c) {
                super.installUI(c);
                buttonBorderToAll = true;
            }
        }
    }
}
