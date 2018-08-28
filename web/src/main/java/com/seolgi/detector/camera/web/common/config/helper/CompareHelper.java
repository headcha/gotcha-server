package com.seolgi.detector.camera.web.common.config.helper;

import com.github.jknack.handlebars.Helper;
import com.github.jknack.handlebars.Options;

import java.io.IOException;

public class CompareHelper implements Helper<Object> {


    @Override
    public CharSequence apply(Object object, Options options) throws IOException {
        int lvalue = (int) object;
        int rvalue = (int) options.param(0);

        String operator = options.hash("operator");

        return calc(lvalue , rvalue , operator) ? options.fn() : options.inverse();

    }

    private boolean calc(int var1, int var2, String operator) {

        switch (operator) {
            case "==":
                return var1 == var2;
            case ">":
                return var1 > var2;
            case "<":
                return var1 < var2;

            case ">=":
                return var1 >= var2;

            case "<=":
                return var1 <= var2;

            case "!=":
                return var1 != var2;
            default:
                throw new RuntimeException("not supported operator " + operator);
        }


    }
}
