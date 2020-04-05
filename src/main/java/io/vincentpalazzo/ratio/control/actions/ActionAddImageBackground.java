package io.vincentpalazzo.ratio.control.actions;

import com.google.inject.Inject;
import io.vincentpalazzo.ratio.App;
import io.vincentpalazzo.ratio.util.Constant;
import io.vincentpalazzo.ratio.util.IAppResourceManager;
import io.vincentpalazzo.ratio.util.aop.annotations.UpdateContentPanelAOP;
import io.vincentpalazzo.ratio.view.*;
import jiconfont.icons.google_material_design_icons.GoogleMaterialDesignIcons;
import mdlaf.utils.MaterialColors;
import mdlaf.utils.MaterialImageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import top.gigabox.supportcomponent.toast.MaterialTost;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ActionAddImageBackground extends AbstractAction {

    private static final Logger LOGGER = LoggerFactory.getLogger(ActionAddImageBackground.class);

    IAppResourceManager resourceManager = (IAppResourceManager) App.getInstance().getInstanceObject(IAppResourceManager.class);

    public ActionAddImageBackground() {
        super.putValue(Action.NAME, "ADD IMAGE");
        super.putValue(Action.LARGE_ICON_KEY,
                MaterialImageFactory.getInstance().getImage(
                        GoogleMaterialDesignIcons.ADD_A_PHOTO,
                        MaterialColors.COSMO_BLACK)
        );
    }

    //@UpdateContentPanelAOP
    @Override
    public void actionPerformed(ActionEvent e) {
        Frame frame = (Frame) App.getInstance().getInstanceObject(Frame.class);
        IMainPanel mainPanel = (IMainPanel) App.getInstance().getInstanceObject(IMainPanel.class);
        PanelPresentation panel = (PanelPresentation) App.getInstance().getInstanceObject(IPresentationPanel.class);

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle(resourceManager.getResourceString(Constant.FILECHOOSER_LOAD_IMAGE_TITLE));

        if (JFileChooser.APPROVE_OPTION == fileChooser.showOpenDialog(frame)) {
            File fileSelected = fileChooser.getSelectedFile();
            try {
                BufferedImage bufferedImage = ImageIO.read(fileSelected);
                if(bufferedImage == null){
                    frame.doShowMessage(MessageLevelError.ERROR, resourceManager.getResourceString(Constant.ERROR_LOAD_IMAGE));
                    return;
                }
                ImageIcon image = new ImageIcon(bufferedImage);
                JLabel label = panel.getBackgroundLabel();
                JButton button = panel.getAddBackground();
                panel.remove(button);
                label.setIcon(image);
                panel.add(label);
                mainPanel.refreshUI();
                //MaterialTost.makeText(frame, "Image added :)").display();
            } catch (IOException ex) {
                LOGGER.error(ex.getLocalizedMessage());
                frame.doShowMessage(MessageLevelError.ERROR, resourceManager.getResourceString(Constant.ERROR_LOAD_IMAGE));
                return;
            }
        }
    }
}
