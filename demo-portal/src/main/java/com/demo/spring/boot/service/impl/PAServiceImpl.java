package com.demo.spring.boot.service.impl;

import com.demo.spring.boot.bo.Module;
import com.demo.spring.boot.config.Cache;
import com.demo.spring.boot.service.PAService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by bqhuy on 6/18/2018.
 */
@Service
public class PAServiceImpl implements PAService {
    private Logger logger = LoggerFactory.getLogger(PAServiceImpl.class);
    private String DAY_FORMAT = "yyyy/MM/dd";
    private String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";


    public List<Module> getDataByDay(String moduleName, String strFormDate, String strToDate) {
        logger.info("Start getDataByDay: moduleName=" + moduleName + ", strFormDate=" + strFormDate + ", strToDate=" + strToDate);
        List<Module> result = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat(DAY_FORMAT);
        Date fromDate = null;
        Date toDate = null;
        try {
            if (strFormDate != null && !strFormDate.equals("")) {
                fromDate = sdf.parse(strFormDate);

            }

            if (strToDate != null && !strToDate.equals("")) {
                toDate = sdf.parse(strToDate);
                Calendar cal = Calendar.getInstance();
                cal.setTime(toDate);
                cal.add(Calendar.DATE, 1);
                toDate = cal.getTime();
            }

            List<Module> lstDetail = Cache.getMapPAData().get(moduleName);
            TreeMap<String, Module> treeMap = new TreeMap<>();
            if (lstDetail != null) {
                for (Module module : lstDetail) {
                    if (toDate != null && fromDate != null
                            && (module.getDt().compareTo(fromDate) < 0 || module.getDt().compareTo(toDate) >= 0)) {
                        continue;
                    }

                    String strDate = sdf.format(module.getDt());
                    Module moduleByDay = treeMap.get(strDate);
                    if (moduleByDay == null) {
                        moduleByDay = new Module(moduleName, sdf.parse(strDate), module.getPa(), false);
                        treeMap.put(strDate, moduleByDay);
                    } else {
                        moduleByDay.addByDay(module.getPa());
                    }
                }
                result.addAll(treeMap.values());
            }
        } catch (Exception ex) {
            logger.error("", "", ex);
        }
        logger.info("End getDataByDay: moduleName=" + moduleName + ", strFormDate=" + strFormDate + ", strToDate=" + strToDate);
        return result;
    }

    public List<Module> getAllDataByDay(String strFormDate, String strToDate) {
        logger.info("Start getAllDataByDay: strFormDate=" + strFormDate + ", strToDate=" + strToDate);
        List<Module> result = new ArrayList<>();
        try {
            for (String moduleName : Cache.getLstSheetName()) {
                result.addAll(getDataByDay(moduleName, strFormDate, strToDate));
            }
        } catch (Exception ex) {
            logger.error("", "", ex);
        }
        logger.info("End getAllDataByDay: strFormDate=" + strFormDate + ", strToDate=" + strToDate);
        return result;
    }

    public List<Module> getDataDetail(String moduleName, Long dt) {
        logger.info("Start getDataDetail: moduleName=" + moduleName + ", dt=" + dt);
        List<Module> result = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat(DAY_FORMAT);
        try {
            List<Module> lstDetail = Cache.getMapPAData().get(moduleName);
            if (lstDetail != null) {
                String strDatePa = sdf.format(new Date(dt));
                for (Module module : lstDetail) {
                    String strDate = sdf.format(module.getDt());
                    if (strDate.equals(strDatePa)) {
                        result.add(module);
                    } else {
                        if (strDate.compareTo(strDatePa) > 0) {
                            break;
                        }
                    }
                }
            }
        } catch (Exception ex) {
            logger.error("", "", ex);
        }
        logger.info("End getDataDetail: moduleName=" + moduleName + ", dt=" + dt);
        return result;
    }
}
