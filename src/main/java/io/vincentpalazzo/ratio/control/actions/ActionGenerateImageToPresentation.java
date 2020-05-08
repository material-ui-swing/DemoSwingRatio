package io.vincentpalazzo.ratio.control.actions;

import com.google.inject.Inject;
import io.vincentpalazzo.ratio.App;
import io.vincentpalazzo.ratio.util.Constant;
import io.vincentpalazzo.ratio.util.IAppResourceManager;
import io.vincentpalazzo.ratio.view.*;
import io.vincentpalazzo.ratio.view.Frame;
import jiconfont.icons.google_material_design_icons.GoogleMaterialDesignIcons;
import mdlaf.utils.MaterialColors;
import mdlaf.utils.MaterialImageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ActionGenerateImageToPresentation extends AbstractAction {

    private static final Logger LOGGER = LoggerFactory.getLogger(ActionAddImageBackground.class);

    @Override
    public void actionPerformed(ActionEvent e) {
        Frame frame = (Frame) App.getInstance().getInstanceObject(IFrameApp.class);
        PanelPresentation presentation = (PanelPresentation) App.getInstance().getInstanceObject(IPresentationPanel.class);

        JFileChooser fileChooser = new JFileChooser();


        if(JFileChooser.APPROVE_OPTION == fileChooser.showSaveDialog(frame)){
            String path = fileChooser.getSelectedFile() + ".png";

            int w = presentation.getWidth();
            int h = presentation.getHeight();
            BufferedImage bi = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
            Graphics2D g = bi.createGraphics();
            presentation.paint(g);
            g.dispose();

            File outputfile = new File(path);
            try {
                ImageIO.write(bi, "png", outputfile);
                //MaterialTost.makeText(frame,"Image stored :)", MaterialTost.SUCCESS).display();
            } catch (IOException ex) {
                LOGGER.error(ex.getMessage());
                frame.doShowMessage(MessageLevelError.ERROR, ex.getMessage());
            }
        }

    }
}
