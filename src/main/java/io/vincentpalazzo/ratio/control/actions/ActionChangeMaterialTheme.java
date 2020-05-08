package io.vincentpalazzo.ratio.control.actions;

import com.google.inject.Inject;
import io.vincentpalazzo.ratio.App;
import io.vincentpalazzo.ratio.model.ModelMediator;
import io.vincentpalazzo.ratio.util.Constant;
import io.vincentpalazzo.ratio.util.IAppResourceManager;
import io.vincentpalazzo.ratio.view.IFrameApp;
import mdlaf.themes.JMarsDarkTheme;
import mdlaf.themes.MaterialLiteTheme;
import mdlaf.themes.MaterialOceanicTheme;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ActionChangeMaterialTheme extends AbstractAction implements ActionListener {

    @Inject
    private IFrameApp frameApp;

    @Override
    public void actionPerformed(ActionEvent e) {
        JRadioButtonMenuItem origin = (JRadioButtonMenuItem) e.getSource();
        if(origin == frameApp.getJmarDark()){
            frameApp.changeThemeApp(new JMarsDarkTheme());
        }else if(origin == frameApp.getMaterialLite()){
            frameApp.changeThemeApp(new MaterialLiteTheme());
        }else if(origin == frameApp.getMaterialOceanic()){
            frameApp.changeThemeApp(new MaterialOceanicTheme());
        }
        frameApp.buildStyleMenu();
    }

    @Inject
    public void setResourceManager(IAppResourceManager resourceManager) {
        putValue(Action.NAME, resourceManager.getResourceString(Constant.TEXT_CHANGE_LANDF));
    }
}
