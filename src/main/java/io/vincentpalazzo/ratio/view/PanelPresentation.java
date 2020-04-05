package io.vincentpalazzo.ratio.view;

import io.vincentpalazzo.ratio.App;
import io.vincentpalazzo.ratio.control.MediatorActions;
import io.vincentpalazzo.ratio.util.Constant;
import io.vincentpalazzo.ratio.view.eception.ViewException;
import mdlaf.components.button.MaterialButtonUI;
import mdlaf.utils.MaterialColors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Singleton;
import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

@Singleton
public class PanelPresentation extends JPanel implements IPresentationPanel, Observer {

    private static final Logger LOGGER = LoggerFactory.getLogger(PanelPresentation.class);

    private JLabel backgroundLabel;
    private JButton addBackground;

    private MediatorActions actions = (MediatorActions) App.getInstance().getInstanceObject(MediatorActions.class);

    public PanelPresentation() {
        LOGGER.debug("New object observable");
        initView();
    }

    @Override
    public void initView() throws ViewException {
        initComponent();
        initActions();

        setVisible(true);
    }

    @Override
    public void initComponent() throws ViewException {
        backgroundLabel = new JLabel();
        addBackground = new JButton();
        addBackground.setUI(new AddImageButtonUI());
        addBackground.setBackground(MaterialColors.COSMO_STRONG_GRAY);
        addBackground.setForeground(MaterialColors.COSMO_BLACK);
        addBackground.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        super.setLayout(new GridBagLayout());
        super.add(addBackground, new GridBagConstraints());

        super.add(addBackground);
    }

    @Override
    public void initActions() throws ViewException {
        addBackground.setAction(actions.getAction(Constant.ACTION_ADD_IMAGE));
    }

    @Override
    public void update(Observable o, Object arg) {
        LOGGER.debug("Panel observable update");
        JSplitPane main = (JSplitPane) App.getInstance().getInstanceObject(MainPanel.class);
        this.revalidate();
        main.repaint();
    }

    public JLabel getBackgroundLabel() {
        return backgroundLabel;
    }

    public JButton getAddBackground() {
        return addBackground;
    }

    public class AddImageButtonUI extends MaterialButtonUI{

        @Override
        public void installUI(JComponent c) {
            mouseHoverEnabled = false;
            super.installUI(c);
        }

        @Override
        protected void paintBorderButton(Graphics graphics, JComponent b) {

        }

        @Override
        protected void paintFocus(Graphics g, AbstractButton b, Rectangle viewRect, Rectangle textRect, Rectangle iconRect) {

        }
    }
}