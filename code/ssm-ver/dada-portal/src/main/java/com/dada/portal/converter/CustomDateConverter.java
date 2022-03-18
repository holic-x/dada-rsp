package com.dada.portal.converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.core.convert.converter.Converter;

/**
 * 自定义类型转换：实现字符串与日期类之间的转换
 */

public class CustomDateConverter implements Converter<String, Date>{

    @Override
    public Date convert(String source) {
        
        SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd");
        
        try {
            return sdf.parse(source);
        } catch (ParseException e) {
            throw new RuntimeException();
        }
    }

}


