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
        LOGGER.debug("The number key of the resource bundle is: " + resourceBundle.keySet().size());
    }

    @Override
    public String getResourceString(String nameKey) {
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
