package com.seolgi.detector.camera.web.common.config.helper;

import com.github.jknack.handlebars.Helper;
import com.github.jknack.handlebars.Options;
import org.springframework.util.AntPathMatcher;

import java.io.IOException;
import java.util.Objects;

public class EqualsHelper implements Helper<Object> {
    @Override
    public CharSequence apply(Object object, Options options) throws IOException {
        Object obj2 = options.param(0);

        if (obj2 instanceof String && object instanceof String) {
            return new AntPathMatcher().match(String.valueOf(obj2) , String.valueOf(object)) ? options.fn() : options.inverse();
        }

        return Objects.equals(object, obj2) ? options.fn() : options.inverse();
    }
}
