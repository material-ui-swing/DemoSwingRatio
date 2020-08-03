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
package io.vincentpalazzo.ratio.util;

import com.google.inject.Singleton;
import io.vincentpalazzo.ratio.util.exceptions.UtilException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * @author https://github.com/vincenzopalazzo
 */
@Singleton
public class AppResourceManager implements IAppResourceManager{

    private static final Logger LOGGER = LoggerFactory.getLogger(AppResourceManager.class);

    private ResourceBundle resourceBundle;

    public AppResourceManager() {
        this.initResourceManager();
    }

    @Override
    public void initResourceManager() {
        resourceBundle = ResourceBundle.getBundle(AppConfiguration.getInstance().getValueWith(Constant.RESOURCE_BUNDLE), Locale.getDefault());
        LOGGER.debug("The number keys of the resource bundle are: " + resourceBundle.keySet().size());
    }

    @Override
    public String getResourceString(String nameKey) {
        if(!resourceBundle.containsKey(nameKey)){
            throw new IllegalArgumentException("Key " + nameKey + " not present inside resourceBundle");
        }
        return resourceBundle.getString(nameKey);
    }

    @Override
    public ImageIcon getResourceImage(String nameKey) {
        InputStream inputStream = ClassLoader.getSystemResourceAsStream(resourceBundle.getString(nameKey));

        try {
            BufferedImage image = ImageIO.read(inputStream);
            return new ImageIcon(image);
        } catch (IOException e) {
            throw new UtilException(e);
        }finally {
            try {
                if(inputStream != null){
                    inputStream.close();
                }
            } catch (IOException e) {
                throw new UtilException(e);
            }
        }
    }
}
