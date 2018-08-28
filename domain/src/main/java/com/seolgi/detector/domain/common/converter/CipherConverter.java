package com.seolgi.detector.domain.common.converter;

import com.seolgi.detector.domain.utils.CipherUtil;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class CipherConverter implements AttributeConverter<String, String> {

    @Override
    public String convertToDatabaseColumn(String value) {

        return CipherUtil.encrypt(value);
    }

    @Override
    public String convertToEntityAttribute(String value) {

        return CipherUtil.decrypt(value);
    }
}
