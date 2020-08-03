/**
 * MIT License
 *
 * Copyright (c) 2020 Vincenzo Palazzo vincenzopalazzodev@gmail.com
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package io.vincentpalazzo.ratio.control.actions;

import com.google.inject.Inject;
import io.vincentpalazzo.ratio.App;
import io.vincentpalazzo.ratio.util.Constant;
import io.vincentpalazzo.ratio.util.IAppResourceManager;
import io.vincentpalazzo.ratio.util.SnackBarBuilder;
import io.vincentpalazzo.ratio.view.*;
import io.vincentpalazzo.ratio.view.Frame;
import mdlaf.utils.MaterialColors;
import mdlaf.utils.MaterialImageFactory;
import mdlaf.utils.icons.MaterialIconFont;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ActionAddImageBackground extends AbstractAction {

    private static final Logger LOGGER = LoggerFactory.getLogger(ActionAddImageBackground.class);

    IAppResourceManager resourceManager;

    public ActionAddImageBackground() {
        super.putValue(Action.LARGE_ICON_KEY,
                MaterialImageFactory.getInstance().getImage(
                        MaterialIconFont.ADD_A_PHOTO,
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
                SnackBarBuilder.build((Window) App.getInstance().getInstanceObject(IFrameApp.class),
                        this.resourceManager.getResourceString(Constant.SNACKBAR_IMAGE_ADDED),
                        this.resourceManager.getResourceString(Constant.SNACKBAR_TEXT_CLOSE))
                        .run();
            } catch (IOException ex) {
                LOGGER.error(ex.getLocalizedMessage());
                frame.doShowMessage(MessageLevelError.ERROR, resourceManager.getResourceString(Constant.ERROR_LOAD_IMAGE));
            }
        }
    }

    @Inject
    public void setResourceManager(IAppResourceManager resourceManager) {
        this.resourceManager = resourceManager;
        super.putValue(Action.NAME, this.resourceManager.getResourceString(Constant.TEXT_ADD_IMAGE));
    }
}
