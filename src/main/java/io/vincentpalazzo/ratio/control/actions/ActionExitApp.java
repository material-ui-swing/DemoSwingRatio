package io.vincentpalazzo.ratio.control.actions;

import io.vincentpalazzo.ratio.App;
import io.vincentpalazzo.ratio.util.AppResourceManager;
import io.vincentpalazzo.ratio.util.Constant;
import io.vincentpalazzo.ratio.util.IAppResourceManager;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * @author https://github.com/vincenzopalazzo
 */
public class ActionExitApp extends AbstractAction {

    private IAppResourceManager resourceManager = (IAppResourceManager) App.getInstance().getInstanceObject(AppResourceManager.class);

    public ActionExitApp(){
        putValue(Action.NAME, resourceManager.getResourceString(Constant.MENU_I_EXIT_VALUE));
        //TODO another value
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.exit(0);
    }
}
