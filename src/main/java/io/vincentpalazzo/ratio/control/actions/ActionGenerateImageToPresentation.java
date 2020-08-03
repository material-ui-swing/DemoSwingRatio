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

import io.vincentpalazzo.ratio.App;
import io.vincentpalazzo.ratio.view.*;
import io.vincentpalazzo.ratio.view.Frame;
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
