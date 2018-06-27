package com.demo.spring.boot.bo;

import com.demo.spring.boot.utils.NumberUtils;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by bqhuy on 11/3/2016.
 */
public class Module implements Serializable {
    private String moduleName;
    private Date dt;
    private Double pa;
    private Double ps;
    private int count;
    private boolean bDetail;

    public int getCount() {
        return count;
    }

    public String getModuleName() {
        return moduleName;
    }

    public Date getDt() {
        return dt;
    }

    public void setDt(Date dt) {
        this.dt = dt;
    }

    public Double getPa() {
        if (pa == null) {
            return null;
        }
        if (bDetail) {
            return NumberUtils.round(pa / count);
        }
        return NumberUtils.round(pa / 6);
    }

    public Double getPs() {
        if (ps == null) {
            return null;
        }
        return NumberUtils.round(ps / count);
    }

    public boolean isbDetail() {
        return bDetail;
    }

    public void setbDetail(boolean bDetail) {
        this.bDetail = bDetail;
    }

    public Module(String moduleName, Date dt, Double pa, Double ps, boolean bDetail) {
        this.moduleName = moduleName;
        this.dt = dt;
        this.pa = pa;
        this.ps = ps;
        this.bDetail = bDetail;
        this.count++;
    }

    public Module(String moduleName, Date dt, Double pa, boolean bDetail) {
        this.moduleName = moduleName;
        this.dt = dt;
        this.pa = pa;
        this.bDetail = bDetail;
        this.count++;
    }

    public void add(Double pa, Double ps) {
        if (this.pa == null) {
            this.pa = 0D;
        }
        this.pa += pa;
        if (this.ps == null) {
            this.ps = 0D;
        }
        this.ps += ps;
        this.count++;
    }

    public void addByDay(Double pa) {
        if (this.pa == null) {
            this.pa = 0D;
        }
        this.pa += pa;
    }
}