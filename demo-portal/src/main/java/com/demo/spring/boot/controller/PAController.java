package com.demo.spring.boot.controller;

import com.demo.spring.boot.bo.Module;
import com.demo.spring.boot.config.Cache;
import com.demo.spring.boot.service.PAService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by bqhuy on 3/19/2018.
 */

@Controller
@RequestMapping("/pa")
public class PAController {
    @Autowired
    private PAService paService;

    @RequestMapping(method = RequestMethod.GET)
    public String preparePage() {
        return "pa/pa-stock";
    }

    @RequestMapping(value = "/getModuleIds", method = RequestMethod.GET)
    public ResponseEntity<List<String>> getModuleIds() {
        return new ResponseEntity<>(Cache.getLstSheetName(), HttpStatus.OK);
    }

    @RequestMapping(value = "/getAllDataByDay", method = RequestMethod.GET, params = {"toDate", "fromDate"})
    public ResponseEntity<List<Module>> getAllDataByDay(@RequestParam(value = "fromDate") String fromDate,
                                                        @RequestParam(value = "toDate") String toDate) {
        return new ResponseEntity<>(paService.getAllDataByDay(fromDate, toDate), HttpStatus.OK);
    }

    @RequestMapping(value = "/getDataByDay", method = RequestMethod.GET, params = {"moduleName", "toDate", "fromDate"})
    public ResponseEntity<List<Module>> getDataByDay(@RequestParam(value = "moduleName") String moduleName,
                                                     @RequestParam(value = "fromDate") String fromDate,
                                                     @RequestParam(value = "toDate") String toDate) {
        return new ResponseEntity<>(paService.getDataByDay(moduleName, fromDate, toDate), HttpStatus.OK);
    }

    @RequestMapping(value = "/getDataDetail", method = RequestMethod.GET, params = {"moduleName", "dt"})
    public ResponseEntity<List<Module>> getDataDetail(@RequestParam(value = "moduleName") String moduleName,
                                                      @RequestParam(value = "dt") Long dt) {
        return new ResponseEntity<>(paService.getDataDetail(moduleName, dt), HttpStatus.OK);
    }
}
