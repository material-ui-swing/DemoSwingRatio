package io.vincentpalazzo.ratio.util;

import io.swingsnackbar.SnackBar;
import mdlaf.utils.MaterialColors;
import mdlaf.utils.MaterialFontFactory;
import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class SnackBarBuilder{

    private static Map<Window, SnackBar> cache = new HashMap<>();

    public static SnackBar build(Window own, String contentText, String textIcon){
        if(own == null){
            throw new IllegalArgumentException("Function parameter null");
        }
        if(cache.containsKey(own)){
            return cache.get(own).setText(contentText);
        }
        SnackBar newSnackbar = SnackBar.make(own, contentText, textIcon)
                .setIconTextColor(MaterialColors.COSMO_ORANGE)
                .setIconTextStyle(MaterialFontFactory.getInstance().getFont(
                        MaterialFontFactory.BOLD
                ))
                .setDuration(SnackBar.LENGTH_LONG);
        cache.put(own, newSnackbar);
        return newSnackbar;
    }

    public static SnackBar build(Window own, String contentText, Icon icon){
        if(own == null){
            throw new IllegalArgumentException("Function parameter null");
        }
        if(cache.containsKey(own)){
            System.out.println("RESTORE");
            return cache.get(own).setText(contentText);
        }
        System.out.println("NEW");
        SnackBar newSnackbar = SnackBar.make(own, contentText, icon);
        cache.put(own, newSnackbar);
        return newSnackbar;
    }

    public static SnackBar getSnackBarOn(Window own){
        if(own == null){
            throw new IllegalArgumentException("Own component null");
        }
        if(cache.containsKey(own)){
            return cache.get(own);
        }
        return null;
    }

    public static void invalidateSnackBar(Window own){
        if(own == null){
            throw new IllegalArgumentException("Own component null");
        }
        if(cache.containsKey(own)){
            SnackBar snackBar = cache.get(own);
            snackBar.dismiss();
            cache.remove(own);
        }
    }

}
