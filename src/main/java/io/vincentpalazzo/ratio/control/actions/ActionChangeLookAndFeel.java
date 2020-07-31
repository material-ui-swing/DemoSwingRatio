package io.vincentpalazzo.ratio.control.actions;

import com.google.inject.Inject;
import io.vincentpalazzo.ratio.view.IFrameApp;
import mdlaf.MaterialLookAndFeel;
import javax.swing.*;
import javax.swing.plaf.metal.MetalLookAndFeel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ActionChangeLookAndFeel extends AbstractAction implements ActionListener {

    @Inject
    private IFrameApp frameApp;

    @Override
    public void actionPerformed(ActionEvent e) {
        JRadioButtonMenuItem source = (JRadioButtonMenuItem) e.getSource();

        if(source == frameApp.getMaterialLAndF()){
            frameApp.changeLookAndFeel(new MaterialLookAndFeel());
            return;
        }
        frameApp.changeLookAndFeel(new MetalLookAndFeel());
    }
}
