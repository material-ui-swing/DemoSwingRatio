package io.vincentpalazzo.ratio.view;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import io.materialtheme.darkstackoverflow.DarkStackOverflowTheme;
import io.vincentpalazzo.ratio.App;
import io.vincentpalazzo.ratio.control.MediatorActions;
import io.vincentpalazzo.ratio.util.Constant;
import io.vincentpalazzo.ratio.util.IAppResourceManager;
import io.vincentpalazzo.ratio.util.SnackBarBuilder;
import io.vincentpalazzo.ratio.view.eception.ViewException;
import mdlaf.MaterialLookAndFeel;
import mdlaf.themes.JMarsDarkTheme;
import mdlaf.themes.MaterialLiteTheme;
import mdlaf.themes.MaterialOceanicTheme;
import mdlaf.themes.MaterialTheme;
import mdlaf.utils.MaterialColors;
import mdlaf.utils.MaterialFontFactory;
import org.material.component.swingsnackbar.SnackBar;
import org.material.component.swingsnackbar.action.AbstractSnackBarAction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import javax.swing.plaf.metal.MetalLookAndFeel;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URI;

/**
 * @author https://github.com/vincenzopalazzo
 */
@Singleton
public class Frame extends AppTheme implements IFrameApp {

    private static final Logger LOGGER = LoggerFactory.getLogger(Frame.class);

    private JFrame frame;
    //Menu bar
    private JMenuBar menuBar;
    //Menu
    private JMenu menuFile;
    private JMenu menuLAndF;
    private JMenu subMenuLAndF;
    private JMenu subMenuMaterialTheme;
    private JMenu menuInfo;
    //Menu Items
    private JMenuItem menuExit;
    private JMenuItem menuDev;

    private JRadioButtonMenuItem materialLAndF;
    private JRadioButtonMenuItem metalLAndF;
    private JRadioButtonMenuItem materialLite;
    private JRadioButtonMenuItem materialOceanic;
    private JRadioButtonMenuItem jmarDark;
    private JRadioButtonMenuItem stackoverflowDark;


    @Inject
    IAppResourceManager appResourceManager;
    @Inject
    MediatorActions mediatorActions;

    public Frame() {
        super();
        super.configureTheme();
        this.frame = this;
    }

    @Override
    public void initView() throws ViewException {
        initComponent();

        setJMenuBar(menuBar);

        GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().setFullScreenWindow(this.getOwner());

        initActions();

        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);

        SnackBarBuilder.build(this, "SnackBar Swing component", "STAR ON GITHUB")
                .setMarginBottom(20)
                .setIconTextColor(MaterialColors.COSMO_ORANGE)
                .setIconTextStyle(MaterialFontFactory.getInstance().getFont(MaterialFontFactory.BOLD))
                .setGap(65)
                .setDuration(SnackBar.LENGTH_INDEFINITE)
                .setAction(new AbstractSnackBarAction() {
                    @Override
                    public void mousePressed(MouseEvent e) {
                        try {
                            Desktop.getDesktop().browse(URI.create("https://github.com/vincenzopalazzo/material-ui-swing"));
                            SnackBarBuilder.getSnackBarOn(frame).dismiss();
                        } catch (IOException ioException) {
                            SnackBarBuilder.getSnackBarOn(frame).dismiss();
                            SnackBarBuilder.build(frame, "Error: " + ioException.getLocalizedMessage(), "CLOSE")
                                    .setAction(new AbstractSnackBarAction() {
                                        @Override
                                        public void mousePressed(MouseEvent e) {
                                            SnackBarBuilder.getSnackBarOn(frame).dismiss();
                                        }
                                    })
                                    .run();
                        }
                    }
                }).run();

    }

    @Override
    public void initComponent() throws ViewException {

        menuBar = new JMenuBar();

        menuFile = new JMenu(appResourceManager.getResourceString(Constant.MENU_FILE_VALUE));
        menuInfo = new JMenu(appResourceManager.getResourceString(Constant.MENU_INFO_VALUE));
        menuLAndF = new JMenu(appResourceManager.getResourceString(Constant.TEXT_MENU_ITEM_LANDF));
        subMenuLAndF = new JMenu(appResourceManager.getResourceString(Constant.TEXT_CHANGE_LANDF));
        subMenuMaterialTheme = new JMenu(appResourceManager.getResourceString(Constant.TEXT_MENU_THEME));

        menuExit = new JMenuItem(appResourceManager.getResourceString(Constant.MENU_I_EXIT_VALUE));
        menuDev = new JMenuItem();
        menuInfo.add(menuDev);

        menuFile.add(new JSeparator());
        menuFile.add(menuExit);
        materialLAndF = new JRadioButtonMenuItem(appResourceManager.getResourceString(Constant.TEXT_MENU_ITEM_MATERIAL));
        metalLAndF = new JRadioButtonMenuItem(appResourceManager.getResourceString(Constant.TEXT_MENU_ITEM_METAL));
        subMenuLAndF.add(materialLAndF);
        subMenuLAndF.add(metalLAndF);

        materialLite = new JRadioButtonMenuItem(new MaterialLiteTheme().getName());
        materialOceanic = new JRadioButtonMenuItem(new MaterialOceanicTheme().getName());
        jmarDark = new JRadioButtonMenuItem(new JMarsDarkTheme().getName());
        stackoverflowDark = new JRadioButtonMenuItem(new DarkStackOverflowTheme().getName());
        this.buildStyleMenu();

        subMenuMaterialTheme.add(materialLite);
        subMenuMaterialTheme.add(materialOceanic);
        subMenuMaterialTheme.add(jmarDark);
        subMenuMaterialTheme.add(stackoverflowDark);

        menuLAndF.add(subMenuLAndF);
        menuLAndF.add(subMenuMaterialTheme);

        menuBar.add(menuFile);
        menuBar.add(menuLAndF);
        menuBar.add(menuInfo);

        IMainPanel mainPanel = (IMainPanel) App.getInstance().getInstanceObject(IMainPanel.class);
        mainPanel.initView();
        super.setContentPane((Container) mainPanel);
    }

    @Override
    public void initActions() throws ViewException {
        if (mediatorActions == null) {
            throw new ViewException("The MediatorActions isn't injected");
        }
        menuExit.setAction(mediatorActions.getAction(Constant.EXIT_ACTION_KEY));
        menuDev.setAction(mediatorActions.getAction(Constant.VIEW_DEV_ACTION_KEY));
        materialLAndF.addActionListener(mediatorActions.getAction(Constant.ACTION_CHANGE_LAF));
        metalLAndF.addActionListener(mediatorActions.getAction(Constant.ACTION_CHANGE_LAF));
        materialLite.addActionListener(mediatorActions.getAction(Constant.ACTION_CHANGE_THEME));
        materialOceanic.addActionListener(mediatorActions.getAction(Constant.ACTION_CHANGE_THEME));
        jmarDark.addActionListener(mediatorActions.getAction(Constant.ACTION_CHANGE_THEME));
        stackoverflowDark.addActionListener(mediatorActions.getAction(Constant.ACTION_CHANGE_THEME));
    }

    @Override
    public void doShowMessage(MessageLevelError levelError, String message) {
        String title;
        int typePanel;
        switch (levelError) {
            case ERROR:
                title = "ERROR";
                typePanel = JOptionPane.ERROR_MESSAGE;
                break;
            case WARNING:
                title = "WARNING";
                typePanel = JOptionPane.WARNING_MESSAGE;
                break;
            default:
                title = "INFO";
                typePanel = JOptionPane.INFORMATION_MESSAGE;
                break;
        }
        JOptionPane.showInternalInputDialog(this, message, title, typePanel);
    }

    @Override
    public void changeThemeApp(MaterialTheme newTheme) {
        super.changeThemeApp(newTheme);
        buildStyleMenu();
    }

    @Override
    public void changeLookAndFeel(LookAndFeel lookAndFeel) {
        super.changeLookAndFeel(lookAndFeel);
        buildStyleMenu();
    }

    public void buildStyleMenu(){
        materialLAndF.setSelected(UIManager.getLookAndFeel() instanceof MaterialLookAndFeel);
        metalLAndF.setSelected(UIManager.getLookAndFeel() instanceof MetalLookAndFeel);

        subMenuMaterialTheme.setEnabled(actualLaF instanceof MaterialLookAndFeel);
        if(actualLaF instanceof MaterialLookAndFeel){
            materialLAndF.setSelected(true);
            metalLAndF.setSelected(false);

            materialLite.setEnabled(true);
            materialOceanic.setEnabled(true);
            jmarDark.setEnabled(true);
            stackoverflowDark.setEnabled(true);
            materialLite.setSelected(theme instanceof MaterialLiteTheme);
            materialOceanic.setSelected(theme instanceof MaterialOceanicTheme);
            jmarDark.setSelected(theme instanceof JMarsDarkTheme);
            stackoverflowDark.setSelected(theme instanceof DarkStackOverflowTheme);
        }else{
            materialLAndF.setSelected(false);
            metalLAndF.setSelected(true);

            materialLite.setEnabled(false);
            materialOceanic.setEnabled(false);
            jmarDark.setEnabled(false);
            stackoverflowDark.setEnabled(false);
        }
    }

    //getter
    public JRadioButtonMenuItem getMaterialLite() {
        return materialLite;
    }

    public JRadioButtonMenuItem getMaterialOceanic() {
        return materialOceanic;
    }

    public JRadioButtonMenuItem getJmarDark() {
        return jmarDark;
    }

    public JRadioButtonMenuItem getMaterialLAndF() {
        return materialLAndF;
    }

    public JRadioButtonMenuItem getMetalLAndF() {
        return metalLAndF;
    }

    public JRadioButtonMenuItem getStackoverflowDark() {
        return stackoverflowDark;
    }
}
