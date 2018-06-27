package com.demo.spring.boot.config;

import com.demo.spring.boot.bo.Meter;
import com.demo.spring.boot.bo.Module;
import com.demo.spring.boot.bo.Site;
import com.demo.spring.boot.bo.User;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by bqhuy on 3/26/2018.
 */
public class Cache {
    private static Logger logger = LoggerFactory.getLogger(Cache.class);
    private static Map<String, User> users;
    private static HashMap<String, List<Module>> mapPAData;
    private static List<String> lstSheetName = new ArrayList<>();
    private static Date dateModify;
    private static String DAY_FORMAT = "yyyy/MM/dd";
    private static String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    @Autowired
    private AcmeProperties acmeProperties;

    static {
        try {
            mapPAData = new HashMap<>();
            lstSheetName.add("ENR062A3");
            lstSheetName.add("ENR04CA0");
            lstSheetName.add("ENR077A9");
            readExcel(AppConfig.pa_data_path);
        } catch (Exception ex) {
            logger.error("", "", ex);
        }
    }

    public static void putUser(User user) {
        System.out.println("putUser");
        if (users == null) {
            users = new HashMap<>();
        }
        users.put(user.getUserName(), user);
    }

//    @Cacheable(value = "userFindCache", key = "#userName")
//    public static User getUser(String userName) {
//        System.out.println("getUser");
//        return users.get(userName);
//    }

    public static HashMap<String, List<Module>> getMapPAData() {
        try {
            readExcel(AppConfig.pa_data_path);
            return mapPAData;
        } catch (Exception ex) {
            logger.error("", "", ex);
        }

        return new HashMap<>();
    }

    public static List<String> getLstSheetName() {
        return lstSheetName;
    }

    public static void main(String[] args) {
        System.out.println("ClassPathXmlApplicationContext -------------------------------------------");
        ApplicationContext context = new ClassPathXmlApplicationContext("application-context.xml");
        System.out.println("scope= singleton");
        Meter singletonMeter1 = (Meter) context.getBean("singletonMeter");
        singletonMeter1.setMeterType("Real");
        System.out.println(singletonMeter1.toString());
        Meter singletonMeter2 = (Meter) context.getBean("singletonMeter");
        System.out.println(singletonMeter2.toString());

        System.out.println("scope= prototype");
        Meter prototypeMeter1 = (Meter) context.getBean("prototypeMeter");
        prototypeMeter1.setMeterType("Real");
        System.out.println(prototypeMeter1.toString());
        Meter prototypeMeter2 = (Meter) context.getBean("prototypeMeter");
        System.out.println(prototypeMeter2.toString());

        System.out.println("AnnotationConfigApplicationContext -------------------------------------------");
        ApplicationContext context2 = new AnnotationConfigApplicationContext(AppConfig.class);
        Meter meter = context2.getBean(Meter.class);
        System.out.println(meter.toString());
        Site site = context2.getBean(Site.class);
        System.out.println(site.toString());
    }

    private static void readExcel(String excelFilePath) throws Exception {
        FileInputStream inputStream = null;
        HSSFWorkbook workbook = null;
        try {
            File file = new File(excelFilePath);
            Date lastModify = new Date(file.lastModified());
            if (dateModify == null) {
                dateModify = lastModify;
            } else {
                //Neu file khong thay doi thi ko can fai doc du lieu lai
                if (dateModify.compareTo(lastModify) == 0) {
                    return;
                } else {
                    dateModify = lastModify;
                }
            }
            SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
            logger.info("Start read excel (" + excelFilePath + "): " + sdf.format(new Date()));
            inputStream = new FileInputStream(file);
            workbook = new HSSFWorkbook(inputStream);
            for (String sheetName : lstSheetName) {
                readSheet(workbook, sheetName);
            }
            logger.info("End read excel (" + excelFilePath + "): " + sdf.format(new Date()));
        } catch (Exception ex) {
            throw ex;
        } finally {
            if (workbook != null) {
                workbook.close();
            }
            if (inputStream != null) {
                inputStream.close();
            }
        }
    }

    private static void readSheet(HSSFWorkbook workbook, String sheetName) throws Exception {
        List<Module> lstData = mapPAData.get(sheetName);
        if (lstData == null) {
            lstData = new ArrayList<>();
            mapPAData.put(sheetName, lstData);
        }
        TreeMap<Date, Module> treeMap = new TreeMap<>();
        HSSFSheet sheet = workbook.getSheet(sheetName);
        if (sheet != null) {
            Iterator<Row> iterator = sheet.iterator();
            SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
            while (iterator.hasNext()) {
                Row nextRow = iterator.next();
                Cell cell = nextRow.getCell(0);
                if (cell.getStringCellValue().equals("datepa1")) {
                    continue;
                }
                //psp
                cell = nextRow.getCell(12);
                Double psp = getValueKW(cell.getStringCellValue());

                for (int i = 0; i < 6; i++) {
                    try {
                        //datepa
                        cell = nextRow.getCell(i);
                        Date datepa = sdf.parse(cell.getStringCellValue().replace("+0000", "").trim());
                        //pa
                        cell = nextRow.getCell(i + 6);
                        Double pa = getValueKW(cell.getStringCellValue());
                        putValue(treeMap, sheetName, datepa, pa, psp);
                    } catch (Exception ex) {
                        logger.error("Error row=" + nextRow.toString());
                        logger.error("", "", ex);
                    }
                }
            }

            lstData.addAll(treeMap.values());
        }
    }

    private static Double getValueKW(String in) {
        Double out = null;
        if (in != null && !in.equals("")) {
            try {
                out = Double.parseDouble(in.substring(0, in.indexOf("kW")));
            } catch (Exception ex) {
                logger.error("", "", ex);
            }
        }
        return out;
    }

    private static void putValue(TreeMap<Date, Module> treeMap, String moduleName, Date dt, Double pa, Double ps) {
        Module module = treeMap.get(dt);
        if (module == null) {
            module = new Module(moduleName, dt, pa, ps, true);
            treeMap.put(dt, module);
        } else {
            module.add(pa, ps);
        }
    }

}
