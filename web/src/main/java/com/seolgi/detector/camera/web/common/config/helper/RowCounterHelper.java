package com.seolgi.detector.camera.web.common.config.helper;

import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.Helper;
import com.github.jknack.handlebars.Options;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;

public class RowCounterHelper implements Helper<Object> {
    private static final int MINIMUM_ROW = 5;

    @Override
    public CharSequence apply(Object object, Options options) throws IOException {
        String text = (String) object;

        if (StringUtils.isEmpty(text))
            return new Handlebars.SafeString(String.valueOf(MINIMUM_ROW));

        int rowCount = StringUtils.countMatches(text , "\n");

        if (rowCount < MINIMUM_ROW)
            return new Handlebars.SafeString(String.valueOf( MINIMUM_ROW));
        else
            return new Handlebars.SafeString(String.valueOf( rowCount + 1));


    }
}
