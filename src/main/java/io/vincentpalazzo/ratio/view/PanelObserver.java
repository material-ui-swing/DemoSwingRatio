package io.vincentpalazzo.ratio.view;

import io.vincentpalazzo.ratio.App;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Singleton;
import javax.swing.*;
import java.util.Observable;
import java.util.Observer;

@Singleton
public class PanelObserver extends JPanel implements Observer {

    private static final Logger LOGGER = LoggerFactory.getLogger(PanelObserver.class);

    public PanelObserver() {
        LOGGER.debug("New object observable");
    }

    @Override
    public void update(Observable o, Object arg) {
        LOGGER.debug("Panel observable update");
        JSplitPane main = (JSplitPane) App.getInstance().getInstanceObject(MainPanel.class);
        this.revalidate();
        main.repaint();
    }
}
