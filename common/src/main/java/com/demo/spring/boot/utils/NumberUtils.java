package com.demo.spring.boot.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.DecimalFormat;

/**
 * Created by bqhuy on 11/3/2016.
 */
public class NumberUtils {
    private Logger logger = LoggerFactory.getLogger(NumberUtils.class);

    public static Double round(Double input) {
        DecimalFormat df = new DecimalFormat(".##");
        return new Double(df.format(input));
    }
}
