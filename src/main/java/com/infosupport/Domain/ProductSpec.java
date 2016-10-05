package com.infosupport.Domain;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Raymond Phua on 19-9-2016.
 */
public class ProductSpec {

    private Map properties;

    public ProductSpec(Map properties) {
        if (properties == null) {
            this.properties = new HashMap();
        } else {
            this.properties = new HashMap(properties);
        }
    }

    public Object getProperty(String key) {
        return properties.get(key);
    }

    public Map getProperties() {
        return this.properties;
    }
}
