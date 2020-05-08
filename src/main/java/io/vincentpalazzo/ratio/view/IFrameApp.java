package io.vincentpalazzo.ratio.view;

import mdlaf.MaterialLookAndFeel;
import mdlaf.themes.MaterialTheme;

import javax.swing.*;

/**
 * @author https://github.com/vincenzopalazzo
 */
public interface IFrameApp extends IAppViewComponent{

    void doShowMessage(MessageLevelError levelError, String message);

    void changeThemeApp(MaterialTheme newTheme);

    void buildStyleMenu();

    JRadioButtonMenuItem getMaterialLite();

    JRadioButtonMenuItem getMaterialOceanic();

    JRadioButtonMenuItem getJmarDark();
}
