package io.vincentpalazzo.ratio.control.actions;

import io.vincentpalazzo.ratio.App;
import io.vincentpalazzo.ratio.util.Constant;
import io.vincentpalazzo.ratio.util.IAppResourceManager;
import io.vincentpalazzo.ratio.view.DialogDeveloperInfo;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * @author https://github.com/vincenzopalazzo
 */
public class ActionViewDeveloper extends AbstractAction {

    private IAppResourceManager resourceManager = (IAppResourceManager) App.getInstance().getInstanceObject(IAppResourceManager.class);

    public ActionViewDeveloper(){
        putValue(Action.NAME, resourceManager.getResourceString(Constant.MENU_I_DEV_VALUE));
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        DialogDeveloperInfo dialogDev = (DialogDeveloperInfo) App.getInstance().getInstanceObject(DialogDeveloperInfo.class);
        dialogDev.initView();
    }
}
