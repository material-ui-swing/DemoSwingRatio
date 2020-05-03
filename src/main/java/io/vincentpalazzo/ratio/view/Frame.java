package io.vincentpalazzo.ratio.view;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import io.swingsnackbar.SnackBar;
import io.vincentpalazzo.ratio.App;
import io.vincentpalazzo.ratio.control.MediatorActions;
import io.vincentpalazzo.ratio.util.Constant;
import io.vincentpalazzo.ratio.util.IAppResourceManager;
import io.vincentpalazzo.ratio.view.eception.ViewException;
import mdlaf.utils.MaterialColors;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

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
    private SnackBar snackBar;

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

        GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().setFullScreenWindow(this.getOwner());

        initActions();

        pack();
        setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);

        snackBar = SnackBar.make(this, "Welcome in the DemoRationSwing", SnackBar.LENGTH_LONG)
                .setActionTextColor("CLOSE", MaterialColors.COSMO_RED, new MouseListener() {
                    @Override
                    public void mouseClicked(MouseEvent e) { }

                    @Override
                    public void mousePressed(MouseEvent e) {
                        snackBar.dismiss();
                    }

                    @Override
                    public void mouseReleased(MouseEvent e) {}

                    @Override
                    public void mouseEntered(MouseEvent e) {}

                    @Override
                    public void mouseExited(MouseEvent e) { }
                }).run();
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

    @Override
    public void doShowMessage(MessageLevelError levelError, String message) {
        String title;
        int typePanel;
        switch (levelError){
            case ERROR: title = "ERROR";
                typePanel = JOptionPane.ERROR_MESSAGE;
                break;
            case WARNING: title = "WARNING";
                typePanel = JOptionPane.WARNING_MESSAGE;
                break;
            default: title = "INFO";
                typePanel = JOptionPane.INFORMATION_MESSAGE;
                break;
        }
        JOptionPane.showInternalInputDialog(this, message, title, typePanel);
    }
}
