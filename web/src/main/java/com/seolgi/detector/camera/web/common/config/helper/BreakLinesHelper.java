package com.seolgi.detector.camera.web.common.config.helper;

import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.Helper;
import com.github.jknack.handlebars.Options;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;

public class BreakLinesHelper implements Helper<Object> {


    @Override
    public CharSequence apply(Object object, Options options) throws IOException {
        String text = (String) object;

        if (StringUtils.isEmpty(text))
            return new Handlebars.SafeString("");
        else
            return new Handlebars.SafeString(text.replace("\n", "<br>"));

    }
}
