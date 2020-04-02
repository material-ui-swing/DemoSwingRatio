package io.vincentpalazzo.ratio.view;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import io.vincentpalazzo.ratio.App;
import io.vincentpalazzo.ratio.control.MediatorActions;
import io.vincentpalazzo.ratio.util.Constant;
import io.vincentpalazzo.ratio.util.IAppResourceManager;
import io.vincentpalazzo.ratio.view.eception.ViewException;

import javax.swing.*;
import java.awt.*;

/**
 * @author https://github.com/vincenzopalazzo
 */
@Singleton
public class Frame extends AppTheme implements IFrameApp {

    //Dimension Frame
    private static final int DIMENSION_X = 400;
    private static final int DIMENSION_Y = 800;

    //Menu bar
    private JMenuBar menuBar;
    //Menu
    private  JMenu menuFile;
    private JMenu menuInfo;
    //Menu Items
    private JMenuItem menuExit;
    private JMenuItem menuDev;

    @Inject
    IAppResourceManager appResourceManager;
    @Inject
    MediatorActions mediatorActions;

    public Frame() {
        super();
        super.configureTheme();
    }

    @Override
    public void initView() throws ViewException {
        initComponent();

        setJMenuBar(menuBar);

        this.getContentPane().setPreferredSize(new Dimension(DIMENSION_Y, DIMENSION_X));

        initActions();

        pack();
        setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);

    }

    @Override
    public void initComponent() throws ViewException {

        menuBar = new JMenuBar();

        menuFile = new JMenu(appResourceManager.getResourceString(Constant.MENU_FILE_VALUE));
        menuInfo = new JMenu(appResourceManager.getResourceString(Constant.MENU_INFO_VALUE));

        menuExit = new JMenuItem(appResourceManager.getResourceString(Constant.MENU_I_EXIT_VALUE));
        menuDev = new JMenuItem();
        menuInfo.add(menuDev);

        menuFile.add(new JSeparator());
        menuFile.add(menuExit);

        menuBar.add(menuFile);
        menuBar.add(menuInfo);

        IMainPanel mainPanel = (IMainPanel) App.getInstance().getInstanceObject(IMainPanel.class);
        mainPanel.initView();
        super.setContentPane((Container) mainPanel);
    }

    @Override
    public void initActions() throws ViewException {
        if(mediatorActions == null){
            throw new ViewException("The mediatorActions not eas injected");
        }

        menuExit.setAction(mediatorActions.getAction(Constant.EXIT_ACTION_KEY));
        menuDev.setAction(mediatorActions.getAction(Constant.VIEW_DEV_ACTION_KEY));
    }
}
