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
package io.vincentpalazzo.ratio.model;

import com.google.inject.Singleton;
import java.util.HashMap;
import java.util.Map;

@Singleton
public class ModelMediator {

    private static Map<String, Object> beans = new HashMap<>();

    public Object getBean(String key) {
        if (key == null || key.isEmpty()) {
            throw new IllegalArgumentException("Objects null");
        }
        if(beans.containsKey(key)){
            return beans.get(key);
        }
        return null;
    }

    public void putBean(String key, Object value) {
        if (key == null || key.isEmpty() || value == null) {
            throw new IllegalArgumentException("Objects null");
        }
        beans.put(key, value);
    }

}
