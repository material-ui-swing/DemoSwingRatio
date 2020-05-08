package io.vincentpalazzo.ratio.control.actions;

import com.google.inject.Inject;
import io.vincentpalazzo.ratio.util.Constant;
import io.vincentpalazzo.ratio.util.IAppResourceManager;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * @author https://github.com/vincenzopalazzo
 */
public class ActionExitApp extends AbstractAction {


    public ActionExitApp(){
        putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke("CTRL E"));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.exit(0);
    }

    @Inject
    public void setResourceManager(IAppResourceManager resourceManager) {
        putValue(Action.NAME, resourceManager.getResourceString(Constant.MENU_I_EXIT_VALUE));
    }
}
