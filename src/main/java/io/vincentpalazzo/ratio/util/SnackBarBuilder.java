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

import mdlaf.utils.MaterialColors;
import mdlaf.utils.MaterialFontFactory;
import org.material.component.swingsnackbar.SnackBar;

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
