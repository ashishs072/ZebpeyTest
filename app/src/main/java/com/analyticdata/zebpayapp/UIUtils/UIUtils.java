package com.analyticdata.zebpayapp.UIUtils;



import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.StringWriter;


public class UIUtils {

    private static UIUtils mUtils;
    public static UIUtils getInstance() {
        if (mUtils == null) {
            mUtils = new UIUtils();
        }
        return mUtils;
    }
    public String toJson(Object pojo, boolean prettyPrint) {
        try {
            ObjectMapper m = new ObjectMapper();
            JsonFactory jf = new JsonFactory();

            StringWriter sw = new StringWriter();
            JsonGenerator jg = jf.createGenerator(sw);
            if (prettyPrint) {
                jg.useDefaultPrettyPrinter();
            }
            m.writeValue(jg, pojo);
            return sw.toString();
        } catch (JsonMappingException jmEx) {
            return null;
        } catch (JsonGenerationException jgEx) {
            return null;
        } catch (IOException ioEx) {
            return null;
        }
    }
    public <T> T getPojoObject(String json, Class<T> clazz) {
        T t = null;
        ObjectMapper map = new ObjectMapper();
        try {
            t = map.readValue(json, clazz);
        } catch (IOException e) {
            //  e.printStackTrace();
            //CLog.d("test_bulk exception >>> " + e);
        }
        return t;
    }
}
