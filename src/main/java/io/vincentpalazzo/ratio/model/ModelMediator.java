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
