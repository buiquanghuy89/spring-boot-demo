package com.demo.spring.boot.service;

import com.demo.spring.boot.bo.Module;

import java.util.List;

/**
 * Created by bqhuy on 6/18/2018.
 */
public interface PAService {
    public List<Module> getDataByDay(String moduleName, String strToDate, String strFormDate);

    public List<Module> getAllDataByDay(String strToDate, String strFormDate);

    public List<Module> getDataDetail(String moduleName, Long dt);
}
