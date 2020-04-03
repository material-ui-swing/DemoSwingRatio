package io.vincentpalazzo.ratio.view;

import io.vincentpalazzo.ratio.model.RatioValue;

import javax.swing.*;
import java.util.Observer;

public interface IPanelSetting extends IAppViewComponent{

    void addObserver(Observer observer);

    JComboBox<RatioValue> getRatiosValue();

    JTextField getWidthComponent();

    JTextField getHightComponent();
}
