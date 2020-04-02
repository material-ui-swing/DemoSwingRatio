package io.vincentpalazzo.ratio.util;

import javax.swing.*;

/**
 * @author https://github.com/vincenzopalazzo
 */
public interface IAppResourceManager {

    void initResourceManager();

    String getResourceString(String nameKey);

    ImageIcon getResourceImage(String nameKey);
}
