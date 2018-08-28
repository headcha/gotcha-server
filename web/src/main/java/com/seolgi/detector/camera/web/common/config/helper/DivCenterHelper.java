package com.seolgi.detector.camera.web.common.config.helper;

import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.Helper;
import com.github.jknack.handlebars.Options;

import java.io.IOException;

public class DivCenterHelper implements Helper<Object> {
    private final int TOTAL_VIEW_SIZE = 12;
    private final String CLASS_NAME = "col-md-";
    @Override
    public CharSequence apply(Object object, Options options) throws IOException {

        Integer size = (Integer) object;
        if (size == null)
            return new Handlebars.SafeString(CLASS_NAME + "0");

        return new Handlebars.SafeString(CLASS_NAME + TOTAL_VIEW_SIZE / size);
    }
}


